package cn.edu.gdmec.s07131033.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbAdapter extends SQLiteOpenHelper {
	static String DB_NAME = "people";
	static String TABLE_NAME = "peopleinfo";
	static int DB_VERSION = 1; 
	
	String 	KEY_ID = "_id",
			KEY_NAME = "name",
			KEY_AGE = "age",
			KEY_HEIGHT = "height";
	
	SQLiteDatabase db;
	Context _context;
	
	String DB_CREATE = "create table "+TABLE_NAME+"("+
						KEY_ID+" integer primary key autoincrement,"+
						KEY_NAME+" text not null,"+
						KEY_AGE+" integer,"+
						KEY_HEIGHT+" float"+")";

	public DbAdapter(Context context, String name, CursorFactory factory,
			int version) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
		db = this.getWritableDatabase();
		_context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DB_CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVERSION, int newVERSION) {
		String drop = "drop table if exists "+TABLE_NAME;
		db.execSQL(drop);
		onCreate(db);
	}

	private People[] convertCusToPeo(Cursor cursor)
	{
		int rowCount = cursor.getCount();
		if(rowCount == 0||!cursor.moveToFirst())
		{
			return null;
		}
		People[] result = new People[rowCount];
		for(int i = 0;i<rowCount;i++)
		{
			result[i] = new People();
			result[i].Id = cursor.getInt(0);
			result[i].Name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
			result[i].Age = cursor.getInt(cursor.getColumnIndex(KEY_AGE));
			result[i].Height = cursor.getFloat(cursor.getColumnIndex(KEY_HEIGHT));
			cursor.moveToNext();
		}
		return result;
	}
	
	public People[] RetrieveById(long id)
	{
		Cursor cursor = db.query(TABLE_NAME, new String [] {KEY_ID,KEY_NAME,KEY_AGE,KEY_HEIGHT}, KEY_ID+"="+id, null, null, null, null);
		if(cursor==null||!cursor.moveToFirst())
		{
			return null;
		}
		return convertCusToPeo(cursor);
	}
	public People[] RetrieveAll()
	{
		Cursor cursor = db.query(TABLE_NAME, new String[] {KEY_ID,KEY_NAME,KEY_AGE,KEY_HEIGHT}, null, null, null, null, null);
		return convertCusToPeo(cursor);
	}
	
	public long Insert(People people)
	{
		ContentValues newValue = new ContentValues();
		newValue.put(KEY_NAME, people.Name);
		newValue.put(KEY_AGE, people.Age);
		newValue.put(KEY_HEIGHT,people.Height);
		long id = db.insert(TABLE_NAME, null, newValue);
		return id;
	}
	
	public long Update(long id,People people)
	{
		ContentValues updateValue = new ContentValues();
		updateValue.put(KEY_NAME, people.Name);
		updateValue.put(KEY_AGE, people.Age);
		updateValue.put(KEY_HEIGHT,people.Height);
		return db.update(TABLE_NAME, updateValue, KEY_ID+"="+id, null);
	}
	public int DeleteById(long id)
	{
		if(RetrieveById(id)==null)
		{
			return -1;
		}
		return db.delete(TABLE_NAME, KEY_ID+"="+id, null);
	}
	public int DeleteAll()
	{
		return db.delete(TABLE_NAME,null, null);
	}
	
}













