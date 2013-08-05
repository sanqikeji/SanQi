package com.sanqi.zqq;

import android.app.Activity;
import android.app.TabActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.TextView;
import com.sanqi.zqq.R;
import com.sanqi.zqq.entity.A;
import com.sanqi.zqq.utils.StringUtils;
import com.sanqi.zqq.utils.Utils;
import com.sanqi.zqq.view.BaseLayout;


/**
 * Activity的基础公共类，每个Activity都去继承这个类
 * @Name：BaseActivity
 * @Description：
 * @Author： YWJ
 * @Create： 2012-4-6
 */

public abstract class BaseActivity extends Activity implements OnClickListener {

	public BaseActivity() {
		mContext = this;
		mApp = ZQQApp.getSelf();
	}

	protected Context mContext;
	protected ZQQApp mApp;

	/**
	 * 当前activity的类型
	 * 
	 * @see {@link NeighborConstants#NAVIGATION_TYPE_MAIN} .
	 *      {@link NeighborConstants#NAVIGATION_TYPE_SUB}
	 *      {@link NeighborConstants#NAVIGATION_TYPE_OTHER}
	 */
	protected byte activityType = ZQQConstants.NAVIGATION_TYPE_SUB;

	/**
	 * 当前Activity是否在在最顶端
	 */
	protected boolean mIsFront = false;

	/**
	 * 退出app的时候发送一条广播，所有子类都将finish()
	 */
	private BroadcastReceiver mExitAppReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			finish();
		}
	};

	protected static final int layout_type_normal = 0;
	protected static final int layout_type_header = 1;
	protected static final int layout_type_progress = 2;
	protected static final int layout_type_header_progress = 3;

	protected BaseLayout baseLayout;

	/**
	 * 设置布局
	 * 
	 * @param layoutResId
	 *            布局id
	 * @param type
	 *            (类型：layout_type_normal为一般布局，layout_type_header为有header布局，
	 *            layout_type_progress为有progressbg布局
	 *            ，layout_type_header_progress为既有header布局又有progressbg布局)
	 */
//	protected void setView(int layoutResId, int type) {
//		switch (type) {
//		case layout_type_normal:
//			setContentView(layoutResId);
//
//			break;
//
//		default:
//			baseLayout = new BaseLayout(this, layoutResId, type);
//			setContentView(baseLayout);
//			break;
//		}
//		if (baseLayout != null) {
//			if (baseLayout.btn_refresh != null)
//				baseLayout.btn_refresh.setOnClickListener(this);
//			if (baseLayout.header_btn != null)
//				baseLayout.header_btn.setOnClickListener(this);
//		}
//		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (mContext == this) {
			super.onCreate(savedInstanceState);
			if (!Utils.isNetworkAvailable(this)) {

			}
			
			/** 注册广播  */
			registerReceiver(mExitAppReceiver, new IntentFilter(ZQQConstants.INTENT_ACTION_EXIT_APP));
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		mIsFront = true;
		
	}

	@Override
	protected void onPause() {
		super.onPause();
		mIsFront = false;
	}

//	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.header_btn:
//			handleHeaderEvent();
//			break;
//
////		case R.id.more:
////			handleOnClickMoreView();
////			break;
//
//		case R.id.btn_refresh:
//			handleOnClickProgress();
//			break;
//		}
//	}

	protected View more;
	private TextView tv_more_text;
