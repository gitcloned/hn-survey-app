package androidadvance.com.androidsurveyexample.http.hosp.resp;

import java.util.ArrayList;

/**
 * Created by asjain on 1/2/2017.
 */

public class SummaryResponse {

    ArrayList<Form> forms = new ArrayList<Form>();

    public void addForm(Form form) {

        forms.add(form);
    }
}