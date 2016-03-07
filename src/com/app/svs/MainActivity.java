package com.app.svs;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	private ListView listView;
	public static final String PREFS_NAME = "MySVSPrefsFile";
	private SharedPreferences mPreferences;
	ViewPager viewPager;
	MyPagerAdapter myPagerAdapter;
	private DrawerLayout drawerLayout;
	private View drawerView;
	private DrawerListener myDrawerListener;
	private TextView tv1, tv2;
	private int dotsCount;
	private TextView[] dots;
	private LinearLayout dotsLayout;
	private Menu mOptionsMenu;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drawer_layout);


		viewPager = (ViewPager)findViewById(R.id.myviewpager);
		viewPager.setOnPageChangeListener(viewPagerPageChangeListener);
		myPagerAdapter = new MyPagerAdapter(this);
		viewPager.setAdapter(myPagerAdapter);
		viewPager.setCurrentItem(0);
		setUiPageViewController();

		if(!Utils.isNetworkAvailable(this)) {
			Toast.makeText(getApplicationContext(), "No internet connection!", Toast.LENGTH_LONG).show();	
			return;
		}

		tv1 = (TextView)findViewById(R.id.tv1);
		tv2 = (TextView)findViewById(R.id.tv2);

		//font
		Typeface myFont = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-Regular.ttf");
		tv1.setTypeface(myFont);
		tv2.setTypeface(myFont);

		drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
		drawerView = (View)findViewById(R.id.drawer);
		drawerLayout.setDrawerListener(myDrawerListener);

		mPreferences = getSharedPreferences(PREFS_NAME, 0); 
		boolean firstTime = mPreferences.getBoolean("firstTime", true);

		if (firstTime) { 

			SharedPreferences.Editor editor = mPreferences.edit();
			editor.putBoolean("firstTime", false);
			editor.commit();

			//call registration activity
			Intent i = new Intent(MainActivity.this, RegistrationActivity.class);
			startActivity(i);
			finish();
		}


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
					Intent i = new Intent(MainActivity.this, GalleryActivity.class);
					startActivity(i);

				}else if(itemValue.equalsIgnoreCase("songs")){
					Intent i = new Intent(MainActivity.this, SongActivity.class);
					startActivity(i);

				}else if(itemValue.equalsIgnoreCase("Videos")){
					Intent i = new Intent(MainActivity.this, YoutubePlayerActivity.class);
					startActivity(i);
				}else if(itemValue.equalsIgnoreCase("Thought of the day")){
					Intent i = new Intent(MainActivity.this, MessageActivity.class);
					startActivity(i);
				}else if(itemValue.equalsIgnoreCase("Darshan")){
					Intent i = new Intent(MainActivity.this, DarshanActivity.class);
					startActivity(i);
				}else if(itemValue.equalsIgnoreCase("Contact us")){
					Intent i = new Intent(MainActivity.this, ContactActivity.class);
					startActivity(i);
				}else if(itemValue.equalsIgnoreCase("Events")){
					Intent i = new Intent(MainActivity.this, EventActivity.class);
					startActivity(i);
				}else if(itemValue.equalsIgnoreCase("Homepage")){
					Intent i = new Intent(MainActivity.this, MainActivity.class);
					startActivity(i);
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



	}//end oncreate

	private void setUiPageViewController() {
		dotsLayout = (LinearLayout)findViewById(R.id.viewPagerCountDots);
		dotsCount = myPagerAdapter.getCount();
		dots = new TextView[dotsCount];
		
		for (int i = 0; i < dotsCount; i++) {
		
			dots[i] = new TextView(this);
			dots[i].setText(Html.fromHtml("&#8226;"));
			dots[i].setTextSize(30);
			dots[i].setTextColor(getResources().getColor(android.R.color.darker_gray));
			dotsLayout.addView(dots[i]);			
		}
		
		dots[0].setTextColor(getResources().getColor(R.color.app_green));
	}
	
	
	//page change listener
	OnPageChangeListener viewPagerPageChangeListener = new OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int position) {
			for (int i = 0; i < dotsCount; i++) {
				dots[i].setTextColor(getResources().getColor(android.R.color.darker_gray));
			}
			dots[position].setTextColor(getResources().getColor(R.color.app_green));
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}
	};

	
	
	private class MyPagerAdapter extends PagerAdapter{

		int NumberOfPages = 5;
		LayoutInflater mLayoutInflater;
		Context mContext;

		int[] res = { 
				R.drawable.one,
				R.drawable.two,
				R.drawable.three,
				R.drawable.four,
				R.drawable.five,
				R.drawable.six,
				R.drawable.seven,
				R.drawable.eight};

		/*int[] backgroundcolor = { 
				0xFFededed,
				0xFFededed,
				0xFFededed,
				0xFFededed,
				0xFFededed};*/

		public MyPagerAdapter(Context context) {
			mContext = context;
			mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			return NumberOfPages;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			View itemView = mLayoutInflater.inflate(R.layout.pager_layout, container, false);
			ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
			imageView.setImageResource(res[position]);
			container.addView(itemView);
			return itemView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((LinearLayout)object);
		}

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
			
			/*MenuItem mi = (MenuItem)findViewById(R.id.feedback);
			mi.setIcon(R.drawable.settings_icon);*/
			
			runOnUiThread(new Runnable() {
				@Override
				public void run() {

					final Dialog dialog = new Dialog(MainActivity.this);
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
									Toast.makeText(MainActivity.this, 
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
			Toast.makeText(MainActivity.this, 
					"There is no email client installed.", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		//menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.settings_icon));
		mOptionsMenu = menu;
		mOptionsMenu.findItem(R.id.feedback).setIcon(R.drawable.settings_icon);
	    return super.onCreateOptionsMenu(menu);
		//return true;
	}

}
