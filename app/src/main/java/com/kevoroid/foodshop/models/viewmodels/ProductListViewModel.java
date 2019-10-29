package com.kevoroid.foodshop.models.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.kevoroid.foodshop.models.ProductList;
import com.kevoroid.foodshop.models.repos.MasterRepo;

import java.util.List;

public class ProductListViewModel extends ViewModel {

	private MasterRepo masterRepo;

	private MutableLiveData<List<ProductList>> productList;

	public void init() {
		if (productList != null ) {
			return;
		}

		masterRepo = MasterRepo.getInstance();
		productList = masterRepo.getProductList();
	}

	public LiveData<List<ProductList>> getProductList() {
		return productList;
	}
}
