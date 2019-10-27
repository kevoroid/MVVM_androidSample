package com.kevoroid.foodshop.models.repos;

import androidx.lifecycle.MutableLiveData;
import com.kevoroid.foodshop.apis.ApiEndpoints;
import com.kevoroid.foodshop.apis.RetroMaster;
import com.kevoroid.foodshop.models.ProductList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class MasterRepo {

	private static MasterRepo INSTANCE;

	private ApiEndpoints repoApiEndpoints;

	// try using Resource<T>
//	private MutableLiveData<Resource<List<ProductList>>> productListLiveData = new MutableLiveData<>();
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
		// try using Resource<T>
//		public MutableLiveData<Resource<List<ProductList>>> getProductList() {
//		repoApiEndpoints.getProductCategories().enqueue(new Callback<Resource<List<ProductList>>>() {
//			@Override
//			public void onResponse(Call<Resource<List<ProductList>>> call, Response<Resource<List<ProductList>>> response) {
////				List<ProductList> productList = new ArrayList<>((response.body()));
////				productListLiveData.setValue(productList);
//
////				List<ProductList> productList = new ArrayList<>();
////				productList.add(Resource.success(<response.body()>));
////				productListLiveData.setValue(Resource.success());
////
////				productListLiveData.setValue(Resource.success(response.body().data));
//			}
//
//			@Override
//			public void onFailure(Call<Resource<List<ProductList>>> call, Throwable t) {
//				t.printStackTrace();
//				productListLiveData.setValue(Resource.error(t.getMessage(), null));
//			}
//		});

		repoApiEndpoints.getProductCategories().enqueue(new Callback<List<ProductList>>() {
			@Override
			public void onResponse(Call<List<ProductList>> call, Response<List<ProductList>> response) {

				List<ProductList> productList = new ArrayList<>((response.body()));
				productListLiveData.postValue(productList);
			}

			@Override
			public void onFailure(Call<List<ProductList>> call, Throwable t) {
				t.printStackTrace();
			}
		});

		return productListLiveData;
	}
}
