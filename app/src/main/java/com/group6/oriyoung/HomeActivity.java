package com.group6.oriyoung;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.group6.Fragments.CategoryFragment;
import com.group6.Fragments.HomeFragment;
import com.group6.Fragments.WishlistFragment;

public class HomeActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavView);
        frameLayout = findViewById(R.id.frame_layout);
        loadFragment(new HomeFragment(), true);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    loadFragment(new HomeFragment(), false);

                } else if (itemId == R.id.nav_category) {
                    loadFragment(new CategoryFragment(), false);

                } else if (itemId == R.id.nav_wishlist) {
                    loadFragment(new WishlistFragment(), false);
                }

                return true;
            }
        });


    }
    private void loadFragment(Fragment fragment, boolean isAppInitialized) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (isAppInitialized) {
            fragmentTransaction.add(R.id.frame_layout, fragment);

        }else {
            fragmentTransaction.replace(R.id.frame_layout, fragment);
        }
        fragmentTransaction.commit();
    }

}