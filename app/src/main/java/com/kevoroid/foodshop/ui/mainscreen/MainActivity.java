package com.kevoroid.foodshop.ui.mainscreen;

import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.kevoroid.foodshop.R;
import com.kevoroid.foodshop.models.ProductList;
import com.kevoroid.foodshop.models.viewmodels.ProductListViewModel;
import com.kevoroid.foodshop.ui.BaseActivity;

import java.util.List;

public class MainActivity extends BaseActivity {

	private ProductListViewModel productListViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		productListViewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);
		productListViewModel.getProductList().observe(this, new Observer<List<ProductList>>() {
			@Override
			public void onChanged(List<ProductList> productLists) {
				System.out.println("MainActivity.onChanged :: " + productLists.get(0).getName());
				System.out.println("MainActivity.onChanged :: " + productLists.get(0).getProducts().size());
				System.out.println("MainActivity.onChanged :: " + productLists.get(1).getName());
			}
		});
	}
}
