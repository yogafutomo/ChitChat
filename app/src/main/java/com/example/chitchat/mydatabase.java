package com.example.chitchat;

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
        db.execSQL("CREATE TABLE profile");
    }
    public long getResult() {
        return result;
    }

    ArrayList<String> getProfile(String qry) {
    }

    boolean insertProfile(String userName, String userAge, String userGender) {

    }
}
