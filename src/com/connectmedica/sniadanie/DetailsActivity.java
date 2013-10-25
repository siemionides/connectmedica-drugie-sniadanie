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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		aq = new AQuery(this);
		
		mImageView = (ImageView) findViewById(R.id.image);
		mNameView = (TextView) findViewById(R.id.name);
		mLocalizationView = (TextView) findViewById(R.id.localization);
		mDateView = (TextView) findViewById(R.id.date);
		
		loadData();
		
		ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
	}
	
	private void loadData() {
		Bundle b = getIntent().getExtras();
		String imageUrl = b.getString("image");
		String name = b.getString(MainActivity.KEY_RELIC_NAME);
		String place = b.getString(MainActivity.KEY_RELIC_PLACE);
		String date = b.getString(MainActivity.KEY_RELIC_FROM);
		
		aq.id(mImageView).image(imageUrl, false, false);
		mNameView.setText(name);
		mLocalizationView.setText(place);
		mDateView.setText(date);
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
