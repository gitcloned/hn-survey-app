package androidadvance.com.androidsurveyexample.http.hosp;

import android.os.Build;
import android.provider.Settings;

/**
 * Created by asjain on 1/7/2017.
 */

public class Device {

    private String id;
    private String type;
    private String model;
    private String osVersion;

    public String getId() {
        return id;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public String getType() {

        return type;
    }

    public String getModel() {
        return model;
    }

    public Device(String id) {

        this.id = id;
        this.type = "AndroidDevice";
        this.model = Build.MODEL;
        this.osVersion = System.getProperty("os.version");
    }
}
