package com.vaporroomonthegrid.register;

import android.net.Uri;
import android.provider.BaseColumns;

//  #3  Create data contract

public interface DbDataContract extends BaseColumns {

	// COnfigure vendor types
	public static final String AUTHORITY = "com.vaporroomonthegrid.register";
	public static final String SINGLE_RECORD_MIME_TYPE = "vnd.android.cursor.item/vnd.vaporroomonthegrid.product";
	public static final String MULTIPLE_RECORDS_MIME_TYPE = "vnd.android.cursor.dir/vnd.vaporroomonthegrid.product";
	public static final String ENTITY_VAPOR_ROOM = "vapor_room";
	
	// Configure our Products fields from data schema
	public static final String FIELD_NAME = "name";
	public static final String FIELD_DISC = "disc";
	public static final String FIELD_COST = "cost";
	public static final String FIELD_RETAIL = "retail";
	public static final String FIELD_QTY = "qty";
	public static final String FIELD_ID = BaseColumns._ID;
	
	// Configure our Customer fields from data schema
	public static final String FIELD_FIRST_NAME = "first_name";
	public static final String FIELD_LAST_NAME = "last_name";
	public static final String FIELD_HOME_PHONE = "home_phone";
	public static final String FIELD_CELL_PHONE = "cell_phone";
	public static final String FIELD_EMAIL = "email";
	public static final String FIELD_ID2 = BaseColumns._ID;
	
	// Configure URI's
	public static final Uri QUERY_ONE_PRODUCT = Uri.parse("content://" + AUTHORITY + "/" + ENTITY_VAPOR_ROOM);
	public static final Uri INSERT_INTO_PRODUCT = QUERY_ONE_PRODUCT;
	public static final Uri QUERY_ALL_PRODUCTS = QUERY_ONE_PRODUCT;
}