package com.example.test;

import com.example.x06_sqlitecreat.SQLiteCreate;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

public class Test extends AndroidTestCase {
	private SQLiteCreate sql;
	private SQLiteDatabase db;

//	public void test(){
//		SQLiteCreate sql = new SQLiteCreate(getContext(), "people.db", null, 1);
//		SQLiteDatabase db = sql.getReadableDatabase();
//	}
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		sql = new SQLiteCreate(getContext(), "people.db", null, 1);
		db = sql.getReadableDatabase();
	}

	
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		db.close();
	}

	public void insert(){
		db.execSQL("insert into person(name,age,salary)values(?,?,?)",new Object[]{"小张",18,"16000"});
		db.execSQL("insert into person(name,age,salary)values(?,?,?)",new Object[]{"小王",19,"15000"});
		db.execSQL("insert into person(name,age,salary)values(?,?,?)",new Object[]{"小赵",20,"14000"});
		db.execSQL("insert into person(name,age,salary)values(?,?,?)",new Object[]{"小李",21,"14000"});
		db.execSQL("insert into person(name,age,salary)values(?,?,?)",new Object[]{"小何",22,"16000"});
		db.execSQL("insert into person(name,age,salary)values(?,?,?)",new Object[]{"小齐",18,"15000"});
	}
	
	public void delete(){
		db.execSQL("delete from person where name=? ", new Object[]{"小王"});
	}
	
	public void update(){
		db.execSQL("update person set salary=? where name=?", new Object[]{"20000","小齐"});
	}
	
	public void select(){
		Cursor cursor = db.rawQuery("select name,salary from person",null);
		while(cursor.moveToNext()){
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String salary = cursor.getString(cursor.getColumnIndex("salary"));
			System.out.println(name + ":" + salary);
		}
	}
	
	public void insertAPI(){
		
		ContentValues values = new ContentValues();
		values.put("name", "小天");
		values.put("age", 22);
		values.put("salary", "21000");
		db.insert("person", null, values );
	}
	
	public void deleteAPI(){
		int i = db.delete("person", "name = ?", new String[]{"小张"});
		System.out.println(i + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
	}
	
	public void updateAPI(){
		ContentValues values = new ContentValues();
		values.put("name", "小石");
		db.update("person", values , "name = ?", new String[]{"小赵"});
	}
	
	public void selectAPI(){
		Cursor cursor = db.query("person", null, null, null, null, null, null, null);
		while(cursor.moveToNext()){
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String salary = cursor.getString(cursor.getColumnIndex("salary"));
			String age = cursor.getString(cursor.getColumnIndex("age"));
			System.out.println(name + ":  工资" + salary + "，年龄： " + age);
		}
	}
	
	public void transaction(){
		try{
			//开启事务
			db.beginTransaction();
			
			ContentValues values = new ContentValues();
			values.put("salary", "13000");
			db.update("person", values, "name = ?", new String[]{"小明"});
			
			values.clear();
			values.put("salary", "17000");
			db.update("person", values, "name = ?", new String[]{"小李"});
			
			db.setTransactionSuccessful();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//结束事务
			db.endTransaction();
			db.close();
		}
	}
}














