package com.example.afomic.edulearn.data;

import android.content.Context;
import android.content.SharedPreferences;

/*
 * Created by afomic on 10-Aug-16.
 */
public class PreferenceManager {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME="user_preference";
    private static final String FIRST_LUNCH="first_lunch";
    private static final String REGISTERED_USER="registered";

    public PreferenceManager(Context context){
        preferences=context.getSharedPreferences(PREF_NAME,0);
        editor=preferences.edit();

    }
    public void setFirstLunch(boolean isFirstLunch){
        editor.putBoolean(FIRST_LUNCH,isFirstLunch);
        editor.commit();
    }
    public void setRegisteredUser(boolean isRegistered){
        editor.putBoolean(REGISTERED_USER,isRegistered);
        editor.commit();
    }

    public boolean isFirstLunch(){
        return preferences.getBoolean(FIRST_LUNCH,true);
    }
    public boolean isRegisteredUser(){
        return preferences.getBoolean(REGISTERED_USER,false);
    }
}
