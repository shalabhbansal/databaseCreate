package com.hanks.databasecreate;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyHelper helper = new MyHelper(this);
        SQLiteDatabase database = ((MyHelper) helper).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("PRICE",275.00);
        database.update("PRODUCTS",values ,"_id = ?",new String[]{"1"});
        database.delete("PRODUCTS","_id = ?" , new String[]{"1"});
        //database.update("PRODUCTS",values , "NAME = ? AND DESCRIPTION = ?" , new String[]{"bread","britannia"});
        Cursor cursor = database.rawQuery("SELECT NAME , PRICE FROM PRODUCTS WHERE NAME = ?",new String[]{"bread"});
        if(cursor != null)
        {
            cursor.moveToFirst();
        }
        StringBuilder builder = new StringBuilder();

        do {
            String name = cursor.getString(0);
            double price = cursor.getDouble(1);
            builder.append("NAME -" + name + "PRICE - " + price);
        }
        while (cursor.moveToNext());

        TextView textView = (TextView) findViewById(R.id.Data);
        textView.setText(builder.toString());
    }

}
