package com.example.afomic.edulearn;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by afomic on 15-Jul-16.
 */
public class LoginFragment extends Fragment {
    Button loginButton;
    TextView forgotPassword,register;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.login, container, false);
        forgotPassword=(TextView) view.findViewById(R.id.forget_password);
        register=(TextView) view.findViewById(R.id.register);
        setGotoSpan(forgotPassword,ForgetPassword.class);
        setGotoSpan(register,ForgetPassword.class);
        loginButton=(Button) view.findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(),NoteViewer.class);
                startActivity(intent);
            }
        });

        return view;
    }
    public void setGotoSpan(TextView view,Class whereTo){
        String text=view.getText().toString();
        SpannableString spannableString=new SpannableString(text);
        spannableString.setSpan(new GotoPlaces(whereTo), 0, text.length(), 0);
        view.setMovementMethod(new LinkMovementMethod());
        view.setText(spannableString);
    }
    public class LoginTask extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

}
