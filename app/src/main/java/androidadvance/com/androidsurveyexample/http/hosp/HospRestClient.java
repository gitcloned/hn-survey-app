package androidadvance.com.androidsurveyexample.http.hosp;

/**
 * Created by asjain on 1/2/2017.
 */

import android.util.Log;

import com.loopj.android.http.*;

public class HospRestClient {

    private static final String BASE_URL = "https://q1e4xm6ex0.execute-api.us-east-1.amazonaws.com/Dev/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    private static final String TAG = "HospRestClient";

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {

        Log.v(TAG, "[GET] Hitting URL: " + url);

        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {

        Log.v(TAG, "[POST] Hitting URL: " + url);

        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
