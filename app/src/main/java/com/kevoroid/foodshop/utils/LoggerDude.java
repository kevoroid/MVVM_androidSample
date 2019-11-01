package com.kevoroid.foodshop.utils;

import android.util.Log;
import com.kevoroid.foodshop.BuildConfig;

public class LoggerDude {

	public static void logDebug(String tag, String msg) {
		if (BuildConfig.DEBUG) {
			Log.d(tag, msg);
		}
	}

	public static void logError(String tag, String msg) {
		if (BuildConfig.DEBUG) {
			Log.e(tag, msg);
		}
	}

	public static void logInfo(String tag, String msg) {
		if (BuildConfig.DEBUG) {
			Log.i(tag, msg);
		}
	}

	public static void logVerbose(String tag, String msg) {
		if (BuildConfig.DEBUG) {
			Log.v(tag, msg);
		}
	}

	public static void logException(String tag, String msg, Exception x) {
		if (BuildConfig.DEBUG) {
			Log.w(tag, msg, x);
		}
	}
}
