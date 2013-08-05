package com.sanqi.zqq.utils;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.widget.TextView;

/**
 * 处理字符串工具类
 * 
 * @author
 * 
 */
public class StringUtils {

	/**
	 * 判断是否为空
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isNullOrEmpty(String text) {
		if (text == null || "".equals(text.trim()) || text.trim().length() == 0
				|| "null".equals(text.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获得MD5加密字符串
	 * 
	 * @param str
	 *            字符串
	 * @return
	 */
	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return md5StrBuff.toString();
	}

	/**
	 * 得到字符串长度
	 * 
	 * @param text
	 * @return
	 */
	public static int getCharCount(String text) {
		String Reg = "^[\u4e00-\u9fa5]{1}$";
		int result = 0;
		for (int i = 0; i < text.length(); i++) {
			String b = Character.toString(text.charAt(i));
			if (b.matches(Reg))
				result += 2;
			else
				result++;
		}
		return result;
	}

	/**
	 * 获取截取后的字符串
	 * 
	 * @param text
	 *            原字符串
	 * @param length
	 *            截取长度
	 * @return
	 */
	public static String getSubString(String text, int length) {
		return getSubString(text, length, true);
	}

	/**
	 * 获取截取后的字符串
	 * 
	 * @param text
	 *            原字符串
	 * @param length
	 *            截取长度
	 * @param isOmit
	 *            是否加上省略号
	 * @return
	 */
	public static String getSubString(String text, int length, boolean isOmit) {
		if (isNullOrEmpty(text)) {
			return "";
		}
		if (getCharCount(text) <= length + 1) {
			return text;
		}

		StringBuffer sb = new StringBuffer();
		String Reg = "^[\u4e00-\u9fa5]{1}$";
		int result = 0;
		for (int i = 0; i < text.length(); i++) {
			String b = Character.toString(text.charAt(i));
			if (b.matches(Reg)) {
				result += 2;
			} else {
				result++;
			}

			if (result <= length + 1) {
				sb.append(b);
			} else {
				if (isOmit) {
					sb.append("...");
				}
				break;
			}
		}
		return sb.toString();
	}

	/**
	 * 电话号码验证
	 * 
	 * @param phoneNumber
	 *            手机号码
	 * @return
	 */
	public static boolean validatePhoneNumber(String phoneNumber) {
		Pattern pattern = Pattern
				.compile("^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
		Matcher m = pattern.matcher(phoneNumber);
		return m.matches();
	}

	/**
	 * 邮箱验证
	 * 
	 * @param mail
	 *            邮箱
	 * @return
	 */
	public static boolean validateEmail(String mail) {
		Pattern pattern = Pattern
				.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher m = pattern.matcher(mail);
		return m.matches();
	}

	/**
	 * 验证字符串内容是否合法
	 * 
	 * @param content
	 *            字符串内容
	 * @return
	 */
	public static boolean validateLegalString(String content) {
		String illegal = "`~!#%^&*=+\\|{};:'\",<>/?○●★☆☉♀♂※¤╬の〆";
		boolean legal = true;
		L1: for (int i = 0; i < content.length(); i++) {
			for (int j = 0; j < illegal.length(); j++) {
				if (content.charAt(i) == illegal.charAt(j)) {
					legal = false;
					break L1;
				}
			}
		}
		return legal;
	}

	/**
	 * 获取更新的时间
	 * 
	 * @param dateStr
	 * @return
	 * @throws Exception
	 */
	public static String getCreateString(String dateStr) {
		if (dateStr != null && !"".equals(dateStr)) {
			try {
				if (dateStr.indexOf(".") > -1) {
					dateStr = dateStr.substring(0, dateStr.indexOf("."));
				}
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date date = sdf.parse(dateStr);
				Calendar calendar = Calendar.getInstance();

				int oneMinuteUnit = 60;
				int oneHourUnit = 60 * 60;
				int oneDayUnit = 60 * 60 * 24;
				long i = (calendar.getTimeInMillis() - date.getTime()) / 1000;
				if (i < oneMinuteUnit && i > 0) {
					return i + "秒前";
				} else if (i < oneHourUnit && i > oneMinuteUnit) {
					return i / 60 + "分钟前";
				} else if (i < oneDayUnit && i > oneHourUnit) {
					return (i / (60 * 60)) + "小时前";
				} else {
					return dateStr;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 获取更新的时间
	 * 
	 * @param dateStr
	 * @return
	 * @throws Exception
	 */
	public static String getCreateString(Date date) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (calendar.get(Calendar.YEAR) - (date.getYear() + 1900) > 0) {
			return sdf.format(date);
		} else if (calendar.get(Calendar.MONTH) - date.getMonth() > 0) {
			return sdf.format(date);
		} else if (calendar.get(Calendar.DAY_OF_MONTH) - date.getDate() > 0) {
			return sdf.format(date);
		} else if (calendar.get(Calendar.HOUR_OF_DAY) - date.getHours() > 0) {
			int i = calendar.get(Calendar.HOUR_OF_DAY) - date.getHours();
			return i + "小时前";
		} else if (calendar.get(Calendar.MINUTE) - date.getMinutes() > 0) {
			int i = calendar.get(Calendar.MINUTE) - date.getMinutes();
			return i + "分钟前";
		} else if (calendar.get(Calendar.SECOND) - date.getSeconds() > 0) {
			int i = calendar.get(Calendar.SECOND) - date.getSeconds();
			return i + "秒前";
		} else {
			return sdf.format(date);
		}
	}

	/**
	 * 对搜房的图片进行动态调整
	 * 
	 * @param coverimg
	 * @param width
	 * @param height
	 * @return
	 */
	public static String getImgPath(String coverimg, int width, int height) {
		if (isNullOrEmpty(coverimg)) {
			return "";
		}
		if (!coverimg.startsWith("http")) {
			return coverimg;
		}
		try {
			URL url = new URL(coverimg);
			if (url.getHost().indexOf("soufun") == -1) {
				return coverimg;
			}
		} catch (MalformedURLException e) {
			return "";
		}
		String http = coverimg.substring(7);
		String head = http.substring(0, http.indexOf("/"));
		String xurl = coverimg.replace("http://" + head, "");
		xurl = xurl.substring(0, xurl.lastIndexOf("."));
		String xend = coverimg.substring(coverimg.lastIndexOf("."));
		if (coverimg.indexOf("viewimage") > -1) {
			coverimg = coverimg.substring(0, coverimg.lastIndexOf("/")) + "/"
					+ width + "x" + height + xend;
		} else {
			coverimg = "http://" + head + "/viewimage" + xurl + "/" + width
					+ "x" + height + xend;
		}
		return coverimg;
	}

	/**
	 * 如果为空显示暂无信息
	 * 
	 * @param tv
	 *            控件名
	 * @param str
	 *            信息
	 */
	public static void viewText(TextView tv, String str) {
		if (isNullOrEmpty(str)) {
			tv.setText("暂无资料");
		} else {
			tv.setText(str);
		}
	}

	/**
	 * 对流转化成字符串
	 * @param is
	 * @return
	 */
	public static String getContentByString(InputStream is) {
		try {
			if (is == null)
				return null;
			byte[] b = new byte[1024];
			int len = -1;
			StringBuilder sb = new StringBuilder();
			while ((len = is.read(b)) != -1) {
				sb.append(new String(b, 0, len));
			}
			return sb.toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
