package com.example.afomic.edulearn;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.util.Log;

import com.example.afomic.edulearn.data.ModelData;
import com.example.afomic.edulearn.data.Note;

import java.util.ArrayList;

/**
 * this is used for editing text
 */
public class TextWork {
    ModelData data;
    public TextWork(Context context){
        data=new ModelData(context);
    }
    public CharSequence getText(String course,String topic){
        Note note=data.getNote(course, topic);
       if(note!=null){
           Spanned courseNote=Html.fromHtml(note.getNote());
           String courseSpan=note.getSpan();
           SpannableStringBuilder builder=new SpannableStringBuilder(courseNote);
           ArrayList<Integer> spans=getSpanLocation(courseSpan);
           if(spans!=null){
               for(int i=0;i<spans.size();i++){
                   int start=spans.get(i);
                   int end=spans.get(i+1);
                   CharSequence substring=courseNote.subSequence(start, end);
                   Spannable span=new SpannableString(substring);
                   span.setSpan(new BackgroundColorSpan(Color.argb(173,251,244,94)), 0, substring.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                   builder.replace(start, end, span);
                   i++;
               }
           }
           return builder.subSequence(0,builder.length());
       }else {
           return null;
       }
    }
    public String setEditedText (String previousNote,String edit,int end,int start,String topic,String course){
        StringBuilder note=new StringBuilder(previousNote);
        int offset=getOffset(start,end,previousNote,edit);
        adjustOffset(offset,start,course,topic);
        Log.e(Constants.TAG,""+offset);
        note.delete(start,end);
        note.insert(start, edit);
        data.updateNote(topic, note.toString(), course);
        return note.toString();
    }
    public void setSpan(int start,int end,String topic ,String course) {
        String courseSpan=getSpanFromDataBase(course,topic);
        String span = ""+start+" "+end+","+courseSpan;
        data.updateSpan(course, topic, span);
    }
    public ArrayList<Integer> getSpanLocation(String location){

        if(!location.equals("")) {
            String[] spanString = location.split(",");
            ArrayList<Integer> spans=new ArrayList<>();
            for(String entry:spanString){
                String[] whereTo=entry.split(" ");
               spans.add(Integer.parseInt(whereTo[0]));
               spans.add(Integer.parseInt(whereTo[1]));
            }
            return spans;
        }
        else {
            return null;
        }

    }
    public int getOffset(int start,int end,String note,String edit){
        String originalText=note.substring(start, end);
        return edit.length()- originalText.length();
    }
    public void adjustOffset(int offset,int start,String course,String topic){
        String span=getSpanFromDataBase(course,topic);
        ArrayList<Integer> locations=getSpanLocation(span);
        if(locations!=null){
            String correctedSpan = convertToString(locations, offset, start);
            data.updateSpan(course,topic,correctedSpan);
            Log.e(Constants.TAG, convertToString(locations,offset,start));
        }

    }
    public String getSpanFromDataBase(String course,String topic){
        Note note=data.getNote(course, topic);
        return note.getSpan();
    }
   public String convertToString(int start,int end){
       return  ""+start+" "+end+",";
   }
    public String convertToString(ArrayList<Integer> list,int offset,int begin){
        String span="";
        for(int i=0;i<list.size();i++){
            int start=list.get(i);
            int end=list.get(i+1);
            if(start>begin){
                start+=offset;
                end+=offset;
            }
            span+=convertToString(start,end);
            i++;

        }
        return span;
    }

}
