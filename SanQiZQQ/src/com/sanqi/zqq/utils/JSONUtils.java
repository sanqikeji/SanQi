package com.sanqi.zqq.utils;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.text.TextUtils;
import android.util.Log;
import java.util.HashMap;

import android.R.integer;
/**
 */
public class JSONUtils {

	private static HashMap<String, JSONHandler> handlerMap = new HashMap<String, JSONUtils.JSONHandler>();
	private static HashMap<String, String> replaceMap = new HashMap<String, String>();

	private static JSONUtils utils;


	public static void setHandler(String fieldKey, JSONHandler handler) {
		handlerMap.put(fieldKey, handler);
	}

	public static void clearHandler() {
		handlerMap.clear();
	}
    
	// private JSONUtils() {
	// }
	//
	// public static JSONUtils getInstance() {
	// if (utils == null) {
	// utils = new JSONUtils();
	// }
	// return utils;
	// }

	@SuppressWarnings("rawtypes")
	public static Object parseJSON(Class type, String json) {
		Object obj = null;
		if (json != null) {
		}
		try {
			obj = parseJSONObject(type, json);
		} catch (Exception ex) {
			try {
				obj = parseJSONArray(type, json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	@SuppressWarnings("rawtypes")
	/**
	 * [xxx]类型，用这个
	 * @param oneType
	 * @param json
	 * @return
	 */
	public static Object parseJSONArray(Class oneType, String json) throws Exception {
		JSONArray jsonArray = null;
		Object[] as = null;
		try {
			jsonArray = new JSONArray(json);
			as = (Object[]) Array.newInstance(oneType, jsonArray.length());
			for (int i = 0; i < jsonArray.length(); i++) {
				as[i] = parseJSONObject(oneType, jsonArray.get(i).toString());
			}
		} catch (JSONException e) {
			throw e;
		}
		return as;
	}

	public static void putFieldMapping(String fieldName, String jsonKeyName) {
		replaceMap.put(fieldName, jsonKeyName);
	}

	public static void clearFieldMapping() {
		replaceMap.clear();

	}

	@SuppressWarnings("rawtypes")
	/**
	 * {xxxx}整体这个类型，用这个,一般都是这个类型的json
	 * @param clazz
	 * @param json
	 * @return
	 */
	//利用发射机制解析Json
	private static Object parseJSONObject(Class clazz, String json) throws Exception {
		if (json == null) {
			return null;
		}
		JSONObject jsonObj = null;
		Object obj = null;
		try {
			jsonObj = new JSONObject(json);
			Field[] fields = clazz.getFields();
			obj = clazz.newInstance();//Returns a new instance of the class represented by this Class
			// 遍历成员变量
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				String fieldName = field.getName();
				
				Class fieldClazz = field.getType();
				/**
				 * String
				 */
				if (fieldClazz == String.class) {
					String value = jsonObj.optString(fieldName);
					if (TextUtils.isEmpty(value) || "null".equals(value.trim().toLowerCase())) {
						continue;//跳出do、for、while的句级   continue是终止本次循环 而 break是终止向外的一个循环
					}
					field.set(obj,value);
				}
				/**
				 * 其他
				 */
				else {
					/**
					 * 如果是数组
					 */
					if (fieldClazz.isArray()) {
						Class comType = fieldClazz.getComponentType();
						try {            
						                       	//jsonObj.opt(fieldName).toString()      	jsonObj = new JSONObject(json);          
							JSONArray jsonArray = jsonObj.optJSONArray(fieldName); // 判断长度
							
							
							
							// 虽然bean中的定义是一个数组，但是返回的是一个数组的一个对象，服务器当作一个对象而不是中括号返回，这里处理异常。
							if (jsonArray == null) {//如果不是JSonArray可能是对象
								JSONObject jObj = jsonObj.optJSONObject(fieldName);
								Object[] array = (Object[]) Array.newInstance(comType, 1);//新建一个ComponentType、Size = 1的数组，且类型为对象
								array[0] = parseJSONObject(comType, jObj.toString());
								field.set(obj, array);
							}
							
							
				    	   
				    	
							if (jsonArray != null && jsonArray.length() != 0) {
								int len = jsonArray.length();
								Object[] array = (Object[]) Array.newInstance(comType, len);

								for (int k = 0; k < len; k++) {
									// 如果是字符串数组
									if (comType == String.class) {
										array[k] = jsonArray.get(k);
									}
									// 如果是其他类型的数组
									else {
										JSONObject fieldJsonObj = jsonArray.optJSONObject(k);
										if (fieldJsonObj != null) {
											array[k] = parseJSONObject(comType, fieldJsonObj.toString());//本身的json数据格式就是嵌套的
										}
									}
								}

								field.set(obj, array);
							}
						} catch (Exception ex) {
							Log.i("fieldClazz.isArray()", "通过Array方式解析" + fieldName + "失败，可能是一个数组只有一个对象,现在通过一个对象解析。");
						}

					}
					/**
					 * 是对象
					 */
					else {
						JSONObject fieldJson = jsonObj.optJSONObject(fieldName);
						if (fieldJson != null) {
							field.set(obj, parseJSONObject(fieldClazz, fieldJson.toString()));
						}
					}
				}

			}
		} catch (Exception e) {
			throw e;
		}
		return obj;
	}
	
	


//public static HashMap<String,String>  parseUpdate(String json){
//	 HashMap<String,String> HM = new  HashMap<String,String>();
//	if (json == null) {
//		return null;
//	}
//// String tid;
// String time;
// String lastSum;
//	try {
//		JSONArray	tid =	 new JSONObject(json).getJSONArray("data");
//		System.out.println("tid-------->"+tid);
////		ForumNumberRecord.UpdateID.put("tid",tid);
////		HM.put("tid",tid);
//	} catch (JSONException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	try {
//		time =	(String) new JSONObject(json).getString("time");
//		ForumNumberRecord.UpdateID.put("time",time);
//		HM.put("time",time);
//	} catch (JSONException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	try {
//		lastSum =	(String) new JSONObject(json).getString("lastSum");
//		ForumNumberRecord.UpdateID.put("lastSum", lastSum);
//		HM.put("lastSum", lastSum);
//	} catch (JSONException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	return HM;
//	
//}

	/*
	 * 解析服务器获取的时间
	 */
	public static long JsonParserServiceTime(String json){
		long data = 0;
		JSONObject jsonObject=null;
		try {
			jsonObject = new JSONObject(json) ;
			data=jsonObject.getLong("data");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public static interface JSONHandler {
		/**
		 * 返回的Object会被设置到Bean的名为：key的字段上。
		 * 
		 * @param key
		 * @param json
		 * @return
		 */
		Object handle(String key, String json);
	}
}
