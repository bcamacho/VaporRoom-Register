package com.vaporroomonthegrid.register;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataAdapter implements DbDataSchema {
	
	DbHelper dbHelper;
	
	public DataAdapter(Context context) {
		dbHelper = new DbHelper(context);
	}
	
	public Cursor findById(long id) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		return db.query(TABLE, null, DbDataSchema.COLUMN_ID + " = ?", new String[] { Long.toString(id) }, null, null, null);
	}

	public Cursor queryAll() {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		return db.query(TABLE, null, null, null, null, null, DbDataSchema.COLUMN_ID + " DESC");
	}

	public long insertValues(ContentValues values) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		return db.insertWithOnConflict(TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
	}

	public int deleteById(long id) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int rows = db.delete(TABLE, DbDataSchema.COLUMN_ID + " = ?", new String[] { Long.toString(id) } );
		return rows;
	}
		
}
