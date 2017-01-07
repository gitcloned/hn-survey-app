package androidadvance.com.androidsurveyexample.http.hosp.resp;

import androidadvance.com.androidsurveyexample.utils.DateUtil;

/**
 * Created by asjain on 1/7/2017.
 */

public class FormResponse {

    private String responseId;
    private String formId;
    private String answers;
    private String timestamp;

    public FormResponse(String responseId, String formId, String answers) {
        this.responseId = responseId;
        this.formId = formId;
        this.answers = answers;
        this.timestamp = DateUtil.getCurrentTimestampString();
    }

    public String getResponseId() {
        return responseId;
    }

    public String getFormId() {
        return formId;
    }

    public String getAnswers() {
        return answers;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
