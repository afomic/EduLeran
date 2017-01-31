package com.example.afomic.edulearn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by afomic on 17-Jul-16.
 */

public class CustomAdapter extends BaseExpandableListAdapter {
    Context context;
    String[] courses;
    String[][] topics;
    String course;
    public CustomAdapter(Context context,String[] courses,String[][]topics){
        this.context=context;
        this.courses=courses;
        this.topics=topics;

    }

    @Override
    public int getGroupCount() {
        return courses.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return topics[groupPosition].length;
    }

    @Override
    public String getGroup(int groupPosition) {
        return courses[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return topics[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.course,parent,false);
        TextView textView=(TextView) convertView.findViewById(R.id.course_name);
        course=courses[groupPosition];
        textView.setText(course);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.topic,parent,false);
        TextView textView=(TextView) convertView.findViewById(R.id.topic_name);
        String course=topics[groupPosition][childPosition];
        textView.setText(course);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}