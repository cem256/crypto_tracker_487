package com.avciuyguntopcu.project.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avciuyguntopcu.project.R;
import com.avciuyguntopcu.project.adapters.TopCoinRecyclerItemAdapter;
import com.avciuyguntopcu.project.database.DatabaseHelper;
import com.avciuyguntopcu.project.database.FavoritesTable;
import com.avciuyguntopcu.project.models.CoinModel;
import com.avciuyguntopcu.project.network.CoinApi;
import com.avciuyguntopcu.project.network.RetrofitManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class TopCoinsFragment extends Fragment {

    private CoinApi coinApi;
    // holds the data coming from the api
    private ArrayList<CoinModel> topCoinsArrayList;
    // holds the favorites data
    private ArrayList<CoinModel> favoritesArrayList;
    private DatabaseHelper databaseHelper;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TopCoinRecyclerItemAdapter recyclerItemAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_coins, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.topCoinsRecycler);
        progressBar = view.findViewById(R.id.topCoinsProgressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        databaseHelper = new DatabaseHelper(getContext());
        // sends the api request to the endpoint

        getTopCoins();


    }

    private void getTopCoins() {
        // create retrofit instance
        Retrofit retrofitInstance = RetrofitManager.getClient();
        coinApi = retrofitInstance.create(CoinApi.class);

        coinApi.getTopCoins().enqueue(new Callback<ArrayList<CoinModel>>() {
            @Override
            public void onResponse(Call<ArrayList<CoinModel>> call, Response<ArrayList<CoinModel>> response) {
                if (response.isSuccessful()) {
                    topCoinsArrayList = response.body();
                    favoritesArrayList = FavoritesTable.getAllFavorites(databaseHelper);
                    recyclerItemAdapter = new TopCoinRecyclerItemAdapter(getContext(), topCoinsArrayList,
                            favoritesArrayList);
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setAdapter(recyclerItemAdapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CoinModel>> call, Throwable t) {
                Log.w("NetworkError", t.toString());
            }
        });
    }


}