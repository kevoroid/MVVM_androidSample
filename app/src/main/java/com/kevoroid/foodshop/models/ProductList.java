package com.kevoroid.foodshop.models;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class ProductList implements Parcelable {

	@SerializedName("id")
	private String id;
	@SerializedName("name")
	private String name;
	@SerializedName("description")
	private String description;
	@SerializedName("products")
	private List<Product> products = null;

	protected ProductList(Parcel in) {
		id = in.readString();
		name = in.readString();
		description = in.readString();
		products = in.createTypedArrayList(Product.CREATOR);
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(name);
		dest.writeString(description);
		dest.writeTypedList(products);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<ProductList> CREATOR = new Creator<ProductList>() {
		@Override
		public ProductList createFromParcel(Parcel in) {
			return new ProductList(in);
		}

		@Override
		public ProductList[] newArray(int size) {
			return new ProductList[size];
		}
	};

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
