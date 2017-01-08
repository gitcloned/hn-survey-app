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
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import cz.msebera.android.httpclient.conn.ssl.SSLSocketFactory;
import cz.msebera.android.httpclient.entity.StringEntity;

public class HospRestClient {

    private static HospRestClient instance;

    public static synchronized HospRestClient getInstance(){
        if(instance==null){
            instance=new HospRestClient();
        }
        return instance;
    }

    private final String BASE_URL = "https://q1e4xm6ex0.execute-api.us-east-1.amazonaws.com/Dev/";

    private AsyncHttpClient client = null;

    private static final String TAG = "HospRestClient";
    private static final String Version = "100";

    private HospRestClient(){

        try {

            /*
            client = new AsyncHttpClient(true, 80, 443);

            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            MySSLSocketFactory sf = new MySSLSocketFactory(trustStore);
            sf.setHostnameVerifier(MySSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            client.setSSLSocketFactory(sf);
            */

            /*
            client = new AsyncHttpClient(true, 80, 443);

            client.setSSLSocketFactory(
                    new SSLSocketFactory(getSslContext(),
                            SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER));
                            */

            client = new AsyncHttpClient();

            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            MySSLSocketFactoryCustom sf = new MySSLSocketFactoryCustom(trustStore);
            sf.setHostnameVerifier(MySSLSocketFactoryCustom.ALLOW_ALL_HOSTNAME_VERIFIER);
            client.setSSLSocketFactory(sf);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private SSLContext getSslContext() {

        Log.v(TAG, "*****************************");

        TrustManager[] byPassTrustManagers = new TrustManager[] { new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }
        } };

        SSLContext sslContext=null;

        try {
            sslContext = SSLContext.getInstance("TLS");
            Log.v(TAG, "*****************************");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            sslContext.init(null, byPassTrustManagers, new SecureRandom());
            Log.v(TAG, "*****************************");
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        return sslContext;
    }

    public void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {

        Log.v(TAG, "[GET] Hitting URL: " + url);

        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {

        Log.v(TAG, "[POST] Hitting URL: " + url);

        params.put("httpMethod", "POST");
        params.put("V", Version);

        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public void postJSON(String url, Context context, JSONObject params, AsyncHttpResponseHandler responseHandler) throws JSONException, UnsupportedEncodingException {

        Log.v(TAG, "[POST] Hitting URL: " + url);

        params.put("httpMethod", "POST");
        params.put("V", Version);

        StringEntity entity = new StringEntity(params.toString());

        client.post(context, getAbsoluteUrl(url), entity, "application/json", responseHandler);
    }

    private String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
