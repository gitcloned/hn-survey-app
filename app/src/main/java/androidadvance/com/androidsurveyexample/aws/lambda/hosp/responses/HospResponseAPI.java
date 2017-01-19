package androidadvance.com.androidsurveyexample.aws.lambda.hosp.responses;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunctionException;

import androidadvance.com.androidsurveyexample.aws.lambda.hosp.InvokerFactory;
import androidadvance.com.androidsurveyexample.aws.lambda.hosp.responses.payload.HospResponseAPIContract;
import androidadvance.com.androidsurveyexample.aws.lambda.hosp.responses.payload.ResponseRequest;
import androidadvance.com.androidsurveyexample.aws.lambda.hosp.responses.payload.listener.ResponseSaveListener;

/**
 * Created by asjain on 1/20/2017.
 */

public class HospResponseAPI {

    private Context context = null;

    private final String TAG = "HospResponseAPI";

    public HospResponseAPI(Context context) {

        this.context = context;
    }

    public void storeResponse(ResponseRequest request, final ResponseSaveListener listener) {

        final HospResponseAPIContract contract = InvokerFactory.getInstance(this.context)
                .getFactory().build(HospResponseAPIContract.class);

        // The Lambda function invocation results in a network call
// Make sure it is not called from the main thread
        Log.i(TAG, "Starting async task ***************************");

        new AsyncTask<ResponseRequest, Void, Object>() {
            @Override
            protected Object doInBackground(ResponseRequest... params) {
                // invoke "echo" method. In case it fails, it will throw a
                // LambdaFunctionException.
                Log.i(TAG, "doing in background ***************************");
                try {
                    return contract.storeResponse(params[0]);
                } catch (LambdaFunctionException lfe) {
                    Log.e(TAG, "Failed to invoke echo", lfe);
                    listener.onError(0, lfe.toString());
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Object result) {

                listener.onResponse(result);

                Log.i(TAG, "Got result 1 ***************************");
                if (result == null) {
                    return;
                }

                Log.i(TAG, "Got result ***************************");
            }
        }.execute(request);
    }
}
