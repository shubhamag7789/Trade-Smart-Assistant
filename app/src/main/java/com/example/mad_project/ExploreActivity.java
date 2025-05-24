package com.example.mad_project;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

public class ExploreActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private RecyclerView indicesRecyclerView;
    private RecyclerView stocksRecyclerView;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        // Initialize all views
        initializeViews();

        // Setup layout managers for RecyclerViews
        indicesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        stocksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load data into adapters
        setupMarketData();

        // Setup bottom navigation
        setupBottomNavigation();

        // TabLayout listener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1) { // Watchlist tab clicked
                    Intent intent = new Intent(ExploreActivity.this, WatchlistActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void initializeViews() {
        tabLayout = findViewById(R.id.tabLayout);
        indicesRecyclerView = findViewById(R.id.indicesRecyclerView);
        stocksRecyclerView = findViewById(R.id.stocksRecyclerView);
        bottomNavigation = findViewById(R.id.bottomNavigation);

        // Set Explore as selected in bottom navigation
        bottomNavigation.setSelectedItemId(R.id.navigation_explore);
    }

    private void setupBottomNavigation() {
        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                startActivity(new Intent(this, DashboardActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.navigation_explore) {
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

    private void setupMarketData() {
        // Setup indices adapter
        List<MarketIndex> indices = new ArrayList<>();
        indices.add(new MarketIndex("S&P 500", "S&P 500 INDEX", "42042.12", "+0.85%"));
        indices.add(new MarketIndex("Dow 30", "DOW JONES INDU", "34529.46", "+0.13%"));
        indices.add(new MarketIndex("DAX", "DAX INDEX", "15619.98", "+0.74%"));

        IndicesAdapter indicesAdapter = new IndicesAdapter(indices);
        indicesRecyclerView.setAdapter(indicesAdapter);

        // Setup stocks adapter
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock("AMC", "AMC Entertainment", "47.91", "+0.35%"));
        // Add more stocks as needed

        StocksAdapter stocksAdapter = new StocksAdapter(stocks);
        stocksRecyclerView.setAdapter(stocksAdapter);
    }
}
