package com.cemavci.project.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.cemavci.project.fragments.FavoritesFragment;
import com.cemavci.project.fragments.TopCoinsFragment;

public class CustomViewPagerAdapter extends FragmentStateAdapter {
    public CustomViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new TopCoinsFragment();
            case 1:
                return new FavoritesFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
