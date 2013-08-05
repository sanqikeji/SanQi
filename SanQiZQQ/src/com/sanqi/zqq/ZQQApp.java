package com.sanqi.zqq;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


/**
 * 继承了application就是在我们启动一个程序的时候就创建了这些
 * @Name：SoufunApp
 * @Description：
 * @Author： YWJ
 * @Create： 2012-4-6
 */

public class ZQQApp extends Application {
	private static ZQQApp mApp;
	private List<Activity> mActivitys = new ArrayList<Activity>();
	public static String CID ="-1";
	private String currentCity;
//	private RemotePictureManager mRemoteImageManager;

	@Override
	public void onCreate() {
		super.onCreate();

		mApp = (ZQQApp) getApplicationContext();
		toastMgr.builder.init(mApp);
		/** 如果应用没有清理缓存功能，则在应用启动时清理缓存 */
	}

	/**	获取mApp本身	*/
	public static ZQQApp getSelf() {
		return mApp;
	}
	
//	/** 用户	*/
//	private User user;
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//	
	
	public String getCID() {
		return CID;
	}

	public void setCID(String cID) {
		CID = cID;
	}
	
	public String getCurrentCity() {
		return currentCity;
	}
	
	/**	清除信息	*/
	public void clearInfo() {
		 //mPictrueManager.clearCache();
		 //mPictrueManager = null;
	}
	
	
	public void exit() {
		clearInfo();
		sendBroadcast(new Intent(ZQQConstants.INTENT_ACTION_EXIT_APP));
		// 延迟半秒杀进程
//		 new Handler().postDelayed(new Runnable() {
//		
//		 @Override
//		 public void run() {
//		 Process.killProcess(Process.myPid());
//		 }
//		 }, 500);
	}

//	/**	远程资源图片管理方法	*/
//	public RemotePictureManager getRemoteResourceManager() {
//		if (mRemoteImageManager == null) {
//			mRemoteImageManager = new RemotePictureManager(getSelf());
//		}
//		return mRemoteImageManager;
//	}

	/**	把Activity加入List集合中	*/
	public void push(Activity c) {
		for (int i = 0; i < mActivitys.size(); i++) {
			if (mActivitys.get(i) == c) {
				return;
			}
		}
		mActivitys.add(c);
	}
	
	/**	把Activity从List集合中取出并弹出	*/
	public void pull(Activity c) {
		for (int i = 0; i < mActivitys.size(); i++) {
			if (mActivitys.get(i) == c) {
				mActivitys.remove(i);
				return;
			}
		}
	}

	/**
	 * toast singleton，用来统一显示toast，这样就可以实现toast的快速刷新
	 * 
	 */
	public enum toastMgr {
		builder;

		private View v;
		private TextView tv;
		private Toast toast;

		private void init(Context c) {
//			 v = Toast.makeText(c, "", Toast.LENGTH_SHORT).getView();
			v = LayoutInflater.from(c).inflate(R.layout.toast, null);
			tv = (TextView) v.findViewById(R.id.tv_toast);
			toast = new Toast(c);
			toast.setView(v);
		}

		public void display(CharSequence text, int duration) {
			if (text.length() != 0) {
				tv.setText(text);
				toast.setDuration(duration);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}
		}

		public void display(int Resid, int duration) {
			if (Resid != 0) {
				tv.setText(Resid);
				toast.setDuration(duration);
				toast.show();
			}
		}
	}

	
}
