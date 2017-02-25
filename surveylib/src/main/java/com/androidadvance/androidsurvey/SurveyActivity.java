package com.androidadvance.androidsurvey;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.androidadvance.androidsurvey.adapters.AdapterFragmentQ;
import com.androidadvance.androidsurvey.fragment.FragmentCheckboxes;
import com.androidadvance.androidsurvey.fragment.FragmentEnd;
import com.androidadvance.androidsurvey.fragment.FragmentMultiline;
import com.androidadvance.androidsurvey.fragment.FragmentNumber;
import com.androidadvance.androidsurvey.fragment.FragmentRadioboxes;
import com.androidadvance.androidsurvey.fragment.FragmentStart;
import com.androidadvance.androidsurvey.fragment.FragmentTextSimple;
import com.androidadvance.androidsurvey.fragment.FragmentUserInfo;
import com.androidadvance.androidsurvey.models.Question;
import com.androidadvance.androidsurvey.models.SurveyPojo;
import com.google.gson.Gson;

import java.util.ArrayList;

public class SurveyActivity extends AppCompatActivity {

    private SurveyPojo mSurveyPojo;
    private String formId;
    private ViewPager mPager;
    private String style_string = null;
    private String language = "English";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_survey);

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            Log.i("Survey", "Got form id: " + bundle.getString("form_id"));

            mSurveyPojo = new Gson().fromJson(bundle.getString("json_survey"), SurveyPojo.class);
            formId = bundle.getString("form_id");
            language = bundle.getString("language");

            ActionBar ab = getActionBar();
            ab.setTitle(bundle.getString("form_title"));

            if (bundle.containsKey("style")) {
                style_string = bundle.getString("style");
            }
        }

        final ArrayList<Fragment> arraylist_fragments = new ArrayList<>();

        //- START -
        if (mSurveyPojo.getSurveyProperties() != null || !mSurveyPojo.getSurveyProperties().getSkipIntro()) {
            FragmentStart frag_start = new FragmentStart();
            Bundle sBundle = new Bundle();
            sBundle.putSerializable("survery_properties", mSurveyPojo.getSurveyProperties());
            sBundle.putString("style", style_string);
            sBundle.putString("language", language);
            frag_start.setArguments(sBundle);
            arraylist_fragments.add(frag_start);
        }

        FragmentUserInfo frag_userInfo = new FragmentUserInfo();
        Bundle fuBundle = new Bundle();
        fuBundle.putString("style", style_string);
        fuBundle.putString("language", language);
        frag_userInfo.setArguments(fuBundle);
        arraylist_fragments.add(frag_userInfo);

        //- FILL -
        for (Question mQuestion : mSurveyPojo.getQuestions()) {

            Log.i("Survey", "pojo qs: " + mQuestion.getQuestionTitle());

            if (mQuestion.getQuestionType().equals("String")) {
                FragmentTextSimple frag = new FragmentTextSimple();
                Bundle xBundle = new Bundle();
                xBundle.putSerializable("data", mQuestion);
                xBundle.putString("style", style_string);
                xBundle.putString("language", language);
                frag.setArguments(xBundle);
                arraylist_fragments.add(frag);
            }

            if (mQuestion.getQuestionType().equals("Checkboxes")) {
                FragmentCheckboxes frag = new FragmentCheckboxes();
                Bundle xBundle = new Bundle();
                xBundle.putSerializable("data", mQuestion);
                xBundle.putString("style", style_string);
                xBundle.putString("language", language);
                frag.setArguments(xBundle);
                arraylist_fragments.add(frag);
            }

            if (mQuestion.getQuestionType().equals("Radioboxes")) {
                FragmentRadioboxes frag = new FragmentRadioboxes();
                Bundle xBundle = new Bundle();
                xBundle.putSerializable("data", mQuestion);
                xBundle.putString("style", style_string);
                xBundle.putString("language", language);
                frag.setArguments(xBundle);
                arraylist_fragments.add(frag);
            }

            if (mQuestion.getQuestionType().equals("Number")) {
                FragmentNumber frag = new FragmentNumber();
                Bundle xBundle = new Bundle();
                xBundle.putSerializable("data", mQuestion);
                xBundle.putString("style", style_string);
                xBundle.putString("language", language);
                frag.setArguments(xBundle);
                arraylist_fragments.add(frag);
            }

            if (mQuestion.getQuestionType().equals("StringMultiline")) {
                FragmentMultiline frag = new FragmentMultiline();
                Bundle xBundle = new Bundle();
                xBundle.putSerializable("data", mQuestion);
                xBundle.putString("style", style_string);
                xBundle.putString("language", language);
                frag.setArguments(xBundle);
                arraylist_fragments.add(frag);
            }

        }

        //- END -
        FragmentEnd frag_end = new FragmentEnd();
        Bundle eBundle = new Bundle();
        eBundle.putSerializable("survery_properties", mSurveyPojo.getSurveyProperties());
        eBundle.putString("style", style_string);
        eBundle.putString("language", language);
        frag_end.setArguments(eBundle);
        arraylist_fragments.add(frag_end);


        mPager = (ViewPager) findViewById(R.id.pager);
        AdapterFragmentQ mPagerAdapter = new AdapterFragmentQ(getSupportFragmentManager(), arraylist_fragments);
        mPager.setAdapter(mPagerAdapter);


    }

    public void go_to_next() {
        mPager.setCurrentItem(mPager.getCurrentItem() + 1);
    }


    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    public void event_survey_completed(Answers instance) {

        Double maxScore = mSurveyPojo.getSurveyProperties().getMaxScore();
        Double score = instance.getScore();
        Double sentiment = 0.0;

        if (maxScore != null && maxScore > 0) {

            if (score > maxScore) sentiment = 1.0;
            else if (score == 0) sentiment = 0.0;
            else {

                double mid = maxScore / 2;
                sentiment = (score - mid) / mid;
            }
        }

        Intent returnIntent = new Intent();
        returnIntent.putExtra("answers", instance.get_json_object());
        returnIntent.putExtra("score", score);
        returnIntent.putExtra("sentiment", sentiment);
        returnIntent.putExtra("form_id", formId);
        returnIntent.putExtra("user_name", instance.getUserName());
        returnIntent.putExtra("user_contact", instance.getUserContact());
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
