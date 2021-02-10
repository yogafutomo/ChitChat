package com.example.chitchat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class mydatabase extends SQLiteOpenHelper {

    private long result;
    private static final String DBname = "data.db";

    mydatabase(Context context) {
        super(context, DBname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        profile(db);
        user(db);
        chat(db);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public void profile(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS profile");
        db.execSQL("CREATE TABLE profile (id INTEGER PRIMARY KEY AUTOINCREMENT, userName TEXT, userAGe TEXT, userGender TEXT)");
    }

    private void user(SQLiteDatabase db){

    }

    private void chat(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS chat");
        db.execSQL("CREATE TABLE chat(id INTEGER PRIMARY KEY AUTOINCREMENT, msgId TEXT UNIQUE, userName TEXT, userId TEXT, msg TEXT, userGender TEXT, groupNumber TEXT)");
    }

    boolean insertProfile(String userName, String userAge, String userGender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("userName", userName);
        cv.put("userAge", userAge);
        cv.put("userGender", userGender);
        result = db.insert("profile",null,cv);
        return result != -1;
    }



    ArrayList<String> getProfile(String qry) {
        ArrayList<String> profile = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(qry, null);
        if (res.getCount() > 0){
            res.moveToFirst();
            while (!res.isAfterLast()){
                profile.add(res.getString(1));
                profile.add(res.getString(2));
                profile.add(res.getString(3));
                res.moveToNext();
            }
            res.close();
            return profile;
        }else
            profile.add("");
        res.close();
        return profile;
    }

    public long getResult() {
        return result;
    }

    ArrayList<String> getProfileChat(String qry) {
        ArrayList<String> profile = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(qry, null);
        if (res.getCount() > 0 ){
            res.moveToFirst();
            while (!res.isAfterLast()){
                profile.add(res.getString(0));
                profile.add(res.getString(1));
            }
            res.close();
            return profile;
        }else
            profile.add("");
        res.close();
        return profile;
    }

    String getString(String s) {
    }
}
