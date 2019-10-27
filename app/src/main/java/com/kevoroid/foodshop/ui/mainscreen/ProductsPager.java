package com.kevoroid.foodshop.ui.mainscreen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.kevoroid.foodshop.models.ProductList;

import java.util.List;

public class ProductsPager extends FragmentPagerAdapter {

	private final int TABS_COUNT = 2;
	private String[] tabTitles = {"Food", "Drinks"};

	private List<ProductList> data;

	public ProductsPager(@NonNull FragmentManager fm, int behavior, List<ProductList> data) {
		super(fm, behavior);
		this.data = data;
	}

	@NonNull
	@Override
	public Fragment getItem(int position) {
		System.out.println("ProductsPager.getItem >> " + position);
		if (position == 0) {
			return FoodFragment.newInstance(data.get(0).getProducts());
		} else {
			return DrinksFragment.newInstance(data.get(1).getProducts());
		}
	}

	@Override
	public int getCount() {
		return TABS_COUNT;
	}

	@Nullable
	@Override
	public CharSequence getPageTitle(int position) {
		System.out.println("ProductsPager.getPageTitle >> " + position);
		return tabTitles[position];
	}
}
