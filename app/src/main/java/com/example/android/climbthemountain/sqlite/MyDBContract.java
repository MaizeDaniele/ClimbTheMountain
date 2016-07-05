package com.example.android.climbthemountain.sqlite;

import android.provider.BaseColumns;

/**
 * Created by Marco on 30/03/16.
 * A contract class is a container for constants that define names for URIs, tables, and columns.
 * The contract class allows you to use the same constants across all the other classes in the same package.
 * This lets you change a column name in one place and have it propagate throughout your code.
 */


public final class MyDBContract {




    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "AccountsData.db";


    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";


    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public MyDBContract() {}


    /* Inner class that defines the table contents */
    public static abstract class FeedEntry implements BaseColumns {

        public static final String TABLE_NAME = "accounts";

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SURNAME = "surname";

        // add more ...


        // create table
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                        FeedEntry.COLUMN_ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                        FeedEntry.COLUMN_USERNAME + TEXT_TYPE + "UNIQUE" + COMMA_SEP +
                        FeedEntry.COLUMN_PASSWORD + TEXT_TYPE + COMMA_SEP +
                        FeedEntry.COLUMN_SURNAME + TEXT_TYPE + COMMA_SEP +
                        FeedEntry.COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                        " )";


        // delete table
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;


    }
}
