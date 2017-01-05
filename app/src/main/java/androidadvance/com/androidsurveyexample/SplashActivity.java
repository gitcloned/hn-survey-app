package androidadvance.com.androidsurveyexample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import org.json.JSONException;

import androidadvance.com.androidsurveyexample.http.hosp.HospRestClientUsage;
import androidadvance.com.androidsurveyexample.http.hosp.resp.SummaryResponse;
import androidadvance.com.androidsurveyexample.http.hosp.resp.SummaryResponseListener;

public class SplashActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        SummaryResponseListener listener = new SummaryResponseListener(this) {

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
            public void onError(String error) {

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


        }

        /*new Handler().postDelayed(new Runnable() {

            *//*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             *//*

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);*/
    }
}