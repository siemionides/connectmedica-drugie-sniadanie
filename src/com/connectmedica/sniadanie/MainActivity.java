package com.connectmedica.sniadanie;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

public class MainActivity extends Activity {

    private EditText mEditName;
    private EditText mEditPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // commit for dev branch
	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditName = (EditText) findViewById(R.id.name);
        mEditPlace = (EditText) findViewById(R.id.place);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
