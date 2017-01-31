package com.androidadvance.androidsurvey.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.androidadvance.androidsurvey.Answers;
import com.androidadvance.androidsurvey.R;
import com.androidadvance.androidsurvey.SurveyActivity;
import com.androidadvance.androidsurvey.models.Quote;
import com.androidadvance.androidsurvey.models.Quotes;
import com.androidadvance.androidsurvey.models.SurveyProperties;

public class FragmentEnd extends Fragment {

    private FragmentActivity mContext;
    private TextView textView_end;
    private TextView textView_start_quote;
    private TextView textView_start_quote_by;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_end, container, false);


        Button button_finish = (Button) rootView.findViewById(R.id.button_finish);
        textView_end = (TextView) rootView.findViewById(R.id.textView_end);
        textView_start_quote = (TextView) rootView.findViewById(R.id.textView_end_quote);
        textView_start_quote_by = (TextView) rootView.findViewById(R.id.textView_end_quote_by);

        button_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((SurveyActivity) mContext).event_survey_completed(Answers.getInstance());

            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContext = getActivity();
        SurveyProperties survery_properties = (SurveyProperties) getArguments().getSerializable("survery_properties");

        assert survery_properties != null;

        Quote quote = Quotes.getInstance().getRandomQuote();

        textView_start_quote.setText(quote.getQuote());
        textView_start_quote_by.setText("- " + quote.getAuthor());

        textView_end.setText(Html.fromHtml(survery_properties.getEndMessage()));

    }
}