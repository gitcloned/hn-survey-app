package androidadvance.com.androidsurveyexample.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by asjain on 1/7/2017.
 */

public class DateUtil {

    public static String getCurrentTimestampString() {

        TimeZone tz = TimeZone.getTimeZone("GMT");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(tz);

        return df.format(new Date());
    }
}
