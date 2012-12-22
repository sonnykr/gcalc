package com.gpa.calculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbConnect {

	private DBHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static final int DATABASE_VERSION = 4;
	private static final String DATABASE_NAME = "GPACALC";
	private static final String DATABASE_STUDENT_TABLE = "STUDENT";
	
	//Student Table Fields
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


	public static class DBHelper extends SQLiteOpenHelper {
		//DB Helper class implementing SQLiteOpenHelper Class
		
		public DBHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {

			db.execSQL(CREATE_STUDENT_TABLE);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_STUDENT_TABLE);
			onCreate(db);
		}
		
	}
	
	public dbConnect(Context c)
	{
		//constructor of the main class
		ourContext = c;
	}
	
	public dbConnect open() throws SQLException
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
		int iPassword = c.getColumnIndex(ST_PASSWORD);
		int iEmail = c.getColumnIndex(ST_EMAIL);
		int iProgram = c.getColumnIndex(ST_PROGRAMID);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			retData += c.getString(iRow) + " " + c.getString(iUserName) + " " + c.getString(iEmail)
								+ " " + c.getString(iProgram) + "\n";
		}
		
		return retData;
	}

}
