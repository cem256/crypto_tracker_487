package com.avciuyguntopcu.project.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// singleton retrofit instance for network requests
public class RetrofitManager {

    private static Retrofit retrofit = null;
    private static String baseUrl = "https://api.coingecko.com/api/v3/";

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit =
                    new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}

