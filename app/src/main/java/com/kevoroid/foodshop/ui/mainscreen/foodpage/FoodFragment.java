package com.kevoroid.foodshop.ui.mainscreen.foodpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.kevoroid.foodshop.R;
import com.kevoroid.foodshop.apis.Status;
import com.kevoroid.foodshop.models.Product;
import com.kevoroid.foodshop.models.viewmodels.ProductListViewModel;
import com.kevoroid.foodshop.ui.BaseFragment;
import com.kevoroid.foodshop.ui.mainscreen.RecyclerViewCallback;
import com.kevoroid.foodshop.utils.BottomSheetHelper;
import com.kevoroid.foodshop.utils.NetworkHandler;

import java.util.*;

public class FoodFragment extends BaseFragment implements RecyclerViewCallback {

	private static final String FOOD_BUNDLE = "FOOD_BUNDLE";

	private RecyclerView recyclerView;
	private FoodAdapter foodAdapter;
	private List<Product> arrayList;

	private BottomSheetDialog bottomSheetDialog;
	private ProductListViewModel productListViewModel;

	public static FoodFragment newInstance(List<Product> foodList) {
		Bundle args = new Bundle();
		args.putParcelableArrayList(FOOD_BUNDLE, new ArrayList<>(foodList));
		FoodFragment fragment = new FoodFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragments_food, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		showLoading();

		if (getArguments() != null) {
			arrayList = getArguments().getParcelableArrayList(FOOD_BUNDLE);
		}

		foodAdapter = new FoodAdapter(this, arrayList);
		recyclerView = view.findViewById(R.id.food_recyclerview);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.setAdapter(foodAdapter);

		initObservers();
	}

	private void initObservers() {
		productListViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(ProductListViewModel.class);
		productListViewModel.getProductList().observe(getActivity(), productLists -> {
			if (Objects.requireNonNull(productListViewModel.getProductList().getValue()).status == Status.ERROR) {
				showErr();
				hideLoading();
			} else {
				if (arrayList != null && productListViewModel.getProductList().getValue().data != null) {
					arrayList.clear();
					arrayList.addAll(productListViewModel.getProductList().getValue().data.get(0).getProducts());
					foodAdapter.notifyDataSetChanged();
					recyclerView.smoothScrollToPosition(productListViewModel.getProductList().getValue().data.get(0).getProducts().size() - 1);
					hideLoading();
				} else {
					showErr();
					hideLoading();
				}
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()) {
			case R.id.main_menu_add_item:
				showLoading();
				addNewFood();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private void addNewFood() {
		if (getActivity() != null && NetworkHandler.internetAvailable(getActivity())) {
			productListViewModel.addNewProduct(0, new Product(returnRandomFood()));
		} else {
			showConnectionErr();
		}
	}

	// Leaving these here (not on top) just because this serves as POC and not real prod use-case!
	// Note: these are not saved/cached! upon restart of the app, the default set from API with be fetched again and displayed!
	private String[] randomFood = {"Pizza", "Burger", "Pasta", "Shake Shack", "Hotdog"};
	private Random random = new Random();

	private String returnRandomFood() {
		int randomNumber = random.nextInt(randomFood.length);
		return randomFood[randomNumber];
	}

	@Override
	public void showSelectedTeam(Product id) {
		//BottomSheetHelper.showProductDetails(getActivity(), id);
		bottomSheetDialog = BottomSheetHelper.showProductDetails(getActivity(), id);
		bottomSheetDialog.show();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (bottomSheetDialog != null) {
			bottomSheetDialog.cancel();
		}
	}
}
