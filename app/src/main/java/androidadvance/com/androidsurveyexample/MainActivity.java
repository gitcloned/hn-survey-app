package androidadvance.com.androidsurveyexample;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.androidadvance.androidsurvey.SurveyActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import androidadvance.com.androidsurveyexample.forms.FormListAdapter;
import androidadvance.com.androidsurveyexample.http.hosp.Device;
import androidadvance.com.androidsurveyexample.http.hosp.HospRestClientUsage;
import androidadvance.com.androidsurveyexample.http.hosp.resp.Form;
import androidadvance.com.androidsurveyexample.http.hosp.resp.FormResponse;
import androidadvance.com.androidsurveyexample.http.hosp.resp.listeners.FormResponseListener;

public class MainActivity extends AppCompatActivity {

    private static final int SURVEY_REQUEST = 1337;
    private static final String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView formItems = (ListView)
                findViewById(R.id.listOfForms);

        formItems.setAdapter(new FormListAdapter
                (MainActivity.this, Config.getInstance().getSummaryResponse().getForms()));

        formItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value;

                Form form = (Form) parent.getItemAtPosition(position);

                Intent i_survey = new Intent(MainActivity.this, SurveyActivity.class);
                //you have to pass as an extra the json string.
                i_survey.putExtra("json_survey", form.getFormContent());
                i_survey.putExtra("form_id", form.getId());
                i_survey.putExtra("form_title", form.getName());

                Log.d("****", "****************** Got Form Content ******************");
                Log.v("Form Content", form.getFormContent());

                startActivityForResult(i_survey, SURVEY_REQUEST);
            }
        });

        //Nothing fancy here. Plain old simple buttons....

        /*Button button_survey_example_1 = (Button) findViewById(R.id.button_survey_example_1);
        Button button_survey_example_2 = (Button) findViewById(R.id.button_survey_example_2);
        Button button_survey_example_3 = (Button) findViewById(R.id.button_survey_example_3);

        button_survey_example_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i_survey = new Intent(MainActivity.this, SurveyActivity.class);
                //you have to pass as an extra the json string.
                i_survey.putExtra("json_survey", loadSurveyJson("example_survey_1.json"));
                startActivityForResult(i_survey, SURVEY_REQUEST);
            }
        });

        button_survey_example_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_survey = new Intent(MainActivity.this, SurveyActivity.class);
                i_survey.putExtra("json_survey", loadSurveyJson("example_survey_2.json"));
                startActivityForResult(i_survey, SURVEY_REQUEST);
            }
        });

        button_survey_example_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_survey = new Intent(MainActivity.this, SurveyActivity.class);
                i_survey.putExtra("json_survey", loadSurveyJson("example_survey_3.json"));
                startActivityForResult(i_survey, SURVEY_REQUEST);
            }
        });*/

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SURVEY_REQUEST) {
            if (resultCode == RESULT_OK) {

                String answers_json = data.getExtras().getString("answers");
                Double score = data.getExtras().getDouble("score");
                Double sentiment = data.getExtras().getDouble("sentiment");
                String form_id = data.getExtras().getString("form_id");
                String responseId = UUID.randomUUID().toString();

                Log.d("****", "****************** WE HAVE ANSWERS ******************");
                Log.v("Form Id", form_id);
                Log.v("ANSWERS JSON", answers_json);
                Log.v("Score", score.toString());
                Log.v("Sentiment", sentiment.toString());
                Log.d("****", "*****************************************************");

                FormResponse formResponse = new FormResponse(responseId, form_id, answers_json, score, sentiment);

                FormResponseListener listener = new FormResponseListener(this) {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.v(TAG, "Successfully saved response");
                    }

                    @Override
                    public void onError(int statusCode, String error) {
                        Log.v(TAG, "Error occurred while saving response");
                    }
                };

                try {
                    new HospRestClientUsage().storeResponse(listener, getApplicationContext(), formResponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //json stored in the assets folder. but you can get it from wherever you like.
    private String loadSurveyJson(String filename) {
        try {
            InputStream is = getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
