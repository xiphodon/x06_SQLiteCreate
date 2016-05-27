package com.example.x06_sqlitecreat;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteCreate extends SQLiteOpenHelper {

	public SQLiteCreate(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		System.out.println("数据库被创建了~~~~~~~~~~~~~~~~~~~~~~~~~");
		db.execSQL("create table person(_id integer primary key autoincrement,name char(10),age integer(5),salary char(10))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		System.out.println("数据库升级了~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

}
