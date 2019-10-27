package com.kevoroid.foodshop.apis;

import com.kevoroid.foodshop.BuildConfig;
import com.kevoroid.foodshop.CommonKeys;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroMaster {

	RetroMaster() {
	}

	public static Retrofit getInstance() {
		return new Retrofit.Builder()
				.baseUrl(CommonKeys.BASE_URL)
				.client(buildHttpClientLogging())
				.addConverterFactory(GsonConverterFactory.create())
				.build();
	}

	private static OkHttpClient buildHttpClientLogging() {
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
		httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		if (BuildConfig.DEBUG) {
			builder.addInterceptor(httpLoggingInterceptor);
		}
		return builder.build();
	}

	public static String returnProductImageUrl(String url) {
		return CommonKeys.BASE_URL + url;
	}
}
