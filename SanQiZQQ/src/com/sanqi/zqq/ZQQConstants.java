package com.sanqi.zqq;

/**
 * 这个接口存放我们需要的常量
 * @Name：SoufunConstants
 * @Description：
 * @Author： YWJ
 * @Create： 2012-4-6
 */

public interface ZQQConstants {
	public static final String APP_NAME = "Android_UnMap";//应用名称
	
	/** activity的导航页type，一级页面 */
	public static final byte NAVIGATION_TYPE_MAIN = 0;
	
	/** activity的导航页type，二级页面 */
	public static final byte NAVIGATION_TYPE_SUB = 1;
	
	/** activity的导航页type，其他页面 */
	public static final byte NAVIGATION_TYPE_OTHER = 2;
	/** 退出activity */
	public static final String INTENT_ACTION_EXIT_APP = "com.soufun.org.intent.action.EXIT_APP";
	
	/**
	 * android.os.Build.xx   这样的写法是获取手机和系统的一些信息 
	 */
	/** android系统的版本号  */
	public static final String OS_VERSION = android.os.Build.VERSION.RELEASE;
	/** 手机的版本号  */
	public static final String MODEL = android.os.Build.MODEL;
	
	/** 支持的图像 */
	/** 小图 */
	public static final int PIC_SIZE_SMALL = 1;
	/** 中图 */
	public static final int PIC_SIZE_MIDDLE = 2;
	/** 大图 */
	public static final int PIC_SIZE_LARGE = 3;
	
	
	/** 根目录 */
	public static final String ROOT_DIR_PATH = "/soufun/res";
	/** 缓存目录 */
	public static final String CACHE_DIR_PATH = ROOT_DIR_PATH + "/soufun/cache";
	/** 图片缓存目录 */
	public static final String PIC_CACHE_DIR_PATH = CACHE_DIR_PATH + "/pic_cache";
	
	
	/** 强制升级 */
	public static final String APK_FORCE_UPDATE = "force_update";
	/** 是否能升级 */
    public static final String APK_HAS_UPDATE = "has_update";
    /** 升级url */
    public static final String APK_UPDATE_URL = "update_url";
    /** apk大小 */
    public static final String APK_APP_SIZE = "app_size";
    /** apk描述 */
    public static final String APK_UPDATE_DESCRIBE = "update_describe";
    /** apk名称 */
    public static final String APK_APP_NAME = "app_name";
    /** apk版本 */
    public static final String APK_APP_VERSION = "app_version";
    /** 本地apk版本 */
    public static final String APK_APP_OLD_VERSION = "app_old_version";
    /** 手动更新标识 */
    public static final String APK_MANUAL_UPDATE ="manual_update";
    public static final String RECORD_NAME = "updateProgress";
    
	public static final String KEY_FILE_SIZE = "FileSize";
	public static final String KEY_DOWNLOADED_SIZE = "Downloaded";
	public static final String UPDATE_APK_PATH = "update_apk_path";
	public static final String APK_NAME = "apk_name";
	public static final String DOWNLOADING = "downloading";
	
	/**
	 * 租房
	 */
	public static final String ZF = "zf";
	/**
	 * 二手房
	 */
	public static final String ESF = "esf";
	/**
	 * 新房
	 */
	public static final String NH = "nh";
	/**
	 * 楼盘社区
	 */
	public static final String XQ = "xq";
	
	
	/**
	 * 是否新用户
	 */
	public static final String IS_NEW_USER = "isnewuser";
	public static final String USER_INFO = "user_info";//本地存储当前用户信息的文件名
	
	public static final String USERPHONE = "userphone";//本地存储当前用户账号名

	public static final int BACK_REGISTER = 100;//forResult定义的code
}
