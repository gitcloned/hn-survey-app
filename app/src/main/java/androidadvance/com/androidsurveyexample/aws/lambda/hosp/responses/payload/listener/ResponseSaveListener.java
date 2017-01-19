package androidadvance.com.androidsurveyexample.aws.lambda.hosp.responses.payload.listener;

import android.content.Context;

/**
 * Created by asjain on 1/20/2017.
 */

public abstract class ResponseSaveListener {

    private Context context;

    public ResponseSaveListener(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public abstract void onResponse(Object response);

    public abstract void onError(int statusCode, String errorResponse);
}
