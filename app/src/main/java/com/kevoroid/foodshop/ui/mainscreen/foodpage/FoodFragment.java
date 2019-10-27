package com.kevoroid.foodshop.ui.mainscreen.foodpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.kevoroid.foodshop.R;
import com.kevoroid.foodshop.models.Product;
import com.kevoroid.foodshop.ui.BaseFragment;
import com.kevoroid.foodshop.ui.mainscreen.RecyclerViewCallback;
import com.kevoroid.foodshop.utils.BottomSheetHelper;

import java.util.ArrayList;
import java.util.List;

public class FoodFragment extends BaseFragment implements RecyclerViewCallback {

	private static final String FOOD_BUNDLE = "FOOD_BUNDLE";

	private RecyclerView recyclerView;
	private FoodAdapter foodAdapter;

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
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragments_food, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ArrayList<Product> arrayList  = null;
		if (getArguments() != null) {
			arrayList = getArguments().getParcelableArrayList(FOOD_BUNDLE);
		}

		foodAdapter = new FoodAdapter(this, arrayList);
		recyclerView = view.findViewById(R.id.food_recyclerview);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.setAdapter(foodAdapter);
	}

	@Override
	public void showSelectedTeam(Product id) {
		BottomSheetHelper.showProductDetails(getActivity(), id);
	}
}
