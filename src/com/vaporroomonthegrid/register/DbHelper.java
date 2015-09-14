package com.vaporroomonthegrid.register;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//  #1 Create Database

public class DbHelper extends SQLiteOpenHelper implements DbDataSchema {
    public static final String DB_NAME = "vapor_room.db";
    public static final int DB_VERSION = 1;

    public DbHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            String sql = String.format("create table %s ( %s INTEGER PRIMARY KEY,"
                            + " %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INT);", TABLE,
                            DbDataSchema.COLUMN_ID, DbDataSchema.COLUMN_NAME,
                            DbDataSchema.COLUMN_DISC, DbDataSchema.COLUMN_COST, 
                            DbDataSchema.COLUMN_RETAIL, DbDataSchema.COLUMN_QTY);
            Log.d("DbHelper", "sql: "+sql);
            db.execSQL(sql);
            
            String sql2 = String.format("create table %s ( %s INTEGER PRIMARY KEY,"
                    + " %s TEXT, %s TEXT, %s INT, %s INT, %s TEXT);", TABLE2,
                    DbDataSchema.COLUMN_ID2, DbDataSchema.COLUMN_FIRST_NAME,
                    DbDataSchema.COLUMN_LAST_NAME, DbDataSchema.COLUMN_HOME_PHONE, 
                    DbDataSchema.COLUMN_CELL_PHONE, DbDataSchema.COLUMN_EMAIL);
    Log.d("DbHelper", "sql: "+sql2);
    db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Temporary for development purposes
            db.execSQL("drop table if exists "+ TABLE);
            onCreate(db);
    }
    
   
}