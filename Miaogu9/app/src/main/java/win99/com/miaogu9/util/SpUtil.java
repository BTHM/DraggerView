package win99.com.miaogu9.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 
 * @Description: sp工具类
 * @author sansu
 * @date 2016-5-18 下午5:22:05
 */
public class SpUtil {

	private static final String FILE_NAME = "config";

	public static void putBoolean(Context context, String key, boolean flag) {
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);
		Editor edit = sp.edit();
		edit.putBoolean(key, flag);
		edit.commit();
	}

	public static boolean getBoolean(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);
		return sp.getBoolean(key, true);
	}
	
	public static boolean getBoolean(Context context, String key, boolean defValue) {
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);
		return sp.getBoolean(key, defValue);
	}
	
	public static boolean getBooleanForInit(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);
		return sp.getBoolean(key, false);
	}

	public static void putString(Context context, String key, String flag) {
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);
		Editor edit = sp.edit();
		edit.putString(key, flag);
		edit.commit();
	}

	public static String getString(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);
		return sp.getString(key, null);
	}
	
	public static void putInt(Context context, String key, int flag) {
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);
		Editor edit = sp.edit();
		edit.putInt(key, flag);
		edit.commit();
	}

	public static int getInt(Context context, String key, int defValue) {
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);
		return sp.getInt(key, defValue);
	}

}
