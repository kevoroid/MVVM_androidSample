package com.kevoroid.foodshop.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.kevoroid.foodshop.R;
import com.kevoroid.foodshop.apis.RetroMaster;
import com.kevoroid.foodshop.models.Product;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class BottomSheetHelper {

	private static final String TAG = "BottomSheetHelper";

	private BottomSheetHelper() {
	}

	public static void showProductDetails(Context context, Product product) {
		View bottomSheetView = View.inflate(context, R.layout.bottom_sheet_item_detail, null);
		BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Objects.requireNonNull(context));
		bottomSheetDialog.setCanceledOnTouchOutside(true);
		bottomSheetDialog.setContentView(bottomSheetView);

		TextView itemName = (TextView) bottomSheetView.findViewById(R.id.item_detail_name);
		ImageView itemImage = (ImageView) bottomSheetView.findViewById(R.id.item_detail_image);
		TextView itemPrice = (TextView) bottomSheetView.findViewById(R.id.item_detail_price);
		TextView itemCurrency = (TextView) bottomSheetView.findViewById(R.id.item_detail_currency);

		itemName.setText(product.getName());
		if (product.getSalePrice() != null) itemPrice.setText(product.getSalePrice().getAmount());
		if (product.getSalePrice() != null) itemCurrency.setText(product.getSalePrice().getCurrency());
		Picasso.get().load(RetroMaster.returnProductImageUrl(product.getImageUrl()))
				.error(R.drawable.ic_fastfood_24px)
				.into(itemImage);

		bottomSheetDialog.setOnShowListener(dialog -> {
			FrameLayout bottomSheet = (FrameLayout) bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
			// Need to set layout param app:layout_behavior="com.google.android.material.bottomsheet.BottomSheetBehavior" in order to use below code!
			//LinearLayout bottomSheet = bottomSheetDialog.findViewById(R.id.bottom_sheet_item_detail_inner_layout);
			if (bottomSheet != null) {
				BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED)
			}
		});

		try {
			// TODO: fix view leak!
			// There is a view leak here if bottomSheet is visible and device rotates!
			bottomSheetDialog.show();
		} catch (Exception x) {
			LoggerDude.debug(TAG, x.getLocalizedMessage());
		}
	}
}
