package com.cemavci.project.adapters;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cemavci.project.R;
import com.cemavci.project.database.DatabaseHelper;
import com.cemavci.project.database.FavoritesTable;
import com.cemavci.project.models.CoinModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TopCoinRecyclerItemAdapter extends RecyclerView.Adapter<TopCoinRecyclerItemAdapter.TopCoinRecyclerItemHolder> {

    private Context context;
    private ArrayList<CoinModel> topCoinsArrayList;
    private ArrayList<CoinModel> favoritesArrayList;
    private DatabaseHelper databaseHelper;
    private MediaPlayer mediaPlayer;


    public TopCoinRecyclerItemAdapter(Context context, ArrayList<CoinModel> topCoinsArrayList,
                                      ArrayList<CoinModel> favoritesArrayList) {
        this.context = context;
        this.topCoinsArrayList = topCoinsArrayList;
        this.favoritesArrayList = favoritesArrayList;
        this.databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public TopCoinRecyclerItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.coin_recycler_item, parent, false);
        return new TopCoinRecyclerItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TopCoinRecyclerItemHolder holder, int position) {

        CoinModel coin = topCoinsArrayList.get(position);
        holder.coinName.setText(coin.getName());
        holder.coinPrice.setText("$" + coin.getCurrentPrice());
        Picasso.get().load(coin.getImageUrl()).into(holder.coinImageView);


        if (isFavorite(coin.getSymbol(), favoritesArrayList)) {
            holder.addFavoriteButton.setColorFilter(Color.YELLOW);
        } else {
            holder.addFavoriteButton.setColorFilter(Color.GRAY);
        }

        holder.addFavoriteButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mediaPlayer = MediaPlayer.create(context, R.raw.click_sound);
                mediaPlayer.start();


                if (isFavorite(coin.getSymbol(), favoritesArrayList)) {
                    FavoritesTable.delete(databaseHelper, coin);
                    Toast.makeText(context, coin.getName() + "is removed from favorites", Toast.LENGTH_SHORT).show();
                    holder.addFavoriteButton.setColorFilter(Color.GRAY);
                } else {
                    FavoritesTable.insert(databaseHelper, coin);
                    Toast.makeText(context, coin.getName() + "is added to favorites", Toast.LENGTH_SHORT).show();
                    holder.addFavoriteButton.setColorFilter(Color.YELLOW);

                }
                favoritesArrayList = FavoritesTable.getAllFavorites(databaseHelper);
                return false;
            }
        });
    }

    private boolean isFavorite(String symbol, ArrayList<CoinModel> favoritesArrayList) {

        for (CoinModel customer : favoritesArrayList) {
            if (customer.getSymbol().equals(symbol)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public int getItemCount() {
        return topCoinsArrayList.size();
    }

    public static class TopCoinRecyclerItemHolder extends RecyclerView.ViewHolder {

        ImageView coinImageView;
        TextView coinName;
        TextView coinPrice;
        ImageButton addFavoriteButton;

        public TopCoinRecyclerItemHolder(@NonNull View itemView) {
            super(itemView);

            coinImageView = itemView.findViewById(R.id.coinImageView);
            coinName = itemView.findViewById(R.id.coinName);
            coinPrice = itemView.findViewById(R.id.coinPrice);
            addFavoriteButton = itemView.findViewById(R.id.addFavoriteButton);
        }


    }

}
