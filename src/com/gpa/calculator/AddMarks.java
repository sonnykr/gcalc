package com.gpa.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AddMarks extends Activity {
	
	TextView courseName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.add_marks);
		courseName = (TextView) findViewById(R.id.tvAddCourseMark);
		
		Bundle extras = getIntent().getExtras();
		if(extras != null)
			courseName.setText(extras.getString("CourseName"));
		else
			courseName.setText("Course name unavailable");
	}

}
