package com.app.svs;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

@SuppressWarnings("rawtypes")
public class GridViewAdapter extends ArrayAdapter {
	
	private Context context;
	private List<String> data = new ArrayList<>();

	@SuppressWarnings("unchecked")
	public GridViewAdapter(Context context, int layoutResourceId,
			List<String> data) {
		super(context, layoutResourceId, data);
		this.context = context;
		this.data = data;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view;
		ImageView imageView = null;
		
		if (convertView == null) {
			view = new View(context);
			view = inflater.inflate(R.layout.row_grid, null);
			imageView = (ImageView) view.findViewById(R.id.image);
			Bitmap bm = getBitmapFromAsset(data.get(position));
			imageView.setImageBitmap(bm);
			
		} else {
			view = (View) convertView;
		}

		return view;
	}

	private Bitmap getBitmapFromAsset(String strName)
	{
		AssetManager assetManager = context.getAssets();
		InputStream istr = null;
		try {
			istr = assetManager.open(strName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Bitmap bitmap = BitmapFactory.decodeStream(istr);
		return bitmap;
	}


}