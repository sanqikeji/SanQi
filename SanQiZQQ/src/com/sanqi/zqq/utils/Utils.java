package com.sanqi.zqq.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import com.sanqi.zqq.R;
import com.sanqi.zqq.ZQQApp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Utils {
	
	
	/**
	 * 弹出SoufunProcessDialog
	 * 
	 */
	public static void showProcessDialog(Context context) {
		Dialog customDialog = new Dialog(context, R.style.Theme_Light_ProcessDialog_Blue);
		customDialog.setContentView(R.layout.process_dialog);
		customDialog.findViewById(R.id.piv_loading_process).setVisibility(View.VISIBLE);
		customDialog.show();
	}

	/**
	 * 弹出SoufunProcessDialog
	 * 
	 * @param content
	 *            显示内容
	 */
	public static void showProcessDialog(Context context, String content) {
		Dialog customDialog = new Dialog(context, R.style.Theme_Light_ProcessDialog_Blue);
		customDialog.setContentView(R.layout.process_dialog);
		customDialog.findViewById(R.id.piv_loading_process).setVisibility(View.VISIBLE);
		((TextView) customDialog.findViewById(R.id.tv_process)).setText(content);
		customDialog.show();
	}

	/**
	 * 获得指定目录的剩余空间.
	 * 
	 * @param path
	 * @return
	 */
	public static long getFreeSpace(String path) {
		StatFs sf = new StatFs(path);
		long blockSize = sf.getBlockSize();
		long availCount = sf.getAvailableBlocks();

		return availCount * blockSize;
	}

	/**
	 * toast （默认 时间Toast.LENGTH_LONG）
	 * 
	 * @param c
	 * @param msg
	 *            内容
	 */
	public static void toast(Context c, String msg) {
		toast(c, msg, true);
	}

	/**
	 * toast
	 * 
	 * @param c
	 * @param msg
	 *            内容
	 * @param duration
	 *            时间
	 */
	public static void toast(Context c, String msg, int duration) {
		toast(c, msg, true, duration);
	}

	/**
	 * toast 时间（默认 时间Toast.LENGTH_LONG）
	 * 
	 * @param c
	 * @param msg
	 *            内容
	 * @param show
	 *            是否显示
	 */
	public static void toast(Context c, String msg, boolean show) {
		toast(c, msg, show, Toast.LENGTH_LONG);
	}

	/**
	 * toast
	 * 
	 * @param c
	 * @param msg
	 *            内容
	 * @param show
	 *            是否显示
	 * @param duration
	 *            时间
	 */
	public static void toast(Context c, String msg, boolean show, int duration) {
		if (!show)
			return;
		ZQQApp.toastMgr.builder.display(msg, duration);
	}

	/**
	 * toast 时间（默认 时间Toast.LENGTH_LONG）
	 * 
	 * @param c
	 * @param resid
	 *            内容资源id
	 */
	public static void toast(Context c, int resid) {
		toast(c, resid, true);
	}

	/**
	 * toast
	 * 
	 * @param c
	 * @param resid
	 *            内容资源id
	 * @param duration
	 *            时间
	 */
	public static void toast(Context c, int resid, int duration) {
		toast(c, resid, true, duration);
	}

	/**
	 * toast 时间（默认 时间Toast.LENGTH_LONG）
	 * 
	 * @param c
	 * @param resid
	 *            内容资源id
	 * @param show
	 *            是否显示
	 */
	public static void toast(Context c, int resid, boolean show) {
		toast(c, resid, show, Toast.LENGTH_LONG);
	}

	/**
	 * toast
	 * 
	 * @param c
	 * @param resid
	 *            内容资源id
	 * @param show
	 *            是否显示
	 * @param duration
	 *            时间
	 */
	public static void toast(Context c, int resid, boolean show, int duration) {
		if (!show)
			return;
		ZQQApp.toastMgr.builder.display(resid, duration);
	}

	/**
	 * 检查SDCard是否存在
	 * 
	 * @return
	 */
	public static boolean checkSDCardPresent() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
			return true;
		else
			return false;

	}

	/**
	 * 检查SDCard是否可读
	 * 
	 * @return
	 */
	public static boolean checkSDCardRead() {
		if (checkSDCardPresent())
			return Environment.getExternalStorageDirectory().canRead();
		else
			return false;
	}

	/**
	 * 检查SDCard是否可写
	 * 
	 * @return
	 */
	public static boolean checkSDCardWriter() {
		if (checkSDCardPresent())
			return Environment.getExternalStorageDirectory().canWrite();
		else
			return false;
	}

	/**
	 * 检查sdcard的剩余容量是否超过size
	 * 
	 * @param size
	 *            单位是KB
	 * @return
	 */
	public static boolean checkSDCardCapacity(int size) {
		// 取得sdcard文件路径
		File pathFile = android.os.Environment.getExternalStorageDirectory();
		android.os.StatFs statfs = new android.os.StatFs(pathFile.getPath());
		// 获取SDCard上每个block的SIZE
		long blocSize = statfs.getBlockSize();
		// 获取可供程序使用的Block的数量
		long availaBlock = statfs.getAvailableBlocks();
		if ((availaBlock * blocSize / 1024) > size)
			return true;
		else
			return false;
	}

	/**
	 * 检查sdcard中是否存在制定路径的文件
	 * 
	 * @param path
	 * @return
	 */
	public static boolean checkSDCardFile(String path) {
		if (path == null || "".equals(path.trim()))
			return false;
		File file = new File(path);
		return file.exists();
	}

	/**
	 * 解压数据库文件并保存(注意这个方法比较耗时请在线程里使用)
	 * 
	 * @param fileName
	 *            数据库文件名称
	 * @param context
	 * @throws Exception
	 */
	public static void UnZipFolder(String fileName, Context context) throws Exception {
		// 获取指定数据库绝对路径
		String outPathString = context.getDatabasePath("db").getAbsolutePath() + "/";
		outPathString = outPathString.substring(0, outPathString.length() - 3);
		UnZipFolder(fileName, outPathString, context);
	}

	public static void UnZipFolder(String fileName, String outPathString, Context context) throws Exception {
		InputStream in = context.getAssets().open(fileName);
		java.util.zip.ZipInputStream inZip = new java.util.zip.ZipInputStream(in);
		java.util.zip.ZipEntry zipEntry;
		String szName = "";
		while ((zipEntry = inZip.getNextEntry()) != null) {
			szName = zipEntry.getName();
			if (zipEntry.isDirectory()) {
				szName = szName.substring(0, szName.length() - 1);
				java.io.File folder = new java.io.File(outPathString + java.io.File.separator + szName);
				folder.mkdirs();
			} else {
				java.io.File file = new java.io.File(outPathString + java.io.File.separator + szName);
				if(!file.getParentFile().exists()){
					file.getParentFile().mkdirs();
				}
				file.createNewFile();
				java.io.FileOutputStream out = new java.io.FileOutputStream(file);
				int len;
				byte[] buffer = new byte[1024];
				while ((len = inZip.read(buffer)) != -1) {
					out.write(buffer, 0, len);
					out.flush();
				}
				out.close();
			}
		}
		inZip.close();
	}

	/**
	 * 保存数据库文件(注意这个方法比较耗时请在线程里使用)
	 * 
	 * @param context
	 * @param fileName
	 * @param path
	 * @return
	 */
	public static boolean retrieveApkFromAssets(String fileName, Context context) {
		String outPathString = context.getDatabasePath("db").getAbsolutePath() + "/";
		outPathString = outPathString.substring(0, outPathString.length() - 3);
		return retrieveApkFromAssets(fileName, outPathString, context);
	}

	public static boolean retrieveApkFromAssets(String fileName, String outPathString, Context context) {
		boolean bRet = false;

		try {
			InputStream is = context.getAssets().open(fileName);

			File file = new File(outPathString + "/" + fileName);
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);

			byte[] temp = new byte[1024];
			int i = 0;
			while ((i = is.read(temp)) > 0) {
				fos.write(temp, 0, i);
			}

			fos.close();
			is.close();

			bRet = true;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return bRet;
	}

	/**
	 * 隐藏软键盘
	 * 
	 * @param activity
	 *            要隐藏软键盘的activity
	 */
	public static void hideSoftKeyBoard(Activity activity) {
		final View v = activity.getWindow().peekDecorView();
		if (v != null && v.getWindowToken() != null) {
			try {
				((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
						activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 显示软键盘
	 * 
	 * @param context
	 * @param editText
	 */
	public static void showSoftKeyBroad(Context context, EditText editText) {
		InputMethodManager mgr = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		// only will trigger it if no physical keyboard is open
		mgr.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
	}

	/**
	 * <功能详细描述>判断网络是否可用<br>
	 * 
	 * @param context
	 * @return<br>
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 隐藏软键盘
	 * 
	 * @param context
	 * @param editText
	 */
	public static void hideSoftKeyBroad(Context context, EditText editText) {
		InputMethodManager mgr = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		mgr.hideSoftInputFromWindow(editText.getWindowToken(), 0);
	}

	/**
	 * 显示软键盘，和上面的showSoftKeyBroad方法的区别在于，如果从其他activity返回的时候需要延迟一点才能显示软键盘
	 * 
	 * @param context
	 * @param editText
	 */
	public static void showKeyBoardLater(final Context context, final EditText editText) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				showSoftKeyBroad(context, editText);
			}
		}, 500);
	}

	/**
	 * 显示软键盘，和上面的showSoftKeyBroad方法的区别在于，如果从其他activity返回的时候需要延迟一点才能显示软键盘
	 * 
	 * @param context
	 * @param editText
	 */
	public static void showKeyBoardLater(final Context context, final EditText editText, long laterTime) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				showSoftKeyBroad(context, editText);
			}
		}, laterTime);
	}

	/**
	 * 获取网络连接类型
	 * 
	 * @return -1表示没有网络
	 */
	public static final int TYPE_WIFI = 0;
	public static final int TYPE_3G = 1;
	public static final int TYPE_GPRS = 2;

	public static final int getNetWorkType(Context c) {
		ConnectivityManager conn = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (conn == null) {
			return -1;
		}
		NetworkInfo info = conn.getActiveNetworkInfo();
		if (info == null || !info.isAvailable()) {
			return -1;
		}

		int type = info.getType(); // MOBILE（GPRS）;WIFI
		if (type == ConnectivityManager.TYPE_WIFI) {
			return TYPE_WIFI;
		} else {
			TelephonyManager tm = (TelephonyManager) c.getSystemService(Context.TELEPHONY_SERVICE);
			switch (tm.getNetworkType()) {
			case TelephonyManager.NETWORK_TYPE_CDMA:
				return TYPE_GPRS;
			case TelephonyManager.NETWORK_TYPE_EDGE:
				return TYPE_GPRS;
			case TelephonyManager.NETWORK_TYPE_GPRS:
				return TYPE_GPRS;
			default:
				return TYPE_3G;
			}
		}
	}

	public static final String getConnMode(Context context) {
		ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (conn == null) {
			return null;
		}
		NetworkInfo info = conn.getActiveNetworkInfo();
		if (info == null || !info.isAvailable()) {
			return null;
		}

		int type = info.getType(); // MOBILE（GPRS）;WIFI
		if (type == ConnectivityManager.TYPE_WIFI) {
			return APN_TYPE_WIFI;
		} else {
			return getApnType(context);
		}
	}

	private static Uri PREFERRED_APN_URI = Uri.parse("content://telephony/carriers/preferapn");
	public static String APN_TYPE_WIFI = "wifi";
	public static String APN_TYPE_CTNET = "ctnet";
	public static String APN_TYPE_CTWAP = "ctwap";
	public static String APN_TYPE_CMNET = "cmnet";
	public static String APN_TYPE_CMWAP = "cmwap";
	public static String APN_TYPE_UNINET = "uninet";
	public static String APN_TYPE_UNIWAP = "uniwap";

	public static String getApnType(Context context) {
		String apntype = "nomatch";
		try {
			Cursor c = context.getContentResolver().query(PREFERRED_APN_URI, null, null, null, null);
			c.moveToFirst();
			String user = c.getString(c.getColumnIndex("user")).toLowerCase();
			c.close();
			System.out.println("user = " + user);
			if (user.startsWith(APN_TYPE_CTNET)) {
				apntype = APN_TYPE_CTNET;
			} else if (user.startsWith(APN_TYPE_CTWAP)) {
				apntype = APN_TYPE_CTWAP;
			} else if (user.startsWith(APN_TYPE_CMNET)) {
				apntype = APN_TYPE_CMNET;
			} else if (user.startsWith(APN_TYPE_CMWAP)) {
				apntype = APN_TYPE_CMWAP;
			} else if (user.startsWith(APN_TYPE_UNINET)) {
				apntype = APN_TYPE_UNINET;
			} else if (user.startsWith(APN_TYPE_UNIWAP)) {
				apntype = APN_TYPE_UNIWAP;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("apntype = " + apntype);
		return apntype;
	}
}