//	private PageLoadingView40 plv_loading_more;
//
//	protected void setMoreView() {
//		more = LayoutInflater.from(mContext).inflate(R.layout.more, null);
//		tv_more_text = (TextView) more.findViewById(R.id.tv_more_text);
//		plv_loading_more = (PageLoadingView40) more.findViewById(R.id.plv_loading_more);
//		more.setOnClickListener(this);
//	}
//
//	/** 如果统一设置MoreView，处理业务逻辑，子类必须重写此方法 */
//	protected void handleOnClickMoreView() {
//		onPreExecuteMoreView();
//	}
//
//	/**
//	 * 预处理MoreView
//	 */
//	protected void onPreExecuteMoreView() {
//		plv_loading_more.startAnimation();
//		plv_loading_more.setVisibility(View.VISIBLE);
//		tv_more_text.setText(R.string.loading);
//	}
//
//	/**
//	 * 处理完MoreView
//	 */
//	protected void onExecuteMoreView() {
//		plv_loading_more.setVisibility(View.GONE);
//		tv_more_text.setText(R.string.more);
//	}

	/**
	 * 预处理progressbg
	 */
	protected void onPreExecuteProgress() {
		baseLayout.progressbg.setVisibility(View.VISIBLE);
		baseLayout.plv_loading.startAnimation();
		baseLayout.tv_load_error.setVisibility(View.INVISIBLE);
		baseLayout.btn_refresh.setVisibility(View.INVISIBLE);
	}

	/**
	 * 加载失败处理progressbg
	 */
	protected void onExecuteProgressError() {
		baseLayout.plv_loading.stopAnimation();
		AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(500);
		baseLayout.btn_refresh.startAnimation(animation);
		baseLayout.tv_load_error.startAnimation(animation);
		animation.setAnimationListener(new AnimationListener() {
			public void onAnimationStart(Animation animation) {
			}

			public void onAnimationRepeat(Animation animation) {
			}

			public void onAnimationEnd(Animation animation) { // 动画结束时执行此方法
				baseLayout.btn_refresh.setVisibility(View.VISIBLE);
				baseLayout.tv_load_error.setVisibility(View.VISIBLE);
			}
		});
	}

	/**
	 * 加载成功处理progressbg
	 */
	protected void onPostExecuteProgress() {
		AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
		animation.setDuration(400);
		baseLayout.progressbg.startAnimation(animation);
		animation.setAnimationListener(new AnimationListener() {
			public void onAnimationStart(Animation animation) {
			}

			public void onAnimationRepeat(Animation animation) {
			}

			public void onAnimationEnd(Animation animation) { // 动画结束时执行此方法
				baseLayout.progressbg.setVisibility(View.GONE);
			}
		});
	}

	/** 如果统一设置progress，处理业务逻辑，子类必须重写此方法 */
	protected void handleOnClickProgress() {
		onPreExecuteProgress();
	}

	/**
	 * 设置HeaderBar的内容
	 * 
	 * @param title
	 *            中间Text文本 （不能为空）
	 * @param btn_text
	 *            右边按钮文本
	 */
	protected void setHeaderBar(String title, String btn_text) {
		baseLayout.setTitleAndButton(title, btn_text);
	}

	/**
	 * 设置HeaderBar的内容
	 * 
	 * @param title
	 *            中间Text文本 （不能为空）
	 * @param btn_imageId
	 *            右边按钮图片
	 */
	protected void setHeaderBar(String title, int btn_imageId) {
		baseLayout.setTitleAndButton(title, btn_imageId);
	}

	/**
	 * 设置HeaderBar的内容
	 * 
	 * @param title
	 *            中间Text文本 （不能为空）
	 */
	protected void setHeaderBar(String title) {
		baseLayout.setTitleAndButton(title);
	}

	/**
	 * 处理Header右边按钮点击事件
	 * 
	 */
	protected void handleHeaderEvent() {
		Log.i("33333", "33333");
	}

	protected void exitActivity() {
//		 if (activityType == NeighborConstants.NAVIGATION_TYPE_MAIN) {
//		 exitApp();
//		 } else {
//		 overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
//		 finish();
//		 }
	}

	protected void setActivityType(byte _type) {
		activityType = _type;
	}

	/**
	 * <功能详细描述>是否响应物理按键到搜索，onKeyDown中调用<br>
	 * 
	 * @return<br>
	 */
	protected boolean isCanSearch() {
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_SEARCH) {
			if (!isCanSearch()) {
				return true;
			}
			// Intent intentSearch = new Intent(Intent.ACTION_VIEW,
			// Uri.parse(AppConst.SEARCH_URI));
			// intentSearch.putExtra(AppConst.PARA_SEARCH_TAB, getSearchTab());
			// mActualContext.startActivity(intentSearch);
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (event.getRepeatCount() == 0) {
				finish();
			}

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onDestroy() {
		if (mContext == this) {
			unregisterReceiver(mExitAppReceiver);
		}

		if (null != mApp) {
			mApp.pull((Activity) mContext);
		}
		super.onDestroy();
	}

	/**
	 * toast（默认 时间Toast.LENGTH_LONG）
	 * 
	 * @param msg
	 *            内容
	 */
	protected void toast(String msg) {
		if (StringUtils.isNullOrEmpty(msg)) {
			return;
		}
		Utils.toast(mContext, msg, true);
	}

	/**
	 * toast（默认 时间Toast.LENGTH_LONG）
	 * 
	 * @param msg
	 *            内容
	 * @param duration
	 *            时间
	 */
	protected void toast(int resid) {
		Utils.toast(mContext, resid, mIsFront);
	}

	/**
	 * toast
	 * 
	 * @param resid
	 *            内容资源id
	 * @param duration
	 *            时间
	 */
	protected void toast(String msg, int duration) {
		if (StringUtils.isNullOrEmpty(msg)) {
			return;
		}
		Utils.toast(mContext, msg, mIsFront, duration);
	}

	protected void toast(int resid, int duration) {
		Utils.toast(mContext, resid, mIsFront, duration);
	}

	protected void exitApp() {

		if (getParent() instanceof TabActivity) {
			mContext = getParent();
		}

		// DialogView.exit(mContext);

	}

	/**
	 * 检查软件版本更新信息
	 * 
	 * @return
	 */
	protected void checkForUpDate() {
//		 mApp.getUpdateManager().checkForUpDate();
	}

	/**
	 * 启动activity带有动画切换
	 * 
	 * @param intent
	 * @param requestCode
	 */
	protected void startActivityForResultAndAnima(Intent intent, int requestCode) {
		startActivityForResultAndAnima(intent, requestCode, null);
	}

	/**
	 * 启动activity带有动画切换
	 * 
	 * @param intent
	 */
	protected void startActivityForAnima(Intent intent) {
		startActivityForAnima(intent, null);
	}

	/**
	 * 启动activity带有动画切换
	 * 
	 * @param intent
	 * @param requestCode
	 * @param parentActivity
	 */
	protected void startActivityForResultAndAnima(Intent intent, int requestCode, Activity parentActivity) {
		if (intent != null) {
			if (parentActivity != null) {
				parentActivity.startActivityForResult(intent, requestCode);
//				parentActivity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
			} else { 
				startActivityForResult(intent, requestCode);
//				overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
			}
		}
	}

	/**
	 * 启动activity带有动画切换
	 * 
	 * @param intent
	 * @param parentActivity
	 */
	protected void startActivityForAnima(Intent intent, Activity parentActivity) {
		if (intent != null) {
			if (parentActivity != null) {
				parentActivity.startActivity(intent);
//				parentActivity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
			} else {
				startActivity(intent);
//				overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
			}
		}
	}

	@Override
	public View findViewById(int id) {
		if (baseLayout != null) {
			return baseLayout.findViewById(id);
		} else {
			return super.findViewById(id);
		}

	}
}
