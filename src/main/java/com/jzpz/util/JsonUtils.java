package com.jzpz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

/**
 * JSON操作工具类
 * @author wQ
 *
 */

public class JsonUtils {
	
	/**
	 * 从一个json对象字符格式中得到一个java对象
	 * 
	 * @param jsonString
	 * @param pojoClass
	 * @return
	 */
	public static Object getObject4JsonString (String jsonString,Class<?> pojoClass){
		Object object;
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		object = JSONObject.toBean(jsonObject,pojoClass);
		return object;
	}
	/**
	 * 将json转化为java实体对象,其中java对象中的property是泛型约束的集合
	 * @param jsonString
	 * @param property 属性
	 * @param pojoClass 属性的集合泛型类型
	 * @return
	 */
	public static Object getObject4JsonString (String jsonString,String property,Class<?> pojoClass){
		//属性
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put(property, pojoClass);
		Object object = JSONObject.toBean(JSONObject.fromObject(jsonString), pojoClass, classMap);
		return object;
	}
	
	/**
	 * 从json HASH表达式中获取一个map,该map支持嵌套功能
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Map<?, ?> getMap4Json(String jsonString){
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Iterator<?> iterator = jsonObject.keys();
		String key;
		Object value;
		Map valueMap = new HashMap();
		while (iterator.hasNext()){
			key = (String) iterator.next();
			value = jsonObject.get(key);
			valueMap.put(key, value);
		}
		return valueMap;
	}
	/**
	 * 从json数组中得到相应的java数组
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Object[] getObjectArray4Json(String jsonString){
		JSONArray array = JSONArray.fromObject(jsonString);
		return array.toArray();
	}
	/**
	 * 从json对象集合表达式中得到一个java对象列表
	 * 
	 * @param jsonString
	 * @param pojoClass
	 * @return
	 */
	public static List getList4Json (String jsonString,Class pojoClass){
		JSONArray array = JSONArray.fromObject(jsonString);
		JSONObject jsonObject ;
		Object pojoValue;
		List list = new ArrayList();
		for (int i = 0; i < array.size(); i++) {
			jsonObject  = array.getJSONObject(i);
			pojoValue = JSONObject.toBean(jsonObject,pojoClass);
			list.add(pojoValue);
		}
		return list;
	}
	
	/**
	 * 从json数组中解析出java字符串数组
	 * 
	 * @param jsonString
	 * @return
	 */
	public static String [] getStringArray4Json (String jsonString){
		JSONArray array = JSONArray.fromObject(jsonString);
		String [] stringArray = new String [array.size()];
		for (int i = 0; i < array.size(); i++) {
			stringArray[i] = array.getString(i);
		}
		return stringArray;
	}
	
	/**
	 * 从json 数组中解析出java Long型对象数组
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Long [] getLongArray4Json (String jsonString){
		JSONArray array = JSONArray.fromObject(jsonString);
		Long [] longArray = new Long [array.size()];
		for (int i = 0; i < array.size(); i++) {
			longArray[i] = array.getLong(i);
		}
		return longArray;
	}
	
	/**
	 * 从json 数组中解析出java Integer型对象数组
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Integer [] getIntegerArray4Json (String jsonString){
		JSONArray array = JSONArray.fromObject(jsonString);
		Integer [] integerArray = new Integer [array.size()];
		for (int i = 0; i < array.size(); i++) {
			integerArray[i] = array.getInt(i);
		}
		return integerArray;
	}
	
	/**
	 * 从json数组中解析出java Date 型对象数组
	 * @param jsonString
	 * @param dataFormat
	 * @return
	 * @throws ParseException
	 */
	public static Date [] getDateArray4Json (String jsonString,String dataFormat) throws ParseException {
		JSONArray array = JSONArray.fromObject(jsonString);
		Date [] dataArray = new Date[array.size()];
		String dateString ;
		Date date;
		for (int i = 0; i < array.size(); i++) {
			dateString = array.getString(i);
			date = DateUtils.getDate4String(dateString,dataFormat);
			dataArray[i] = date;
		}
		return dataArray;
	}
	
	/**
	 * 从json 数组中解析出java Double型对象数组
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Double [] getDoubleArray4Json (String jsonString){
		JSONArray array = JSONArray.fromObject(jsonString);
		Double [] doubleArray = new Double [array.size()];
		for (int i = 0; i < array.size(); i++) {
			doubleArray[i] = array.getDouble(i);
		}
		return doubleArray;
	}
	
	/**
	 * 将java 对象转换成json字符串
	 * @param object
	 * @return
	 */
	public static String getJsonString4JavaPoje(Object object){
		return JSONObject.fromObject(object).toString();
	}
	
	/**
	 * 将List 对象转换成json字符串
	 * @param ‘object’
	 * @return
	 */
	public static String getJsonString4JavaPoje1(List list){
		return JSONArray.fromObject(list).toString();
	}
	
	
	/**
	 * 将java对象转换成json字符串,并设定日期格式
	 * @param object
	 * @param dataFormat
	 * @return
	 */
	public static String getJsonString4JavaPoje(Object object,String dataFormat){
		JSONObject json;
		JsonConfig jsonConfig = jsonConfig(dataFormat);
		json = JSONObject.fromObject(object,jsonConfig);
		return json.toString();
	}
	
	/**
	 * 将java集合转换成json字符串
	 * @param objects
	 * @return
	 */
	public static String getJsonString4JavaListPoje(List<?> objects){
		JSON json = JSONSerializer.toJSON(objects,jsonConfig("yyyy-MM-dd HH:mm:ss"));
		return json.toString();
	}
	/**
	 * 使用自定义日期格式创建JsonConfig
	 * 
	 * @param dateFormat 自定义日期格式
	 * @return
	 */
	public static JsonConfig jsonConfig(String dateFormat){
		JsonConfig config=new JsonConfig();
		final String df = dateFormat;
		config.registerJsonValueProcessor(Date.class, new net.sf.json.processors.JsonValueProcessor() {
			
			@Override
			public Object processObjectValue(String key, Object value, JsonConfig arg2) {
				if(value == null) 
					return "";
				if(value instanceof Date) {
					String string = new SimpleDateFormat(df).format((Date)value);
					return string;
				}
				return value.toString();
			}
			
			@Override
			//这个可能有问题，对于非date的[]未做处理。
			public Object processArrayValue(Object value, JsonConfig jsonconfig) {
				
				String [] obj = {};
				if(value instanceof Date[]) {
					SimpleDateFormat sFormat = new SimpleDateFormat(df);
					Date [] dates = (Date []) value;
					obj = new String[dates.length];
					for(int i = 0;i<dates.length;i++) {
						obj[i] = sFormat.format(dates[i]);
					}
				}
				return obj;
			}
		});
		return config;
	}

}

