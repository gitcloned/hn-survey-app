package androidadvance.com.androidsurveyexample.aws.lambda.hosp;

import android.content.Context;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory;
import com.amazonaws.regions.Regions;

/**
 * Created by asjain on 1/19/2017.
 */

public class InvokerFactory {

    private static InvokerFactory instance;

    private CognitoCachingCredentialsProvider credentialsProvider = null;
    private LambdaInvokerFactory factory = null;

    // Restrict the constructor from being instantiated
    private InvokerFactory(Context context){

        credentialsProvider = new CognitoCachingCredentialsProvider(
                context,
                "us-east-1:86d7f19d-a03a-40f6-9826-81fe59b0ed84", // Identity Pool ID
                Regions.US_EAST_1 // Region
        );

        factory = new LambdaInvokerFactory(
                context,
                Regions.US_EAST_1,
                credentialsProvider);
    }

    public static synchronized InvokerFactory getInstance(Context context){
        if(instance==null){
            instance=new InvokerFactory(context);
        }
        return instance;
    }

    public LambdaInvokerFactory getFactory() {
        return factory;
    }
}
