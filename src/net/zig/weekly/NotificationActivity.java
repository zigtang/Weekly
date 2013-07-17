package net.zig.weekly;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

public class NotificationActivity extends Activity{
	NotificationManager nm ;
	static Notification notification;
	String todayDate;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		todayDate = getTodayDate();
		if(notification == null){//第一次进来
			initNC();
		} 
		
		String dakaTime = getStoredDakaTime();
		if(dakaTime != null){//如果已经记录了今天的打卡时间
			setDakaTime(dakaTime);
		} else {//今天没有打卡
			Intent intent = getIntent();
			//Activity有两个入口，一个是第一次进入程序，一个是点击打卡按钮
			if(intent != null && intent.getBooleanExtra("daka", false)){//如果是点击打卡按钮进来的～
				dakaTime = getCurrentTime();
				setDakaTime(dakaTime);
			}
		}
		
		nm.notify(notification.hashCode(), notification);
		//this.finish();
	}



	private void initNC() {
		notification = new Notification(R.drawable.ic_launcher,"", System.currentTimeMillis());
		RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.view_notify);
		
		Intent rtIntent = new Intent(this, NotificationActivity.class);
		rtIntent.putExtra("daka", true);
		PendingIntent ci = PendingIntent.getActivity(this, 0, rtIntent, 0);
		remoteViews.setOnClickPendingIntent(R.id.btn_record_time, ci);
		notification.contentView = remoteViews;
		notification.flags = Notification.FLAG_ONGOING_EVENT;
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,new Intent(), 0);
		notification.contentIntent = contentIntent;
	}
	
	private void setDakaTime(String dakaTime){
		 
		RemoteViews remoteViews = notification.contentView; //调整UI，隐藏Btn,显示时间
		remoteViews.setViewVisibility(R.id.btn_record_time, View.GONE);
		remoteViews.setViewVisibility(R.id.tv, View.VISIBLE);
		remoteViews.setTextViewText(R.id.tv, dakaTime);
		
		
	}
	
	private String getTodayDate(){
		long time = System.currentTimeMillis();
		SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
		return dateformat.format(new Date(time));
	}
	
	private String getCurrentTime(){
		long time = System.currentTimeMillis();
		SimpleDateFormat dateformat=new SimpleDateFormat("HH:mm");
		return dateformat.format(new Date(time));
	}
	
	private String getStoredDakaTime(){
		SharedPreferences preferences = getSharedPreferences("DAKA", MODE_PRIVATE);
		String dakaTime = preferences.getString(todayDate, null);
		return dakaTime;
	}
	
	private void storeDakaTime(String dakaTime){
		SharedPreferences preferences = getSharedPreferences("DAKA", MODE_PRIVATE);
//		String dakaTime = preferences.getString(todayDate, null);
		SharedPreferences.Editor editor = preferences.edit();//将打卡时间存储到本地
		editor.putString(todayDate, dakaTime);
		editor.commit();
	}

}
