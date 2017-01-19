package androidadvance.com.androidsurveyexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.internal.LinkedTreeMap;

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

public class SplashActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String deviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        Config.getInstance().setDevice(new Device(deviceId));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        /*SummaryResponseListener listener = new SummaryResponseListener(this) {

            @Override
            public void onResponse(SummaryResponse response) {

                Config.getInstance().setSummaryResponse(response);

                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }

            @Override
            public void onError(int statusCode, String error) {

                AlertDialog.Builder builder = new AlertDialog.Builder(null);
                builder.setTitle("ERROR !!");
                builder.setMessage("Error getting data from the Internet.\nNetwork Unavailable!");

                builder.setPositiveButton("Retry", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
//                Toast.makeText("Network Unavailable!", Toast.LENGTH_LONG).show();
            }
        };

        try {
            new HospRestClientUsage().getSummary(listener);
        } catch (JSONException e) {
            e.printStackTrace();


        }*/

        Log.i("Splash", "Getting result ***************************");
        new HospFormsAPI(getApplicationContext()).getForms(new FormsResponseListener(getApplicationContext()) {
            @Override
            public void onResponse(ArrayList<Object> response) {

                //Log.i("Splash", "Got result *************************** len: " + response.size());
                //Log.i("Splash", "Got result *************************** len: " + response.toString());
                //Log.i("Splash", "Got result *************************** len: " + response.getClass().getName());
                //Log.i("Splash", "Got result *************************** len: " + response.get(0).getClass().getName());

                SummaryResponse summaryResponse = new SummaryResponse();

                for (int i=0; i<response.size(); i++) {

                    LinkedTreeMap<String, String> treeMap = (LinkedTreeMap<String, String>)response.get(i);
                    String FormId = treeMap.containsKey("FormId") ?
                            treeMap.get("FormId") : null;
                    String Name = treeMap.containsKey("Name") ?
                            treeMap.get("Name") : null;
                    String Description = treeMap.containsKey("Description") ?
                            treeMap.get("Description") : "";
                    String form = treeMap.containsKey("form") ?
                            treeMap.get("form") : null;

                    JSONObject formJSon = null;

                    try {
                        formJSon = new JSONObject(form);
                    } catch (JSONException e) {
                        Log.i("Splash", "Parse error: " + e);
                        e.printStackTrace();
                    }

                    //Log.i("Splash", "Got result *************************** form: " + form);

                    if(Description == null) Description = "";

                    Form nForm = new Form(FormId, Name, Description, "SPPC", form);
                    summaryResponse.addForm(nForm);
                }
                /*



                Log.i("Splash", "Contain name: " + treeMap.containsKey("FormId"));



                try {
                    JSONArray array = new JSONArray(response.toString());

                    for (int i = 0; i<array.length(); i++){

                        JSONObject obj = array.getJSONObject(i);

                        Form form = new Form(obj.getString("FormId")
                                , obj.getString("Name")
                                , obj.getString("Description")
                                , Config.getInstance().getClientId()
                                , null);
                        summaryResponse.addForm(form);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                /*
                for (HospitalForm hospForm : response) {

                    Form form = new Form(hospForm.getFormId(), hospForm.getName(), hospForm.getDescription(), Config.getInstance().getClientId(), null);
                    summaryResponse.addForm(form);
                }

                Config.getInstance().setHospitalForms(response);
                */
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