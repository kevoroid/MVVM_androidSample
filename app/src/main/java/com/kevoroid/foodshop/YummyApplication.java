package com.kevoroid.foodshop;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

public class YummyApplication extends Application {

	@SuppressLint("StaticFieldLeak")
	private static Context mContext;

	@Override
	public void onCreate() {
		super.onCreate();

		YummyApplication.mContext = getApplicationContext();
	}

	public static Context getContext() {
		return mContext;
	}
}
