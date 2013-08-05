package com.sanqi.zqq;

import net.youmi.android.offers.PointsManager;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.dlnetwork.Dianle;
import com.dlnetwork.GetTotalMoneyListener;

/**
 * 收支计算
 * @author ename
 *
 */
public class IOPCount {//implements GetTotalMoneyListener{
	
	public int myPointBalance;
	private String name;
	
	public Context mContext;
	public static long amount;
	public Activity activity;
	
	public IOPCount(Context mContext) {
		super();
		this.mContext = mContext;
	}
	
	public IOPCount(Context mContext, Activity activity) {
		super();
		this.mContext = mContext;
		this.activity = activity;
	}

	/**
	 * 获取账户金币
	 * @return
	 */
	public long getAccGold(){
		
		return getIncomeTotalGold()-getPayTotalGold();
		
	}
	
	/**
	 * 金币总收入
	 * @return 
	 */
	public long getIncomeTotalGold(){
		
		return getTaskIncome() + getRakeGold();
	}
	
	/**
	 * 金币总支出
	 * @return
	 */
	public long getPayTotalGold(){
		return 0;
		
	}
	
	/**
	 * 获取做任务金币收入
	 * @return
	 */
	public long getTaskIncome(){
		long taskIncome = getYouMi() + getDianLe();
		Log.i("getTaskIncome", "youmi" + getYouMi() + "dianle" + getDianLe());
		return taskIncome;
		
	}
	
	/**
	 * 获取抽成金币
	 * @return
	 */
	public long getRakeGold(){
		return 0;
		
	}

	/**
	 * 获取有米金币
	 * @return
	 */
	public long getYouMi(){
		
		/************************有米***********************/
		myPointBalance = PointsManager.getInstance(mContext).queryPoints(); //有米
		/************************有米***********************/
		return myPointBalance;
		
	}

	/**
	 * 获取点乐金币
	 * @return
	 */
	public long getDianLe(){
		/************************点乐***********************/
		GetTotalMoneyListener totalMoney = new GetTotalMoneyListener() {

			@Override
			public void getTotalMoneySuccessed(String name0, long amount0) {
				name = name0;
				amount = amount0;
				
			}
			
			@Override
			public void getTotalMoneyFailed(String error) {
			}
		};
		Dianle.getTotalMoney(totalMoney);
		return amount;
	}
	
}
