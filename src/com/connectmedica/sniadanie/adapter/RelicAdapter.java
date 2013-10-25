package com.connectmedica.sniadanie.adapter;

import java.util.ArrayList;
import java.util.List;
import com.androidquery.AQuery;
import com.connectmedica.sniadanie.R;
import com.connectmedica.sniadanie.rest.json.RelicJson;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

public class RelicAdapter extends ArrayAdapter<RelicJson> {
	
	private AQuery aq;
	private Context mContext;
	private List<RelicJson> mData, mFilterBaseData;
	private int mRowResId;
	
	private Object mLock = new Object();
	
	public RelicAdapter(Context context, int rowResId, List<RelicJson> data) {
		super(context, rowResId, data);
		mContext = context;
		mRowResId = rowResId;
		mData = mFilterBaseData = data;
		aq = new AQuery(mContext);
	}
	
	@Override
	public Filter getFilter() {
	    return new Filter() {
            
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mData = (List<RelicJson>) results.values;
                RelicAdapter.this.notifyDataSetChanged();
            }
            
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                synchronized (mLock) {
                    
                List<RelicJson> filteredList = new ArrayList<RelicJson>();
                for (RelicJson  rj : mFilterBaseData) {
                    if (rj.identification.toLowerCase().contains(constraint.toString().toLowerCase()))
                        filteredList.add(rj);
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
                }
            }
        };
	}
	
	@Override
	public int getCount() {
	    if (mData == null) return 0;
	    return mData.size();
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
