package com.kevoroid.foodshop.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.kevoroid.foodshop.R;
import com.kevoroid.foodshop.apis.RetroMaster;
import com.kevoroid.foodshop.models.Product;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class BottomSheetHelper {

	public static void showProductDetails(Context context, Product product) {
		View bottomSheetView = View.inflate(context, R.layout.bottom_sheet_item_detail, null);
		BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Objects.requireNonNull(context));
		bottomSheetDialog.setContentView(bottomSheetView);

		TextView itemName = (TextView) bottomSheetView.findViewById(R.id.item_detail_name);
		ImageView itemImage = (ImageView) bottomSheetView.findViewById(R.id.item_detail_image);
		TextView itemPrice = (TextView) bottomSheetView.findViewById(R.id.item_detail_price);
		TextView itemCurrency = (TextView) bottomSheetView.findViewById(R.id.item_detail_currency);

		itemName.setText(product.getName());
		itemPrice.setText(product.getSalePrice().getAmount());
		itemCurrency.setText(product.getSalePrice().getCurrency());
		Picasso.get().load(RetroMaster.BASE_URL + product.getImageUrl()).into(itemImage, new Callback() {
			@Override
			public void onSuccess() {
			}

			@Override
			public void onError(Exception e) {
				itemImage.setImageResource(R.drawable.outline_fastfood_white_48);
			}
		});

		try {
			bottomSheetDialog.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
