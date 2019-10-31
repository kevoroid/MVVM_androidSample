package com.kevoroid.foodshop.ui.mainscreen.drinkspage;

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
import com.kevoroid.foodshop.R;
import com.kevoroid.foodshop.apis.Status;
import com.kevoroid.foodshop.models.Product;
import com.kevoroid.foodshop.models.viewmodels.ProductListViewModel;
import com.kevoroid.foodshop.ui.BaseFragment;
import com.kevoroid.foodshop.ui.mainscreen.RecyclerViewCallback;
import com.kevoroid.foodshop.utils.BottomSheetHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class DrinksFragment extends BaseFragment implements RecyclerViewCallback {

	private static final String DRINKS_BUNDLE = "DRINKS_BUNDLE";

	private RecyclerView recyclerView;
	private DrinksAdapter drinksAdapter;
	private ArrayList<Product> arrayList;

	private ProductListViewModel productListViewModel;

	public static DrinksFragment newInstance(List<Product> drinkList) {
		Bundle args = new Bundle();
		args.putParcelableArrayList(DRINKS_BUNDLE, new ArrayList<>(drinkList));
		DrinksFragment fragment = new DrinksFragment();
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
		return inflater.inflate(R.layout.fragments_drinks, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		productListViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(ProductListViewModel.class);
		productListViewModel.getProductList().observe(this, productLists -> {
			if (Objects.requireNonNull(productListViewModel.getProductList().getValue()).status == Status.ERROR) {
				showErr();
			} else {
				if (arrayList != null && productListViewModel.getProductList().getValue() != null) {
					int oldListItemsCount = arrayList.size();
					arrayList.clear();
					arrayList.addAll(Objects.requireNonNull(productListViewModel.getProductList().getValue().data).get(1).getProducts());
					drinksAdapter.notifyItemChanged(oldListItemsCount + 1, arrayList);
					//drinksAdapter.notifyDataSetChanged();
					recyclerView.smoothScrollToPosition(productListViewModel.getProductList().getValue().data.get(1).getProducts().size() - 1);
					hideLoading();
				}
			}
		});

		if (getArguments() != null) {
			arrayList = getArguments().getParcelableArrayList(DRINKS_BUNDLE);
		}

		drinksAdapter = new DrinksAdapter(this, arrayList);
		recyclerView = view.findViewById(R.id.drinks_recyclerview);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.setAdapter(drinksAdapter);
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()) {
			case R.id.main_menu_add_item:
				showLoading();
				addNewDrinks();
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private void addNewDrinks() {
		productListViewModel.addNewProduct(1, new Product(returnRandomDrinks()));
	}

	private String returnRandomDrinks() {
		String[] randomDrinks = {"Wine", "Whisky", "Milk", "Ice Tea", "Coffee"};
		Random random = new Random();
		int randomNumber = random.nextInt(randomDrinks.length);
		return randomDrinks[randomNumber];
	}

	@Override
	public void showSelectedTeam(Product id) {
		BottomSheetHelper.showProductDetails(getActivity(), id);
	}
}
