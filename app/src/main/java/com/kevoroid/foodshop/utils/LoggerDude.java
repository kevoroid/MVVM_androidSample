package com.kevoroid.foodshop.utils;

import android.util.Log;
import com.kevoroid.foodshop.BuildConfig;

public class LoggerDude {

	private LoggerDude() {
	}

	public static void debug(String tag, String msg) {
		if (BuildConfig.DEBUG) {
			Log.d(tag, msg);
		}
	}

	public static void error(String tag, String msg) {
		if (BuildConfig.DEBUG) {
			Log.e(tag, msg);
		}
	}

	public static void info(String tag, String msg) {
		if (BuildConfig.DEBUG) {
			Log.i(tag, msg);
		}
	}

	public static void verbose(String tag, String msg) {
		if (BuildConfig.DEBUG) {
			Log.v(tag, msg);
		}
	}

	public static void wtf(String tag, String msg, Exception x) {
		if (BuildConfig.DEBUG) {
			Log.w(tag, msg, x);
		}
	}
}
