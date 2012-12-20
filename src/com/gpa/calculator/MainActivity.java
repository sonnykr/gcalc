package com.gpa.calculator;

import utility.dbConnect;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	EditText StudentName;
	EditText StudentEmail;
	EditText StudentPassword;
	Button SaveStudent, Student;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();

		SaveStudent.setOnClickListener(this);
		Student.setOnClickListener(this);
	}

	private void init() {
		StudentName = (EditText) findViewById(R.id.etUserName);
		StudentEmail = (EditText) findViewById(R.id.etEmail);
		StudentPassword = (EditText) findViewById(R.id.etPassword);
		SaveStudent = (Button) findViewById(R.id.bSaveStudent);
		
		Student = (Button) findViewById(R.id.bStudent);
	}

	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.bSaveStudent:
			
				boolean saved = true;
				
				try
				{
				String studentName = StudentName.getText().toString();
				String password = StudentPassword.getText().toString();
				String email = StudentEmail.getText().toString();
				String programId = "0";
				
				dbConnect dbAccess = new dbConnect(MainActivity.this);
				dbAccess.open();
				
				dbAccess.insertStudent(studentName, password, email, programId);
				
				dbAccess.close();	
				
				} catch(Exception e)
				{
					saved = false;
				} finally
				{
					if(saved != false)
					{
						Toast.makeText(this, "Thank you!", Toast.LENGTH_LONG).show();
						Intent intent = new Intent(MainActivity.this, DisplayGPA.class);
						startActivity(intent);
					}
					else
					{
						Toast.makeText(this, "Some error occoured. Please try again!", Toast.LENGTH_LONG).show();
					}

				}
			break;
			
		case R.id.bStudent:
			Intent intent = new Intent(MainActivity.this, StudentDetails.class);
			startActivity(intent);
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
