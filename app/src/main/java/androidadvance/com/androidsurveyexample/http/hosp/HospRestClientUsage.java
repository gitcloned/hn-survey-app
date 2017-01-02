package androidadvance.com.androidsurveyexample.http.hosp;

/**
 * Created by asjain on 1/2/2017.
 */

import org.json.*;
import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

public class HospRestClientUsage {

    public void getSummary() throws JSONException {

        HospRestClient.get("hospital_summary", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray

            }
        });
    }
}
