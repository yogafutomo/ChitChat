package com.example.chitchat;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class mydatabase extends SQLiteOpenHelper {

    private long result;
    private static final String DBname = "data.db";

    mydatabase(Context context) {
        super(context, DBname, null, 1);
    }

    public void onCreate(SQLiteDatabase db){

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public void profile(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS profile");
        db.execSQL("CREATE TABLE profile (id INTEGER PRIMARY KEY AUTOINCREMENT, userName TEXT, userAGe TEXT, userGender TEXT)");
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

    public long getResult() {
        return result;
    }

    ArrayList<String> getProfile(String qry) {
    }


}
