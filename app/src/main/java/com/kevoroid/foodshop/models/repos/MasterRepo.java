package com.kevoroid.foodshop.models.repos;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.kevoroid.foodshop.DaggerYummyComponent;
import com.kevoroid.foodshop.YummyApplication;
import com.kevoroid.foodshop.YummyComponent;
import com.kevoroid.foodshop.apis.ApiEndpoints;
import com.kevoroid.foodshop.apis.Resource;
import com.kevoroid.foodshop.models.ProductList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class MasterRepo {

	private final String TAG = "MasterRepo";

	private MutableLiveData<Resource<List<ProductList>>> productListLiveData = new MutableLiveData<>();

	@Inject
	public MasterRepo() {
	}

	public MutableLiveData<Resource<List<ProductList>>> getProductList() {
		YummyApplication.getYummyComponent().getRetroMaster().getProductCategories().enqueue(new Callback<List<ProductList>>() {
			@Override
			public void onResponse(Call<List<ProductList>> call, Response<List<ProductList>> response) {
				if (response.isSuccessful()) {
					productListLiveData.postValue(Resource.success(response.body()));
				} else {
					productListLiveData.postValue(Resource.error(response.message(), null));
				}
			}

			@Override
			public void onFailure(Call<List<ProductList>> call, Throwable t) {
				Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
				productListLiveData.postValue(Resource.error(t.getMessage(), null));
			}
		});

		return productListLiveData;
	}
}
