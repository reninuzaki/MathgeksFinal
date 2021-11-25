package com.example.mathgeksfinal;

public class Rank {

    private String title;
    private String description;
    private int priority;

    public Rank(){

    }

    public Rank(String title, String description, int priority){
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }


    public int getPriority() {
        return priority;
    }

}
