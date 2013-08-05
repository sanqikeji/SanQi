package com.sanqi.zqq.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sanqi.zqq.BaseActivity;
import com.sanqi.zqq.R;
import com.sanqi.zqq.adapter.ZQQExcCenAdapter;
import com.sanqi.zqq.entity.A;
import com.sanqi.zqq.utils.StringUtils;

public class ZQQAccSetActivity extends BaseActivity {

	private Button btnRight, btnLeft;
	private TextView tvTitle, tvTitleNotice;
	private ImageView ivTitleCancle;
	private RelativeLayout rlTitleNotice;
	private EditText etPhone, etQQ, etAlipay, etNickname, etEmail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.zqq_acc_set);
		
		findViews();
		initDatas();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		if(A.isShowNotic){
			rlTitleNotice.setVisibility(View.GONE);
		}
	}
	
	private void initDatas() {
		// TODO Auto-generated method stub
		if(!StringUtils.isNullOrEmpty(A.phone)){
			etPhone.setText(A.phone);
		}
		if (!StringUtils.isNullOrEmpty(A.qq)) {
			etQQ.setText(A.qq);
		}
		if (!StringUtils.isNullOrEmpty(A.alipay)) {
			etAlipay.setText(A.alipay);
		}
		if (!StringUtils.isNullOrEmpty(A.nickname)) {
			etNickname.setText(A.nickname);
		}
		if (!StringUtils.isNullOrEmpty(A.email)) {
			etEmail.setText(A.email);
		}
		
	}

	/**
	 * 
	 * 从输入框中获取信息
	 */
	public void getAccInfo() {
		A.phone = etPhone.getText().toString();
		A.qq = etQQ.getText().toString();
		A.alipay = etAlipay.getText().toString();
		A.email = etEmail.getText().toString();
		A.nickname = etNickname.getText().toString();
	}

	private void findViews() {
		// TODO Auto-generated method stub
		btnRight = (Button) this.findViewById(R.id.btn_right); 
		btnLeft = (Button) this.findViewById(R.id.btn_left);
		tvTitle = (TextView) this.findViewById(R.id.tv_title);
		tvTitle.setText("账户设置");
		
		rlTitleNotice = (RelativeLayout) this.findViewById(R.id.rl_title_notice);
		tvTitleNotice = (TextView) this.findViewById(R.id.tv_title_notice);
		if(A.isShowNotic){
			rlTitleNotice.setVisibility(View.GONE);
		}
		
		etPhone = (EditText) this.findViewById(R.id.et_set_phone);
		etQQ = (EditText) this.findViewById(R.id.et_set_qq);
		etAlipay = (EditText) this.findViewById(R.id.et_set_alipay);
		etNickname = (EditText) this.findViewById(R.id.et_set_nickname);
		etEmail = (EditText) this.findViewById(R.id.et_set_email);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_confirm:
			getAccInfo();
			this.finish();
			break;

		case R.id.btn_cancel:
			this.finish();
			
			break;
		
		case R.id.iv_title_cancle:
			A.isShowNotic = true;
			rlTitleNotice.setVisibility(View.GONE);
			break;
			
		default:
			break;
		}
	}

}
