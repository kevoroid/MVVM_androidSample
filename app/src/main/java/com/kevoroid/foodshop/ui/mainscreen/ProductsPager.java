package com.kevoroid.foodshop.ui.mainscreen;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.kevoroid.foodshop.apis.Resource;
import com.kevoroid.foodshop.models.ProductList;
import com.kevoroid.foodshop.ui.mainscreen.drinkspage.DrinksFragment;
import com.kevoroid.foodshop.ui.mainscreen.foodpage.FoodFragment;

import java.util.ArrayList;
import java.util.List;

public class ProductsPager extends FragmentPagerAdapter {

	private static final String TAG = "ProductsPager";

	private final int TABS_COUNT = 2;
	private String[] tabTitles = {"Food", "Drinks"};

	private Resource<List<ProductList>> listResource;

	public ProductsPager(@NonNull FragmentManager fm, int behavior, Resource<List<ProductList>> listResource) {
		super(fm, behavior);
		Log.d(TAG, "ProductsPager: created!");
		this.listResource = listResource;
	}

	@NonNull
	@Override
	public Fragment getItem(int position) {
		if (position == 0) {
			if (listResource != null && listResource.data != null) {
				return FoodFragment.newInstance(listResource.data.get(0).getProducts());
			} else {
				return FoodFragment.newInstance(new ArrayList<>());
			}
		} else {
			if (listResource != null && listResource.data != null) {
				return DrinksFragment.newInstance(listResource.data.get(1).getProducts());
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
