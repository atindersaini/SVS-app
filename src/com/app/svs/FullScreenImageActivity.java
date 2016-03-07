package com.app.svs;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FullScreenImageActivity extends Activity {

	private ImageView imageView;
	private ListView listView;
	private Button btnShare;
	private String uri;
	private DrawerLayout drawerLayout;
	private View drawerView;
	private DrawerListener myDrawerListener;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_full_screen);
		
		imageView = (ImageView)findViewById(R.id.imageView1);
		btnShare = (Button)findViewById(R.id.btnShare);
		Intent intent = getIntent();
		Bitmap bitmap = (Bitmap) intent.getParcelableExtra("image");
		uri = intent.getStringExtra("path");
		imageView.setImageBitmap(bitmap);
		
		drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
		drawerView = (View)findViewById(R.id.drawer);
		drawerLayout.setDrawerListener(myDrawerListener);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#597894")));
		bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		bar.setCustomView(R.layout.actionbar_layout);
		bar.setHomeButtonEnabled(false);
		bar.setDisplayUseLogoEnabled(false);
		bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
		bar.setHomeAsUpIndicator(R.drawable.ic_drawer);

		drawerView.setOnTouchListener(new OnTouchListener() {

			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return false;
			}		   

		});

		
		btnShare.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					Intent msg = new Intent(Intent.ACTION_SEND);
					msg.setType("image/jpeg");
					msg.putExtra(Intent.EXTRA_STREAM, Uri.parse(uri));
					startActivity(Intent.createChooser(msg, "Share image"));
				}catch(Exception e){
					Toast.makeText(getApplicationContext(), "Unable to share photo!", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		myDrawerListener = new DrawerListener(){

			@Override
			public void onDrawerClosed(View drawerView) {
			}

			@Override
			public void onDrawerOpened(View drawerView) {
			}

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
			}

			@Override
			public void onDrawerStateChanged(int newState) {
				switch(newState){
				case DrawerLayout.STATE_IDLE:
					break;
				case DrawerLayout.STATE_DRAGGING:
					break;
				case DrawerLayout.STATE_SETTLING:
					break;
				default:
				}
			}
		};
		
		listView = (ListView)findViewById(R.id.list);
		String[] values = new String[] { "HOMEPAGE", "DARSHAN", 
				"THOUGHT OF THE DAY",
				"EVENTS",
				"GALLERY", 
				"SONGS", 
				"VIDEOS", 
				"CONTACT US"
		};

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, values){

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = super.getView(position, convertView, parent);
				TextView text = (TextView) view.findViewById(android.R.id.text1);
				text.setTextColor(Color.WHITE);
				return view;
			}
		};

		listView.setAdapter(adapter); 
		// ListView Item Click Listener
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String  itemValue    = (String) listView.getItemAtPosition(position);
				// Show Alert 
				Log.i("App", "item selected:" +itemValue);

				if(itemValue.equalsIgnoreCase("gallery")){
					Intent i = new Intent(FullScreenImageActivity.this, GalleryActivity.class);
					startActivity(i);

				}else if(itemValue.equalsIgnoreCase("songs")){
					Intent i = new Intent(FullScreenImageActivity.this, SongActivity.class);
					startActivity(i);

				}else if(itemValue.equalsIgnoreCase("Videos")){
					Intent i = new Intent(FullScreenImageActivity.this, YoutubePlayerActivity.class);
					startActivity(i);
				}else if(itemValue.equalsIgnoreCase("Thought of the day")){
					Intent i = new Intent(FullScreenImageActivity.this, MessageActivity.class);
					startActivity(i);
				}else if(itemValue.equalsIgnoreCase("Darshan")){
					Intent i = new Intent(FullScreenImageActivity.this, DarshanActivity.class);
					startActivity(i);
				}else if(itemValue.equalsIgnoreCase("Contact us")){
					Intent i = new Intent(FullScreenImageActivity.this, ContactActivity.class);
					startActivity(i);
				}else if(itemValue.equalsIgnoreCase("Events")){
					Intent i = new Intent(FullScreenImageActivity.this, EventActivity.class);
					startActivity(i);
				}else if(itemValue.equalsIgnoreCase("Homepage")){
					Intent i = new Intent(FullScreenImageActivity.this, MainActivity.class);
					startActivity(i);
				}
			}

		}); 
	}//end oncreate
	
	
	
	@Override
	protected void onResume() {
		super.onResume();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {        
		case android.R.id.home: 
			if (!drawerLayout.isDrawerOpen(drawerView)) {
				drawerLayout.openDrawer(drawerView);
			} else {
				drawerLayout.closeDrawers();
			}
			break;
		case R.id.feedback:
			runOnUiThread(new Runnable() {
				@Override
				public void run() {

					final Dialog dialog = new Dialog(FullScreenImageActivity.this);
					dialog.setContentView(R.layout.send_feedback);
					dialog.setTitle("Feedback");
					final EditText content = (EditText) dialog.findViewById(R.id.et);
					final Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
					// if button is clicked, close the custom dialog
					dialogButton.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							try{
								final String body = content.getText().toString();
								if(null!=body){
									sendEmail(body);
								}else{
									Toast.makeText(FullScreenImageActivity.this, 
											"Please enter your feedback!", Toast.LENGTH_SHORT).show();
								}

								dialog.dismiss();

							}catch(Exception e){
								e.printStackTrace();
							}

						}
					});

					dialog.show();
				}
			});
			break;
		default:            
			return super.onOptionsItemSelected(item);    
		}
		return true;
	}

	public void sendEmail(String body){

		String[] TO = {"svsplusss@gmail.com"};
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		emailIntent.setData(Uri.parse("mailto:"));
		emailIntent.setType("text/plain");
		emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "SVS+ app feedback");
		emailIntent.putExtra(Intent.EXTRA_TEXT, body);

		try {
			startActivity(Intent.createChooser(emailIntent, "Send mail..."));
			finish();
			Log.i("App","Finished sending email...");
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(FullScreenImageActivity.this, 
					"There is no email client installed.", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}