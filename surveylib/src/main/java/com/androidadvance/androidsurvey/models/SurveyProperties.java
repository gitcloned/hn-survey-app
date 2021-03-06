package com.androidadvance.androidsurvey.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SurveyProperties implements Serializable {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("intro_message")
    @Expose
    private String introMessage;
    @SerializedName("intro_message_h")
    @Expose
    private String introMessageHindi;
    @SerializedName("end_message")
    @Expose
    private String endMessage;
    @SerializedName("end_message_h")
    @Expose
    private String endMessageHindi;
    @SerializedName("skip_intro")
    @Expose
    private Boolean skipIntro;
    @SerializedName("max_score")
    @Expose
    private Double maxScore;

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The introMessage
     */
    public String getIntroMessage() {
        return introMessage;
    }

    /**
     * @return The introMessage
     */
    public String getIntroMessage(String language)
    {
        if (language.equals("Hindi"))
            return introMessageHindi;
        else
            return introMessage;
    }

    /**
     * @param introMessage The intro_message
     */
    public void setIntroMessage(String introMessage) {
        this.introMessage = introMessage;
    }

    /**
     * @return The endMessage
     */
    public String getEndMessage() {
        return endMessage;
    }

    /**
     * @return The endMessage
     */
    public String getEndMessage(String language) {
        if (language.equals("Hindi"))
            return endMessageHindi;
        else
            return endMessage;
    }

    /**
     * @param endMessage The end_message
     */
    public void setEndMessage(String endMessage) {
        this.endMessage = endMessage;
    }

    /**
     * @return The skipIntro
     */
    public Boolean getSkipIntro() {
        return skipIntro;
    }

    /**
     * @param skipIntro The skip_intro
     */
    public void setSkipIntro(Boolean skipIntro) {
        this.skipIntro = skipIntro;
    }

    public Double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Double maxScore) {
        this.maxScore = maxScore;
    }
}