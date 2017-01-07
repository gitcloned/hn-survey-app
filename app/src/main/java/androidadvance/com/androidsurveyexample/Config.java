package androidadvance.com.androidsurveyexample;

import androidadvance.com.androidsurveyexample.http.hosp.Device;
import androidadvance.com.androidsurveyexample.http.hosp.resp.SummaryResponse;

/**
 * Created by asjain on 1/5/2017.
 */

public class Config {

    private static Config instance;

    public SummaryResponse getSummaryResponse() {
        return summaryResponse;
    }

    public void setSummaryResponse(SummaryResponse summaryResponse) {
        this.summaryResponse = summaryResponse;
    }

    // Global variable
    private SummaryResponse summaryResponse;
    private Device device;

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
