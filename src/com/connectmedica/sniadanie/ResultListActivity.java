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
    }
    
    private void initListView(ArrayAdapter monumentsArrayAdapter) {
    	ListView monumentsList = (ListView) findViewById(R.id.listview);
    	monumentsList.setAdapter(monumentsArrayAdapter);
    }
}
