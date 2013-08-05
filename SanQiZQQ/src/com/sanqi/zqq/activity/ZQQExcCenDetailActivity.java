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
import android.widget.Toast;

import com.sanqi.zqq.BaseActivity;
import com.sanqi.zqq.R;
import com.sanqi.zqq.adapter.ZQQExcCenAdapter;
import com.sanqi.zqq.entity.A;
import com.sanqi.zqq.utils.StringUtils;

/**
 * 兑换中心详情
 * 
 * @author ename
 * 
 */

public class ZQQExcCenDetailActivity extends BaseActivity {

	private Button btnRight, btnLeft;
	private TextView tvTitle, tvTitleNotice;
	private ImageView ivTitleCancle;
	private RelativeLayout rlTitleNotice;
	private ZQQExcCenAdapter excAdapter;
	private ListView lvGoldExchange;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.zqq_exchange_cen);

		findViews();
		initDatas();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		if (A.isShowNotic) {
			rlTitleNotice.setVisibility(View.GONE);
		}
	}

	private void initDatas() {
		// TODO Auto-generated method stub

		excAdapter = new ZQQExcCenAdapter(this);
		lvGoldExchange.setAdapter(excAdapter);
		lvGoldExchange.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "玲玲我爱你，点击这里可以兑换金币，可以买好多好吃的。", Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void findViews() {
		// TODO Auto-generated method stub
		btnRight = (Button) this.findViewById(R.id.btn_right);
		btnLeft = (Button) this.findViewById(R.id.btn_left);
		tvTitle = (TextView) this.findViewById(R.id.tv_title);
		btnRight.setBackgroundResource(R.drawable.btn_refresh);
		tvTitle.setText("Q币兑换中心");

		rlTitleNotice = (RelativeLayout) this
				.findViewById(R.id.rl_title_notice);
		tvTitleNotice = (TextView) this.findViewById(R.id.tv_title_notice);
		if (A.isShowNotic) {
			rlTitleNotice.setVisibility(View.GONE);
		}

		lvGoldExchange = (ListView) this.findViewById(R.id.lv_gold_exchange);

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
