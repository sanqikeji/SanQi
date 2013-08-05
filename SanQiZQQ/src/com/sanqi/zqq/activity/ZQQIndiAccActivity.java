package com.sanqi.zqq.activity;

import net.youmi.android.offers.PointsManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dlnetwork.Dianle;
import com.dlnetwork.GetTotalMoneyListener;
import com.juzi.main.AppConnect;
import com.sanqi.zqq.BaseActivity;
import com.sanqi.zqq.IOPCount;
import com.sanqi.zqq.R;
import com.sanqi.zqq.entity.A;
import com.sanqi.zqq.utils.StringUtils;

/**
 * 个人中心
 * @author ename
 *
 */

public class ZQQIndiAccActivity extends BaseActivity {

	
	private Button btnRight, btnLeft;
	private TextView tvTitle, tvTitleNotice;
	private ImageView ivTitleCancle;
	private RelativeLayout rlTitleNotice;
	
	private TextView tvAccBalance;
	private String name;
	private long amount;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.zqq_indi_acc);
		findViews();
		initDatas();
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
//		int myPointBalance = PointsManager.getInstance(this).queryPoints(); //有米
		
//		String juzi = AppConnect.getInstance(this).getPoints (this);//桔子
//		
//		Log.i("juzi", juzi);
		
		
		/**
		 * 点乐
		 */
//		Dianle.getTotalMoney(new GetTotalMoneyListener() {
//
//			@Override
//			public void getTotalMoneySuccessed(String name0, long amount) {
////				name = name0;
//			}
//
//			@Override
//			public void getTotalMoneyFailed(String error) {
//			}
//		});
//		
//		Dianle.getTotalMoney(this);
		
//		A.accGold = myPointBalance + amount - A.extractGold;
		
		IOPCount accGold = new IOPCount(mContext);
		A.accGold = accGold.getAccGold();
		
		if (StringUtils.isNullOrEmpty(A.accGold+"")) {
			A.accGold = 0;
		}
		tvAccBalance.setText(A.accGold+"");
		
		if(A.isShowNotic){
			rlTitleNotice.setVisibility(View.GONE);
		}
	}
	
//	@Override
//	public void getTotalMoneySuccessed(String name0, long amount0) {
//		name = name0;
//		amount = amount0;
//		message = name + "总额: " + amount + "(" + name + ")";
//		mTextView.setText(message);
//		showOffers.setText("免费获取" + name);
//		getAmountButton.setText("查询我的" + name + "总额");
//		spendMoneyButton.setText("扣除" + name);
//	}
	
	
	private void initDatas() {
		// TODO Auto-generated method stub
	}

	private void findViews() {
		// TODO Auto-generated method stub
		btnRight = (Button) this.findViewById(R.id.btn_right); 
		btnLeft = (Button) this.findViewById(R.id.btn_left);
		tvTitle = (TextView) this.findViewById(R.id.tv_title);
		btnRight.setBackgroundResource(R.drawable.btn_refresh);
		tvTitle.setText("个人中心");
		
		rlTitleNotice = (RelativeLayout) this.findViewById(R.id.rl_title_notice);
		tvTitleNotice = (TextView) this.findViewById(R.id.tv_title_notice);
		if(A.isShowNotic){
			rlTitleNotice.setVisibility(View.GONE);
		}
		
		tvAccBalance = (TextView) this.findViewById(R.id.tv_acc_balance);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.btn_right:
			tvTitle.setText("点击了刷新");
			break;

		case R.id.rl_change_record:
			
			Intent changeIntent = new Intent(getApplicationContext(), ZQQGoldRankActivity.class);
			changeIntent.putExtra("SHOWWHAT", 1);
			startActivity(changeIntent);
			break;
			
		case R.id.rl_rec_peo:
			Intent peoIntent = new Intent(getApplicationContext(), ZQQGoldRankActivity.class);
			peoIntent.putExtra("SHOWWHAT", 2);
			startActivity(peoIntent);
			break;
			
		case R.id.rl_rec_ear:
			Intent earIntent = new Intent(getApplicationContext(), ZQQGoldRankActivity.class);
			earIntent.putExtra("SHOWWHAT", 3);
			startActivity(earIntent);
			break;
			
		case R.id.rl_income_details:
			Intent detailsIntent = new Intent(getApplicationContext(), ZQQGoldRankActivity.class);
			detailsIntent.putExtra("SHOWWHAT", 4);
			startActivity(detailsIntent);
			break;
			
		case R.id.iv_title_cancle:
			A.isShowNotic = true;
			rlTitleNotice.setVisibility(View.GONE);
			break;

		default:
			break;
		}
		
	}

//	@Override
//	public void getTotalMoneyFailed(String arg0) {
//		// TODO Auto-generated method stub
//		
//	}

}
