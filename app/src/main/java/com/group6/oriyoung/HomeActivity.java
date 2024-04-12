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
import com.group6.fragments.AccountFirstFragment;
import com.group6.fragments.CategoryFragment;
import com.group6.fragments.HomeFragment;
import com.group6.fragments.WishlistFragment;

public class HomeActivity extends AppCompatActivity {

    FrameLayout containerLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavView);
        containerLayout = findViewById(R.id.containeraLayout);
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
                } else if (itemId == R.id.nav_account) {
                loadFragment(new AccountFirstFragment(), false);
            }


                return true;
            }
        });


    }
    private void loadFragment(Fragment fragment, boolean isAppInitialized) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (isAppInitialized) {
            fragmentTransaction.add(R.id.containeraLayout, fragment);

        }else {
            fragmentTransaction.replace(R.id.containeraLayout, fragment);
        }
        fragmentTransaction.commit();
    }

}