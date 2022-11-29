package com.cemavci.project.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class CoinModel implements Parcelable {

    @Nullable
    private final String symbol;
    @Nullable
    private final String name;
    @SerializedName("image")
    @Nullable
    private final String imageUrl;
    @SerializedName("current_price")
    @Nullable
    private final double currentPrice;

    public CoinModel(String symbol, String name, String imageUrl, double currentPrice) {
        this.symbol = symbol;
        this.name = name;
        this.imageUrl = imageUrl;
        this.currentPrice = currentPrice;
    }

    protected CoinModel(Parcel in) {
        symbol = in.readString();
        name = in.readString();
        imageUrl = in.readString();
        currentPrice = in.readDouble();
    }

    public static final Creator<CoinModel> CREATOR = new Creator<CoinModel>() {
        @Override
        public CoinModel createFromParcel(Parcel in) {
            return new CoinModel(in);
        }

        @Override
        public CoinModel[] newArray(int size) {
            return new CoinModel[size];
        }
    };

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(symbol);
        dest.writeString(name);
        dest.writeString(imageUrl);
        dest.writeDouble(currentPrice);
    }
}
