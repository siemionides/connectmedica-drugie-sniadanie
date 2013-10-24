package com.connectmedica.sniadanie;

import com.connectmedica.sniadanie.rest.OtwarteZabytkiClient;
import com.connectmedica.sniadanie.rest.json.RelicJson;
import com.connectmedica.sniadanie.rest.json.RelicJsonWrapper;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.AlertDialog;
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

        	String relicName = mEditName.getText().toString();
        	String relicPlace = mEditPlace.getText().toString();
        	String relicDatingFrom = mEditDateFrom.getText().toString();
        	String relicDatingTo = mEditDateTo.getText().toString();

        	
            @Override
            public void onClick(View v) {
               // Toast.makeText(MainActivity.this, "Tutaj b��dzie wyszukiwanie", Toast.LENGTH_SHORT).show();
            	
            	Callback<RelicJsonWrapper> cb = new Callback<RelicJsonWrapper>() {

					@Override
					public void failure(RetrofitError arg0) {
						Log.d("connectmedica", "failure");
						
					}

					@Override
					public void success(RelicJsonWrapper arg0, Response arg1) {
						Log.d("connectmedica", "success");
						
						Toast.makeText(getApplicationContext(), "Fetched " + arg0.relics.size() + " relics!", Toast.LENGTH_LONG).show();
						
						for (RelicJson relic : arg0.relics){
							Log.d("zabytek", relic.toString());
						}
						
						
					}
				};
				
				OtwarteZabytkiClient.getInstance().getSideEffects(relicPlace, relicName, relicDatingFrom, relicDatingTo, cb);

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
                builder.setTitle("Info").setMessage("Otwarte zabytki na ��niadaniu z Connectmedica").create().show();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
