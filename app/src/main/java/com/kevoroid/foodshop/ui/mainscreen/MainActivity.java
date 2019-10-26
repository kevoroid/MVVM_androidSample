package com.kevoroid.foodshop.ui.mainscreen;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.kevoroid.foodshop.R;
import com.kevoroid.foodshop.apis.Resource;
import com.kevoroid.foodshop.apis.Status;
import com.kevoroid.foodshop.models.ProductList;
import com.kevoroid.foodshop.models.viewmodels.ProductListViewModel;
import com.kevoroid.foodshop.ui.BaseActivity;
import com.kevoroid.foodshop.utils.NetworkHandler;
import com.kevoroid.foodshop.utils.PromptHandler;

import java.util.List;

public class MainActivity extends BaseActivity {

	private ProductListViewModel productListViewModel;

	// I know its deprecated and better to use Progressbar but for sake of simplicity!
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage(getResources().getString(R.string.label_please_wait));
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(false);

		productListViewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);
//		if (NetworkHandler.internetAvailable(this)) {
			productListViewModel.getProductList().observe(this, getStuff());
//		} else {
//			showErr();
//		}
	}

	private Observer<Resource<List<ProductList>>> getStuff() {
		return productLists -> {
//			if (productLists.status.equals(Status.ERROR)) {
//				System.out.println("MainActivity.getStuff >>>>>> " + productLists);
//				System.out.println("MainActivity.getStuff >>>>>> " + productLists.data);
//				System.out.println("MainActivity.getStuff >>>>>> " + productLists.message);
//				System.out.println("MainActivity.getStuff >>>>>> " + productLists.status);
//			} else {
//				if (productLists.data != null) {
//					System.out.println("MainActivity.onChanged :: " + productLists.data.get(0).getName());
//					System.out.println("MainActivity.onChanged :: " + productLists.data.get(0).getProducts().size());
//					System.out.println("MainActivity.onChanged :: " + productLists.data.get(1).getName());
//				}
//			}
		};
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
