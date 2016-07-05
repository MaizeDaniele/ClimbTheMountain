package com.example.android.climbthemountain.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.climbthemountain.user_data.UserData;

/**
 * Created by Marco on 31/03/16.
 * fot query method see: http://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html#query(java.lang.String, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String, java.lang.String, java.lang.String, java.lang.String)
 */
public class MyMainDB {

    public final String[] columns = {
            MyDBContract.FeedEntry.COLUMN_USERNAME,
            MyDBContract.FeedEntry.COLUMN_PASSWORD,
            MyDBContract.FeedEntry.COLUMN_NAME,
            MyDBContract.FeedEntry.COLUMN_SURNAME
    };
    Context context;
    // create an instance of DBHelper
    MyDBHelper myDBHelper = new MyDBHelper(context);


    // constructor
    public MyMainDB(Context context){
        this.context = context;
    }

    // method to add a new user into the db
    public void addUserData(UserData userData){

        // Get the database. If it does not exist, this is where it will
        // also be created.

        // Gets the data repository in write mode
        SQLiteDatabase db = myDBHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(MyDBContract.FeedEntry.COLUMN_USERNAME, userData.getUsername());
        values.put(MyDBContract.FeedEntry.COLUMN_PASSWORD, userData.getPassword());

        // add more ...

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                MyDBContract.DATABASE_NAME,
                null,
                values);

    }


    // method to delete user from the db
    public void deleteUserData(String username){

        // Gets the data repository in write mode
        SQLiteDatabase db = myDBHelper.getWritableDatabase();

        // Define 'where' part of query.
        String selection = MyDBContract.FeedEntry.COLUMN_USERNAME + " LIKE ?"; // "?" is the placeholder

        // Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(username) };

        // Issue SQL statement.
        db.delete(MyDBContract.DATABASE_NAME, selection, selectionArgs);

    }


    // method to read from the db NOTE: to check it out, it was made by myself so it have to be tested
    // problems mapping string position and value may occurs
    public String[] readUserData(String username){
        SQLiteDatabase db = myDBHelper.getReadableDatabase();
        String[] string = {};


        // we use the query method offered by SQLiteDatabase class
        // we wont to retrieve information of a specific user, to compare the password typed by the user

        // define witch columns to return
        String[] projection = columns;

        // Define 'where' part of query.
        String selection = MyDBContract.FeedEntry.COLUMN_USERNAME + " LIKE ?"; // "?" is the placeholder

        // Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(username) };


        // define a cursor to keep the returned one from the db
        Cursor cursor;

        cursor = db.query(
                MyDBContract.FeedEntry.TABLE_NAME,     // the table to query
                projection,                            // the columns to return
                selection,                             // the columns for the WHERE clause
                selectionArgs,                         // the values for the selection clause
                null,                                  // don't group the row
                null,                                  // don't filter by row groups
                null                                   // don't order the rows
        );

        // manage the cursor
        cursor.moveToFirst();


        // for more info see documentation for this cursor's method
        for (int k = 0; k <= columns.length; k ++){
            string[k] =  cursor.getString(cursor.getColumnIndex(columns[k]));
        }

        cursor.close();

        return string;

    }


}
