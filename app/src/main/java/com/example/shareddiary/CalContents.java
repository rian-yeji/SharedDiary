package com.example.shareddiary;

/**
 * Created by 이예지 on 2017-08-03.
 */

public class CalContents {
    String content, time, location,date;
    public  CalContents(){

    }

    public  CalContents(String date,String time,String content,String location){
        this.date = date;
        this.time = time;
        this.content = content;
        this.location = location;
    }

    public String getContent(){ return content;}
    public String getTime(){ return  time;}
    public String getLocation(){ return location;}
    public String getDate(){ return date;}
}
