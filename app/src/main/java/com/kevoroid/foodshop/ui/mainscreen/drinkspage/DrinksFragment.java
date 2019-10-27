package com.kevoroid.foodshop.ui.mainscreen.drinkspage;

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
import com.kevoroid.foodshop.ui.mainscreen.foodpage.FoodAdapter;

import java.util.ArrayList;
import java.util.List;

public class DrinksFragment extends BaseFragment {

	private static final String DRINKS_BUNDLE = "DRINKS_BUNDLE";

	private RecyclerView recyclerView;
	private DrinksAdapter drinksAdapter;

	public static DrinksFragment newInstance(List<Product> foodList) {
		Bundle args = new Bundle();
		args.putParcelableArrayList(DRINKS_BUNDLE, new ArrayList<>(foodList));
		DrinksFragment fragment = new DrinksFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("DrinksFragment.onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		System.out.println("DrinksFragment.onCreateView");
		return inflater.inflate(R.layout.fragments_drinks, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		System.out.println("DrinksFragment.onViewCreated");

		ArrayList<Product> arrayList  = null;
		if (getArguments() != null) {
			System.out.println("DrinksFragment.onViewCreated not null arguments!");
			arrayList = getArguments().getParcelableArrayList(DRINKS_BUNDLE);
			if (arrayList != null) {
				System.out.println("DrinksFragment.onViewCreated >> " + arrayList);
				System.out.println("DrinksFragment.onViewCreated >>> " + arrayList.get(0).getName());
				System.out.println("DrinksFragment.onViewCreated >>> " + arrayList.get(0).getDescription());
			}
		}

		drinksAdapter = new DrinksAdapter(arrayList);
		recyclerView = view.findViewById(R.id.drinks_recyclerview);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.setAdapter(drinksAdapter);
	}
}
