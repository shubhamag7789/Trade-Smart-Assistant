package com.example.mad_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    private LineChart portfolioChart;
    private ChipGroup timeFilterGroup;
    private MaterialButton watchlistButton;
    private BottomNavigationView bottomNavigation;
    private FloatingActionButton chatFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize views
        portfolioChart = findViewById(R.id.portfolioChart);
        timeFilterGroup = findViewById(R.id.timeFilterGroup);
        watchlistButton = findViewById(R.id.watchlistButton);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        chatFab = findViewById(R.id.chatFab);

        // Setup chart
        setupChart();

        // Setup time filter chips
        setupTimeFilters();

        // Set up ChatBot FAB
        chatFab.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivityChatBot.class));
        });

        // Handle watchlist button click
        watchlistButton.setOnClickListener(v -> {
            startActivity(new Intent(this, WatchlistActivity.class));
        });

        // Profile menu (including logout)
        ImageView profileImage = findViewById(R.id.profileImage);
        profileImage.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(DashboardActivity.this, view);
            popupMenu.getMenuInflater().inflate(R.menu.profile_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.menu_logout) {
                    // ✅ Proper logout from Firebase
                    FirebaseAuth.getInstance().signOut();

                    Toast.makeText(DashboardActivity.this, "Logged out", Toast.LENGTH_SHORT).show();

                    // Redirect to Login and clear backstack
                    Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    return true;
                }
                return false;
            });
            popupMenu.show();
        });

        // Setup bottom navigation
        setupBottomNavigation();
    }

    private void setupChart() {
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 180000));
        entries.add(new Entry(1, 185000));
        entries.add(new Entry(2, 190000));
        entries.add(new Entry(3, 188000));
        entries.add(new Entry(4, 195000));
        entries.add(new Entry(5, 203557));

        LineDataSet dataSet = new LineDataSet(entries, "Portfolio Value");
        dataSet.setColor(getResources().getColor(R.color.purple_primary));
        dataSet.setLineWidth(2f);
        dataSet.setDrawCircles(false);
        dataSet.setDrawValues(false);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        LineData lineData = new LineData(dataSet);
        portfolioChart.setData(lineData);
        portfolioChart.getDescription().setEnabled(false);
        portfolioChart.getLegend().setEnabled(false);
        portfolioChart.setDrawGridBackground(false);
        portfolioChart.setDrawBorders(false);
        portfolioChart.getAxisLeft().setDrawGridLines(false);
        portfolioChart.getAxisRight().setEnabled(false);
        portfolioChart.getXAxis().setDrawGridLines(false);
        portfolioChart.getXAxis().setDrawAxisLine(false);
        portfolioChart.getAxisLeft().setDrawAxisLine(false);
        portfolioChart.getAxisLeft().setTextColor(getResources().getColor(R.color.white));
        portfolioChart.animateX(1000);
        portfolioChart.invalidate();
    }

    private void setupTimeFilters() {
        timeFilterGroup.setOnCheckedChangeListener((group, checkedId) -> {
            // TODO: Update chart data based on selected time period
        });
    }

    private void setupBottomNavigation() {
        bottomNavigation.setSelectedItemId(R.id.navigation_home);

        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                return true;
            } else if (itemId == R.id.navigation_explore) {
                startActivity(new Intent(this, ExploreActivity.class));
                return true;
            } else if (itemId == R.id.navigation_portfolio) {
                startActivity(new Intent(this, PortfolioActivity.class));
                return true;
            } else if (itemId == R.id.navigation_news) {
                return true;
            }
            return false;
        });
    }
}