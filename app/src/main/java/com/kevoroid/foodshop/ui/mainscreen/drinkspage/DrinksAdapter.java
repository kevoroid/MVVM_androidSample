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
import com.kevoroid.foodshop.ui.mainscreen.RecyclerViewCallback;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.DrinksViewHolder> {

	private RecyclerViewCallback recyclerViewCallback;
	private List<Product> allDrinks;

	DrinksAdapter(RecyclerViewCallback recyclerViewCallback, List<Product> data) {
		this.recyclerViewCallback = recyclerViewCallback;
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
			holder.drinkName.setText(allDrinks.get(position).getName());
			if (allDrinks.get(position).getImageUrl().isEmpty()) {
				holder.drinkImage.setImageResource(R.drawable.outline_fastfood_white_24);
			} else {
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

	class DrinksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		TextView drinkName;
		ImageView drinkImage;

		DrinksViewHolder(@NonNull View itemView) {
			super(itemView);

			drinkName = itemView.findViewById(R.id.item_name);
			drinkImage = itemView.findViewById(R.id.item_image);

			itemView.setOnClickListener(this);
			itemView.setTag(this);
		}

		@Override
		public void onClick(View v) {
			recyclerViewCallback.showSelectedTeam(allDrinks.get(getAdapterPosition()));
		}
	}
}
