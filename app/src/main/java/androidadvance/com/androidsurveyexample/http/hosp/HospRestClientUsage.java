package androidadvance.com.androidsurveyexample.http.hosp;

/**
 * Created by asjain on 1/2/2017.
 */

import android.content.Context;
import android.util.Log;

import org.json.*;
import com.loopj.android.http.*;

import java.io.UnsupportedEncodingException;

import androidadvance.com.androidsurveyexample.Config;
import androidadvance.com.androidsurveyexample.http.hosp.resp.Form;
import androidadvance.com.androidsurveyexample.http.hosp.resp.FormResponse;
import androidadvance.com.androidsurveyexample.http.hosp.resp.SummaryResponse;
import androidadvance.com.androidsurveyexample.http.hosp.resp.listeners.FormResponseListener;
import androidadvance.com.androidsurveyexample.http.hosp.resp.listeners.SummaryResponseListener;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

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

                                Form form = new Form(formJSON.getString("id"),
                                        formJSON.getString("name"),
                                        formJSON.getString("description"),
                                        formJSON.getString("client"),
                                        formJSON.getJSONObject("form"));
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

    public void storeResponse(final FormResponseListener listener, Context context, FormResponse response) throws JSONException, UnsupportedEncodingException {

        JSONObject params = new JSONObject();

        params.put("name", "survey");

        params.put("FormId", response.getFormId());
        params.put("ResponseId", response.getResponseId());
        params.put("body", response.getAnswers());
        params.put("Timestamp", response.getTimestamp());

        params.put("DeviceId", Config.getInstance().getDevice().getId());
        params.put("DeviceType", Config.getInstance().getDevice().getType());
        params.put("DeviceModel", Config.getInstance().getDevice().getModel());
        params.put("DeviceOS", Config.getInstance().getDevice().getOsVersion());

        HospRestClient.postJSON("hospital_summary", context, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Log.v(TAG, "Hospital Summary Store Response, got result");

                listener.onResponse(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                Log.v(TAG, "Hospital Summary Store Response, got error: " + statusCode);
                Log.v(TAG, errorResponse.toString());

                listener.onError(statusCode, errorResponse.toString());
            }
        });
    }
}
