package net.zig.weekly.util;

import java.util.ArrayList;
import java.util.Observer;
import java.util.logging.Logger;

import net.zig.weekly.base.AppConts;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

	private SQLiteDatabase db;
	private Context context;
	private final String TAG = "DBHelper";
	private static DBHelper dbHelper;
	private DBHelper(Context context) {
		super(context, AppConts.DB_NAME,null,1);
		this.context = context;
		db = getWritableDatabase();
	}

	public static DBHelper getInstance(Context context){
		if(dbHelper == null){
			dbHelper = new DBHelper(context);
		}
		return dbHelper;
	}
	 
	 
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table "+AppConts.TABLE_NAME+" ("+
				AppConts.FILED_NAME_ID+" integer primary key autoincrement,"+
				AppConts.FILED_NAME_YEAR+" integer,"+
				AppConts.FILED_NAME_MOUTH+" integer,"+
				AppConts.FILED_NAME_WEEK+" integer,"+
				AppConts.FILED_NAME_DAY+" integer,"+
				AppConts.FILED_NAME_DIARY+" text)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
	public void insert(String string) {
    	ContentValues values = new ContentValues();
    	String[] str = string.split(";");
    	values.put(AppConts.FILED_NAME_SUBJECT, str[0]);
    	values.put(AppConts.FILED_NAME_CONTENT, str[1]);
    	values.put(AppConts.FILED_NAME_TIME, System.currentTimeMillis());	
    	db.insert(AppConts.TABLE_NAME, null, values);
    	notifyChange();
	}
	
	public Cursor getAll(){
		Cursor c = db.query(
				AppConts.TABLE_NAME, 
				new String[]{AppConts.FILED_NAME_ID,AppConts.FILED_NAME_SUBJECT,AppConts.FILED_NAME_TIME,AppConts.FILED_NAME_CONTENT}, 
				null, 
				null, 
				null, 
				null, 
				AppConts.FILED_NAME_ID + " desc");
		return c;
	}



	public Cursor query(ContentValues values) {
		StringBuffer WHERE = new StringBuffer();
		Object keyword =  values.get(AppConts.KEY_HISTORY_KEYWORD);
		Object startTime =  values.get(AppConts.KEY_HISTORY_STARTTIME);
		Object endTime =  values.get(AppConts.KEY_HISTORY_ENDTIME);
		if(keyword != null){
			WHERE.append("("+AppConts.FILED_NAME_SUBJECT+" like '%"+(String)keyword+"%'");
			WHERE.append(" or "+AppConts.FILED_NAME_CONTENT+" like '%"+(String)keyword+"%')");
		}
		
		if(startTime != null && endTime != null){
			if(keyword != null){
				WHERE.append(" and ");
			}
			WHERE.append("("+AppConts.FILED_NAME_TIME +" > " +((Long)startTime).longValue());
			WHERE.append(" and "+AppConts.FILED_NAME_TIME +" < " +((Long)endTime).longValue()+")");
		}
		
		Cursor c = db.query(
				AppConts.TABLE_NAME, 
				new String[]{AppConts.FILED_NAME_ID,AppConts.FILED_NAME_SUBJECT,AppConts.FILED_NAME_TIME,AppConts.FILED_NAME_CONTENT}, 
				WHERE.toString(), 
				null, 
				null, 
				null, 
				AppConts.FILED_NAME_TIME + " desc");
		return c;
	}
	
	public void deleDB(){
		context.deleteDatabase(AppConts.DB_NAME);
		notifyChange();
	}
	
	private ArrayList<Observer> obList = new ArrayList<Observer>();
	public void registerObserver(Observer ob){
		obList.add(ob);
	}
	
	public void unRegisterObserver(Observer ob){
		obList.remove(ob);
	}
	
	private void notifyChange(){
		Logger.i(TAG, "notifyChange()");
		for(Observer ob: obList){
			ob.onChange();
		}
	}

}
