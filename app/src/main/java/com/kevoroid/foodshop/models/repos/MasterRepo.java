package com.kevoroid.foodshop.models.repos;

import androidx.lifecycle.MutableLiveData;
import com.kevoroid.foodshop.BuildConfig;
import com.kevoroid.foodshop.apis.ApiEndpoints;
import com.kevoroid.foodshop.apis.RetroMaster;
import com.kevoroid.foodshop.models.ProductList;
import junit.framework.Assert;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class MasterRepo {

	private static MasterRepo INSTANCE;

	private ApiEndpoints repoApiEndpoints;

	private MutableLiveData<List<ProductList>> productListLiveData = new MutableLiveData<>();

	private MasterRepo() {
		repoApiEndpoints = RetroMaster.getInstance().create(ApiEndpoints.class);
	}

	public static synchronized MasterRepo getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MasterRepo();
		}
		return INSTANCE;
	}

	public MutableLiveData<List<ProductList>> getProductList() {
		repoApiEndpoints.getProductCategories().enqueue(new Callback<List<ProductList>>() {
			@Override
			public void onResponse(Call<List<ProductList>> call, Response<List<ProductList>> response) {

				List<ProductList> productList = new ArrayList<>((response.body()));
				productListLiveData.postValue(productList);
			}

			@Override
			public void onFailure(Call<List<ProductList>> call, Throwable t) {
				if (BuildConfig.DEBUG) {
					Assert.fail();
				}
				t.printStackTrace();
			}
		});

		return productListLiveData;
	}
}
