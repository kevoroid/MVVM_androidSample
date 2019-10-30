package com.kevoroid.foodshop.models.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.kevoroid.foodshop.models.Product;
import com.kevoroid.foodshop.models.ProductList;
import com.kevoroid.foodshop.models.repos.MasterRepo;

import java.util.List;

public class ProductListViewModel extends ViewModel {

	private MasterRepo masterRepo;

	//private LiveData<Resource<List<ProductList>>> productList; // try using Resource<T>
	private MutableLiveData<List<ProductList>> productList;

	public void init() {
		if (productList != null) {
			return;
		}

		masterRepo = MasterRepo.getInstance();
		productList = masterRepo.getProductList();
	}

	//public LiveData<Resource<List<ProductList>>> getProductList() { // try using Resource<T>
	public LiveData<List<ProductList>> getProductList() {
		return productList;
	}

	/**
	 * @param productListIndex -> 0 for Food, 1 for Drinks
	 * @param product
	 */
	public void addNewProduct(int productListIndex, Product product) {
		List<ProductList> productsInStock = productList.getValue();
		if (productsInStock != null) {
			productsInStock.get(productListIndex).getProducts().add(product);
		}
		productList.postValue(productsInStock);
	}
}
