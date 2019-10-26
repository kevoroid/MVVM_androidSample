package com.kevoroid.foodshop.apis;

import com.kevoroid.foodshop.models.ProductList;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface ApiEndpoints {

	@GET()
	Call<List<ProductList>> getProductCategories();

}
