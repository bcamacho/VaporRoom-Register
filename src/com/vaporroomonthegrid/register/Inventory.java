package com.vaporroomonthegrid.register;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;


public class Inventory extends Activity {
	public static final String TAG = "Inventory Class";
	
	
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
				setContentView(R.layout.start);
			}
		else
			{
				setContentView(R.layout.start);
			}
		// --  End  --  // 
		
	
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
	
	public void register(View v) {
		Intent i = new Intent(Inventory.this, Register.class);
		startActivity(i);
	}
	
	public void customers(View v) {
		Intent i = new Intent(Inventory.this, Customers.class);
		startActivity(i);
	}
	
	public void inventory(View v) {

		Intent i = new Intent(Inventory.this, Products.class);
		startActivity(i);
		//setContentView(R.layout.activity_main);
		
	}

	// --  End  --  // 
	
}
