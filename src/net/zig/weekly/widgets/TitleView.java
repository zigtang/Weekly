package net.zig.weekly.widgets;

import net.zig.weekly.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TitleView extends RelativeLayout {

	private String titleStr;
	private String textLeftBtn;
	private String textRightBtn;
	private boolean isLeftBtnGone;
	private boolean isRightBtnGone;
	private TextView tvTitle;
	private Button btnLeft;
	private Button btnRight;

	public TitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.titleView);
		titleStr = typedArray.getString(R.styleable.titleView_text);
		isLeftBtnGone = typedArray.getBoolean(
				R.styleable.titleView_gone_leftBtn, false);
		isRightBtnGone = typedArray.getBoolean(
				R.styleable.titleView_gone_rightBtn, false);
		textLeftBtn = typedArray.getString(R.styleable.titleView_text_leftBtn);
		textRightBtn = typedArray
				.getString(R.styleable.titleView_text_rightbtn);
		typedArray.recycle();
		initUI(context);
		setUI();
	}

	private void setUI() {
		tvTitle.setText(titleStr == null ? "Title" : titleStr);
		if (isLeftBtnGone) {
			btnLeft.setVisibility(View.GONE);
		} else {
			btnLeft.setText(textLeftBtn);
		}

		if (isRightBtnGone) {
			btnRight.setVisibility(View.GONE);
		} else {
			btnRight.setText(textRightBtn);
		}
	}

	private void initUI(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.view_title,
				this);
		tvTitle = (TextView) view.findViewById(R.id.tv_title);
		btnLeft = (Button) view.findViewById(R.id.btn_title_left);
		btnRight = (Button) view.findViewById(R.id.btn_title_right);
	}

	public void setLeftText(String str) {
		btnLeft.setText(str);
	}

	public void setRifhtText(String str) {
		btnRight.setText(str);
	}

	public void setTitleText(String str) {
		tvTitle.setText(str);
	}

}
