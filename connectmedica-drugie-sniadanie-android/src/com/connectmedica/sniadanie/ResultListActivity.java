package com.connectmedica.sniadanie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.connectmedica.sniadanie.adapter.RelicAdapter;
import com.connectmedica.sniadanie.rest.OtwarteZabytkiClient;
import com.connectmedica.sniadanie.rest.json.PhotoJson;
import com.connectmedica.sniadanie.rest.json.RelicJson;
import com.connectmedica.sniadanie.rest.json.RelicJsonWrapper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnCloseListener;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ResultListActivity extends ActionBarActivity implements OnQueryTextListener, OnCloseListener {

    private RelicAdapter mAdapter;
    private SearchView   mSearchView;

	private ProgressBar progressBar;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);

        String[] fakeMonumentsList = new String[] { "Zabytek 1", "Zabytek 2", "Zabytek 3", "Zabytek 4", "Zabytek 5",
                "Zabytek 6", "Zabytek 7", "Zabytek 8",

        };
        
        progressBar = (ProgressBar) findViewById(R.id.progress);

        Bundle b = getIntent().getExtras();
        if (b != null)
            performApiQuery(b);

    }

    private void performApiQuery(Bundle b) {

        String relicName = b.getString(MainActivity.KEY_RELIC_NAME);
        String relicPlace = b.getString(MainActivity.KEY_RELIC_PLACE);
        String relicFrom = b.getString(MainActivity.KEY_RELIC_FROM);
        String relicTo = b.getString(MainActivity.KEY_RELIC_TO);

        Callback<RelicJsonWrapper> cb = new Callback<RelicJsonWrapper>() {

            @Override
			public void failure(RetrofitError arg0) {
				Log.d("connectmedica", "failure");
				progressBar.setVisibility(View.GONE);
			}

			@Override
			public void success(RelicJsonWrapper arg0, Response arg1) {
				Log.d("connectmedica", "success");
				progressBar.setVisibility(View.GONE);
				
				Toast.makeText(getApplicationContext(), "Fetched " + arg0.relics.size() + " relics!", Toast.LENGTH_LONG).show();
				
				ArrayList<String> relicNames = new ArrayList<String>();
				for (RelicJson relic : arg0.relics){
//					Log.d("zabytek", relic.toString());
					relicNames.add( relic.identification);
					for (PhotoJson photo : relic.photos){
						if (photo.file.maxi != null) Log.d("photo maxi ", photo.file.maxi.url);
						if (photo.file.mini != null) Log.d("photo mini", photo.file.mini.url);
						// dodaj do tego urla host http://otwartezabytki.pl/
//						/system/uploads/photo/file/1010/icon_IMG_0627.JPG

					}

				}
				
				
		        /* STARY ADAPTER  
		        ArrayAdapter<String> adapter = new ArrayAdapter<String>( ResultListActivity.this,
		        		android.R.layout.simple_list_item_1, relicNames );
		        initListView(adapter);
				*/
				
				//RelicJson[] DATA = new RelicJson[]{};
				//RelicJson[] data = arg0.relics.toArray(DATA);
		        
				// NOWY ADAPTER
				RelicAdapter adapter = mAdapter = new RelicAdapter(ResultListActivity.this, 
						R.layout.list_row, arg0.relics);
				initNewListView(adapter, arg0.relics);
				
			}
		};
		
		OtwarteZabytkiClient.getInstance().getSideEffects(relicPlace, relicName, relicFrom, relicTo, cb);
    }

    private void initListView(ArrayAdapter monumentsArrayAdapter) {
        ListView monumentsList = (ListView) findViewById(R.id.listview);
        monumentsList.setAdapter(monumentsArrayAdapter);
    }
    
    private void initNewListView(RelicAdapter relicAdapter, final List<RelicJson> data) {
    	ListView relicsList = (ListView) findViewById(R.id.listview);
    	relicsList.setAdapter(relicAdapter);
    	relicsList.setOnItemClickListener(new OnItemClickListener() {

    	    @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) {
                Intent i = new Intent(ResultListActivity.this, DetailsActivity.class);
                i.putExtra("image", "http://otwartezabytki.pl/" + data.get(position).photos.get(0).file.maxi.url);
                i.putExtra(MainActivity.KEY_RELIC_NAME, data.get(position).identification);
                i.putExtra(MainActivity.KEY_RELIC_PLACE, data.get(position).place_name);
                i.putExtra(MainActivity.KEY_RELIC_FROM, data.get(position).dating_of_obj);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.result_list, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setOnCloseListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextChange(String query) {
        if (mAdapter != null) {
            mAdapter.getFilter().filter(query);
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String arg0) {
        return true;
    }

    @Override
    public boolean onClose() {
        mAdapter.getFilter().filter("");
        return true;
    }
}
