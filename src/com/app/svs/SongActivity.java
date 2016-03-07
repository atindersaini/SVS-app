package com.app.svs;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SongActivity extends Activity {

	private List<String> songList = new  ArrayList<String>();
	String[] names = new String[] { "Sakhi Sardar Parushah (Madaah)","Vasan Shah Jo Aaya Ma","Allahlok Manhu Asaan","Mu Gareeb Ishq"};
	int s1[]={R.raw.song1,R.raw.song2,R.raw.song3,R.raw.song4};
	MediaPlayer mp = new MediaPlayer();    
	ListView listView, listView2;
	SongAdapter adapter;
	Button btn1;
	TextView tv1;
	private DrawerLayout drawerLayout;
	private View drawerView;
	private DrawerListener myDrawerListener;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_song_view);
		
		listView2 = (ListView)findViewById(R.id.list);
		String[] values = new String[] { "HOMEPAGE", "DARSHAN", 
				"THOUGHT OF THE DAY",
				"EVENTS",
				"GALLERY", 
				"VIDEOS",
				"CONTACT US"
		};

		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, values){

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = super.getView(position, convertView, parent);
				TextView text = (TextView) view.findViewById(android.R.id.text1);
				text.setTextColor(Color.WHITE);
				return view;
			}
		};
		
		drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
		drawerView = (View)findViewById(R.id.drawer);
		drawerLayout.setDrawerListener(myDrawerListener);


		listView2.setAdapter(adapter2); 
		// ListView Item Click Listener
		listView2.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String  itemValue    = (String) listView2.getItemAtPosition(position);
				// Show Alert 
				Log.i("App", "item selected:" +itemValue);

				if(itemValue.equalsIgnoreCase("gallery")){
					Intent i = new Intent(SongActivity.this, GalleryActivity.class);
					startActivity(i);

				}else if(itemValue.equalsIgnoreCase("Contact us")){
					Intent i = new Intent(SongActivity.this, ContactActivity.class);
					startActivity(i);

				}else if(itemValue.equalsIgnoreCase("Videos")){
					Intent i = new Intent(SongActivity.this, YoutubePlayerActivity.class);
					startActivity(i);
				}else if(itemValue.equalsIgnoreCase("Thought of the day")){
					Intent i = new Intent(SongActivity.this, MessageActivity.class);
					startActivity(i);
				}else if(itemValue.equalsIgnoreCase("Darshan")){
					Intent i = new Intent(SongActivity.this, DarshanActivity.class);
					startActivity(i);
				}else if(itemValue.equalsIgnoreCase("Events")){
					Intent i = new Intent(SongActivity.this, EventActivity.class);
					startActivity(i);
				}else if(itemValue.equalsIgnoreCase("Homepage")){
					Intent i = new Intent(SongActivity.this, MainActivity.class);
					startActivity(i);
				}
			}

		}); 
		
		
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

		tv1 = (TextView)findViewById(R.id.tv1);
		Typeface myFont = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-Regular.ttf");
		tv1.setTypeface(myFont);
		String udata="TOP SONGS OF SVS DARBAR";
		SpannableString content = new SpannableString(udata);
		content.setSpan(new UnderlineSpan(), 0, udata.length(), 0);
		tv1.setText(content);
		
		listView = (ListView)findViewById(R.id.songList);
		songList.add("Sakhi Sardar Parushah (Madaah)");
		songList.add("Vasan Shah Jo Aaya Ma");
		songList.add("Allahlok Manhu Asaan");
		songList.add("Mu Gareeb Ishq");
		
		adapter = new SongAdapter(this, songList);
		
		listView.setAdapter(adapter);	
		// ListView Item Click Listener
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				
				int itemValue = (int) listView.getItemAtPosition(position);
				// Show Alert 
				Log.i("App", "item selected:" +itemValue);
				try{
					mp.reset();                             
					mp.release();                        
					mp=MediaPlayer.create(SongActivity.this,s1[itemValue]);                                        
					mp.seekTo(0);                                   
					mp.start();                                            
				}
				catch (Exception e){
					e.printStackTrace();
				}
			}
		}); 
			
		/*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, names){

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = super.getView(position, convertView, parent);
				TextView text = (TextView) view.findViewById(android.R.id.text1);
				text.setTextColor(Color.WHITE);
				return view;
			}
		};*/

		btn1 = (Button)findViewById(R.id.stopPlaying);
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mp.stop();
				mp.release();
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

					final Dialog dialog = new Dialog(SongActivity.this);
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
									Toast.makeText(SongActivity.this, 
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
			Toast.makeText(SongActivity.this, 
					"There is no email client installed.", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}


