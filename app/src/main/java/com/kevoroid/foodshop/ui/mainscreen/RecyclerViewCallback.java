package com.kevoroid.foodshop.ui.mainscreen;

import com.kevoroid.foodshop.models.Product;

@FunctionalInterface
public interface RecyclerViewCallback {

	void showSelectedTeam(Product product);
}
