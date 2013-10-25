package com.connectmedica.sniadanie;

import com.connectmedica.sniadanie.rest.OtwarteZabytkiClient;
import com.connectmedica.sniadanie.rest.json.RelicJson;
import com.connectmedica.sniadanie.rest.json.RelicJsonWrapper;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private EditText mEditName;
    private EditText mEditPlace;
    private EditText mEditDateFrom;
    private EditText mEditDateTo;
    private Button   mSearchBtn;
   
    public final static String KEY_RELIC_NAME = "rName";
    public final static String KEY_RELIC_PLACE = "rPlace";
    public final static String KEY_RELIC_FROM = "rFrom";
    public final static String KEY_RELIC_TO = "rTo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditName = (EditText) findViewById(R.id.name);
        mEditPlace = (EditText) findViewById(R.id.place);
        mEditDateFrom = (EditText) findViewById(R.id.date_from);
        mEditDateTo = (EditText) findViewById(R.id.date_to);

        mSearchBtn = (Button) findViewById(R.id.search_btn);

        mSearchBtn.setOnClickListener(new View.OnClickListener() {     	 
        	
            @Override
            public void onClick(View v) {
            	
               	String relicName = mEditName.getText().toString();
            	String relicPlace = mEditPlace.getText().toString();
            	String relicDatingFrom = mEditDateFrom.getText().toString();
            	String relicDatingTo = mEditDateTo.getText().toString();
            	
            	Intent intent = new Intent(MainActivity.this, ResultListActivity.class);
       	  		
            	intent.putExtra(KEY_RELIC_NAME, relicName);
       	  		intent.putExtra(KEY_RELIC_PLACE, relicPlace);
       	  		intent.putExtra(KEY_RELIC_FROM, relicDatingFrom);
       	  		intent.putExtra(KEY_RELIC_TO, relicDatingFrom);
 
                MainActivity.this.startActivity(intent);
                 
            	
            	
               // Toast.makeText(MainActivity.this, "Tutaj b��dzie wyszukiwanie", Toast.LENGTH_SHORT).show();
            	
            	

            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_info:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Info").setMessage(getResources().getString(R.string.info)).create().show();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
