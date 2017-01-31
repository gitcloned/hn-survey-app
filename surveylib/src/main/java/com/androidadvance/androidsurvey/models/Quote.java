package com.androidadvance.androidsurvey.models;

/**
 * Created by amit on 31/1/17.
 */

public class Quote {

    String quote;
    String author;

    public Quote(String quote, String author) {
        this.quote = quote;
        this.author = author;
    }

    public String getQuote() {
        return quote;
    }

    public String getAuthor() {
        return author;
    }
}
