package com.vaporroomonthegrid.register;

import android.provider.BaseColumns;

// #2 Create database schema

interface DbDataSchema {
	
	
	// Configuration of Products Database schema 
	public static final String TABLE = "products";	
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_DISC = "disc";
	public static final String COLUMN_COST = "cost";
	public static final String COLUMN_RETAIL = "retail";
	public static final String COLUMN_QTY = "qty";
	public static final String COLUMN_ID = BaseColumns._ID;
	
	// Configuration of Customers Database schema 
	public static final String TABLE2 = "customers";	
	public static final String COLUMN_FIRST_NAME = "first_name";
	public static final String COLUMN_LAST_NAME = "last_name";
	public static final String COLUMN_HOME_PHONE = "home_phone";
	public static final String COLUMN_CELL_PHONE = "cell_phone";
	public static final String COLUMN_EMAIL = "email";
	public static final String COLUMN_ID2 = BaseColumns._ID;
}
