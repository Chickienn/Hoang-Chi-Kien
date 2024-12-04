package com.example.testmodel.adapter;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.testmodel.ProfileFragment;
import com.example.testmodel.ActivityFragment;
import com.example.testmodel.StatsFragment;
import com.example.testmodel.HomeFragment;
public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            return new HomeFragment();
        } else if (position == 1) {
            return new ActivityFragment();
        } else if (position == 2) {
            return new StatsFragment();
        } else if (position == 3) {
            return new ProfileFragment();
        }
        else {
            return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}

