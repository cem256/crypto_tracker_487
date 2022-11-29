package com.cemavci.project.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cemavci.project.R;
import com.cemavci.project.adapters.TopCoinRecyclerItemAdapter;
import com.cemavci.project.models.CoinModel;
import com.cemavci.project.network.CoinApi;
import com.cemavci.project.network.RetrofitManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class TopCoinsFragment extends Fragment {

    private CoinApi coinApi;
    // holds the data coming from the api
    private ArrayList<CoinModel> coinModelArrayList;
    private RecyclerView recyclerView;
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
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
                    coinModelArrayList = response.body();
                    recyclerItemAdapter = new TopCoinRecyclerItemAdapter(getContext(), coinModelArrayList);
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