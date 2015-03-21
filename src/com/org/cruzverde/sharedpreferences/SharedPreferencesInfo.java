package com.org.cruzverde.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class SharedPreferencesInfo {
	
	//Aqui guardamos en memoria el numero 

	static final String INFO_USER = "InfoUser";
	static final String INFO_ALERT = "InfoAlert";
	static SharedPreferences userInfo;
	static SharedPreferences.Editor editorInfo;

	public static void setInfo(Context mContext, Map<String, String> map) {
		userInfo = mContext.getSharedPreferences(INFO_ALERT,
				Context.MODE_PRIVATE);

		editorInfo = userInfo.edit();
		editorInfo.putString("Info", map.get("Info").toString());
		editorInfo.commit();	
	}

	public static Map<String, String> getInfo(Context mContext) {

		userInfo = mContext.getSharedPreferences(INFO_ALERT,
				Context.MODE_PRIVATE);
		Map<String, String> map = new HashMap<String, String>();
		map.put("Info", userInfo.getString("Info", ""));
		return map;

	}

	public static boolean getBooleanMap(String status) {
		boolean temp = false;

		if (status.equals("true")) {

			temp = true;

		} else if (status.equals("false")) {
			temp = false;
		}

		return temp;

	}
}