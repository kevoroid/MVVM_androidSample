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

	public ProductListViewModel() {
		System.out.println("ProductListViewModel.ProductListViewModel");
		masterRepo = MasterRepo.getInstance();
		if (productList == null) {
			System.out.println("ProductListViewModel.ProductListViewModel 2");
			productList = masterRepo.getProductList();
		}
	}

	public LiveData<List<ProductList>> getProductList() {
		return productList;
	}

//	public LiveData<List<ProductList>> getProductList() {
//		if (productList == null) {
//			System.out.println("ProductListViewModel.getProductList is NULL");
//			productList = new MutableLiveData<>();
////			productList = masterRepo.getProductList();
//			fetchProductList();
//		}
//		return productList;
//	}

	private void fetchProductList() {
		System.out.println("ProductListViewModel.fetchProductList");
		masterRepo.getProductList();
	}

//	public void setProductList(MutableLiveData<List<ProductList>> productList) {
//		this.productList = productList;
//	}
}
