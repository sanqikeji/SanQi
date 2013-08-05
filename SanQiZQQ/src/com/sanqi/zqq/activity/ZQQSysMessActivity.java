package com.sanqi.zqq.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sanqi.zqq.BaseActivity;
import com.sanqi.zqq.R;
import com.sanqi.zqq.entity.A;

public class ZQQSysMessActivity extends BaseActivity {

	private Button btnRight, btnLeft;
	private TextView tvTitle, tvTitleNotice;
	private ImageView ivTitleCancle;
	private RelativeLayout rlTitleNotice;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.zqq_sys_mess);
		
		findViews();
		initData();
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		if(A.isShowNotic){
			rlTitleNotice.setVisibility(View.GONE);
		}
	}
	
	private void findViews() {
		// TODO Auto-generated method stub
		btnRight = (Button) this.findViewById(R.id.btn_right); 
		btnRight.setVisibility(View.GONE);
		btnLeft = (Button) this.findViewById(R.id.btn_left);
		btnLeft.setVisibility(View.GONE);
		tvTitle = (TextView) this.findViewById(R.id.tv_title);
		tvTitle.setText("系统消息");
		
		rlTitleNotice = (RelativeLayout) this.findViewById(R.id.rl_title_notice);
		tvTitleNotice = (TextView) this.findViewById(R.id.tv_title_notice);
		if(A.isShowNotic){
			rlTitleNotice.setVisibility(View.GONE);
		}
		
	}

	private void initData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_title_cancle:
			A.isShowNotic = true;
			rlTitleNotice.setVisibility(View.GONE);
			break;

			
		default:
			break;
		}
	}

}
