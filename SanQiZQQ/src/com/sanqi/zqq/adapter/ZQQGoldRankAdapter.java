package com.sanqi.zqq.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sanqi.zqq.R;

public class ZQQGoldRankAdapter extends BaseAdapter {

	public Context mContext;
	public int SHOWWHAT; // 要显示的样式
	public TextView tvRank, tvUser, tvGold;

	public ZQQGoldRankAdapter(Context mContext) {
		super();
		this.mContext = mContext;
	}

	public ZQQGoldRankAdapter(Context mContext, int sHOWWHAT) {
		super();
		this.mContext = mContext;
		SHOWWHAT = sHOWWHAT;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 10+1;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		convertView = LayoutInflater.from(mContext).inflate(
				R.layout.zqq_rank_item, null);
		tvRank = (TextView) convertView.findViewById(R.id.tv_rank);
		tvUser = (TextView) convertView.findViewById(R.id.tv_user);
		tvGold = (TextView) convertView.findViewById(R.id.tv_gold);
		switch (SHOWWHAT) {
		case 1:
			if (position < 1) {
				tvRank.setText("时间");
				tvUser.setText("记录");
				tvGold.setText("状态");
			} else {
				tvRank.setText("2013-4-"+ position);
				tvUser.setText("温玲玲兑换"+ (10000*position) +"元支付宝");
				tvGold.setText("成功");
			}
			break;

		case 2:
			tvGold.setVisibility(View.GONE);
			if (position < 1) {
				tvRank.setText("时间");
				tvUser.setText("好友ID");
//				tvGold.setText("金币");
			} else {
				tvRank.setText("第" + position + "名");
				tvUser.setText("" + position + "次");
//				tvGold.setText((position * 50) + "");
			}
			break;

		case 3:
			tvGold.setVisibility(View.GONE);
			if (position < 1) {
				tvRank.setText("好友ID");
				tvUser.setText("金额");
//				tvGold.setText("金币");
			} else {
				tvRank.setText("第" + position + "名");
				tvUser.setText("" + position + "次");
//				tvGold.setText((position * 50) + "");
			}
			break;

		case 4:
			if (position < 1) {
				tvRank.setText("排名");
				tvUser.setText("用户");
				tvGold.setText("金币");
			} else {
				tvRank.setText("第" + position + "名");
				tvUser.setText("" + position + "次");
				tvGold.setText((position * 50) + "");
			}
			break;

		default:
			if (position < 1) {
				tvRank.setText("排名");
				tvUser.setText("用户");
				tvGold.setText("金币");
			} else {
				tvRank.setText("第" + position + "名");
				tvUser.setText("" + position + "次");
				tvGold.setText((position * 50) + "");
			}
			
			break;
		}
		

		return convertView;
	}

}
