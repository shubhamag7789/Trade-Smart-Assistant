package com.example.mad_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class PortfolioActivity extends AppCompatActivity {
    private RecyclerView holdingsRecyclerView;
    private FloatingActionButton addStockFab;
    private MaterialToolbar topAppBar;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);

        // Initialize views
        initializeViews();
        
        // Setup toolbar
        setupToolbar();
        
        // Setup RecyclerView
        setupRecyclerView();
        
        // Setup FAB
        setupFab();

        // Setup bottom navigation
        setupBottomNavigation();
    }

    private void initializeViews() {
        holdingsRecyclerView = findViewById(R.id.holdingsRecyclerView);
        addStockFab = findViewById(R.id.addStockFab);
        topAppBar = findViewById(R.id.topAppBar);
        bottomNavigation = findViewById(R.id.bottomNavigation);
    }

    private void setupToolbar() {
        setSupportActionBar(topAppBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        topAppBar.setNavigationOnClickListener(v -> onBackPressed());

        // Handle menu item clicks
        topAppBar.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.action_search) {
                // Handle search
                return true;
            } else if (itemId == R.id.action_filter) {
                // Handle filter
                return true;
            } else if (itemId == R.id.action_sort) {
                // Handle sort
                return true;
            } else if (itemId == R.id.action_settings) {
                // Handle settings
                return true;
            } else if (itemId == R.id.action_export) {
                // Handle export
                return true;
            }
            return false;
        });
    }

    private void setupRecyclerView() {
        holdingsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Set adapter with sample data
        List<StockHolding> holdings = getSampleHoldings();
        StockHoldingsAdapter adapter = new StockHoldingsAdapter(holdings);
        holdingsRecyclerView.setAdapter(adapter);
    }

    private void setupFab() {
        addStockFab.setOnClickListener(v -> {
            // Handle FAB click - open add stock dialog/activity
        });
    }

    private void setupBottomNavigation() {
        bottomNavigation.setSelectedItemId(R.id.navigation_portfolio); // Set Portfolio as selected
        
        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                startActivity(new Intent(this, DashboardActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.navigation_explore) {
                startActivity(new Intent(this, ExploreActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.navigation_portfolio) {
                return true;
            } else if (itemId == R.id.navigation_news) {
                // TODO: Navigate to News
                return true;
            }
            return false;
        });
    }

    private List<StockHolding> getSampleHoldings() {
        List<StockHolding> holdings = new ArrayList<>();
        holdings.add(new StockHolding("AAPL", "Apple Inc.", 10, 175.34, 245.30, 15245.30));
        holdings.add(new StockHolding("GOOGL", "Alphabet Inc.", 5, 2750.20, 150.45, 13751.00));
        holdings.add(new StockHolding("MSFT", "Microsoft Corp.", 15, 305.75, 325.20, 4586.25));
        holdings.add(new StockHolding("TSLA", "Tesla Inc.", 8, 875.45, -45.30, 7003.60));
        holdings.add(new StockHolding("AMZN", "Amazon.com Inc.", 3, 3250.00, 125.75, 9750.00));
        return holdings;
    }

    @Override
    public void onBackPressed() {
        // Navigate to Dashboard when back is pressed
        startActivity(new Intent(this, DashboardActivity.class));
        finish();
    }
} 