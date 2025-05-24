package com.example.mad_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class WatchlistActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);

        // Initialize views
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        backButton = findViewById(R.id.backButton);

        // Setup ViewPager with fragments
        setupViewPager();

        // Connect TabLayout with ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Watchlist");
                    break;
                case 1:
                    tab.setText("AI Signals");
                    break;
            }
        }).attach();

        // Handle back button click
        backButton.setOnClickListener(v -> finish());

        // Setup bottom navigation
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        setupBottomNavigation();
    }

    private void setupViewPager() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new WatchlistFragment());
        fragments.add(new AiSignalsFragment());

        ViewPagerAdapter adapter = new ViewPagerAdapter(this, fragments);
        viewPager.setAdapter(adapter);
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.navigation_home); // Set Home as selected
        
        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                finish();
                return true;
            } else if (itemId == R.id.navigation_explore) {
                startActivity(new Intent(this, ExploreActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.navigation_portfolio) {
                startActivity(new Intent(this, PortfolioActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.navigation_news) {
                // TODO: Navigate to News
                return true;
            }
            return false;
        });
    }
} 