package com.hanks.databasecreate;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyHelper extends SQLiteOpenHelper {
    private static final String dbname = "mydb";
    private static final int version = 2 ;
    public MyHelper(Context context)
    {
        super(context , dbname , null , version);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql = "CREATE TABLE PRODUCTS (_id INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT , DESCRIPTION TEXT , PRICE REAL)";
        db.execSQL(sql);
        //insert data
        insertData("Jam","fruit",200.00,db);
        insertData("bread","britannia",20.00,db);
        insertData("eggs","whiteeggs",7.00,db);
        insertData("milk","Amul",53.00,db);
    }

    private void insertData(String name , String description , double price , SQLiteDatabase database)
    {
        ContentValues values = new ContentValues();
        values.put("NAME",name);
        values.put("DESCRIPTION",description);
        values.put("PRICE",price);
        database.insert("PRODUCTS",null , values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
         //when there is a difference between the versions of the app then onUpgrade method is called
        Log.d("my code","on upgrade is called");
    }
}

