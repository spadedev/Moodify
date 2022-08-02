package com.example.finalactivity;

public class firebasemodel {

    String title;
    String content;
    String date;

    public firebasemodel(){

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public firebasemodel(String title,String content,String date,int isImportant){
        this.title=title;
        this.content=content;
        this.date=date;

    }
}
