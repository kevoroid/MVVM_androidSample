package com.kevoroid.foodshop.ui.mainscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kevoroid.foodshop.R;
import com.kevoroid.foodshop.ui.BaseFragment;

public class DrinksFragment extends BaseFragment {

	public static DrinksFragment newInstance() {
		Bundle args = new Bundle();
		DrinksFragment fragment = new DrinksFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragments_drinks, container, false);
	}
}
