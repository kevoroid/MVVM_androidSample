package com.kevoroid.foodshop.ui.mainscreen.drinkspage;

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
import com.kevoroid.foodshop.ui.mainscreen.foodpage.FoodAdapter;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.DrinksViewHolder> {

	private List<Product> allDrinks;

	DrinksAdapter(List<Product> data) {
		this.allDrinks = data;
	}

	@NonNull
	@Override
	public DrinksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_cardview, parent, false);
		return new DrinksAdapter.DrinksViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull DrinksViewHolder holder, int position) {
		try {
			System.out.println("FoodAdapter.onBindViewHolder :::::::::::::::::::: " + allDrinks.size());
			System.out.println("FoodAdapter.onBindViewHolder :::::::::::::::::::: " + allDrinks.get(position).getName());
			System.out.println("FoodAdapter.onBindViewHolder :::::::::::::::::::: " + allDrinks.get(position).getSalePrice().getAmount());
			holder.drinkName.setText(allDrinks.get(position).getName());
			if (allDrinks.get(position).getImageUrl().isEmpty()) {
				System.out.println("FoodAdapter.onBindViewHolder >>> has no image >>" + allDrinks.get(position).getImageUrl());
				holder.drinkImage.setImageResource(R.drawable.outline_fastfood_white_24);
			} else {
				System.out.println("FoodAdapter.onBindViewHolder >>> " + RetroMaster.BASE_URL + allDrinks.get(position).getImageUrl());
				Picasso.get().load(RetroMaster.BASE_URL + allDrinks.get(position).getImageUrl()).into(holder.drinkImage, new Callback() {
					@Override
					public void onSuccess() {
					}

					@Override
					public void onError(Exception e) {
						holder.drinkImage.setImageResource(R.drawable.outline_fastfood_white_24);
					}
				});
			}
		} catch (Exception x) {
			x.printStackTrace();
		}
	}

	@Override
	public int getItemCount() {
		return allDrinks.size();
	}

	static class DrinksViewHolder extends RecyclerView.ViewHolder {

		TextView drinkName;
		ImageView drinkImage;

		DrinksViewHolder(@NonNull View itemView) {
			super(itemView);

			drinkName = itemView.findViewById(R.id.item_name);
			drinkImage = itemView.findViewById(R.id.item_image);

			itemView.setTag(this);
		}
	}

//	@FunctionalInterface
//	public interface RecyclerViewCallback {
//
//		void showSelectedTeam(int id);
//	}

}
