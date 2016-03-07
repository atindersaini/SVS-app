package com.app.svs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.support.v4.app.NotificationCompat;
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

public class EventActivity extends Activity {


	ArrayList<HashMap<String, String>> eventMapList = new ArrayList<HashMap<String, String>>();
	static final String KEY_EVENT_TITLE = "title";
	static final String KEY_EVENT_DATE = "date";
	ListView list;
	EventAdapter adapter;
	TextView tv1;
	public List<String> eventNamesList = new ArrayList<String>();
	public List<String> eventDatesList = new ArrayList<String>();
	private ListView listView;
	private DrawerLayout drawerLayout;
	private View drawerView;
	private DrawerListener myDrawerListener;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_view);

		tv1 = (TextView)findViewById(R.id.tv1);
		Typeface myFont = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-Regular.ttf");
		tv1.setTypeface(myFont);

		listView = (ListView)findViewById(R.id.list);
		String[] values = new String[] { "HOMEPAGE", "DARSHAN", 
				"THOUGHT OF THE DAY",
				"GALLERY", 
				"SONGS", 
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
		
		listView.setAdapter(adapter2); 
		// ListView Item Click Listener
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String  itemValue    = (String) listView.getItemAtPosition(position);
				// Show Alert 
				Log.i("App", "item selected:" +itemValue);

				if(itemValue.equalsIgnoreCase("gallery")){
					Intent i = new Intent(EventActivity.this, GalleryActivity.class);
					startActivity(i);

				}else if(itemValue.equalsIgnoreCase("songs")){
					Intent i = new Intent(EventActivity.this, SongActivity.class);
					startActivity(i);

				}else if(itemValue.equalsIgnoreCase("Videos")){
					Intent i = new Intent(EventActivity.this, YoutubePlayerActivity.class);
					startActivity(i);
				}else if(itemValue.equalsIgnoreCase("Thought of the day")){
					Intent i = new Intent(EventActivity.this, MessageActivity.class);
					startActivity(i);
				}else if(itemValue.equalsIgnoreCase("Darshan")){
					Intent i = new Intent(EventActivity.this, DarshanActivity.class);
					startActivity(i);
				}else if(itemValue.equalsIgnoreCase("Homepage")){
					Intent i = new Intent(EventActivity.this, MainActivity.class);
					startActivity(i);
				}else if(itemValue.equalsIgnoreCase("Contact us")){
					Intent i = new Intent(EventActivity.this, ContactActivity.class);
					startActivity(i);
				}
			}

		}); 


		eventNamesList.add("Parkash Utsav 1");
		eventNamesList.add("Parkash Utsav 2");
		eventNamesList.add("Parkash Utsav 3");
		eventNamesList.add("Parkash Utsav 4");

		eventDatesList.add("01/02/2015");
		eventDatesList.add("01/02/2015");
		eventDatesList.add("01/02/2015");
		eventDatesList.add("01/02/2015");


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


		String udata="UPCOMING EVENTS";
		SpannableString content = new SpannableString(udata);
		content.setSpan(new UnderlineSpan(), 0, udata.length(), 0);
		tv1.setText(content);

		HashMap<String, String> map = new HashMap<String, String>();

		for(int i=0 ; i < eventNamesList.size(); i++){	

			String eventName = eventNamesList.get(i);
			String eventDate = eventDatesList.get(i);	
			map.put(KEY_EVENT_TITLE, eventName);
			map.put(KEY_EVENT_DATE, eventDate);
			eventMapList.add(map); 
		}


		list=(ListView)findViewById(R.id.eventList); 
		adapter=new EventAdapter(this, eventMapList);
		list.setAdapter(adapter);

		// Click event for single list row
		list.setOnItemClickListener(new OnItemClickListener() {

			@SuppressLint("InlinedApi")
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				// TODO Auto-generated method stub
				TextView textview1 = (TextView) v.findViewById(R.id.event_date);
				TextView textview2 = (TextView) v.findViewById(R.id.event_title);

				String eventTitle = textview2.getText().toString();
				String itemValue = textview1.getText().toString();
				Log.i("App", "Got date:" +itemValue);

				showNotification(eventTitle);

				//set calendar event
				Intent calIntent = new Intent(Intent.ACTION_INSERT); 
				calIntent.setType("vnd.android.cursor.item/event");    
				calIntent.putExtra(Events.TITLE, textview2.getText().toString()); 
				GregorianCalendar calDate = new GregorianCalendar(2015, 2, 1);
				calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true); 
				calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, 
						calDate.getTimeInMillis()); 
				calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, 
						calDate.getTimeInMillis()); 

				startActivity(calIntent);


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

	}//on create ends

	@SuppressWarnings("unused")
	private class GetSongListTask extends AsyncTask<Void, String, String>{
		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String readJSON = getJSON("http://date.jsontest.com");
			try{
				JSONObject jsonObject = new JSONObject(readJSON);
				Log.i("App", jsonObject.getString("date"));
			} catch(Exception e){e.printStackTrace();}
			finally{
				System.out.println("Success");
			}
			return null;
		}	
		
		@Override
		protected void onPostExecute(String result){
			super.onPostExecute(result);
			
		}
	};

	public String getJSON(String address){
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(address);
		try{
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if(statusCode == 200){
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
				String line;
				while((line = reader.readLine()) != null){
					builder.append(line);
				}
			} else {
				Log.e("App","Failed to get JSON");
			}	
		}catch(ClientProtocolException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		return builder.toString();
	}


	public void showNotification(String eventTitle){
		NotificationCompat.Builder mBuilder =   new NotificationCompat.Builder(this)
		.setSmallIcon(R.drawable.ic_launcher) // notification icon
		.setContentTitle("Upcoming event") // title for notification
		.setContentText(eventTitle) // message for notification
		.setAutoCancel(true); // clear notification after click
		Intent intent = new Intent(this, EventActivity.class);
		PendingIntent pi = PendingIntent.getActivity(this,0,intent,Intent.FLAG_ACTIVITY_NEW_TASK);
		mBuilder.setContentIntent(pi);
		NotificationManager mNotificationManager =
				(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(0, mBuilder.build());
	}
	
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

					final Dialog dialog = new Dialog(EventActivity.this);
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
									Toast.makeText(EventActivity.this, 
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
			Toast.makeText(EventActivity.this, 
					"There is no email client installed.", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
