package com.avciuyguntopcu.project.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avciuyguntopcu.project.R;
import com.avciuyguntopcu.project.adapters.FavoritesRecyclerItemAdapter;
import com.avciuyguntopcu.project.database.DatabaseHelper;
import com.avciuyguntopcu.project.database.FavoritesTable;
import com.avciuyguntopcu.project.models.CoinModel;

import java.util.ArrayList;


public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView noFavoritesTextView;
    private FavoritesRecyclerItemAdapter recyclerItemAdapter;
    private ArrayList<CoinModel> favoritesArrayList;
    private DatabaseHelper databaseHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.favoritesRecycler);
        noFavoritesTextView = view.findViewById(R.id.noFavoritesTextView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        databaseHelper = new DatabaseHelper(getContext());
        favoritesArrayList = FavoritesTable.getAllFavorites(databaseHelper);

        setView();


    }

    @Override
    public void onResume() {
        super.onResume();
        favoritesArrayList = FavoritesTable.getAllFavorites(databaseHelper);
        setView();
    }

    private void setView() {
        if (favoritesArrayList.isEmpty()) {
            noFavoritesTextView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            noFavoritesTextView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerItemAdapter = new FavoritesRecyclerItemAdapter(getContext(), favoritesArrayList);
            recyclerView.setAdapter(recyclerItemAdapter);
        }
    }
}