package net.zig.weekly.base;

import net.zig.weekly.util.SharedPreferenceUtil;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class BaseActivity extends Activity{

	protected SharedPreferenceUtil preferenceUtil;
	protected final String TAG = getClass().getSimpleName();
	protected Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		context = getApplicationContext();
		preferenceUtil = SharedPreferenceUtil.getInstance(context);
//		Calendar.WEEK_OF_MONTH;
//		Calendar.DAY_OF_WEEK;
	}
	
	protected void showToast(String str){
		Toast.makeText(context, str, Toast.LENGTH_LONG).show();
	}

	public void onClick(View v) {
		
	}
	
	

	
	
	

}
