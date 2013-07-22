package net.zig.weekly;

import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import net.zig.weekly.base.AppConts;
import net.zig.weekly.base.BaseActivity;

public class DiaryActivity extends BaseActivity {

	EditText etDiary;
	int position = -1;
//	int lineCount = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diary);
		etDiary = (EditText)findViewById(R.id.et_diary);
		
//		etDiary.setOForRenKeyListener(this);
//		etDiary.setSelection(etDiary.getText().toString().length());
		position = getIntent().getExtras().getInt(AppConts.KEY_ITEM_POSITON);
		etDiary.setText(preferenceUtil.getDiary(""+position));
	}
	
	



	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.btn_title_left:
			break;
		case R.id.btn_title_right:
			preferenceUtil.saveDiary(""+position, etDiary.getText().toString());
			setResult(RESULT_OK);
			break;
		}
		
		this.finish();
	}

	
//	@Override
//	public boolean onKey(View v, int keyCode, KeyEvent event) {
//		if(event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_ENTER){
//			int index = etDiary.getSelectionStart();
//			lineCount++;
//			Editable editable = etDiary.getEditableText();
//			String prefix = lineCount+".";
//			if(index < 0 || index > etDiary.length()){
//				editable.append(prefix);
//			} else {
//				editable.insert(index, prefix);
//			}
//		}
//		return false;
//	}
	
	
}
