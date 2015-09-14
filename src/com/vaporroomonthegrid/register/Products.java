package com.vaporroomonthegrid.register;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.res.Configuration;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Products extends Activity implements DbDataContract {
	public static final String TAG = "MainActivity";
	
	ProductListFragment frag;
	String recordCount;
	int productId;
	
	// Start onCreate method
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// We need to control the layout file depending on screen orientation
		if (this.getResources()
				.getConfiguration()
				.orientation == Configuration
				.ORIENTATION_PORTRAIT) 
			{
				setContentView(R.layout.activity_main);
			}
		else
			{
				setContentView(R.layout.activity_main_landscape);
			}
		// --  End  --  // 
		
		// Establish Fragment Management
		if (savedInstanceState == null) {
			FragmentManager fm = getFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			frag = new ProductListFragment();
			ft.add(R.id.container, frag);
			ft.commit();
		}
		// --  End  --  // 
		
		// We need to obtain a current rows of database to populate productId
		ContentResolver res = getContentResolver();
		Cursor c = res.query(QUERY_ALL_PRODUCTS, null, null, null, null);
		Log.d(TAG, "Found " + Integer.toString(c.getCount()) + " items");
		productId = c.getCount();
		// End
	}
	
	// -- End onCreate()  //
	
	
	// Menu 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	// --  End  --  // 
	
	public void tryQuery(View v) {
		ContentResolver res = getContentResolver();
		Cursor c = res.query(QUERY_ALL_PRODUCTS, null, null, null, null);
		Log.d(TAG, "Found " + Integer.toString(c.getCount()) + " items");
	}
	
	// --  End  --  // 
	
	int id = 0;
	public void insertItem(View v) {
		productId = productId +1;
		ContentValues val = new ContentValues();
		val.put(FIELD_NAME, 
				((EditText)findViewById(R.id.et_product_name)).getText().toString()) ;
		val.put(FIELD_DISC, 
				((EditText)findViewById(R.id.et_product_disc)).getText().toString() + ". \n Product ID = " + productId);
		val.put(FIELD_COST, 
				((EditText)findViewById(R.id.et_product_cost)).getText().toString());
		val.put(FIELD_RETAIL, 
				((EditText)findViewById(R.id.et_product_retail)).getText().toString());
		val.put(FIELD_QTY, 
				((EditText)findViewById(R.id.et_product_qty)).getText().toString());
		
		Uri newUri = getContentResolver().insert(INSERT_INTO_PRODUCT, val);
		Log.d(TAG, "Inserted item at " + newUri.toString());
		
		// reset fields
		((EditText)findViewById(R.id.et_product_name)).setText(null) ;
		((EditText)findViewById(R.id.et_product_disc)).setText(null) ;
		((EditText)findViewById(R.id.et_product_cost)).setText(null) ;
		((EditText)findViewById(R.id.et_product_retail)).setText(null) ;
		((EditText)findViewById(R.id.et_product_qty)).setText(null) ;

	}
	
	// --  End  --  // 

	//  Delete Method
	public void del(View v) {
		long id = Long.parseLong(((EditText)findViewById(R.id.et_product_name)).getText().toString());
		//int affected  = getContentResolver().delete(ContentUris.withAppendedId(QUERY_ONE_PRODUCT, id), null, null);
		getContentResolver().delete(ContentUris.withAppendedId(QUERY_ONE_PRODUCT, id), null, null);
		((EditText)findViewById(R.id.et_product_name)).setText(null) ;
		tryQuery(v);
		}

	// --  End  --  // 
	
}
