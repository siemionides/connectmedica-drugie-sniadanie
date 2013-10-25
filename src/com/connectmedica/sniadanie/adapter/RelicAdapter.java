package com.connectmedica.sniadanie.adapter;

import java.util.List;

import com.androidquery.AQuery;
import com.connectmedica.sniadanie.R;
import com.connectmedica.sniadanie.rest.json.RelicJson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;	
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RelicAdapter extends ArrayAdapter<RelicJson> {
	
	private AQuery aq;
	private Context mContext;
	private List<RelicJson> mData;
	private int mRowResId;
	
	public RelicAdapter(Context context, int rowResId, List<RelicJson> data) {
		super(context, rowResId, data);
		mContext = context;
		mRowResId = rowResId;
		mData = data;
		aq = new AQuery(mContext);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(mRowResId, parent, false);
        
        ImageView image = (ImageView) rowView.findViewById(R.id.image);
        TextView name = (TextView) rowView.findViewById(R.id.title);

        if (mData.get(position).photos.size() > 0)
        	aq.id(image).image("http://otwartezabytki.pl/" + mData.get(position).photos.get(0).file.mini.url).progress(R.id.progress);
        name.setText(mData.get(position).identification);
        
        return rowView;
	}

}
