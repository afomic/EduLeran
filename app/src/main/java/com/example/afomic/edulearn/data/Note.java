package com.example.afomic.edulearn.data;

/**
 * Created by afomic on 16-Jul-16.
 */
public class Note {
    String mTopic,mCourse,mNote,mSpan;
    public Note(String note,String topic,String course,String span){
        mCourse=course;
        mNote=note;
        mTopic=topic;
        mSpan=span;

    }
    public String getCourse(){
        return mCourse;
    }
    public String getNote(){
        return mNote;
    }
    public String getTopic(){
        return mTopic;
    }
    public String  getSpan(){
        return mSpan;
    }
    public void setSpan(String span){
        mSpan=span;
    }

}
