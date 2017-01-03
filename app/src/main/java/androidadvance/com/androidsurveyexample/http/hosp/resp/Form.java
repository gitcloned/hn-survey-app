package androidadvance.com.androidsurveyexample.http.hosp.resp;

import org.json.JSONObject;

/**
 * Created by asjain on 1/2/2017.
 */

public class Form {

    private String id;
    private String client;
    private JSONObject formJSON;

    public Form(String id, String client, JSONObject formJSON) {

        this.id = id;
        this.client = client;
        this.formJSON = formJSON;
    }

    public String getFormContent() {

        return this.formJSON.toString();
    }
}
