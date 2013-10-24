package com.connectmedica.sniadanie;

import com.androidquery.AQuery;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends ActionBarActivity {

	private AQuery aq;
	private ImageView mImageView;
	private TextView mNameView;
	private TextView mLocalizationView;
	private TextView mDateView;
	private TextView mDescriptionView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		aq = new AQuery(this);
		
		mImageView = (ImageView) findViewById(R.id.image);
		mNameView = (TextView) findViewById(R.id.name);
		mLocalizationView = (TextView) findViewById(R.id.localization);
		mDateView = (TextView) findViewById(R.id.date);
		mDescriptionView = (TextView) findViewById(R.id.description);
		
		//TODO: zmienić adres url zdjęcia zabytku.
		loadImage("adres url do obrazka");
		
		ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
	}
	
	private void loadImage(String url) {
		aq.id(mImageView).image(url, false, false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
