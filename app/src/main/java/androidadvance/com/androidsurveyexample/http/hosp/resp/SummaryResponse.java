package androidadvance.com.androidsurveyexample.http.hosp.resp;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by asjain on 1/2/2017.
 */

public class SummaryResponse  implements Serializable {

    @SerializedName("forms")
    @Expose
    ArrayList<Form> forms = new ArrayList<Form>();

    public ArrayList<Form> getForms() {
        return forms;
    }

    public Form getForm(String formId) {

        for (Form form : forms) {

            Log.v("Summary Resp", "Finding form: " + formId + " with: " + form.getId());

            if (form.getId().equals(formId)) return form;
        }

        return null;
    }

    public void addForm(Form form) {

        forms.add(form);
    }
}
