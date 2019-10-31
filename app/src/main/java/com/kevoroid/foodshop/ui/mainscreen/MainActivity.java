package com.kevoroid.foodshop.ui.mainscreen;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.kevoroid.foodshop.R;
import com.kevoroid.foodshop.models.viewmodels.ProductListViewModel;
import com.kevoroid.foodshop.ui.BaseActivity;

public class MainActivity extends BaseActivity {

	private ProductListViewModel productListViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		productListViewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);
		productListViewModel.init();

		setupViewPager();
	}

	private void setupViewPager() {
		ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
		viewPager.setAdapter(new ProductsPager(getSupportFragmentManager(), 1, productListViewModel.getProductList().getValue()));
		//viewPager.setOffscreenPageLimit(1); // if ViewPager was extending from FragmentStatePagerAdapter
		TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
		tabLayout.setupWithViewPager(viewPager);
	}
}
