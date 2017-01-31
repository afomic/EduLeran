package com.example.afomic.edulearn;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.text.SpannedString;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.afomic.edulearn.data.ModelData;

/**
 * Created by michael on 15-Jul-16.
 *
 */
public class NoteFragment extends Fragment {
    TextView mNote;
    TextWork work;
    ScrollView mScroll;
    String course;
    String topic;
    ModelData data;


    public static NoteFragment getInstance(String topic,String course){
        Bundle bundle=new Bundle();
        bundle.putString(Constants.TOPIC, topic);
        bundle.putString(Constants.COURSE, course);
        NoteFragment fragment=new NoteFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        work=new TextWork(getActivity());
        Bundle bundle=getArguments();
        data=new ModelData(getActivity());
        if(bundle!=null){
            topic=bundle.getString(Constants.TOPIC);
            course=bundle.getString(Constants.COURSE);

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.note, container, false);
        mNote=(TextView) view.findViewById(R.id.note);
        mScroll=(ScrollView) view.findViewById(R.id.scroll);
        mNote.setTextIsSelectable(true);
        mNote.setCustomSelectionActionModeCallback(new Action());
        mNote.setText(work.getText(course, topic));

        return view;
    }
    public class Action implements ActionMode.Callback{
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenu().clear();
            mode.setTitle("");
            mode.setCustomView(null);
            mode.getMenuInflater().inflate(R.menu.text_context_menu, menu);
          return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.highlight:
                    highlightText();
                    mode.finish();
                    return true;
                case R.id.edit:
                   startEditFragment();
                    return true;
                default:
                    return false;
            }

        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
        }
    }
    public void scrollTo(final int start){
        new Handler().post(new Runnable() {
            @Override
            public void run() {

                mScroll.smoothScrollBy(0, start);
            }
        });
    }
    public void startEditFragment(){
        CharSequence text=mNote.getText();
        Spanned span=new SpannedString(text);
        String spanNote= Html.toHtml(span);
        int start=mNote.getSelectionStart();
        int end=mNote.getSelectionEnd();
        int offsetStart=getStart(start,end,spanNote);
        int offsetEnd=offsetStart+(end-start);
        EditFragment editFragment=EditFragment.getInstance(spanNote,topic,course,offsetStart,offsetEnd);
        editFragment.show(getFragmentManager(), null);
    }
    public void highlightText(){
        work.setSpan(mNote.getSelectionStart(), mNote.getSelectionEnd(), topic, course);
        mNote.setText(work.getText(course, topic));
        scrollTo(mNote.getSelectionStart());
    }
    public int getStart(int start,int end,String span){
        String text=mNote.getText().toString().substring(start,end);
        start=span.indexOf(text);
        return start;
    }

}
