package androidadvance.com.androidsurveyexample.http.hosp;

/**
 * Created by asjain on 1/2/2017.
 */

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;

public class HospRestClient {

    private static final String BASE_URL = "https://q1e4xm6ex0.execute-api.us-east-1.amazonaws.com/Dev/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    private static final String TAG = "HospRestClient";
    private static final String Version = "100";

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {

        Log.v(TAG, "[GET] Hitting URL: " + url);

        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {

        Log.v(TAG, "[POST] Hitting URL: " + url);

        params.put("httpMethod", "POST");
        params.put("V", Version);

        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void postJSON(String url, Context context, JSONObject params, AsyncHttpResponseHandler responseHandler) throws JSONException, UnsupportedEncodingException {

        Log.v(TAG, "[POST] Hitting URL: " + url);

        params.put("httpMethod", "POST");
        params.put("V", Version);

        StringEntity entity = new StringEntity(params.toString());

        client.post(context, getAbsoluteUrl(url), entity, "application/json", responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
