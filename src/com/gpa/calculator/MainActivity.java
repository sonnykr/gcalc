package com.gpa.calculator;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener{

	protected static final int ADDMARKS = 0;
	TextView gpaLabel, addNewCourse;
	ListView courseList;
	String[] listItems = {"WebApp", "Java Programming", "College Communications", 
							"Global Citizenship", "Database Programming"};
	ArrayAdapter<String> adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		gpaLabel = (TextView) findViewById(R.id.tvDisplayGPA);
		gpaLabel.setText("Overall GPA: 4.12 / 4.5");
		courseList = (ListView) findViewById(R.id.lvCourseList);
		
		adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_multiple_choice, listItems);
		courseList.setAdapter(adapter);
		courseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
			Object o = courseList.getItemAtPosition(position);
			String courseName = (String)o;
			Intent intent = new Intent(getApplicationContext(), AddMarks.class);
			intent.putExtra("CourseName", courseName);
			startActivityForResult(intent, ADDMARKS);
			
			}
		});
		
		addNewCourse = (TextView) findViewById(R.id.tvAddNewCourse);
		addNewCourse.setOnClickListener(this);
	
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvAddNewCourse:
			gpaLabel.setText("Add New Course Clicked!");
			break;

		default:
			break;
		}
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}



	

}
