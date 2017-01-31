package com.example.afomic.edulearn;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Created by afomic on 16-Jul-16.
 */
public class EditFragment extends DialogFragment {
    EditText editText;
    String note,topic ,course;
    String selectedText;
    int start, end;
    TextWork work;
    public static EditFragment getInstance(String note,String topic ,String course,int start,int end){
        EditFragment fragment =new EditFragment();
        Bundle bundle=new Bundle();
        bundle.putInt(Constants.END, end);
        bundle.putInt(Constants.START,start);
        bundle.putString(Constants.TOPIC, topic);
        bundle.putString(Constants.COURSE,course);
        bundle.putString(Constants.NOTE,note);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle bundle= getArguments();
        if(bundle!=null){
            topic=bundle.getString(Constants.TOPIC);
            note=bundle.getString(Constants.NOTE);
            course=bundle.getString(Constants.COURSE);
            end=bundle.getInt(Constants.END);
            start=bundle.getInt(Constants.START);


        }
        work=new TextWork(getActivity());
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view= getActivity().getLayoutInflater().inflate(R.layout.edit,null);
       editText=(EditText)view.findViewById(R.id.edit_note);
        Log.e(Constants.TAG, start+" "+end);
        selectedText=note.substring(start,end);
        editText.append(selectedText);
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setView(view);
        builder.setTitle("Edit");
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                work.setEditedText(note,editText.getText().toString(),end,start,topic,course);
                NoteFragment fragment=NoteFragment.getInstance(topic,course);
                getFragmentManager().beginTransaction().replace(R.id.viewer_container,fragment).commit();

            }
        });
        builder.setNegativeButton(android.R.string.cancel,null);
        return builder.create();
    }
}
