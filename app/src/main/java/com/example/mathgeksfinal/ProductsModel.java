package com.example.mathgeksfinal;

public class ProductsModel {

    private String NAME;
    private String EMAIL_ID;
    private long TOTAL_SCORE;

    public ProductsModel(){
    }

    public ProductsModel(String NAME, String EMAIL_ID, long TOTAL_SCORE) {
        this.NAME = NAME;
        this.EMAIL_ID = EMAIL_ID;
        this.TOTAL_SCORE = TOTAL_SCORE;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getEMAIL_ID() {
        return EMAIL_ID;
    }

    public void setEMAIL_ID(String EMAIL_ID) {
        this.EMAIL_ID = EMAIL_ID;
    }

    public long getTOTAL_SCORE() {
        return TOTAL_SCORE;
    }

    public void setTOTAL_SCORE(long TOTAL_SCORE) {
        this.TOTAL_SCORE = TOTAL_SCORE;
    }
}
