package com.kevoroid.foodshop.apis;

import com.kevoroid.foodshop.BuildConfig;
import com.kevoroid.foodshop.CommonKeys;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;

@Module
public class RetroMaster {

	@Singleton
	@Provides
	public static Retrofit getRetrofit() {
		return new Retrofit.Builder()
				.baseUrl(CommonKeys.BASE_URL)
				.client(buildHttpClientLogging())
				.addConverterFactory(GsonConverterFactory.create())
				.build();
	}

	public static OkHttpClient buildHttpClientLogging() {
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
		httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		if (BuildConfig.DEBUG) {
			builder.addInterceptor(httpLoggingInterceptor);
		}
		return builder.build();
	}

	@Provides
	public static ApiEndpoints getApiEndpoints(Retrofit retrofit) {
		return retrofit.create(ApiEndpoints.class);
	}

	public static String returnProductImageUrl(String url) {
		return CommonKeys.BASE_URL + url;
	}
}
