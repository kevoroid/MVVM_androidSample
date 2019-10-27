package com.kevoroid.foodshop.ui.mainscreen;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ProductsPager extends FragmentPagerAdapter {

	private final int TABS_COUNT = 2;
	private String[] tabTitles = {"Food", "Drinks"};

	ProductsPager(@NonNull FragmentManager fm, int behavior) {
		super(fm, behavior);
	}

	@NonNull
	@Override
	public Fragment getItem(int position) {
		System.out.println("FoodFragment.getItem >> " + position);
		if (position == 0) {
			return FoodFragment.newInstance();
		} else {
			return DrinksFragment.newInstance();
		}
	}

	@Override
	public int getCount() {
		return TABS_COUNT;
	}

	@Nullable
	@Override
	public CharSequence getPageTitle(int position) {
		System.out.println("FoodFragment.getPageTitle >> " + position);
		return tabTitles[position];
	}
}
