package androidadvance.com.androidsurveyexample.http.hosp.resp.listeners;

import android.app.Activity;

import org.json.JSONObject;

/**
 * Created by asjain on 1/7/2017.
 */

public abstract class FormResponseListener {

    private Activity context;

    public FormResponseListener(Activity context) {
        this.context = context;
    }

    public Activity getActivity() {
        return this.context;
    }

    public abstract void onResponse(JSONObject response);

    public abstract void onError(int statusCode, String errorResponse);
}
