package androidadvance.com.androidsurveyexample.aws.lambda.hosp.forms.payload.listener;

import android.content.Context;

import java.util.ArrayList;

import androidadvance.com.androidsurveyexample.aws.lambda.hosp.forms.HospitalForm;

/**
 * Created by asjain on 1/19/2017.
 */

public abstract class FormsResponseListener {

    private Context context;

    public FormsResponseListener(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public abstract void onResponse(ArrayList<Object> response);

    public abstract void onError(int statusCode, String errorResponse);
}
