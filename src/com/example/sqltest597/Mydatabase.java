package com.example.sqltest597;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Mydatabase extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "Ken";
	private static final String TABLE_MEMBER = "Member";

	public Mydatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE " + TABLE_MEMBER
				+ " (MemberID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " NAME TEXT (100)," + " Tel TEXT (100));");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBER);
		onCreate(db);

	}
	//Insert
	public long InsertData(String strName, String strTel){
		try{
			
			SQLiteDatabase db;
			db = getWritableDatabase();
			
			ContentValues values = new ContentValues();
			values.put("Name", strName);
			values.put("Tel", strTel);
			
			long rows = db.insert(TABLE_MEMBER, null, values);
			
			db.close();
			return rows;
			
		}catch (Exception e){
			return -1;
		}

	}
	//Select All Data
	public ArrayList<HashMap<String, String>> SelectAllData(){
		try{
			ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> map;
			SQLiteDatabase db;
			db = this.getReadableDatabase();
			
			String strSQL = "SELECT * FROM " + TABLE_MEMBER;
			Cursor cursor = db.rawQuery(strSQL, null);
			if(cursor != null){
				if(cursor.moveToFirst()){
					do{
						map = new HashMap<String, String>();
						map.put("Member ID", cursor.getString(0));
						map.put("Name", cursor.getString(1));
						map.put("Tel", cursor.getString(2));
						arrayList.add(map);
						
					}while(cursor.moveToNext());
				}
			}
			cursor.close();
			db.close();
			return arrayList;
			
		}catch(Exception e){
		return null;
		}
		
		
	}

}
