package com.kevoroid.foodshop.models.repos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.kevoroid.foodshop.apis.ApiEndpoints;
import com.kevoroid.foodshop.apis.RetroMaster;
import com.kevoroid.foodshop.models.ProductList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Collection;
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
				System.out.println("MasterRepo.onResponse >> " + response.isSuccessful());
				System.out.println("MasterRepo.onResponse >> " + response);
				System.out.println("MasterRepo.onResponse >> " + response.body());

				//List<ProductList> productList = new ArrayList<ProductList>((Collection<? extends ProductList>) response.body());
				List<ProductList> productList = new ArrayList<>((response.body()));

				productListLiveData.postValue(productList);
			}

			@Override
			public void onFailure(Call<List<ProductList>> call, Throwable t) {
				System.out.println("MasterRepo.onFailure >> " + t.getLocalizedMessage());
				System.out.println("MasterRepo.onFailure >> " + t);
			}
		});

		return productListLiveData;

	}
}
