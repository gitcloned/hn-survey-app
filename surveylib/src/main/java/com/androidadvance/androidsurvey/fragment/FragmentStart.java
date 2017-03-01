package com.androidadvance.androidsurvey.fragment;

import android.graphics.Typeface;
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


public class FragmentStart extends Fragment {

    private FragmentActivity mContext;
    private TextView textView_start;
    private TextView textView_start_quote;
    private TextView textView_start_quote_by;
    private String language = "English";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_start, container, false);



        textView_start = (TextView) rootView.findViewById(R.id.textView_start);
        textView_start_quote = (TextView) rootView.findViewById(R.id.textView_start_quote);
        textView_start_quote_by = (TextView) rootView.findViewById(R.id.textView_start_quote_by);

        //textView_start = (TextView) findViewById(R.id.textView1);
        //textView_start.setTypeface(Answers.getInstance().getFont());

        Button button_continue = (Button) rootView.findViewById(R.id.button_continue);
        button_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SurveyActivity) mContext).go_to_next();
            }
        });

        language = (String)getArguments().getSerializable("language");

        if (language.equals("Hindi"))
            button_continue.setText("प्रारंभ");
        else
            button_continue.setText("Start");

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContext = getActivity();
        SurveyProperties survery_properties = (SurveyProperties) getArguments().getSerializable("survery_properties");

        assert survery_properties != null;

        Quote quote = Quotes.getInstance(language).getRandomQuote();

        textView_start_quote.setText(quote.getQuote());
        textView_start_quote_by.setText("- " + quote.getAuthor());
        //textView_start.setText(Html.fromHtml(survery_properties.getIntroMessage()));

        textView_start.setText(survery_properties.getIntroMessage(language));
    }
}