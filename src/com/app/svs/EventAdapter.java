package com.app.svs;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EventAdapter extends BaseAdapter {

	private Activity activity;
	private ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
	private static LayoutInflater inflater=null;

	public EventAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
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
			vi = inflater.inflate(R.layout.event_list_row, null);
		}

		TextView event_title = (TextView)vi.findViewById(R.id.event_title); 
		TextView event_date = (TextView)vi.findViewById(R.id.event_date); 

		HashMap<String, String> event = new HashMap<String, String>();
		event = data.get(position);
		
		event_title.setText(event.get(EventActivity.KEY_EVENT_TITLE));
		event_date.setText(event.get(EventActivity.KEY_EVENT_DATE));
		return vi;
	}
}