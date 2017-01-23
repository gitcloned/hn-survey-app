package com.androidadvance.androidsurvey;

import android.util.Log;

import com.androidadvance.androidsurvey.models.Answer;
import com.androidadvance.androidsurvey.models.Question;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.DuplicateFormatFlagsException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//Singleton Answers ........

public class Answers {

    private volatile static Answers uniqueInstance;

    private final LinkedHashMap<String, Answer> answered_hashmap = new LinkedHashMap<>();

    private List<Answer> answers = new ArrayList<Answer>();

    private String UserName;
    private String UserContact;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserContact() {
        return UserContact;
    }

    public void setUserContact(String userContact) {
        UserContact = userContact;
    }

    private Answers() {
    }

    /*public void put_answer(String key, String value) {
        answered_hashmap.put(key, value);
    }*/

    public void put_answer(Question question, String value) {

        Answer answer = new Answer();

        answer.setQuestion(question.getQuestionId());
        answer.setResponse(value);
        answer.setScore(0.0);
//        answer.setScoreSentiment(0.0);

        answered_hashmap.put(question.getQuestionId(), answer);
    }

    public void put_answer(Question question, String value, List<Integer> choice_indexes) {

        String id = question.getQuestionId();
        Answer answer = null;

        if (answered_hashmap.get(id) == null) answer = new Answer();
        else answer = answered_hashmap.get(id);

        List<Integer> scores = question.getScores();

        /*Log.v(" <<< " + question.getQuestionId() + ": ", Arrays.toString(scores.toArray()));
        Log.v(" <<< " + question.getQuestionId() + ", score enabled: ", question.getScoreEnabled().toString());
        Log.v(" <<< " + question.getQuestionId() + ", choices: ", Arrays.toString(choice_indexes.toArray()));
        Log.v(" <<< " + question.getQuestionId() + ", weight: ", question.getScoreWeight().toString());*/

        answer.setQuestion(question.getQuestionId());
        answer.setResponse(value);

        Double quesScore = 0.0;

        if (question.getScoreEnabled() && scores != null && scores.size() > 0 && choice_indexes.size() > 0) {

            Double weight = question.getScoreWeight();

            if (weight == null || weight <= 0) weight = 1.0;

            for (Integer index : choice_indexes) {

                if (index < scores.size()) {

                    quesScore += scores.get(index);
                }
            }

            answer.setScore(quesScore * weight);
//            answer.setScoreSentiment(0.0);

            Log.v(" <<< " + question.getQuestionId() + ", scores, choices: " +  scores.toString(), choice_indexes.toString() + " * " +weight);
            Log.v(" <<< " + question.getQuestionId() + ", score, sentiment: ", String.valueOf((quesScore * weight)));

        } else {

            answer.setScore(0.0);
//            answer.setScoreSentiment(0.0);
        }

        answered_hashmap.put(id, answer);
    }

    public String get_json_object() {
        Gson gson = new Gson();
        return gson.toJson(answered_hashmap,LinkedHashMap.class);
        //return gson.toJson(answers,List.class);
    }

    public Double getScore() {

        Double score = 0.0;
        for (Map.Entry<String, Answer> entry : answered_hashmap.entrySet()) {

            if (entry.getValue() != null)
                score += entry.getValue().getScore();
        }

        return score;
    }

    @Override
    public String toString() {
        return String.valueOf(answered_hashmap);
    }

    public static Answers getInstance() {
        if (uniqueInstance == null) {
            synchronized (Answers.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Answers();
                }
            }
        }
        return uniqueInstance;
    }
}
