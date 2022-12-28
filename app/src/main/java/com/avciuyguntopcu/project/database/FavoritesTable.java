package com.avciuyguntopcu.project.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.avciuyguntopcu.project.models.CoinModel;

import java.util.ArrayList;

public class FavoritesTable {

    public static String TABLE_NAME = "favorites";
    public static String FIELD_SYMBOL = "symbol";
    public static String FIELD_NAME = "name";
    public static String FIELD_IMAGE_URL = "imageUrl";
    public static String FIELD_CURRENT_PRICE = "currentPrice";

    public static String CREATE_TABLE_SQL =
            "CREATE TABLE " + TABLE_NAME + " ( " + FIELD_SYMBOL + " text, " + FIELD_NAME + " text, " + FIELD_IMAGE_URL + " text, " + FIELD_CURRENT_PRICE + " text );";
    public static String DROP_TABLE_SQL = "DROP TABLE if exists " + TABLE_NAME;

    public static ArrayList<CoinModel> getAllFavorites(DatabaseHelper dbHelper) {
        CoinModel aCoin;
        ArrayList<CoinModel> data = new ArrayList<>();
        Cursor cursor = dbHelper.getAllRecords(TABLE_NAME, null);
        Log.d("DATABASE OPERATIONS", cursor.getCount() + ",  " + cursor.getColumnCount());
        while (cursor.moveToNext()) {
            String symbol = cursor.getString(0);
            String name = cursor.getString(1);
            String imageUrl = cursor.getString(2);
            double currentPrice = Double.parseDouble(cursor.getString(3));
            aCoin = new CoinModel(symbol, name, imageUrl, currentPrice);
            data.add(aCoin);

        }
        Log.d("DATABASE OPERATIONS", data.toString());
        return data;
    }


    public static boolean insert(DatabaseHelper dbHelper, CoinModel coin) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_SYMBOL, coin.getSymbol());
        contentValues.put(FIELD_NAME, coin.getName());
        contentValues.put(FIELD_IMAGE_URL, coin.getImageUrl());
        contentValues.put(FIELD_CURRENT_PRICE, coin.getCurrentPrice());

        boolean res = dbHelper.insert(TABLE_NAME, contentValues);
        return res;
    }


    public static boolean delete(DatabaseHelper dbHelper, CoinModel coin) {
        Log.d("DATABASE OPERATIONS", "DELETE DONE");
        String where = FIELD_SYMBOL + " = " + "'" + coin.getSymbol() + "'";
        boolean res = dbHelper.delete(TABLE_NAME, where);
        return res;
    }
}
