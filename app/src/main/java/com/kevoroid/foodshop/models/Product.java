package com.kevoroid.foodshop.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Product implements Parcelable {

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

	public Product(String name) {
		this.name = name;
	}

	private Product(Parcel in) {
		id = in.readString();
		categoryId = in.readString();
		name = in.readString();
		imageUrl = in.readString();
		description = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(categoryId);
		dest.writeString(name);
		dest.writeString(imageUrl);
		dest.writeString(description);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Product> CREATOR = new Creator<Product>() {
		@Override
		public Product createFromParcel(Parcel in) {
			return new Product(in);
		}

		@Override
		public Product[] newArray(int size) {
			return new Product[size];
		}
	};

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