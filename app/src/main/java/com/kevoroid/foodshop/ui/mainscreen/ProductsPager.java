package com.kevoroid.foodshop.ui.mainscreen;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.kevoroid.foodshop.models.ProductList;
import com.kevoroid.foodshop.ui.mainscreen.drinkspage.DrinksFragment;
import com.kevoroid.foodshop.ui.mainscreen.foodpage.FoodFragment;

import java.util.ArrayList;
import java.util.List;

public class ProductsPager extends FragmentPagerAdapter {

	private static final String TAG = "ProductsPager";

	private final int TABS_COUNT = 2;
	private String[] tabTitles = {"Food", "Drinks"};

	private List<ProductList> data;

	public ProductsPager(@NonNull FragmentManager fm, int behavior, List<ProductList> data) {
		super(fm, behavior);
		Log.d(TAG, "ProductsPager: created!");
		this.data = data;
	}

	@NonNull
	@Override
	public Fragment getItem(int position) {
		if (position == 0) {
			if (data != null) {
				return FoodFragment.newInstance(data.get(0).getProducts());
			} else {
				return FoodFragment.newInstance(new ArrayList<>());
			}
		} else {
			if (data != null) {
				return DrinksFragment.newInstance(data.get(1).getProducts());
			} else {
				return DrinksFragment.newInstance(new ArrayList<>());
			}
		}
	}

	@Override
	public int getCount() {
		return TABS_COUNT;
	}

	@Nullable
	@Override
	public CharSequence getPageTitle(int position) {
		return tabTitles[position];
	}
}
