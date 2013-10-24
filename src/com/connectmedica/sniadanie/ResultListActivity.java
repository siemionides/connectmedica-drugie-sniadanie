package com.connectmedica.sniadanie;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, fakeMonumentsList );
        initListView(adapter);
        
        
    }
    
    private void initListView(ArrayAdapter monumentsArrayAdapter) {
    	ListView monumentsList = (ListView) findViewById(R.id.listview);
    	monumentsList.setAdapter(monumentsArrayAdapter);
    }
}
