package androidadvance.com.androidsurveyexample.aws.lambda.hosp.forms.payload;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunction;

import java.util.ArrayList;

import androidadvance.com.androidsurveyexample.aws.lambda.hosp.forms.HospitalForm;

/**
 * Created by asjain on 1/19/2017.
 */

public interface HospFormsAPIContract {

    /**
     * Invoke lambda function "echo". The function name is the method name
     */
    @LambdaFunction(functionName = "HospitalAPI")
    ArrayList<Object> getForms(FormsRequest formsRequest);
}
