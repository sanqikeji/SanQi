package com.sanqi.zqq.activity;
import net.youmi.android.offers.OffersManager;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;
import android.widget.TextView;

import com.juzi.main.AppConnect;
import com.sanqi.zqq.R;

/**
 * <一句话功能简述>定制居底的TabHost<BR>
 * <功能详细描述>
 * 
 * @author chenli
 * @version [版本号, 2011-1-27]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ZQQHomeActivity extends TabActivity
{
    /**
     * TabHost控件
     */
    private TabHost mTabHost;

    /**
     * TabWidget控件
     */
    private TabWidget mTabWidget;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        mTabHost = this.getTabHost();
        /* 去除标签下方的白线 */
        mTabHost.setPadding(mTabHost.getPaddingLeft(),
                mTabHost.getPaddingTop(), mTabHost.getPaddingRight(),
                mTabHost.getPaddingBottom() - 5);
        

        setTabSpec();

        setTabWidget();
    }

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		// 请务必在应用退出的时候调用以下代码，告诉SDK应用已经关闭，可以让SDK进行一些资源的释放和清理。
	    OffersManager.getInstance(this).onAppExit(); 
	    //桔子平台
	    AppConnect.getInstance(this).finalize();
	}

	public void setTabWidget() {
		/* 对Tab标签的定制 */
        mTabWidget = mTabHost.getTabWidget();
//        mTabWidget.setBackgroundDrawable(rs.getDrawable(R.drawable.navigation_bg));
        for (int i = 0; i < mTabWidget.getChildCount(); i++)
        {
            /* 得到每个标签的视图 */
            View view = mTabWidget.getChildAt(i);
            /* 设置每个标签的背景 */
            if (mTabHost.getCurrentTab() == i)
            {
                view.setBackgroundDrawable(getResources().getDrawable(
                        R.drawable.navigation_select));
            }
            else
            {
//                view.setBackgroundDrawable(getResources().getDrawable(
//                        R.drawable.navigation_select));
                view.setBackgroundColor(getResources().getColor(R.color.transparent));
            }
            /* 设置Tab间分割竖线的颜色 */
//            mTabWidget.setBackgroundColor(Color.WHITE);
            /* 设置Tab间分割竖线的背景图片 */
            // tabWidget.setBackgroundResource(R.drawable.icon);
            /* 设置tab的高度 */
//            mTabWidget.getChildAt(i).getLayoutParams().height = 50;
//            TextView tv = (TextView) mTabWidget.getChildAt(i).findViewById(
//                    android.R.id.title);
            /* 设置tab内字体的颜色 */
//            tv.setTextColor(Color.rgb(49, 116, 171));
        }

        /* 当点击Tab选项卡的时候，更改当前Tab标签的背景 */
        mTabHost.setOnTabChangedListener(new OnTabChangeListener()
        {
            @Override
            public void onTabChanged(String tabId)
            {
                for (int i = 0; i < mTabWidget.getChildCount(); i++)
                {
                    View view = mTabWidget.getChildAt(i);
                    if (mTabHost.getCurrentTab() == i)
                    {
                        view.setBackgroundDrawable(getResources().getDrawable(
                                R.drawable.navigation_select));
                    }
                    else
                    {
//                        view.setBackgroundDrawable(getResources().getDrawable(
//                                R.drawable.navigation_select));
                    	 view.setBackgroundColor(getResources().getColor(R.color.transparent));
                    }
                }
            }
        });
	}

	public void setTabSpec() {
		Resources rs = getResources();
		
		View vTaskCenter = LayoutInflater.from(this).inflate(R.layout.home_tabspec, null);
		ImageView ivTaskCenter = (ImageView) vTaskCenter.findViewById(R.id.iv_tabs);
		ivTaskCenter.setBackgroundDrawable(getResources().getDrawable(R.drawable.n_home_iv));
		TextView tvTaskCenter = (TextView) vTaskCenter.findViewById(R.id.tv_tabs);
		tvTaskCenter.setText("任务中心");
		
		Intent layout1intent = new Intent();
        layout1intent.setClass(this, ZQQTaskCenterActivity.class);
        TabHost.TabSpec layout1spec = mTabHost.newTabSpec("layout1");
        layout1spec.setIndicator(vTaskCenter);
//        layout1spec.setIndicator("任务中心",
//                rs.getDrawable(R.drawable.n_home_iv));
        layout1spec.setContent(layout1intent);
        mTabHost.addTab(layout1spec);

        
        View vIndiAcc = LayoutInflater.from(this).inflate(R.layout.home_tabspec, null);
		ImageView ivIndiAcc = (ImageView) vIndiAcc.findViewById(R.id.iv_tabs);
		ivIndiAcc.setBackgroundDrawable(getResources().getDrawable(R.drawable.n_search_iv));
		TextView tvIndiAcc = (TextView) vIndiAcc.findViewById(R.id.tv_tabs);
		tvIndiAcc.setText("个人账户");
		
        Intent layout2intent = new Intent();
        layout2intent.setClass(this, ZQQIndiAccActivity.class);
        TabHost.TabSpec layout2spec = mTabHost.newTabSpec("layout2");
//        layout2spec.setIndicator("个人账户",
//                rs.getDrawable(R.drawable.n_search_iv));
        layout2spec.setIndicator(vIndiAcc);
        layout2spec.setContent(layout2intent);
        mTabHost.addTab(layout2spec);

        View vExchangeCen = LayoutInflater.from(this).inflate(R.layout.home_tabspec, null);
		ImageView ivExchangeCen = (ImageView) vExchangeCen.findViewById(R.id.iv_tabs);
		ivExchangeCen.setBackgroundDrawable(getResources().getDrawable(R.drawable.n_top_iv));
		TextView tvExchangeCen = (TextView) vExchangeCen.findViewById(R.id.tv_tabs);
		tvExchangeCen.setText("兑换中心");
        
        Intent layout3intent = new Intent();
        layout3intent.setClass(this, ZQQExchangeCenActivity.class);
        TabHost.TabSpec layout3spec = mTabHost.newTabSpec("layout3");
        layout3spec.setIndicator(vExchangeCen);
        layout3spec.setContent(layout3intent);
        mTabHost.addTab(layout3spec);

        View vSysMes = LayoutInflater.from(this).inflate(R.layout.home_tabspec, null);
		ImageView ivSysMes = (ImageView) vSysMes.findViewById(R.id.iv_tabs);
		ivSysMes.setBackgroundDrawable(getResources().getDrawable(R.drawable.n_category_iv));
		TextView tvSysMes = (TextView) vSysMes.findViewById(R.id.tv_tabs);
		tvSysMes.setText("系统消息");
        
        Intent layout4intent = new Intent();
        layout4intent.setClass(this, ZQQSysMessActivity.class);
        TabHost.TabSpec layout4spec = mTabHost.newTabSpec("layout4");
        layout4spec.setIndicator(vSysMes);
        layout4spec.setContent(layout4intent);
        mTabHost.addTab(layout4spec);

        View vMore = LayoutInflater.from(this).inflate(R.layout.home_tabspec, null);
		ImageView ivMore = (ImageView) vMore.findViewById(R.id.iv_tabs);
		ivMore.setBackgroundDrawable(getResources().getDrawable(R.drawable.n_manage_iv));
		TextView tvMore = (TextView) vMore.findViewById(R.id.tv_tabs);
		tvMore.setText("更多...");
        
        Intent layout5intent = new Intent();
        layout5intent.setClass(this, ZQQMoreActivity.class);
        TabHost.TabSpec layout5spec = mTabHost.newTabSpec("layout5");
        layout5spec.setIndicator(vMore);
        layout5spec.setContent(layout5intent);
        mTabHost.addTab(layout5spec);
	}
}
