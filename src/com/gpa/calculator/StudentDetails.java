package com.gpa.calculator;

import utility.dbConnect;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class StudentDetails extends Activity {

	TextView studentDetails;
	dbConnect dbAccess;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_profile);
		
		studentDetails = (TextView) findViewById(R.id.tvStudentDetails);
		dbAccess = new dbConnect(this);
		dbAccess.open();
		String detailsFromSql = dbAccess.getStudentData();
		dbAccess.close();
		studentDetails.setText(detailsFromSql);
	}
}
