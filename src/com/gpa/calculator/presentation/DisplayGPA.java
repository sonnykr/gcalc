package com.gpa.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayGPA extends Activity {

	TextView display;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_gpa);
		
		display = (TextView) findViewById(R.id.tvDisplayGPA);
		
		display.setText("Overall GPA : 3.7/4.5 \nThis semester : 4.0/4.5");
	}
}
