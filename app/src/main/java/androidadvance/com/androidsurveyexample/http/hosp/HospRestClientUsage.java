package androidadvance.com.androidsurveyexample.http.hosp;

/**
 * Created by asjain on 1/2/2017.
 */

import android.util.Log;

import org.json.*;
import com.loopj.android.http.*;

import androidadvance.com.androidsurveyexample.http.hosp.resp.Form;
import androidadvance.com.androidsurveyexample.http.hosp.resp.SummaryResponse;
import androidadvance.com.androidsurveyexample.http.hosp.resp.SummaryResponseListener;
import cz.msebera.android.httpclient.Header;

public class HospRestClientUsage {

    private static final String TAG = "HospRestClientUsage";

    public void getSummary(final SummaryResponseListener listener) throws JSONException {

        HospRestClient.get("hospital_summary", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Log.v(TAG, "Hospital Summary, got response");

                SummaryResponse summaryResponse = new SummaryResponse();

                // If the response is JSONObject instead of expected JSONArray
                JSONArray forms = null;
                try {

                    forms = response.getJSONArray("forms");

                    if(forms!=null && forms.length()>0){

                        for (int i = 0; i < forms.length(); i++) {

                            JSONObject formJSON = forms.getJSONObject(i);

                            if(formJSON!=null) {

                                Form form = new Form(formJSON.getString("id"), formJSON.getString("client"), formJSON.getJSONObject("form"));
                                summaryResponse.addForm(form);
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                listener.onResponse(summaryResponse);
            }
        });
    }
}
