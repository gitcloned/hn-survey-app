package androidadvance.com.androidsurveyexample.http.hosp.resp;

import androidadvance.com.androidsurveyexample.utils.DateUtil;

/**
 * Created by asjain on 1/7/2017.
 */

public class FormResponse {

    private String responseId;
    private String formId;
    private String answers;
    private Double sentiment;
    private String timestamp;
    private Double score;

    public FormResponse(String responseId, String formId, String answers, Double score, Double sentiment) {
        this.responseId = responseId;
        this.formId = formId;
        this.answers = answers;
        this.sentiment = sentiment;
        this.timestamp = DateUtil.getCurrentTimestampString();
        this.score = score;
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

    public Double getScore() {
        return score;
    }

    public Double getSentiment() {
        return sentiment;
    }
}
