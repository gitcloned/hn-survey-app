package androidadvance.com.androidsurveyexample;

import java.util.ArrayList;
import java.util.StringTokenizer;

import androidadvance.com.androidsurveyexample.aws.lambda.hosp.forms.HospitalForm;
import androidadvance.com.androidsurveyexample.http.hosp.Device;
import androidadvance.com.androidsurveyexample.http.hosp.resp.SummaryResponse;

/**
 * Created by asjain on 1/5/2017.
 */

public class Config {

    private static Config instance;
    private String ClientId = "SPPC";
    private String ENV = "DEV";

    public String getClientId() {
        return ClientId;
    }

    public String getENV() { return ENV; }

    public SummaryResponse getSummaryResponse() {
        return summaryResponse;
    }

    public void setSummaryResponse(SummaryResponse summaryResponse) {
        this.summaryResponse = summaryResponse;
    }

    public ArrayList<HospitalForm> getHospitalForms() {
        return hospitalForms;
    }

    public void setHospitalForms(ArrayList<HospitalForm> hospitalForms) {
        this.hospitalForms = hospitalForms;
    }

    // Global variable
    private SummaryResponse summaryResponse;
    private Device device;
    private ArrayList<HospitalForm> hospitalForms;

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    // Restrict the constructor from being instantiated
    private Config(){

    }

    public static synchronized Config getInstance(){
        if(instance==null){
            instance=new Config();
        }
        return instance;
    }

}
