package com.kevoroid.foodshop.ui.mainscreen;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.kevoroid.foodshop.R;
import com.kevoroid.foodshop.models.ProductList;
import com.kevoroid.foodshop.models.viewmodels.ProductListViewModel;
import com.kevoroid.foodshop.ui.BaseActivity;
import com.kevoroid.foodshop.utils.NetworkHandler;
import com.kevoroid.foodshop.utils.PromptHandler;

import java.util.List;

public class MainActivity extends BaseActivity {

	private ProductListViewModel productListViewModel;

	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage(getResources().getString(R.string.label_please_wait));
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(false);

		showLoading();
		productListViewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);
		productListViewModel.init();
		if (NetworkHandler.internetAvailable(this)) {
			productListViewModel.getProductList().observe(this, getStuff());
		} else {
			showErr();
		}
	}

	private Observer<List<ProductList>> getStuff() {
		return productLists -> {
			setupViewPager();
			hideLoading();
		};
	}

	private void setupViewPager() {
		ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
		viewPager.setAdapter(new ProductsPager(getSupportFragmentManager(), 1, productListViewModel.getProductList().getValue()));
		//viewPager.setOffscreenPageLimit(1); // if ViewPager was extending from FragmentStatePagerAdapter
		TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
		tabLayout.setupWithViewPager(viewPager);
	}

	public void showLoading() {
		progressDialog.show();
	}

	public void hideLoading() {
		progressDialog.dismiss();
	}

	public void showErr() {
		hideLoading();
		PromptHandler.showErrSnackBar(getMainLayout(), this);
	}

	public View getMainLayout() {
		return findViewById(android.R.id.content);
	}
}
