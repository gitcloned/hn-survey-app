package androidadvance.com.androidsurveyexample.aws.lambda.hosp.forms;

/**
 * Created by asjain on 1/19/2017.
 */

public class HospitalForm {

    private String FormId;
    private String Name;
    private String Description;
    private String form;
    private String SMSN;
    private String EmailN;
    private double MaxScore;

    public HospitalForm() {
    }

    public HospitalForm(String formId, String name, String description, String form) {
        FormId = formId;
        Name = name;
        Description = description;
        this.form = form;
    }

    public String getFormId() {
        return FormId;
    }

    public void setFormId(String formId) {
        FormId = formId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
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

    public double getMaxScore() {
        return MaxScore;
    }

    public void setMaxScore(double maxScore) {
        MaxScore = maxScore;
    }
}
