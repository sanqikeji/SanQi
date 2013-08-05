package com.sanqi.zqq.view;

import com.sanqi.zqq.R;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class BaseLayout extends LinearLayout {

	private static final int HEADER = 1;
	private static final int PROGRESS = 2;
	private static final int HEADERANDPROGRESS = 3;
	
	private Context mContext;
	private TextView tv_header;
	private TextView tv_btn_header;
	private ImageView iv_btn_header;
	private LinearLayout ll_header_right;
	private LinearLayout header_button;
	public View header_bar, header_btn;

	public View progressbg;
	public Button btn_refresh;
	public PageLoadingView plv_loading;
	public TextView tv_load_error;
	
	
	public BaseLayout(Context context, int layoutResourceId, int type) {
		super(context);
		super.setOrientation(VERTICAL);
		mContext = context;
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		switch (type) {
		case PROGRESS:
			setProgressBg(layoutInflater);
			break;
		case HEADER:
			setHeaderBar(layoutInflater);
			break;
 
		case HEADERANDPROGRESS:
			setProgressBg(layoutInflater);
			setHeaderBar(layoutInflater);
			break;
		}
		
		View view = layoutInflater.inflate(layoutResourceId, null);
		addView(view);
		
		header_button = (LinearLayout) layoutInflater.inflate(R.layout.header_btn, null);
		ll_header_right.addView(header_button, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		tv_btn_header = (TextView) header_button.findViewById(R.id.tv_btn_header);
		iv_btn_header = (ImageView) header_button.findViewById(R.id.iv_btn_header);
	}

	protected void setHeaderBar(LayoutInflater layoutInflater) {
		header_bar = layoutInflater.inflate(R.layout.header_bar, null);
		tv_header = (TextView) header_bar.findViewById(R.id.tv_header);
		ll_header_right = (LinearLayout) header_bar.findViewById(R.id.ll_header_right);
		addView(header_bar);
//		header_bar.setVisibility(View.GONE);
	}

	protected void setProgressBg(LayoutInflater layoutInflater) {
		progressbg = layoutInflater.inflate(R.layout.process_page, null);
		plv_loading = (PageLoadingView) progressbg.findViewById(R.id.plv_loading);
		tv_load_error = (TextView) progressbg.findViewById(R.id.tv_load_error);
		btn_refresh = (Button) progressbg.findViewById(R.id.btn_refresh);
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		addView(progressbg, params);
	}

	private void setHeaderTitle(String title) {
		tv_header = (TextView) header_bar.findViewById(R.id.tv_header);
		ll_header_right = (LinearLayout) header_bar.findViewById(R.id.ll_header_right);
		header_btn = LayoutInflater.from(mContext).inflate(R.layout.header_btn, null);
		ll_header_right.addView(header_btn, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		tv_btn_header = (TextView) header_btn.findViewById(R.id.tv_btn_header);
		iv_btn_header = (ImageView) header_btn.findViewById(R.id.iv_btn_header);
		if (title != null) {
			tv_header.setVisibility(View.VISIBLE);
			tv_header.setText(title);
		} else {
			tv_header.setVisibility(View.GONE);
		}
		if(title != null) {
			tv_header.setVisibility(View.VISIBLE);
			tv_header.setText(title);
		} else {
			tv_header.setVisibility(View.GONE);
		}
	}
	
	private void setHeaderBtn(String btn_text) {
		if (btn_text != null) {
			tv_btn_header.setVisibility(View.VISIBLE);
			iv_btn_header.setVisibility(View.GONE);
			tv_btn_header.setText(btn_text);
		}
	}
	
	private void setHeaderBtn(int btn_imageId) {
		if (btn_imageId != -1000) {
			iv_btn_header.setVisibility(View.VISIBLE);
			tv_btn_header.setVisibility(View.GONE);
			iv_btn_header.setImageResource(btn_imageId);
		}
	}
	
	public void setTitleAndButton(String title, String btn_text) {
		setHeaderTitle(title);
		setHeaderBtn(btn_text);
	}

	public void setTitleAndButton(String title, int btn_imageId) {
		setHeaderTitle(title);
		setHeaderBtn(btn_imageId);
	}

	public void setTitleAndButton(String title) {
		setHeaderTitle(title);
	}
}
