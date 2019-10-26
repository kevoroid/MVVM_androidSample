package com.kevoroid.foodshop.apis;

import com.kevoroid.foodshop.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroMaster {

	private static final String BASE_URL = "http://mobcategories.s3-website-eu-west-1.amazonaws.com/";

	RetroMaster() {
	}

	public static Retrofit getInstance() {
		return new Retrofit.Builder()
				.baseUrl(BASE_URL)
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
}
