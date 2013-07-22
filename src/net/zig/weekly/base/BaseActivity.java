package net.zig.weekly.base;

import net.zig.weekly.R;
import net.zig.weekly.util.DateUtil;
import net.zig.weekly.util.SharedPreferenceUtil;
import net.zig.weekly.widgets.TitleView;
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
	protected TitleView titleView;
	protected DateUtil dateUtil;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		context = getApplicationContext();
		preferenceUtil = SharedPreferenceUtil.getInstance(context);
		dateUtil = DateUtil.getInstance();
//		Calendar.WEEK_OF_MONTH;
//		Calendar.DAY_OF_WEEK;
	}
	
	protected void showToast(String str){
		Toast.makeText(context, str, Toast.LENGTH_LONG).show();
	}

	public void onClick(View v) {
		
	}

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		titleView = (TitleView)findViewById(R.id.layout_title);
	}
	
}
