package androidadvance.com.androidsurveyexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsAuthButton;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.google.gson.internal.LinkedTreeMap;

import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidadvance.com.androidsurveyexample.aws.lambda.hosp.forms.HospitalForm;
import androidadvance.com.androidsurveyexample.aws.lambda.hosp.forms.HospFormsAPI;
import androidadvance.com.androidsurveyexample.aws.lambda.hosp.forms.payload.listener.FormsResponseListener;
import androidadvance.com.androidsurveyexample.http.hosp.Device;
import androidadvance.com.androidsurveyexample.http.hosp.resp.Form;
import androidadvance.com.androidsurveyexample.http.hosp.resp.SummaryResponse;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class SplashActivity extends Activity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "Cof6qTszd3KyOT4B8bjQwF2GQ";
    private static final String TWITTER_SECRET = "wgWRtzB1gRfkZ5I7jFvzrqJ59A9EqtnPoKdScaBBYMcGcjVQtL";

    AlphaAnimation inAnimation;
    AlphaAnimation outAnimation;

    FrameLayout progressBarHolder;


    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String deviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        Config.getInstance().setDevice(new Device(deviceId));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        progressBarHolder = (FrameLayout) findViewById(R.id.progressBarHolder);

        Digits.Builder digitsBuilder = new Digits.Builder().withTheme(R.style.CustomDigitsTheme);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Crashlytics(), new TwitterCore(authConfig), digitsBuilder.build());

        final DigitsAuthButton digitsButton = (DigitsAuthButton) findViewById(R.id.auth_button);
        digitsButton.setText("Login");
        digitsButton.setBackgroundColor(getResources().getColor(R.color.primary_dark));
        digitsButton.setHeight(35);

        digitsButton.setCallback(new AuthCallback() {
            @Override
            public void success(DigitsSession session, String phoneNumber) {
                // TODO: associate the session userID with your user model
                Toast.makeText(getApplicationContext(), "Authentication successful for "
                        + phoneNumber, Toast.LENGTH_LONG).show();

                // TODO: Use the current user's information
                // You can call any combination of these three methods
                Crashlytics.setUserIdentifier(session.getPhoneNumber());
                Crashlytics.setUserEmail(session.getEmail().address);
                Crashlytics.setUserName(session.getPhoneNumber());

                digitsButton.setVisibility(View.GONE);
                inAnimation = new AlphaAnimation(0f, 1f);
                inAnimation.setDuration(200);
                progressBarHolder.setAnimation(inAnimation);
                progressBarHolder.setVisibility(View.VISIBLE);

                /* Get the hospital forms
                * */
                new HospFormsAPI(getApplicationContext()).getForms(new FormsResponseListener(getApplicationContext()) {
                    @Override
                    public void onResponse(ArrayList<Object> response) {

                        // parse summary reponse
                        outAnimation = new AlphaAnimation(1f, 0f);
                        outAnimation.setDuration(200);
                        progressBarHolder.setAnimation(outAnimation);
                        progressBarHolder.setVisibility(View.GONE);

                        SummaryResponse summaryResponse = new SummaryResponse();

                        for (int i=0; i<response.size(); i++) {

                            LinkedTreeMap<String, String> treeMap = (LinkedTreeMap<String, String>)response.get(i);
                            String FormId = treeMap.containsKey("FormId") ?
                                    treeMap.get("FormId") : null;
                            String Name = treeMap.containsKey("Name") ?
                                    treeMap.get("Name") : null;
                            String Description = treeMap.containsKey("Description") ?
                                    treeMap.get("Description") : "Gather feedback";
                            String form = treeMap.containsKey("form") ?
                                    treeMap.get("form") : null;
                            String password = treeMap.containsKey("Password") ?
                                    treeMap.get("Password") : null;

                            if (Description == null)
                                Description = "Gather feedback";

                            Form nForm = new Form(FormId, Name, Description, "SPPC", form, password);

                            if (treeMap.containsKey("SMSN")) {
                                nForm.setSMSN(treeMap.get("SMSN"));
                            }

                            if (treeMap.containsKey("EmailN")) {
                                nForm.setEmailN(treeMap.get("EmailN"));
                            }

                            if (treeMap.containsKey("MaxScore")) {
                                nForm.setMaxScore(treeMap.get("MaxScore"));
                            }

                            summaryResponse.addForm(nForm);
                        }

                       Config.getInstance().setSummaryResponse(summaryResponse);

                        // This method will be executed once the timer is over
                        // Start your app main activity
                        Intent i = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(i);

                        // close this activity
                        finish();
                    }

                    @Override
                    public void onError(int statusCode, String errorResponse) {

                        Toast.makeText(this.getContext(),
                                "Some error occurred while fetching forms", Toast.LENGTH_LONG).show();

                        // This method will be executed once the timer is over
                        // Start your app main activity
                        Intent i = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(i);

                        // close this activity
                        finish();
                    }
                });


            }

            @Override
            public void failure(DigitsException exception) {
                Log.d("Digits", "Sign in with Digits failure", exception);
            }
        });

        new Handler().postDelayed(new Runnable() {

            /*
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                //Intent i = new Intent(SplashActivity.this, MainActivity.class);
                //startActivity(i);

                // close this activity
                //finish();
            }
        }, SPLASH_TIME_OUT);
    }
}