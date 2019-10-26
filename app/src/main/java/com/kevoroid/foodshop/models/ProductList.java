package com.kevoroid.foodshop.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

// Could have used Parcelable here but for sake of simplicity will avoid that!
public class ProductList {

	@SerializedName("id")
	private String id;
	@SerializedName("name")
	private String name;
	@SerializedName("description")
	private String description;
	@SerializedName("products")
	private List<Product> products = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
