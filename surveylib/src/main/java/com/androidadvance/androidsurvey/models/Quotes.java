package com.androidadvance.androidsurvey.models;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by amit on 31/1/17.
 */

public class Quotes {

    private static Quotes hindiInstance;
    private static Quotes englishInstance;
    private ArrayList<Quote> quotes;

    private int startIdx = 0;
    private int endIdx = 0;

    private Quotes(int startIdx, int endIdx) {

        this.startIdx = startIdx;
        this.endIdx = endIdx;

        quotes = new ArrayList<Quote>();

        quotes.add(new Quote("To enjoy good health, to bring true happiness to one's family, to bring peace to all, one must first discipline and control one's own mind. If a man can control his mind he can find the way to Enlightenment, and all wisdom and virtue will naturally come to him.", "Buddha"));
        quotes.add(new Quote("Calm mind brings inner strength and self-confidence, so that's very important for good health.", "Dalai Lama"));
        quotes.add(new Quote("To keep the body in good health is a duty... otherwise we shall not be able to keep our mind strong and clear.", "Buddha"));
        quotes.add(new Quote("There's nothing more important than our good health - that's our principal capital asset. ","Arlen Specter"));
        quotes.add(new Quote("Good health is not something we can buy. However, it can be an extremely valuable savings account.","Anne Wilson Schaef"));
        quotes.add(new Quote("Good health and good sense are two of life's greatest blessings. ","Publilius Syrus"));

        quotes.add(new Quote("स्वास्थ का मतलब शारीरिक, मानसिक और सामजिक रूप से मजबूती है", ""));
        quotes.add(new Quote("अच्छे स्वास्थ्य में शरीर रखना एक कर्तव्य है … अन्यथा हम हमारे मन को मजबूत और साफ रखने के लिए सक्षम नहीं हो पाएंगे", "गौतम बुध"));
        quotes.add(new Quote("स्वस्थ्य सबसे बड़ा उपहार है, संतोष सबसे बड़ा धन है, वफ़ादारी सबसे बड़ा सम्बन्ध है.", "गौतम बुध"));
        quotes.add(new Quote("बिना सेहत के जीवन जीवन नहीं है; बस पीड़ा की एक स्थिति है- मौत की छवि है.", "गौतम बुध"));
    }

    public static synchronized Quotes getInstance(String language){
        if (language.equals("Hindi")) {
            if (hindiInstance == null) {
                hindiInstance = new Quotes(6, 9);
            }
            return hindiInstance;
        } else {
            if (englishInstance == null) {
                englishInstance = new Quotes(0, 5);
            }
            return englishInstance;
        }
    }

    public Quote getRandomQuote() {

        int idx = ThreadLocalRandom.current().nextInt(this.startIdx, this.endIdx);
        return quotes.get(idx);
    }
}
