package com.avciuyguntopcu.project.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class CoinModel implements Parcelable {


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
    @SerializedName("price_change_percentage_24h")
    @Nullable
    private final double percentageChange;


    public CoinModel(@Nullable String symbol, @Nullable String name, @Nullable String imageUrl, double currentPrice,
                     double percentageChange) {
        this.symbol = symbol;
        this.name = name;
        this.imageUrl = imageUrl;
        this.currentPrice = currentPrice;
        this.percentageChange = percentageChange;
    }

    protected CoinModel(Parcel in) {
        symbol = in.readString();
        name = in.readString();
        imageUrl = in.readString();
        currentPrice = in.readDouble();
        percentageChange = in.readDouble();
    }

    @Nullable
    public String getSymbol() {
        return symbol;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @Nullable
    public String getImageUrl() {
        return imageUrl;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public double getPercentageChange() {
        return percentageChange;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(symbol);
        dest.writeString(name);
        dest.writeString(imageUrl);
        dest.writeDouble(currentPrice);
        dest.writeDouble(percentageChange);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
