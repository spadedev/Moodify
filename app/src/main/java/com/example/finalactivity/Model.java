package com.example.finalactivity;

public class Model {
    String description;
    String time;

    public Model(){

    }

    public Model(String description, String time){
        this.description = description;
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
