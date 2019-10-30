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
import com.kevoroid.foodshop.ui.mainscreen.RecyclerViewCallback;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

	private RecyclerViewCallback recyclerViewCallback;
	private List<Product> allFood;

	FoodAdapter(RecyclerViewCallback recyclerViewCallback, List<Product> data) {
		this.recyclerViewCallback = recyclerViewCallback;
		this.allFood = data;
	}

	@NonNull
	@Override
	public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new FoodViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_cardview, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
		try {
			holder.foodName.setText(allFood.get(position).getName());
			if (allFood.get(position).getImageUrl() == null || allFood.get(position).getImageUrl().isEmpty()) {
				holder.foodImage.setImageResource(R.drawable.ic_fastfood_24px);
			} else {
				Picasso.get().load(RetroMaster.returnProductImageUrl(allFood.get(position).getImageUrl()))
						.into(holder.foodImage, new Callback() {
					@Override
					public void onSuccess() {
					}

					@Override
					public void onError(Exception e) {
						holder.foodImage.setImageResource(R.drawable.ic_fastfood_24px);
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

	class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		TextView foodName;
		ImageView foodImage;

		FoodViewHolder(@NonNull View itemView) {
			super(itemView);

			foodName = itemView.findViewById(R.id.item_name);
			foodImage = itemView.findViewById(R.id.item_image);

			itemView.setOnClickListener(this);
			itemView.setTag(this);
		}

		@Override
		public void onClick(View v) {
			// Another way would be to send product's id and fetch it inside fragment -> Integer.parseInt(allFood.get(getAdapterPosition()).getId())
			recyclerViewCallback.showSelectedTeam(allFood.get(getAdapterPosition()));
		}
	}
}
