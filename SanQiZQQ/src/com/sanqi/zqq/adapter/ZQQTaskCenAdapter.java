package com.sanqi.zqq.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanqi.zqq.R;

public class ZQQTaskCenAdapter extends BaseAdapter {

	public Context mContext;
	public TextView tvExc, tvTaskList;
	public ZQQTaskCenAdapter(Context mContext) {
		super();
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 10;
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
				R.layout.zqq_task_cen_item, null);
		
		
		tvExc = (TextView) convertView.findViewById(R.id.tv_exc);
		tvTaskList = (TextView) convertView.findViewById(R.id.tv_task_list);
		
//		ivExc.setBackgroundColor(mContext.getResources().getColor(R.color.orange));
		tvTaskList.setText("任务" + position);
		tvExc.setText("点此任务可以获取20~30金币");

		return convertView;
	}

}
