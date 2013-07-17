package net.zig.weekly.util;

import net.zig.weekly.base.AppConts;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtil {

	private static SharedPreferenceUtil preferenceUtil;
	private SharedPreferences preferences;
	private SharedPreferences.Editor editor;
	private SharedPreferenceUtil(Context context){
		preferences = context.getSharedPreferences(AppConts.PREFER_NAME, context.MODE_PRIVATE);
		editor = preferences.edit();
	}
	
	public static SharedPreferenceUtil getInstance(Context context){
		if(preferenceUtil == null){
			preferenceUtil = new SharedPreferenceUtil(context);
		}
		return preferenceUtil;
	}
	
	public void saveDiary(String position,String diaryStr){
		editor.putString(position, diaryStr);
		editor.commit();
	}
	
	public String getDiary(String position){
		return preferences.getString(position, "");
	}
}
