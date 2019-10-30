package com.kevoroid.foodshop.ui.mainscreen.foodpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.kevoroid.foodshop.R;
import com.kevoroid.foodshop.models.Product;
import com.kevoroid.foodshop.models.ProductList;
import com.kevoroid.foodshop.models.viewmodels.ProductListViewModel;
import com.kevoroid.foodshop.ui.BaseFragment;
import com.kevoroid.foodshop.ui.mainscreen.RecyclerViewCallback;
import com.kevoroid.foodshop.utils.BottomSheetHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FoodFragment extends BaseFragment implements RecyclerViewCallback {

	private static final String FOOD_BUNDLE = "FOOD_BUNDLE";

	private RecyclerView recyclerView;
	private FoodAdapter foodAdapter;
	private List<Product> arrayList;

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

		productListViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(ProductListViewModel.class);
		productListViewModel.getProductList().observe(getActivity(), productLists -> {
			if (arrayList != null && productListViewModel.getProductList().getValue() != null) {
				arrayList.clear();
				arrayList.addAll(productListViewModel.getProductList().getValue().get(0).getProducts());
				foodAdapter.notifyDataSetChanged();
				recyclerView.smoothScrollToPosition(productListViewModel.getProductList().getValue().get(0).getProducts().size() - 1);
//					hideLoading();
			}
		});

		if (getArguments() != null) {
			arrayList = getArguments().getParcelableArrayList(FOOD_BUNDLE);
		}

		foodAdapter = new FoodAdapter(this, arrayList);
		recyclerView = view.findViewById(R.id.food_recyclerview);
		recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
		recyclerView.setAdapter(foodAdapter);
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()) {
			case R.id.main_menu_add_item:
				Toast.makeText(getContext(), getResources().getString(R.string.msg_new_product_added_food), Toast.LENGTH_SHORT).show();
				addNewFood();
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private void addNewFood() {
//		showLoading();
		productListViewModel.addNewProduct(0, new Product("Pizza"));
	}

	@Override
	public void showSelectedTeam(Product id) {
		BottomSheetHelper.showProductDetails(getActivity(), id);
	}
}
