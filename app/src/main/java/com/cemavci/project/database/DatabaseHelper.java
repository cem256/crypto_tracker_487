package com.cemavci.project.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.cemavci.project.models.CoinModel;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {


    private Context context;
    private static final String DATABASE_NAME = "Favorites.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "favorites";
    private static final String COLUMN_SYMBOL = "symbol";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_IMAGE_URL = "imageUrl";
    private static final String COLUMN_CURRENT_PRICE = "currentPrice";
    public static ArrayList<CoinModel> favoritesArrayList = new ArrayList<>();

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COLUMN_SYMBOL + " TEXT, " + COLUMN_NAME +
                " TEXT," + " " + COLUMN_IMAGE_URL + " TEXT ," + COLUMN_CURRENT_PRICE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addToFavorites(CoinModel coin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SYMBOL, coin.getSymbol());
        cv.put(COLUMN_NAME, coin.getName());
        cv.put(COLUMN_IMAGE_URL, coin.getImageUrl());
        cv.put(COLUMN_CURRENT_PRICE, String.valueOf(coin.getCurrentPrice()));

        long result = db.insert(TABLE_NAME, null, cv);

        if (result == -1) {
            Toast.makeText(context, "Error occurred while inserting " + coin.getName(), Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(context, coin.getName() + " added to the " + "favorites", Toast.LENGTH_SHORT).show();
        }


    }

    public void removeFromFavorites(CoinModel coin) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(TABLE_NAME, COLUMN_SYMBOL + "=?", new String[]{coin.getSymbol()});

        if (result == -1) {
            Toast.makeText(context, "Error occurred while deleting " + coin.getName(), Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(context, coin.getName() + " removed from the " + "favorites", Toast.LENGTH_SHORT).show();
        }


    }

    public void readFromDatabase() {
        ArrayList<CoinModel> tempList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        while (cursor.moveToNext()) {
            tempList.add(new CoinModel(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                    Double.parseDouble(cursor.getString(3))));
        }
        favoritesArrayList = tempList;

    }
}
