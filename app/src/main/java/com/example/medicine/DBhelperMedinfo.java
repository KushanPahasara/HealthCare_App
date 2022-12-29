package com.example.medicine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelperMedinfo  extends SQLiteOpenHelper {
    public DBhelperMedinfo(@Nullable Context context) {
        super(context, "medidata.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table MedicineInfo(medicineName TEXT primary key, medicineQuntity TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists MedicineInfo");

    }


    public Boolean insertmedicine(String medicineName,String medicineQuntity){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("medicinename",medicineName);
        contentValues.put("medicinequntity",medicineQuntity);

        long answer = DB.insert("MedicineInfo",null,contentValues);
        if(answer == -1){
            return false;
        }else{
            return true;
        }




    }



    public Boolean updatemedicine(String medicineName,String medicineQuntity){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("medicinename",medicineName);
        contentValues.put("medicinequntity",medicineQuntity);

        Cursor cursor = DB.rawQuery("Select * from MedicineInfo where medicineName =?", new String[]{medicineName});



        if(cursor.getCount()>0) {

            long answer = DB.update("medicineInfo", contentValues, "medicineName=?", new String[]{medicineName});

            if (answer == -1) {
                return false;
            } else {
                return true;
            }

        }else{
            return false;
        }



    }


    public Boolean deletemedicine(String medicineName){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();



        Cursor cursor = DB.rawQuery("Select * from MedicineInfo where medicineName =?", new String[]{medicineName});



        if(cursor.getCount()>0) {

            long answer = DB.delete("medicineInfo", "medicineName=?", new String[]{medicineName});

            if (answer == -1) {
                return false;
            } else {
                return true;
            }

        }else{
            return false;
        }



    }



    public  Cursor ViewdataMedicine(){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor =DB.rawQuery("Select * from medicineInfo",null);
        return cursor;

    }



}
