package com.example.android.climbthemountain.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.climbthemountain.user_data.UserData;

/**
 * Created by Marco on 30/03/16.
 */
public class MyDBHelper extends SQLiteOpenHelper{


    // Method is called during creation of the database
    public MyDBHelper(Context context) {
        super(context, MyDBContract.DATABASE_NAME, null, MyDBContract.DATABASE_VERSION);
    }


    // Method is called during an upgrade of the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MyDBContract.FeedEntry.SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(MyDBContract.FeedEntry.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

}
