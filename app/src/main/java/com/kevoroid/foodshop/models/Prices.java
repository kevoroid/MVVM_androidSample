package com.kevoroid.foodshop.models;

import com.google.gson.annotations.SerializedName;

// Could have used Parcelable here but for sake of simplicity will avoid that!
public class Prices {

	@SerializedName("amount")
	private String amount;
	@SerializedName("currency")
	private String currency;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
