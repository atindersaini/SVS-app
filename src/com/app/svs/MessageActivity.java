package com.app.svs;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("UseSparseArrays")
public class MessageActivity extends Activity{


	private Map<Integer,String> map = new HashMap<Integer,String>();
	private ListView listView;
	private TextView txt1;
	private DrawerLayout drawerLayout;
	private View drawerView;
	private DrawerListener myDrawerListener;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);

		try{
			
			//font
			txt1 = (TextView)findViewById(R.id.txt1);
			Typeface myFont = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-Regular.ttf");
			txt1.setTypeface(myFont);
			
			listView = (ListView)findViewById(R.id.list);
			String[] values = new String[] { "HOMEPAGE", "DARSHAN", 
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
						Intent i = new Intent(MessageActivity.this, GalleryActivity.class);
						startActivity(i);

					}else if(itemValue.equalsIgnoreCase("songs")){
						Intent i = new Intent(MessageActivity.this, SongActivity.class);
						startActivity(i);

					}else if(itemValue.equalsIgnoreCase("Videos")){
						Intent i = new Intent(MessageActivity.this, YoutubePlayerActivity.class);
						startActivity(i);
					}else if(itemValue.equalsIgnoreCase("Darshan")){
						Intent i = new Intent(MessageActivity.this, DarshanActivity.class);
						startActivity(i);
					}else if(itemValue.equalsIgnoreCase("Events")){
						Intent i = new Intent(MessageActivity.this, EventActivity.class);
						startActivity(i);
					}else if(itemValue.equalsIgnoreCase("Homepage")){
						Intent i = new Intent(MessageActivity.this, MainActivity.class);
						startActivity(i);
					}else if(itemValue.equalsIgnoreCase("Contact us")){
						Intent i = new Intent(MessageActivity.this, ContactActivity.class);
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

			TextView tv = (TextView)findViewById(R.id.txt2);
			tv.setTypeface(myFont);
			
			drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
			drawerView = (View)findViewById(R.id.drawer);
			drawerLayout.setDrawerListener(myDrawerListener);
			
			String udata="THOUGHT OF THE DAY";
			SpannableString content = new SpannableString(udata);
			content.setSpan(new UnderlineSpan(), 0, udata.length(), 0);
			txt1.setText(content);

			map.put(1, "Teach me to treat all that comes to me with peace of soul and with firm conviction that You will govern all my Shahenshah Sai Parushah Sarkar");
			map.put(2, "Our greatest weakness lies in giving up. The most certain way to succeed is always to try just one more time with saying Rakh Rohri Ware Te.");
			map.put(3, "Always do your best. What you plant now, you will harvest later.");
			map.put(4, "It is always too early to quit.");
			map.put(5, "Believe in yourself! Have faith in your Guru. Without a humble but reasonable confidence in your own powers you cannot be successful or happy.");
			map.put(6,"	Expect problems and eat them in your breakfast.");
			map.put(7, "Gunaah mere bade hain Toh, tera darr bhi hai bada Maaf karega tu, mere Sai Vasanshah Isiliye hu tere darr pe khada.");
			map.put(8, "If you want to achieve greatness stop asking for permission from anyone in this world, just let your Sai know that thing and ask for his blessings.");
			map.put(9,"Be kind whenever possible, it is always possible.");
			map.put(10,"Just as a candle cannot burn without fire, men cannot live without a spiritual life");
			map.put(11,"Your mind cannot possibly understand God.  Your heart already knows.");
			map.put(12,"If you never thank God after every smile..then you have no right to blame him for every tear..");
			map.put(13, "Sai always has something for you, a key for every problem, a light for every shadow, a relief for every sorrow & a plan for every tomorrow.");
			map.put(14,"Don't dwell on when the truth was spoken. What is important is that what has been said is the truth.");
			map.put(15, "Never ever compare anyone with anybody. One who is not satisfied with his own self can not find satisfaction anywhere in the world.");
			map.put(16, "Nobody can make the whole world happy. Learn to keep your own self happy and remain happy under all circumstances in life. The key to being happy is a non-insisting mind, non-complaining mind, and a pure mind.");
			map.put(17, "We all belong to one human family with one cause which is to bring more happiness and more smiles on the planet.");
			map.put(18,"Sadguru is the complete director for his disciple. The disciple is filled with knots and the Sadguru has the keys.");
			map.put(19,"If you are aiming for the highest and none are laughing at you, know that your goal is not high enough.");
			map.put(20,"Ik Parushah Ko Reejhaaya mere Vasan Ne aur Vasanshah Hua, Bandhe Mujh tak baad mein Aa, Pahle apne maa-baap ko reejha.");

			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(20);
			if(randomInt <= 20 ){
				Log.i("App", "Value" +map.get(randomInt));
				tv.setText(map.get(randomInt));
			}
			
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

		}catch(Exception e){
			e.printStackTrace();
		}

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

					final Dialog dialog = new Dialog(MessageActivity.this);
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
									Toast.makeText(MessageActivity.this, 
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
			Toast.makeText(MessageActivity.this, 
					"There is no email client installed.", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
}
