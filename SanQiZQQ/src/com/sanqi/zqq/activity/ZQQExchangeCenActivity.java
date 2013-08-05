package com.sanqi.zqq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.sanqi.zqq.entity.A;
import com.sanqi.zqq.utils.StringUtils;

/**
 * 兑换中心
 * 
 * @author ename
 * 
 */

public class ZQQExchangeCenActivity extends BaseActivity {

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
				switch (position) {
				case 0:
					if (StringUtils.isNullOrEmpty(A.qq)) {
						Intent intent = new Intent(getApplicationContext(),
								ZQQAccSetActivity.class);
						startActivity(intent);

					} else {
						Intent intent = new Intent(getApplicationContext(), ZQQExcCenDetailActivity.class);
						startActivity(intent);
					}

					break;
				case 1:
					if (StringUtils.isNullOrEmpty(A.phone)) {
						Intent intent = new Intent(getApplicationContext(),
								ZQQAccSetActivity.class);
						startActivity(intent);

					} else {
						Intent intent = new Intent(getApplicationContext(), ZQQExcCenDetailActivity.class);
						startActivity(intent);
					}
					break;
				case 2:
					if (StringUtils.isNullOrEmpty(A.alipay)) {
						Intent intent = new Intent(getApplicationContext(),
								ZQQAccSetActivity.class);
						startActivity(intent);

					} else {
						Intent intent = new Intent(getApplicationContext(), ZQQExcCenDetailActivity.class);
						startActivity(intent);
					}
					break;
				case 3:
					if (StringUtils.isNullOrEmpty(A.email)) {
						Intent intent = new Intent(getApplicationContext(),
								ZQQAccSetActivity.class);
						startActivity(intent);

					} else {
						Intent intent = new Intent(getApplicationContext(), ZQQExcCenDetailActivity.class);
						startActivity(intent);
					}
					break;

				default:
					break;
				}
//				if (StringUtils.isNullOrEmpty(A.qq)) {
//					Intent intent = new Intent(getApplicationContext(),
//							ZQQAccSetActivity.class);
//					startActivity(intent);
//
//				} else {
//					//
//				}
//
//				A.extractGold = A.extractGold + 10;

			}
		});
	}

	private void findViews() {
		// TODO Auto-generated method stub
		btnRight = (Button) this.findViewById(R.id.btn_right);
		btnLeft = (Button) this.findViewById(R.id.btn_left);
		tvTitle = (TextView) this.findViewById(R.id.tv_title);
		btnRight.setBackgroundResource(R.drawable.btn_refresh);
		tvTitle.setText("兑换中心");

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
