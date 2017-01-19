package androidadvance.com.androidsurveyexample.aws.lambda.hosp.responses.payload;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunction;

import java.util.ArrayList;

/**
 * Created by asjain on 1/20/2017.
 */

public interface HospResponseAPIContract {

    /**
     * Invoke lambda function "echo". The function name is the method name
     */
    @LambdaFunction(functionName = "HospitalAPI")
    Object storeResponse(ResponseRequest responseRequest);
}
