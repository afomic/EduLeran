package com.example.afomic.edulearn;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import com.example.afomic.edulearn.data.ModelData;

public class NoteViewer extends AppCompatActivity {
    FragmentManager fm;
    DrawerLayout layout;
    Fragment container;
    TextView mCourseTopic;
    ExpandableListView listView;
    String topic,course;

    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_viewer);
        mCourseTopic=(TextView) findViewById(R.id.topic_name);

        final String courses[]={"chemistry","physics","biology"};
        final String [][] topics={{"Chemical Reaction","Measurement","Fire"},{"Measurement","Fire","water","Light"},{"reproduction","sex","water"}};

        listView=(ExpandableListView) findViewById(R.id.topic_list);
        CustomAdapter adapter=new CustomAdapter(this,courses,topics);
        listView.setAdapter(adapter);
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                layout.closeDrawers();
                topic = topics[groupPosition][childPosition];
                course=courses[groupPosition];
                mCourseTopic.setText(topic);
                createFragment(course, topic);
                return true;
            }
        });

        fm=getSupportFragmentManager();
        displayNote(savedInstanceState);

        layout=(DrawerLayout) findViewById(R.id.layout);

    }
    public void displayNote(Bundle savedInstanceState) {
        if(savedInstanceState!=null){
            topic=savedInstanceState.getString(Constants.PRESENT_TOPIC);
            course=savedInstanceState.getString(Constants.PRESENT_COURSE);
            createFragment(course,topic);
        }
        else {
            createFragment("chemistry",getString(R.string.topic_measurement));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(Constants.PRESENT_COURSE,course);
        outState.putString(Constants.PRESENT_TOPIC,topic);
        super.onSaveInstanceState(outState);

    }

    public void createFragment(String course,String topic){
        container=fm.findFragmentById(R.id.viewer_container);
        if(container==null){
            NoteFragment fragment1=NoteFragment.getInstance(topic,course);
            fm.beginTransaction().add(R.id.viewer_container,fragment1).commit();
            Log.w(Constants.TAG, "createFragment: add is called");
        }else {
            Log.w(Constants.TAG, "createFragment: replace is called" );
            NoteFragment fragment1=NoteFragment.getInstance(topic,course);
            fm.beginTransaction().replace(R.id.viewer_container,fragment1).commit();
        }
    }

}
