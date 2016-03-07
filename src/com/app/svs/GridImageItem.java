package com.app.svs;

import android.graphics.Bitmap;


public class GridImageItem {
	private Bitmap image;
	
	
	public GridImageItem(Bitmap image) {
		super();
		this.image = image;
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}

	
}