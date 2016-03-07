package com.app.svs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends Activity {

	private Button btnSubmit;
	private EditText et1, et2, et3,et4, et6,et7;
	private Spinner sp1;
	Calendar myCalendar; 
	DatePickerDialog.OnDateSetListener date;
	TextView tv1;
	String list [] = new String[] {"Male", "Female"};

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		tv1 = (TextView)findViewById(R.id.tv1);
		Typeface myFont = Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-Regular.ttf");
		tv1.setTypeface(myFont);




		String udata="REGISTER";
		SpannableString content = new SpannableString(udata);
		content.setSpan(new UnderlineSpan(), 0, udata.length(), 0);
		tv1.setText(content);

		if(!Utils.isNetworkAvailable(this)) {
			Toast.makeText(getApplicationContext(), "No internet connection!", Toast.LENGTH_LONG).show();	
			return;
		}

		myCalendar = Calendar.getInstance();
		date = new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				// TODO Auto-generated method stub

				Calendar newDate = Calendar.getInstance();
				newDate.set(year, monthOfYear, dayOfMonth);

				Date current = newDate.getTime();
				int diff1 =new Date().compareTo(current);

				if(diff1<0){
					Toast.makeText(getApplicationContext(), "Please select a valid date",  Toast.LENGTH_LONG).show();
					return;
				}

				myCalendar.set(Calendar.YEAR, year);
				myCalendar.set(Calendar.MONTH, monthOfYear);
				myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

				updateLabel();
			}

		};

		et1= (EditText)findViewById(R.id.et1);
		et2= (EditText)findViewById(R.id.et2);
		et3= (EditText)findViewById(R.id.et3);
		et4= (EditText)findViewById(R.id.et4);

		et4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(RegistrationActivity.this, date, myCalendar
						.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
						myCalendar.get(Calendar.DAY_OF_MONTH)).show();
			}
		});


		et6= (EditText)findViewById(R.id.et6);
		et7= (EditText)findViewById(R.id.et7);
		sp1 = (Spinner)findViewById(R.id.spinnerGender);

		sp1.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
					int arg2, long arg3) {

				((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_row_spinner, list);
		sp1.setAdapter(adapter);
		

		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#597894")));
		bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		bar.setCustomView(R.layout.actionbar_layout);
		bar.setHomeButtonEnabled(false);
		bar.setDisplayUseLogoEnabled(false);
		bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
		bar.setHomeAsUpIndicator(R.drawable.ic_drawer);


		btnSubmit = (Button)findViewById(R.id.btnSubmit);
		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					Log.i("App", "button clicked");
					String val;
					int age = 0;
					String name = et1.getText().toString();
					String contact = et2.getText().toString();
					val = et3.getText().toString();
					Log.i("App", "Contact and age" +contact + " "  +val);

					if(contact.equalsIgnoreCase("")){
						Toast.makeText(getApplicationContext(), "Pleas enter contact number!", Toast.LENGTH_LONG).show();
						return;
					}

					if(contact != null){
						int digit = contact.length();
						if(digit != 10){
							Toast.makeText(getApplicationContext(), "Invalid contact number!", Toast.LENGTH_LONG).show();
							return;
						}
					}

					if(val.equalsIgnoreCase("")){
						Toast.makeText(getApplicationContext(), "Pleas enter age!", Toast.LENGTH_LONG).show();
						return;
					}	

					if(val != null){
						age = Integer.parseInt(val);
						if (age > 100){
							Toast.makeText(getApplicationContext(), "Invalid age!", Toast.LENGTH_LONG).show();
							return;
						}
					}

					String date = et4.getText().toString();
					Log.i("App", "date:" +date);
					String gender = sp1.getSelectedItem().toString();
					Log.i("App", "Gender selected" +gender);
					String profession = et6.getText().toString();
					String city = et7.getText().toString();

					if(name.equalsIgnoreCase("")  || gender.equalsIgnoreCase("") || profession.equalsIgnoreCase("") || city.equalsIgnoreCase("")){
						Toast.makeText(getApplicationContext(), "Please fill required fields..!", Toast.LENGTH_LONG).show();
						return;
					}


					if(null!= name && age>=0 && null!=date && null!=gender && null!=profession && null!=city){
						Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_LONG).show();
						Intent i = new Intent(RegistrationActivity.this, MainActivity.class);
						startActivity(i);
						finish();		
					}

				}catch(Exception e){
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "Registration unsuccessful", Toast.LENGTH_LONG).show();
				}

			}
		});
	}//end oncreate

	private void updateLabel() {
		String myFormat = "dd/MM/yyyy"; 
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
		et4.setText(sdf.format(myCalendar.getTime()));
	}

}
