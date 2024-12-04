package com.example.testmodel;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.testmodel.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView bottomNavigationView;
    ViewPager2 viewPager2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        viewPager2 = findViewById(R.id.viewPager);
        setupViewPager();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.home_menu){
                    viewPager2.setCurrentItem(0);
                } else if (menuItem.getItemId() == R.id.activity_menu) {
                    viewPager2.setCurrentItem(1);
                } else if (menuItem.getItemId() == R.id.stat_menu) {
                    viewPager2.setCurrentItem(2);
                } else if (menuItem.getItemId() == R.id.profile_menu) {
                    viewPager2.setCurrentItem(3);
                }
                return true;
            }
        });
    }

    // setup viewpager
    private void setupViewPager(){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager2.setAdapter(viewPagerAdapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 0) {
                    bottomNavigationView.getMenu().findItem(R.id.home_menu).setChecked(true);
                } else if (position == 1) {
                    bottomNavigationView.getMenu().findItem(R.id.activity_menu).setChecked(true);
                } else if (position == 2) {
                    bottomNavigationView.getMenu().findItem(R.id.stat_menu).setChecked(true);
                } else {
                    bottomNavigationView.getMenu().findItem(R.id.home_menu).setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home_menu){
            viewPager2.setCurrentItem(0);
        } else if (item.getItemId() == R.id.activity_menu) {
            viewPager2.setCurrentItem(1);
        } else if (item.getItemId() == R.id.stat_menu) {
            viewPager2.setCurrentItem(2);
        } else if (item.getItemId() == R.id.profile_menu) {
            viewPager2.setCurrentItem(3);
        } else {
            viewPager2.setCurrentItem(0);
        }
        return true;

    }

}

