package com.kevoroid.foodshop.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Prices implements Parcelable {

	@SerializedName("amount")
	private String amount;
	@SerializedName("currency")
	private String currency;

	private Prices(Parcel in) {
		amount = in.readString();
		currency = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(amount);
		dest.writeString(currency);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Prices> CREATOR = new Creator<Prices>() {
		@Override
		public Prices createFromParcel(Parcel in) {
			return new Prices(in);
		}

		@Override
		public Prices[] newArray(int size) {
			return new Prices[size];
		}
	};

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
