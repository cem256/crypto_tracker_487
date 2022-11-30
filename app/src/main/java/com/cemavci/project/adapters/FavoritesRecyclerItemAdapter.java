package com.cemavci.project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cemavci.project.R;
import com.cemavci.project.models.CoinModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavoritesRecyclerItemAdapter extends RecyclerView.Adapter<FavoritesRecyclerItemAdapter.FavoritesRecyclerItemHolder> {

    private Context context;
    private ArrayList<CoinModel> favoritesArrayList;


    public FavoritesRecyclerItemAdapter(Context context, ArrayList<CoinModel> favoritesArrayList) {
        this.context = context;
        this.favoritesArrayList = favoritesArrayList;
    }

    @NonNull
    @Override
    public FavoritesRecyclerItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.coin_recycler_item, parent, false);
        return new FavoritesRecyclerItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesRecyclerItemHolder holder, int position) {


        CoinModel coin = favoritesArrayList.get(position);
        holder.addFavoriteButton.setVisibility(View.GONE);
        holder.coinPrice.setVisibility(View.GONE);
        holder.coinName.setText(coin.getName());
        Picasso.get().load(coin.getImageUrl()).into(holder.coinImageView);
    }


    @Override
    public int getItemCount() {
        return favoritesArrayList.size();
    }

    public static class FavoritesRecyclerItemHolder extends RecyclerView.ViewHolder {

        ImageView coinImageView;
        TextView coinName;
        TextView coinPrice;
        ImageButton addFavoriteButton;

        public FavoritesRecyclerItemHolder(@NonNull View itemView) {
            super(itemView);

            coinImageView = itemView.findViewById(R.id.coinImageView);
            coinName = itemView.findViewById(R.id.coinName);
            coinPrice = itemView.findViewById(R.id.coinPrice);
            addFavoriteButton = itemView.findViewById(R.id.addFavoriteButton);

        }
    }


}
