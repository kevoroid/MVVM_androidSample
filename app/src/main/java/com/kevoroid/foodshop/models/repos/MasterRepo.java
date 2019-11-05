package com.kevoroid.foodshop.models.repos;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.kevoroid.foodshop.apis.ApiEndpoints;
import com.kevoroid.foodshop.apis.Resource;
import com.kevoroid.foodshop.apis.RetroMaster;
import com.kevoroid.foodshop.models.ProductList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MasterRepo {

	private static final String TAG = "MasterRepo";

	private static MasterRepo INSTANCE;

	private ApiEndpoints repoApiEndpoints;

	private MutableLiveData<Resource<List<ProductList>>> productListLiveData = new MutableLiveData<>();

	private MasterRepo() {
		repoApiEndpoints = RetroMaster.getInstance().create(ApiEndpoints.class);
	}

	public static synchronized MasterRepo getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MasterRepo();
		}
		return INSTANCE;
	}

	public MutableLiveData<Resource<List<ProductList>>> getProductList() {
		repoApiEndpoints.getProductCategories().enqueue(new Callback<List<ProductList>>() {
			@Override
			public void onResponse(Call<List<ProductList>> call, Response<List<ProductList>> response) {
				if (response.isSuccessful()) {
					productListLiveData.setValue(Resource.success(response.body()));
				} else {
					productListLiveData.setValue(Resource.error(response.message(), null));
				}
			}

			@Override
			public void onFailure(Call<List<ProductList>> call, Throwable t) {
				Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
				productListLiveData.setValue(Resource.error(t.getMessage(), null));
			}
		});

		return productListLiveData;
	}
}
