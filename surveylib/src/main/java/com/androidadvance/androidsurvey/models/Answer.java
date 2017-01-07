package com.androidadvance.androidsurvey.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Answer implements Serializable {

    @SerializedName("Ques")
    @Expose
    private String question;

    @SerializedName("Resp")
    @Expose
    private String response;

    @SerializedName("Score")
    @Expose
    private Double score;

    /*@SerializedName("ScoreSentiment")
    @Expose
    private Double scoreSentiment;*/

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    /*public Double getScoreSentiment() {
        return scoreSentiment;
    }

    public void setScoreSentiment(Double scoreSentiment) {
        this.scoreSentiment = scoreSentiment;
    }*/
}
