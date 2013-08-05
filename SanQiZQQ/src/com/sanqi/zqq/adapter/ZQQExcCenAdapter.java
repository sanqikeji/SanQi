package com.sanqi.zqq.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sanqi.zqq.R;

public class ZQQExcCenAdapter extends BaseAdapter {

	public Context mContext;
	public TextView tvExc;
	
	
	public ZQQExcCenAdapter(Context mContext) {
		super();
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 4;
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
		
		convertView = LayoutInflater.from(mContext).inflate(R.layout.zqq_exchange_item, null);
		tvExc = (TextView) convertView.findViewById(R.id.tv_exc);
		
		switch (position) {
		case 0:
			tvExc.setText("Q币兑换中心");
			break;
		case 1:
			tvExc.setText("话费兑换中心");
			break;
		case 2:
			tvExc.setText("支付宝兑换中心");
			break;
		case 3:
			tvExc.setText("财付通兑换中心");
			break;

		default:
			break;
		}
		
		return convertView;
	}

}
