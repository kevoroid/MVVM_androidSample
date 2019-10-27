package com.kevoroid.foodshop.ui.mainscreen.foodpage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.kevoroid.foodshop.R;
import com.kevoroid.foodshop.apis.RetroMaster;
import com.kevoroid.foodshop.models.Product;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

	private List<Product> allFood;

	FoodAdapter(List<Product> data) {
		this.allFood = data;
	}

	@NonNull
	@Override
	public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_cardview, parent, false);
		return new FoodViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
		try {
			System.out.println("FoodAdapter.onBindViewHolder :::::::::::::::::::: " + allFood.size());
			System.out.println("FoodAdapter.onBindViewHolder :::::::::::::::::::: " + allFood.get(position).getName());
			System.out.println("FoodAdapter.onBindViewHolder :::::::::::::::::::: " + allFood.get(position).getSalePrice().getAmount());
			holder.foodName.setText(allFood.get(position).getName());
			if (allFood.get(position).getImageUrl().isEmpty()) {
				System.out.println("FoodAdapter.onBindViewHolder >>> has no image >>" + allFood.get(position).getImageUrl());
				holder.foodImage.setImageResource(R.drawable.outline_fastfood_white_24);
			} else {
				System.out.println("FoodAdapter.onBindViewHolder >>> " + RetroMaster.BASE_URL + allFood.get(position).getImageUrl());
				Picasso.get().load(RetroMaster.BASE_URL + allFood.get(position).getImageUrl()).into(holder.foodImage, new Callback() {
					@Override
					public void onSuccess() {

					}

					@Override
					public void onError(Exception e) {
						holder.foodImage.setImageResource(R.drawable.outline_fastfood_white_24);
					}
				});
			}
		} catch (Exception x) {
			x.printStackTrace();
		}
	}

	@Override
	public int getItemCount() {
		return allFood.size();
	}

	static class FoodViewHolder extends RecyclerView.ViewHolder {

		TextView foodName;
		ImageView foodImage;

		FoodViewHolder(@NonNull View itemView) {
			super(itemView);

			foodName = itemView.findViewById(R.id.item_name);
			foodImage = itemView.findViewById(R.id.item_image);

			itemView.setTag(this);
		}
	}
//
//	@FunctionalInterface
//	public interface RecyclerViewCallback {
//
//		void showSelectedTeam(int id);
//	}
}
