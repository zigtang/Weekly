package net.zig.weekly;

import net.zig.weekly.base.AppConts;
import net.zig.weekly.base.BaseActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements OnItemClickListener {

	// String[] week ;
	private ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView) findViewById(R.id.lv_main);
		lv.setOnItemClickListener(this);
		lv.setAdapter(new Adapter());
		titleView.setTitleText(dateUtil.getYearMonthWeek());
	}

	class Adapter extends BaseAdapter {
		String[] week = getResources().getStringArray(R.array.week);

		@Override
		public int getCount() {
			return week.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = LayoutInflater.from(context).inflate(R.layout.item_memo, null,false);
			((TextView) convertView.findViewById(R.id.tv_item_dayinweek)).setText(week[position]);
			((TextView) convertView.findViewById(R.id.tv_item_diary)).setText(preferenceUtil.getDiary("" + position));
			convertView.setEnabled(false);
			if ((position+1) == dateUtil.getTodayIndex()) {
				convertView.setBackgroundColor(getResources().getColor(R.color.blue_light));
			}
			return convertView;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent();
		intent.setClass(context, DiaryActivity.class);
		intent.putExtra(AppConts.KEY_ITEM_POSITON, position);
		startActivityForResult(intent, AppConts.REQUEST_CODE_DIARY);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == AppConts.REQUEST_CODE_DIARY
				&& resultCode == RESULT_OK) {
			((BaseAdapter) lv.getAdapter()).notifyDataSetChanged();
		}
	}

}
