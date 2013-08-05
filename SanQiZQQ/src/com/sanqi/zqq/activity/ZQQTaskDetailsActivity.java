package com.sanqi.zqq.activity;

import net.youmi.android.offers.OffersManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.immob.sdk.ImmobView;
import cn.immob.sdk.LMAdListener;
import cn.immob.sdk.listener.AdUtilityListener;

import com.dlnetwork.Dianle;
import com.juzi.main.AppConnect;
import com.sanqi.zqq.BaseActivity;
import com.sanqi.zqq.R;
import com.sanqi.zqq.entity.A;

public class ZQQTaskDetailsActivity extends BaseActivity implements AdUtilityListener{

	private Button btnRight, btnLeft;
	private TextView tvTitle, tvTitleNotice;
	private ImageView ivTitleCancle;
	private RelativeLayout rlTitleNotice;
	
	private int WHATTASK;
	
	
	private FrameLayout layout = null; 
	 private LinearLayout layer = null; 
	 private String tag = "MainActivity"; 
	 private static ImmobView webView = null; 
	 private ImmobView adview = null; 
	 private LayoutParams layoutParams; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		
//		// 创建新的 ImmobView 对象，请把 123456abcdef 替换为从力美平台申请所获得的广告位 ID。
//		ImmobView lmView=new ImmobView(ZQQTaskDetailsActivity.this, ""); 
//		// 将 lmView 作为当前 activity 的 contentview。
//		this.setContentView(lmView); 
//		// 显示 lmView，
//		lmView.display(); 
		
		setContentView(R.layout.zqq_task_details);
		
		Intent intent = getIntent();
		WHATTASK = intent.getIntExtra("WHATTASK", 0);
		
		findViews();
		initData();
		
		adview = new ImmobView(this, ""); 
		adview.setAdListener(new LMAdListener(){

			@Override
			public void onAdReceived(ImmobView arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onDismissScreen(ImmobView arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onFailedToReceiveAd(ImmobView arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onLeaveApplication(ImmobView arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onPresentScreen(ImmobView arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
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
		tvTitle.setText("任务详情");
		
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

		case R.id.btn_enter_task:
			//调用showOffersWall显示全屏的积分墙界面
			
			if(WHATTASK == 0){
				OffersManager.getInstance(this).showOffersWall(); //有米
			}else if(WHATTASK == 1){
				Dianle.showOffers();							//点乐
			}else{	
				AppConnect.getInstance(this).showOffers(this); //桔子
			}
			
			break;
			
		default:
			break;
		}
	}

	@Override
	public void onReceiveScore(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReceiveScoreFailed(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

}
