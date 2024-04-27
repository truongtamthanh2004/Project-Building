package com.javaweb.utils;

import java.util.Map;

public class MapUtil {
	public static<T> T getObject(Map<String, Object> params, String key, Class<T> tClass) {
		Object obj = params.getOrDefault(key, null); // If cannot get data by key, default value of object is null
		if (obj != null) {
			if (tClass.getTypeName().equals("java.lang.String")) {
				obj = obj.toString();
			}
			else if (tClass.getTypeName().equals("java.lang.Long")) {
				obj = obj != "" ? Long.valueOf(obj.toString()) : null;;
			}
			else if (tClass.getTypeName().equals("java.lang.Integer")) {
				obj = obj != "" ? Integer.valueOf(obj.toString()) : null;;
			}
			return tClass.cast(obj);
		}
		
		return null;
	}
}
