package com.gpa.calculator.models;

import android.app.Application;
import android.content.Context;
import com.gpa.calculator.utility.dbConnect;

public class Marks extends Application {

	private int _courseId;
	private String _testName;
	private float _scoredMarks;
	private float _fromTotal;
	private float _totalWeight;

	private static Context thisClass;
	private dbConnect dba;

	public int get_courseId() {
		return _courseId;
	}

	public void set_courseId(int _courseId) {
		this._courseId = _courseId;
	}

	public String get_testName() {
		return _testName;
	}

	public void set_testName(String _testName) {
		this._testName = _testName;
	}

	public float get_scoredMarks() {
		return _scoredMarks;
	}

	public void set_ScoredMarks(float _scoredMarks) {
		this._scoredMarks = _scoredMarks;
	}

	public float get_fromTotal() {
		return _fromTotal;
	}

	public void set_fromTotal(float _fromTotal) {
		this._fromTotal = _fromTotal;
	}

	public float get_totalWeight() {
		return _totalWeight;
	}

	public void set_totalWeight(float _totalWeight) {
		this._totalWeight = _totalWeight;
	}

	public boolean insertMarks()
	{
		thisClass = getApplicationContext();
		dba = new dbConnect(thisClass);
		dba.open();
		//Code to insert values to Marks table
		dba.insertMarks(_courseId, _testName, _scoredMarks, _fromTotal, _totalWeight);
		dba.close();

		return true;
	}

	public float getMarks(int CourseId)
	{
		//not complete
		return 1;
	}

}