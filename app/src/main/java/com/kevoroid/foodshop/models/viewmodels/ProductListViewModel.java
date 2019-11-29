package com.kevoroid.foodshop.models.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.kevoroid.foodshop.YummyApplication;
import com.kevoroid.foodshop.apis.Resource;
import com.kevoroid.foodshop.models.Product;
import com.kevoroid.foodshop.models.ProductList;

import java.util.List;
import java.util.Objects;

public class ProductListViewModel extends ViewModel {

	// Could've used 2 viewModels; one for food page and one for drinks, but using one for sake of this sample!
	private MutableLiveData<Resource<List<ProductList>>> productList;

	public void init() {
		if (productList != null) {
			return;
		}

		productList = YummyApplication.getYummyComponent().getRepo().getProductList();
	}

	public LiveData<Resource<List<ProductList>>> getProductList() {
		return productList;
	}

	/**
	 * @param productListIndex: 0 for Food, 1 for Drinks
	 * @param product: data
	 */
	public void addNewProduct(int productListIndex, Product product) {
		List<ProductList> productsInStock = Objects.requireNonNull(productList.getValue()).data;
		if (productsInStock != null) {
			productsInStock.get(productListIndex).getProducts().add(product);
		}
		productList.postValue(Resource.success(productsInStock));
	}
}
