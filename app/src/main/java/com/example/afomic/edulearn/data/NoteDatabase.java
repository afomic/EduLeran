package com.example.afomic.edulearn.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.afomic.edulearn.Constants;

import java.util.ArrayList;


/**
 * Created by afomic on 16-Jul-16.
 *
 */
public class NoteDatabase extends SQLiteOpenHelper {
   
   

    public NoteDatabase(Context context) {
        super(context, Constants.DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + Constants.DB_TABLE + "( ";
        sql += Constants.COURSE + " VARCHAR(10),";
        sql += Constants.SPAN + " VARCHAR,";
        sql += Constants.TOPIC + " VARCHAR(20),";
        sql += Constants.NOTE + " VARCHAR )";
        db.execSQL(sql);
        Log.w(Constants.TAG, "onCreate:is called ");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP TABLE" + Constants.DB_TABLE;
        db.execSQL(dropTable);
    }

    public void addNotes(ArrayList<Note> notes) {
        for(Note entryNote:notes){
            addNote(entryNote);
        }
    }

    public void addNote(Note note) {
        SQLiteDatabase db=null;
        try{
            db=getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            contentValues.put(Constants.COURSE,note.getCourse());
            contentValues.put(Constants.SPAN,note.getSpan());
            contentValues.put(Constants.TOPIC,note.getTopic());
            contentValues.put(Constants.NOTE,note.getNote());
            db.insert(Constants.DB_TABLE,null,contentValues);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(db!=null){
                db.close();
            }
        }
    }

    public Note getNote(String course, String topic) {
        SQLiteDatabase db=null;
        try{
            db=getReadableDatabase();
            String[] projection={Constants.NOTE,Constants.SPAN};
            String[] selection={course,topic};
            Cursor cursor=db.query(Constants.DB_TABLE,projection,Constants.COURSE+" =? AND "+Constants.TOPIC+" =?",selection,null,null,null);
            if(cursor.moveToFirst()) {
                 String note= cursor.getString(cursor.getColumnIndexOrThrow(Constants.NOTE));
                String span=cursor.getString(cursor.getColumnIndexOrThrow(Constants.SPAN));
                return new Note(note,course,topic,span);
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if(db!=null){
                db.close();
            }
        }
    }
    public void updateNote(String course,String topic,String note){
        SQLiteDatabase db=null;
        try{
            db=getWritableDatabase();
            String[] selection={course,topic};
            ContentValues contentValues=new ContentValues();
            contentValues.put(Constants.NOTE,note);
            db.update(Constants.DB_TABLE,contentValues,Constants.COURSE+" =? AND "+Constants.TOPIC+" =?",selection);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(db!=null){
                db.close();
            }
        }
    }
    public void updateSpan(String course,String topic,String span){
        SQLiteDatabase db=null;
        try{
            db=getWritableDatabase();
            String[] selection={course,topic};
            ContentValues contentValues=new ContentValues();
            contentValues.put(Constants.SPAN,span);

            db.update(Constants.DB_TABLE, contentValues, Constants.COURSE + " =? AND " + Constants.TOPIC + " =?", selection);
        }catch (Exception e){
            Log.e(Constants.TAG, " error  " + span);
            e.printStackTrace();

        }
        finally {
            if(db!=null){
                db.close();
            }
        }
    }
    public boolean isEmpty(){
        SQLiteDatabase db=null;
        try{
            db=getReadableDatabase();
            String[] projection={Constants.NOTE,Constants.SPAN};
            Cursor cursor=db.query(Constants.DB_TABLE,projection,null,null,null,null,null);
            if(cursor.moveToFirst()) {
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return true;
        }finally {
            if(db!=null){
                db.close();
            }
        }
    }

}