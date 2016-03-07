package com.app.svs;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
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

public class YoutubePlayerActivity extends Activity {

	private ListView listview, listView2;
	private String videoUrl;
	private TextView tv1, tv2;
	private DrawerLayout drawerLayout;
	private View drawerView;
	private DrawerListener myDrawerListener;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		
		if(!Utils.isNetworkAvailable(this)) {
			Toast.makeText(getApplicationContext(), "No internet connection!", Toast.LENGTH_LONG).show();	
			return;
		}
		
		listView2 = (ListView)findViewById(R.id.list);
		String[] values = new String[] { "HOMEPAGE", "DARSHAN", 
				"THOUGHT OF THE DAY",
				"EVENTS",
				"GALLERY", 
				"SONGS", 
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

		
		drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
		drawerView = (View)findViewById(R.id.drawer);
		drawerLayout.setDrawerListener(myDrawerListener);

		
		listView2.setAdapter(adapter); 
		// ListView Item Click Listener
		listView2.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String  itemValue    = (String) listView2.getItemAtPosition(position);
				// Show Alert 
				Log.i("App", "item selected:" +itemValue);

				if(itemValue.equalsIgnoreCase("gallery")){
					Intent i = new Intent(YoutubePlayerActivity.this, GalleryActivity.class);
					startActivity(i);

				}else if(itemValue.equalsIgnoreCase("songs")){
					Intent i = new Intent(YoutubePlayerActivity.this, SongActivity.class);
					startActivity(i);

				}else if(itemValue.equalsIgnoreCase("Contact us")){
					Intent i = new Intent(YoutubePlayerActivity.this, ContactActivity.class);
					startActivity(i);
				}else if(itemValue.equalsIgnoreCase("Thought of the day")){
					Intent i = new Intent(YoutubePlayerActivity.this, MessageActivity.class);
					startActivity(i);
				}else if(itemValue.equalsIgnoreCase("Darshan")){
					Intent i = new Intent(YoutubePlayerActivity.this, DarshanActivity.class);
					startActivity(i);
				}else if(itemValue.equalsIgnoreCase("Events")){
					Intent i = new Intent(YoutubePlayerActivity.this, EventActivity.class);
					startActivity(i);
				}else if(itemValue.equalsIgnoreCase("Homepage")){
					Intent i = new Intent(YoutubePlayerActivity.this, MainActivity.class);
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
		tv2 = (TextView)findViewById(R.id.tv2);
		tv2.setClickable(true);
		tv2.setMovementMethod(LinkMovementMethod.getInstance());
		String text = "<a href='http://www.youtube.com/saikaliram13'> For more videos click here </a>";
		tv2.setText(Html.fromHtml(text));
		
		Typeface myFont = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-Regular.ttf");
		tv1.setTypeface(myFont);
		tv2.setTypeface(myFont);
		String udata="TOP VIDEOS OF SVS DARBAR";
		SpannableString content = new SpannableString(udata);
		content.setSpan(new UnderlineSpan(), 0, udata.length(), 0);
		tv1.setText(content);
		
		listview = (ListView)findViewById(R.id.videoList);
		String[] values2 = new String[] { "Roohaniyat Title Track", 
				"Amma/Roohaniyat",
				"Mataa Kahenji Dukhayi Dil",
				"Dukh Sukh Zindagi Mein", 
				"Dil Bhul Ji (Aetbar) ", 
				"Dhaar Thyan Jo Shauq", 
				"Tuhinje Ishq Kha Po",
				"Disaan Tho Duniya Mein",
				"Zindagi Jo Kasam",
				"Asaanjo Rishto Roohaniyat Waro",
				"Soni Mundi Wara Sain",
				"Paar Aa Paar Aa"
		};

		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, values2){

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = super.getView(position, convertView, parent);
				TextView text = (TextView) view.findViewById(android.R.id.text1);
				text.setTextColor(Color.BLACK);
				return view;
			}
		};

		listview.setAdapter(adapter2); 
		// ListView Item Click Listener
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String  itemValue = (String)listview.getItemAtPosition(position);
				// Show Alert 
				Log.i("App", "item selected:" +itemValue);
				
				if(itemValue.equalsIgnoreCase("Roohaniyat Title Track")){
					videoUrl = "http://youtu.be/w_O3Oc4HQIU";
				}else if(itemValue.equalsIgnoreCase("Amma/Roohaniyat")){
					videoUrl = "http://youtu.be/Jih3CX9BwTg";
				}else if(itemValue.equalsIgnoreCase("Mataa Kahenji Dukhayi Dil")){
					videoUrl = "http://youtu.be/MDv4ZchSEdc";
				}else if(itemValue.equalsIgnoreCase("Dukh Sukh Zindagi Mein")){
					videoUrl = "http://youtu.be/g5Klb482kKw";
				}else if(itemValue.equalsIgnoreCase("Dil Bhul Ji (Aetbar)")){
					videoUrl = "http://youtu.be/Mu04NGjl88g";
				}else if(itemValue.equalsIgnoreCase("Dhaar Thyan Jo Shauq")){
					videoUrl = "http://youtu.be/22jv0sWx9e0";
				}else if(itemValue.equalsIgnoreCase("Tuhinje Ishq Kha Po")){
					videoUrl = "http://youtu.be/r37JLTalfCg";
				}else if(itemValue.equalsIgnoreCase("Disaan Tho Duniya Mein")){
					videoUrl = "http://youtu.be/9w3zWwrHjRE";
				}else if(itemValue.equalsIgnoreCase("Zindagi Jo Kasam")){
					videoUrl = "http://youtu.be/RnzKW9b-6yA";
				}else if(itemValue.equalsIgnoreCase("Asaanjo Rishto Roohaniyat Waro")){
					videoUrl = "http://youtu.be/5Aj7I2bYIgw";
				}else if(itemValue.equalsIgnoreCase("Soni Mundi Wara Sain")){
					videoUrl = "http://youtu.be/G6SyxKLKvgI";
				}else if(itemValue.equalsIgnoreCase("Paar Aa Paar Aa")){
					videoUrl = "http://youtu.be/U2m6HUag-98";
				}
				
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(videoUrl));
				startActivity(i);
		
			}

		}); 
	}// end oncreate
	
	
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

					final Dialog dialog = new Dialog(YoutubePlayerActivity.this);
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
									Toast.makeText(YoutubePlayerActivity.this, 
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
			Toast.makeText(YoutubePlayerActivity.this, 
					"There is no email client installed.", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
