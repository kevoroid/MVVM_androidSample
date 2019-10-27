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
		System.out.println("ProductListViewModel.ProductListViewModel");
		masterRepo = MasterRepo.getInstance();
		if (productList == null) {
			System.out.println("ProductListViewModel.ProductListViewModel 2");
			fetchProductList();
		}
	}

	// try using Resource<T>
//	public LiveData<Resource<List<ProductList>>> getProductList() {
	public LiveData<List<ProductList>> getProductList() {
		System.out.println("ProductListViewModel.getProductList");
		return productList;
	}

	private void fetchProductList() {
		System.out.println("ProductListViewModel.fetchProductList");
		productList = masterRepo.getProductList();
	}
}
