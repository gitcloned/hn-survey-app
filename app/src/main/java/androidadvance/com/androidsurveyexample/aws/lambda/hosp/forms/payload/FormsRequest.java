package androidadvance.com.androidsurveyexample.aws.lambda.hosp.forms.payload;

/**
 * Created by asjain on 1/19/2017.
 */

public class FormsRequest {

    private String name = "forms";
    private String select = "";
    private String ClientId;
    private String env;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClientId() {
        return ClientId;
    }

    public void setClientId(String clientId) {
        ClientId = clientId;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getEnv() { return env; }

    public void setEnv(String env) { this.env = env; }

    public FormsRequest(String clientId, String select, String env)
    {
        ClientId = clientId;
        this.select = select;
        this.env = env == null ? "DEV" : env;
    }

    public FormsRequest() {

    }
}
