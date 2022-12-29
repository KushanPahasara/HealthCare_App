package com.example.medicine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelperAppointment extends SQLiteOpenHelper {
    public DBhelperAppointment(@Nullable Context context) {
        super(context, "appoin.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table AppointmentInfo(patientName TEXT primary key, patientID TEXT, contact TEXT, date TEXT, time TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists AppointmentInfo");

    }


    public Boolean insertAppointment(String patientName,String patientID, String contact, String date,String time){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("patientname",patientName);
        contentValues.put("patientID",patientID);
        contentValues.put("contact",contact);
        contentValues.put("date", date);
        contentValues.put("time", time);

        long answer = DB.insert("AppointmentInfo",null,contentValues);
        if(answer == -1){
            return false;
        }else{
            return true;
        }




    }




    public Boolean updateAppointment(String patientName,String patientID, String contact, String date,String time){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("patientname",patientName);
        contentValues.put("patientID",patientID);
        contentValues.put("contact",contact);
        contentValues.put("date", date);
        contentValues.put("time", time);

        Cursor cursor = DB.rawQuery("Select * from AppointmentInfo where patientName =?", new String[]{patientName});



        if(cursor.getCount()>0) {

            long answer = DB.update("AppointmentInfo", contentValues, "patientName=?", new String[]{patientName});

            if (answer == -1) {
                return false;
            } else {
                return true;
            }

        }else{
            return false;
        }



    }



    public Boolean deleteAppointment(String patientName){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();



        Cursor cursor = DB.rawQuery("Select * from AppointmentInfo where patientName =?", new String[]{patientName});



        if(cursor.getCount()>0) {

            long answer = DB.delete("AppointmentInfo", "patientName=?", new String[]{patientName});

            if (answer == -1) {
                return false;
            } else {
                return true;
            }

        }else{
            return false;
        }



    }



    public  Cursor ViewdataAppointment(){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor =DB.rawQuery("Select * from AppointmentInfo",null);
        return cursor;

    }




}

