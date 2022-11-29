package com.cemavci.project.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cemavci.project.R;
import com.cemavci.project.adapters.FavoritesRecyclerItemAdapter;
import com.cemavci.project.database.DatabaseHelper;


public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerView;
    private FavoritesRecyclerItemAdapter recyclerItemAdapter;

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
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerItemAdapter = new FavoritesRecyclerItemAdapter(getContext(), DatabaseHelper.favoritesArrayList);
        recyclerView.setAdapter(recyclerItemAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerItemAdapter = new FavoritesRecyclerItemAdapter(getContext(), DatabaseHelper.favoritesArrayList);
        recyclerView.setAdapter(recyclerItemAdapter);
    }
}