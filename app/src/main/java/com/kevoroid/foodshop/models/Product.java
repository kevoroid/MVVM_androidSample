package com.kevoroid.foodshop.models;

import com.google.gson.annotations.SerializedName;

// Could have used Parcelable here but for sake of simplicity will avoid that!
public class Product {

	@SerializedName("id")
	private String id;
	@SerializedName("categoryId")
	private String categoryId;
	@SerializedName("name")
	private String name;
	@SerializedName("url")
	private String imageUrl;
	@SerializedName("description")
	private String description;
	@SerializedName("salePrice")
	private Prices salePrice;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Prices getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Prices salePrice) {
		this.salePrice = salePrice;
	}
}