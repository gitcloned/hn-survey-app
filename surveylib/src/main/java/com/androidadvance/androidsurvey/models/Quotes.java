package com.androidadvance.androidsurvey.models;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by amit on 31/1/17.
 */

public class Quotes {

    private static Quotes instance;
    private ArrayList<Quote> quotes;

    private Quotes() {

        quotes = new ArrayList<Quote>();

        quotes.add(new Quote("To enjoy good health, to bring true happiness to one's family, to bring peace to all, one must first discipline and control one's own mind. If a man can control his mind he can find the way to Enlightenment, and all wisdom and virtue will naturally come to him.", "Buddha"));
        quotes.add(new Quote("Calm mind brings inner strength and self-confidence, so that's very important for good health.", "Dalai Lama"));
        quotes.add(new Quote("To keep the body in good health is a duty... otherwise we shall not be able to keep our mind strong and clear.", "Buddha"));
        quotes.add(new Quote("There's nothing more important than our good health - that's our principal capital asset. ","Arlen Specter"));
        quotes.add(new Quote("Good health is not something we can buy. However, it can be an extremely valuable savings account.","Anne Wilson Schaef"));
        quotes.add(new Quote("Good health and good sense are two of life's greatest blessings. ","Publilius Syrus"));
    }

    public static synchronized Quotes getInstance(){
        if(instance==null){
            instance=new Quotes();
        }
        return instance;
    }

    public Quote getRandomQuote() {

        int idx = ThreadLocalRandom.current().nextInt(0, quotes.size() - 1);
        return quotes.get(idx);
    }
}
