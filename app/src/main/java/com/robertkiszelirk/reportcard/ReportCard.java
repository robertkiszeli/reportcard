package com.robertkiszelirk.reportcard;

import android.content.Context;

class ReportCard {

    private Context context;

    private double readingMark;
    private double languageArtsMark;
    private double basicMathMark;
    private double algebraMark;
    private double artMark;
    private double healthMark;

    ReportCard(double readingMark,
                      double languageArtsMark,
                      double basicMathMark,
                      double algebraMark,
                      double artMark,
                      double healthMark,
               Context context) {
        this.readingMark = readingMark;
        this.languageArtsMark = languageArtsMark;
        this.basicMathMark = basicMathMark;
        this.algebraMark = algebraMark;
        this.artMark = artMark;
        this.healthMark = healthMark;
        this.context = context;
    }

    double getTOTAL_SUBJECTS() {
        return 6;
    }

    double getbHigh() {
        return 90.0;
    }

    double getcHigh() {
        return 80.0;
    }

    double getdHigh() {
        return 70.0;
    }

    double getfHigh() {
        return 60.0;
    }

    double getReadingMark() {
        return readingMark;
    }

    void setReadingMark(double readingMark) {
        this.readingMark = readingMark;
    }

    double getLanguageArtsMark() {
        return languageArtsMark;
    }

    void setLanguageArtsMark(double languageArtsMark) {
        this.languageArtsMark = languageArtsMark;
    }

    double getBasicMathMark() {
        return basicMathMark;
    }

    void setBasicMathMark(double basicMathMark) {
        this.basicMathMark = basicMathMark;
    }

    double getAlgebraMark() {
        return algebraMark;
    }

    void setAlgebraMark(double algebraMark) {
        this.algebraMark = algebraMark;
    }

    double getArtMark() {
        return artMark;
    }

    void setArtMark(double artMark) {
        this.artMark = artMark;
    }

    double getHealthMark() {
        return healthMark;
    }

    void setHealthMark(double healthMark) {
        this.healthMark = healthMark;
    }

    String toString(boolean nextClass){

        String rating;

        if(nextClass){
            rating = context.getResources().getString(R.string.report_card_rating_pass);
        }else{
            rating = context.getResources().getString(R.string.report_card_rating_repeat);
        }

        return rating;
    }
}
