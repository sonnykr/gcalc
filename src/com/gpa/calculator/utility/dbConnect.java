package com.gpa.calculator.utility;

import android.R.array;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBConnect {

	private DBHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static final int DATABASE_VERSION = 5;
	private static final String DATABASE_NAME = "GPACALC";
	
	//Student Table Fields
	private static final String DATABASE_STUDENT_TABLE = "STUDENT";
	public static final String ST_USERID = "_id";
	public static final String ST_USERNAME = "_studentName";
	public static final String ST_PASSWORD = "_password";
	public static final String ST_EMAIL = "_emailAddress";
	public static final String ST_PROGRAMID = "_programId";
	public static final String CREATE_STUDENT_TABLE = "CREATE TABLE "
			+ DATABASE_STUDENT_TABLE + " ("
			+ ST_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ ST_USERNAME + " VARCHAR(50) NOT NULL," 
			+ ST_PASSWORD + " VARCHAR(30),"
			+ ST_EMAIL + " VARCHAR(30)," 
			+ ST_PROGRAMID + " VARCHAR(10)" + ");";
	//end of Student Table variables
	
	//Marks Table Fields
	private static final String DATABASE_MARKS_TABLE = "MARKS";
	public static final String MK_ID = "_id";
	public static final String MK_COURSEID = "_courseId";
	public static final String MK_TESTNAME = "_testName";
	public static final String MK_SCOREDMARKS = "_scoredMarks";
	public static final String MK_FROMTOTAL = "_fromTotal";
	public static final String MK_TOTALWEIGHT = "_totalWeight";
	public static final String CREATE_MARKS_TABLE = "CREATE TABLE "
			+ DATABASE_MARKS_TABLE + " ("
			+ MK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ MK_COURSEID + " INTEGER NOT NULL," 
			+ MK_TESTNAME + " VARCHAR(30),"
			+ MK_SCOREDMARKS + " FLOAT," 
			+ MK_FROMTOTAL + " FLOAT," 
			+ MK_TOTALWEIGHT + " FLOAT" + ");";
	//end of Marks table variables

	public static class DBHelper extends SQLiteOpenHelper {
		//DB Helper class implementing SQLiteOpenHelper Class
		
		public DBHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {

			db.execSQL(CREATE_STUDENT_TABLE);
			db.execSQL(CREATE_MARKS_TABLE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_STUDENT_TABLE);
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_MARKS_TABLE);
			onCreate(db);
		}
		
	}
	
	public DBConnect(Context c)
	{
		//constructor of the main class
		ourContext = c;
	}
	
	public DBConnect open() throws SQLException
	{
		ourHelper = new DBHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close()
	{
		ourHelper.close();
	}

	public long insertStudent(String studentName, String password,
			String email, String programId) {
		ContentValues cv = new ContentValues();
		cv.put(ST_USERNAME, studentName);
		cv.put(ST_PASSWORD, password);
		cv.put(ST_EMAIL, email);
		cv.put(ST_PROGRAMID, programId);
		
		return ourDatabase.insert(DATABASE_STUDENT_TABLE, null, cv);
		
	}

	public String getStudentData() {
		// Get Student Details from Student table
		String retData = "";
		
		String[] columns = new String[] {ST_USERID, ST_USERNAME, ST_PASSWORD, ST_EMAIL, ST_PROGRAMID};
		Cursor c = ourDatabase.query(DATABASE_STUDENT_TABLE, columns, null, null, null, null, null);
		
		int iRow = c.getColumnIndex(ST_USERID);
		int iUserName = c.getColumnIndex(ST_USERNAME);
		//int iPassword = c.getColumnIndex(ST_PASSWORD);
		int iEmail = c.getColumnIndex(ST_EMAIL);
		int iProgram = c.getColumnIndex(ST_PROGRAMID);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			retData += c.getString(iRow) + " " + c.getString(iUserName) + " " + c.getString(iEmail)
								+ " " + c.getString(iProgram) + "\n";
		}
		
		return retData;
	}

	public long insertMarks(int _courseId, String _testName,float _scoredMarks, float _fromTotal, float _totalWeight) 
	{
		ContentValues cv = new ContentValues();
		cv.put(MK_COURSEID, _courseId);
		cv.put(MK_TESTNAME, _testName);
		cv.put(MK_SCOREDMARKS, _scoredMarks);
		cv.put(MK_FROMTOTAL, _fromTotal);
		cv.put(MK_TOTALWEIGHT, _totalWeight);
		
		return ourDatabase.insert(DATABASE_MARKS_TABLE, null, cv);
		
	}
	
	public String getTotalMarks()
	{
		// Get Student Details from Student table
				String retData = "";
				
				String[] columns = new String[] {MK_COURSEID, MK_TESTNAME,
													MK_SCOREDMARKS, MK_FROMTOTAL, MK_TOTALWEIGHT};
				Cursor c = ourDatabase.query(DATABASE_STUDENT_TABLE, columns, null, null, null, null, null);
				
				int iRow = c.getColumnIndex(MK_COURSEID);
				int iTestName = c.getColumnIndex(MK_TESTNAME);
				int iScoredMarks = c.getColumnIndex(MK_SCOREDMARKS);
				int iFromTotal = c.getColumnIndex(MK_FROMTOTAL);
				int iTotalWeight = c.getColumnIndex(MK_TOTALWEIGHT);
				
				for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
				{
					retData += c.getString(iRow) + " " + c.getString(iTestName) + " " + c.getString(iScoredMarks)
										+ " " + c.getString(iFromTotal) + " " + c.getString(iTotalWeight) + "\n";
				}
				
				return retData;
		
	}
	
	

}
