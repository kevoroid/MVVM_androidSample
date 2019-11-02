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
import com.kevoroid.foodshop.utils.LoggerDude;
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
		return new DrinksViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_cardview, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull DrinksViewHolder holder, int position) {
		try {
			holder.drinkName.setText(allDrinks.get(position).getName());
			if (allDrinks.get(position).getImageUrl() == null || allDrinks.get(position).getImageUrl().isEmpty()) {
				holder.drinkImage.setImageResource(R.drawable.ic_fastfood_24px);
			} else {
				Picasso.get().load(RetroMaster.returnProductImageUrl(allDrinks.get(position).getImageUrl()))
						.error(R.drawable.ic_fastfood_24px)
						.into(holder.drinkImage);
			}
		} catch (Exception x) {
			LoggerDude.debug(getClass().getSimpleName(), x.getLocalizedMessage());
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
