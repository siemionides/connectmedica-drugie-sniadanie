package com.connectmedica.sniadanie.adapter;

import com.androidquery.AQuery;
import com.connectmedica.sniadanie.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MonumentAdapter extends ArrayAdapter<String> {
	
	private AQuery aq;
	private Context mContext;
	private String[] mNames;
	private String[] mImageUrls;
	private int mRowResId;
	
	public MonumentAdapter(Context context, int rowResId, String[] names, String[] urls) {
		super(context, rowResId, names);
		mContext = context;
		mRowResId = rowResId;
		mNames = names;
		mImageUrls = urls;
		aq = new AQuery(mContext);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(mRowResId, parent, false);
        
        ImageView image = (ImageView) rowView.findViewById(R.id.image);
        TextView name = (TextView) rowView.findViewById(R.id.name);

        aq.id(image).image(mImageUrls[position]);
        name.setText(mNames[position]);
        
        return rowView;
	}

}
