package com.example.medicine;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="register.db";
    public static final String TABLE_NAME="register";
    public static final String COL_1="UserName";
    public static final String COL_2="FirstName";
    public static final String COL_3="LastName";
    public static final String COL_4="Gender";
    public static final String COL_5="Email";
    public static final String COL_6="Password";
    public static final String COL_7="UserType";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +TABLE_NAME+ "(UserName,FirstName TEXT," +
                "LastName TEXT,Gender Text,Email TEXT,Password TEXT,UserType TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(db);
    }
}
