package com.cemavci.project.network;

import com.cemavci.project.models.CoinModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

// endpoints
public interface CoinApi {

    @GET("coins/markets?vs_currency=usd")
    Call<ArrayList<CoinModel>> getTopCoins();
}
