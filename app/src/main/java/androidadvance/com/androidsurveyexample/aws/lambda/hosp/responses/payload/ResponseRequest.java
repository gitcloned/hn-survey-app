package androidadvance.com.androidsurveyexample.aws.lambda.hosp.responses.payload;

/**
 * Created by asjain on 1/20/2017.
 */

public class ResponseRequest {

    private String name = "responses";
    private String ClientId;
    private String FormId;
    private String httpMethod = "POST";
    private String UserName;
    private String UserContact;
    private String body;
    private String DeviceId;
    private String DeviceType;
    private String DeviceModel;
    private String DeviceOS;
    private double Score;
    private String SMSN;
    private String EmailN;
    private String MaxScore;

    public ResponseRequest() {
    }

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

    public String getFormId() {
        return FormId;
    }

    public void setFormId(String formId) {
        FormId = formId;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserContact() {
        return UserContact;
    }

    public void setUserContact(String userContact) {
        UserContact = userContact;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    public String getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(String deviceType) {
        DeviceType = deviceType;
    }

    public String getDeviceModel() {
        return DeviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        DeviceModel = deviceModel;
    }

    public String getDeviceOS() {
        return DeviceOS;
    }

    public void setDeviceOS(String deviceOS) {
        DeviceOS = deviceOS;
    }

    public double getScore() {
        return Score;
    }

    public void setScore(double score) {
        Score = score;
    }

    public String getSMSN() {
        return SMSN;
    }

    public void setSMSN(String SMSN) {
        this.SMSN = SMSN;
    }

    public String getEmailN() {
        return EmailN;
    }

    public void setEmailN(String emailN) {
        EmailN = emailN;
    }

    public String getMaxScore() {
        return MaxScore;
    }

    public void setMaxScore(String maxScore) {
        MaxScore = maxScore;
    }
}
