package com.vaporroomonthegrid.register;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class DbProvider extends ContentProvider implements DbDataContract {
	static final String TAG = "DemoProvider";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
	
	DataAdapter adapter;
	UriMatcher matcher = new UriMatcher(NO_MATCH);
	
	final static int NO_MATCH = 0;
	final static int URI_ALL_PRODUCTS = 1;
	final static int URI_ONE_PRODUCT = 2;
	
	public DbProvider() {
		matcher.addURI(DbDataContract.AUTHORITY, "/" + DbDataContract.ENTITY_VAPOR_ROOM + "/#", URI_ONE_PRODUCT);
		matcher.addURI(DbDataContract.AUTHORITY, "/" + DbDataContract.ENTITY_VAPOR_ROOM, URI_ALL_PRODUCTS);
	}
	
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		long id = ContentUris.parseId(uri);
		int affected = adapter.deleteById(id);
		getContext().getContentResolver().notifyChange(uri, null);
		getContext().getContentResolver().notifyChange(DbDataContract.QUERY_ALL_PRODUCTS, null);
		return affected;
	}

	@Override
	public String getType(Uri uri) {
		// TODO: Implement this to handle requests for the MIME type of the data
		// at the given URI.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	
	int id = 1;
	
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		long id = adapter.insertValues(values);
		
		//HERE WE ARE BROADCASTING THAT A CHANGE HAS OCCURRED TO THE DATA AT content://.../product
		getContext().getContentResolver().notifyChange(uri, null);
		Log.d(TAG, uri.toString());
		return ContentUris.withAppendedId(uri, id);
	}

	@Override
	public boolean onCreate() {
		adapter = new DataAdapter(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		Log.d(TAG, "Received query for " + uri.toString());
		int queryType = matcher.match(uri);
		if (queryType == URI_ONE_PRODUCT) {
			long id = ContentUris.parseId(uri);
			Log.d(TAG, "Looks like you want record with id " + Long.toString(id));	
			return adapter.findById(id);
		} else {
			Cursor c = adapter.queryAll();
			//// >>> HERE WE LISTEN FOR CHANGES TO THE DATA BEHIND content://.../products
			c.setNotificationUri(getContext().getContentResolver(), DbDataContract.QUERY_ALL_PRODUCTS);
			return c;
		}
		//Log.d(TAG, "Selection clause looks like " + selection);
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO: Implement this to handle requests to update one or more rows.
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
