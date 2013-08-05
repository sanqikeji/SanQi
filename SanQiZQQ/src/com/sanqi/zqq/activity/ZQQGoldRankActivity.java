package com.sanqi.zqq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sanqi.zqq.BaseActivity;
import com.sanqi.zqq.R;
import com.sanqi.zqq.adapter.ZQQExcCenAdapter;
import com.sanqi.zqq.adapter.ZQQGoldRankAdapter;
import com.sanqi.zqq.entity.A;
import com.sanqi.zqq.utils.StringUtils;

/**
 * 金币排行榜
 * 
 * @author ename
 * 
 */

public class ZQQGoldRankActivity extends BaseActivity {

	private Button btnRight, btnLeft;
	private TextView tvTitle, tvTitleNotice, tvInfo ;
	private ImageView ivTitleCancle;
	private RelativeLayout rlTitleNotice;
	private ZQQGoldRankAdapter goldAdapter;
	private ListView lvGoldRank;

	public int SHOWWHAT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.zqq_gold_rank);

		Intent intent = getIntent();
		SHOWWHAT = intent.getIntExtra("SHOWWHAT", 0);

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
		switch (SHOWWHAT) {
		case 1:
			tvTitle.setText("兑换记录");
			tvInfo.setText("兑换详细记录");
			goldAdapter = new ZQQGoldRankAdapter(this, SHOWWHAT);
			lvGoldRank.setAdapter(goldAdapter);
			break;
		case 2:
			tvTitle.setText("推荐人数");
			tvInfo.setText("推荐好友记录");
			goldAdapter = new ZQQGoldRankAdapter(this, SHOWWHAT);
			lvGoldRank.setAdapter(goldAdapter);
			break;
		case 3:
			tvTitle.setText("推荐收益");
			tvInfo.setText("推荐收益明细");
			goldAdapter = new ZQQGoldRankAdapter(this, SHOWWHAT);
			lvGoldRank.setAdapter(goldAdapter);
			break;
		case 4:
			tvTitle.setText("收益详情");
			
			goldAdapter = new ZQQGoldRankAdapter(this, SHOWWHAT);
			lvGoldRank.setAdapter(goldAdapter);
			break;

		default:
			tvTitle.setText("金币排行");
			tvInfo.setText("");
			goldAdapter = new ZQQGoldRankAdapter(this, SHOWWHAT);
			lvGoldRank.setAdapter(goldAdapter);
			break;
		}
	}

	private void findViews() {
		// TODO Auto-generated method stub
		btnRight = (Button) this.findViewById(R.id.btn_right);
		btnLeft = (Button) this.findViewById(R.id.btn_left);
		tvTitle = (TextView) this.findViewById(R.id.tv_title);
		btnRight.setBackgroundResource(R.drawable.btn_refresh);

		rlTitleNotice = (RelativeLayout) this.findViewById(R.id.rl_title_notice);
		tvTitleNotice = (TextView) this.findViewById(R.id.tv_title_notice);
		if(A.isShowNotic){
			rlTitleNotice.setVisibility(View.GONE);
		}
		
		tvInfo = (TextView) this.findViewById(R.id.tv_info);
		lvGoldRank = (ListView) this.findViewById(R.id.lv_gold_rank);

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
