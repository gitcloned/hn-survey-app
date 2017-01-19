package androidadvance.com.androidsurveyexample.http.hosp.resp;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.io.Serializable;

import androidadvance.com.androidsurveyexample.http.hosp.resp.pojo.SurveyPojo;

/**
 * Created by asjain on 1/2/2017.
 */

public class Form  implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("client")
    @Expose
    private String client;
    @SerializedName("description")
    @Expose
    private String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public String getClient() {
        return client;
    }

    public String getFormJSON() {
        return formJSON;
    }

    private String formJSON;

    private String password;

    public Form(String id, String name, String description, String client, String formJSON, String password) {

        this.id = id;
        this.client = client;
        this.name = name;
        this.description = description;
        this.formJSON = formJSON;
        this.password = password;
    }

    public Form() {

    }

    public String getFormContent() {

        return formJSON;
    }

    public String getPassword() {
        return password;
    }
}
