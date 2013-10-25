package com.connectmedica.sniadanie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.connectmedica.sniadanie.adapter.RelicAdapter;
import com.connectmedica.sniadanie.rest.OtwarteZabytkiClient;
import com.connectmedica.sniadanie.rest.json.PhotoJson;
import com.connectmedica.sniadanie.rest.json.RelicJson;
import com.connectmedica.sniadanie.rest.json.RelicJsonWrapper;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ResultListActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);
        
        String[] fakeMonumentsList = new String[] {
        		"Zabytek 1",
        		"Zabytek 2",
        		"Zabytek 3",
        		"Zabytek 4",
        		"Zabytek 5",
        		"Zabytek 6",
        		"Zabytek 7",
        		"Zabytek 8",

        };
        
        Bundle b = getIntent().getExtras();
     
        performApiQuery (b);
        

        

        
        
    }
    
    private void performApiQuery( Bundle b){
    	
        String relicName = b.getString(MainActivity.KEY_RELIC_NAME);
        String relicPlace = b.getString(MainActivity.KEY_RELIC_PLACE);
        String relicFrom = b.getString(MainActivity.KEY_RELIC_FROM);
        String relicTo = b.getString(MainActivity.KEY_RELIC_TO);
        
        
    	Callback<RelicJsonWrapper> cb = new Callback<RelicJsonWrapper>() {

			@Override
			public void failure(RetrofitError arg0) {
				Log.d("connectmedica", "failure");
				
			}

			@Override
			public void success(RelicJsonWrapper arg0, Response arg1) {
				Log.d("connectmedica", "success");
				
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
				//public static final Foo[] FOO = new Foo[]{};

				//Foo[] foos = fooCollection.toArray(FOO);
				
				RelicJson[] DATA = new RelicJson[]{};
				RelicJson[] data = arg0.relics.toArray(DATA);
		        
				// NOWY ADAPTER
				RelicAdapter adapter = new RelicAdapter(ResultListActivity.this, 
						R.layout.list_row, data);
				initNewListView(adapter);
				
				
			}
		};
		
		OtwarteZabytkiClient.getInstance().getSideEffects(relicPlace, relicName, relicFrom, relicTo, cb);
    }
    
    private void initListView(ArrayAdapter monumentsArrayAdapter) {
    	ListView monumentsList = (ListView) findViewById(R.id.listview);
    	monumentsList.setAdapter(monumentsArrayAdapter);
    }
    
    private void initNewListView(RelicAdapter relicAdapter) {
    	ListView monumentsList = (ListView) findViewById(R.id.listview);
    	monumentsList.setAdapter(relicAdapter);
    }
}
