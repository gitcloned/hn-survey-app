package androidadvance.com.androidsurveyexample.http.hosp.resp;

import org.json.JSONObject;

/**
 * Created by asjain on 1/2/2017.
 */

public class Form {

    private String id;
    private String name;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    private String description;

    public String getId() {
        return id;
    }

    public String getClient() {
        return client;
    }

    public JSONObject getFormJSON() {
        return formJSON;
    }

    private String client;
    private JSONObject formJSON;

    public Form(String id, String name, String description, String client, JSONObject formJSON) {

        this.id = id;
        this.client = client;
        this.name = name;
        this.description = description;
        this.formJSON = formJSON;
    }

    public String getFormContent() {

        return this.formJSON.toString();
    }
}
