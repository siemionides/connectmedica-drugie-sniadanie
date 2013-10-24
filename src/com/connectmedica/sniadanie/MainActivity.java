package com.connectmedica.sniadanie;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
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

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Tutaj będzie wyszukiwanie", Toast.LENGTH_SHORT).show();

            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);

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
                builder.setTitle("Info").setMessage("Otwarte zabytki na śniadaniu z Connectmedica").create().show();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
