package com.kevoroid.foodshop.models.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.kevoroid.foodshop.models.ProductList;
import com.kevoroid.foodshop.models.repos.MasterRepo;

import java.util.List;

public class ProductListViewModel extends ViewModel {

	private MasterRepo masterRepo;

	// try using Resource<T>
//	private LiveData<Resource<List<ProductList>>> productList;
	private LiveData<List<ProductList>> productList;

	public ProductListViewModel() {
		masterRepo = MasterRepo.getInstance();
		if (productList == null) {
			fetchProductList();
		}
	}

	// try using Resource<T>
//	public LiveData<Resource<List<ProductList>>> getProductList() {
	public LiveData<List<ProductList>> getProductList() {
		return productList;
	}

	private void fetchProductList() {
		productList = masterRepo.getProductList();
	}
}
