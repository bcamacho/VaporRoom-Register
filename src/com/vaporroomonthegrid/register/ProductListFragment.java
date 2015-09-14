package com.vaporroomonthegrid.register;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

public class ProductListFragment extends ListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View v = inflater.inflate(R.layout.demo_list_fragment, container, false);		
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(), R.layout.list_item, 
				null, new String[] { DbDataContract.FIELD_NAME, DbDataContract.FIELD_DISC, DbDataContract.FIELD_COST, DbDataContract.FIELD_RETAIL, DbDataContract.FIELD_QTY },
				new int[] { R.id.name, R.id.disc, R.id.cost, R.id.retail, R.id.qty }, SimpleCursorAdapter.NO_SELECTION);		
		setListAdapter(adapter);
		
		LoaderManager loaderManager = getLoaderManager();
		loaderManager.initLoader(42, null, new LoaderManager.LoaderCallbacks<Cursor>() {

			@Override
			public Loader<Cursor> onCreateLoader(int id, Bundle args) {
				Log.d("FRAG", DbDataContract.QUERY_ALL_PRODUCTS.toString());
				CursorLoader loader = new CursorLoader(getActivity(), DbDataContract.QUERY_ALL_PRODUCTS, null, null, null, null);
				return loader;
			}

			@Override
			public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
				((SimpleCursorAdapter)getListAdapter()).swapCursor(data);				
			}

			@Override
			public void onLoaderReset(Loader<Cursor> loader) {
				((SimpleCursorAdapter)getListAdapter()).swapCursor(null);				
			}
			
		});
		
		
	}

}
