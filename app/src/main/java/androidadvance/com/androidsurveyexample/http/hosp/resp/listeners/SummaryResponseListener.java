package androidadvance.com.androidsurveyexample.http.hosp.resp.listeners;

import android.app.Activity;

import androidadvance.com.androidsurveyexample.http.hosp.resp.SummaryResponse;

/**
 * Created by asjain on 1/2/2017.
 */

public abstract class SummaryResponseListener {

    private Activity context;

    public SummaryResponseListener(Activity context) {
        this.context = context;
    }

    public Activity getActivity() {
        return this.context;
    }

    public abstract void onResponse(SummaryResponse response);

    public abstract void onError(int statusCode, String errorResponse);
}
