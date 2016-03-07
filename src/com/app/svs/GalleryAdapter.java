package com.app.svs;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GalleryAdapter extends BaseAdapter {

	private Activity activity;
	private List<String> data = new  ArrayList<String>();
	private static LayoutInflater inflater=null;

	public GalleryAdapter(Activity a, List<String> d) {
		activity = a;
		data=d;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi=convertView;
		if(convertView==null){
			vi = inflater.inflate(R.layout.gallery_row, null);
		}
		
		TextView title = (TextView)vi.findViewById(R.id.name); 
		
		String name;
		name = data.get(position);
		title.setText(name);
		return vi;
	}
}