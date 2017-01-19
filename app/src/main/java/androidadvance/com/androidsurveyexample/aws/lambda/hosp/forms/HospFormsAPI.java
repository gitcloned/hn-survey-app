package androidadvance.com.androidsurveyexample.aws.lambda.hosp.forms;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunctionException;

import java.util.ArrayList;

import androidadvance.com.androidsurveyexample.Config;
import androidadvance.com.androidsurveyexample.aws.lambda.hosp.InvokerFactory;
import androidadvance.com.androidsurveyexample.aws.lambda.hosp.forms.payload.FormsRequest;
import androidadvance.com.androidsurveyexample.aws.lambda.hosp.forms.payload.HospFormsAPIContract;
import androidadvance.com.androidsurveyexample.aws.lambda.hosp.forms.payload.listener.FormsResponseListener;

/**
 * Created by asjain on 1/19/2017.
 */

public class HospFormsAPI {

    private Context context = null;

    private final String TAG = "HospFormsAPI";

    public HospFormsAPI(Context context) {

        this.context = context;
    }

    public void getForms(final FormsResponseListener listener) {

        final HospFormsAPIContract contract = InvokerFactory.getInstance(this.context)
                .getFactory().build(HospFormsAPIContract.class);

        // The Lambda function invocation results in a network call
// Make sure it is not called from the main thread
        Log.i(TAG, "Starting async task ***************************");
        new AsyncTask<FormsRequest, Void, ArrayList<Object>>() {
            @Override
            protected ArrayList<Object> doInBackground(FormsRequest... params) {
                // invoke "echo" method. In case it fails, it will throw a
                // LambdaFunctionException.
                Log.i(TAG, "doing in background ***************************");
                try {
                    return contract.getForms(params[0]);
                } catch (LambdaFunctionException lfe) {
                    Log.e(TAG, "Failed to invoke echo", lfe);
                    listener.onError(0, lfe.toString());
                    return null;
                }
            }

            @Override
            protected void onPostExecute(ArrayList<Object> result) {

                listener.onResponse(result);

                Log.i(TAG, "Got result 1 ***************************");
                if (result == null) {
                    return;
                }

                Log.i(TAG, "Got result ***************************");
            }
        }.execute(new FormsRequest(Config.getInstance().getClientId(), "detail"));
    }
}
