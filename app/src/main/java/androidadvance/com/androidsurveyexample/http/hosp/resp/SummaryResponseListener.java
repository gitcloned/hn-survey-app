package androidadvance.com.androidsurveyexample.http.hosp.resp;

import android.app.Activity;

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

    public abstract void onError(String error);
}
