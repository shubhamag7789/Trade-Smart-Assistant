package com.example.mad_project;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import java.util.List;

public class StockDetailActivity extends AppCompatActivity {
    private ImageButton backButton;
    private ImageView companyLogo;
    private TextView companyName;
    private TextView priceText;
    private TextView changeText;
    private LineChart priceChart;
    private TextView openPrice;
    private TextView previousClosePrice;
    private TextView marketStatus;
    private MaterialButton buyButton;
    private MaterialButton sellButton;
    private MaterialButton watchlistButton;
    
    // Range views
    private TextView daysRangeLow;
    private TextView daysRangeHigh;
    private TextView weekRangeLow;
    private TextView weekRangeHigh;
    
    // Stats views
    private TextView volumeValue;
    private TextView avgVolumeValue;
    private TextView marketCapValue;
    private TextView dividendYieldValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_detail);

        // Initialize views
        initializeViews();
        
        // Get data from intent
        String companyNameStr = getIntent().getStringExtra("companyName");
        String symbol = getIntent().getStringExtra("symbol");
        String price = getIntent().getStringExtra("price");
        String change = getIntent().getStringExtra("change");
        int logoResId = getIntent().getIntExtra("logoResId", 0);

        // Set data to views
        companyLogo.setImageResource(logoResId);
        companyName.setText(companyNameStr);
        priceText.setText(price);
        changeText.setText(change);
        
        // Set sample data
        openPrice.setText("610.07");
        previousClosePrice.setText("635.54");
        
        daysRangeLow.setText("610.85");
        daysRangeHigh.setText("632.16");
        
        weekRangeLow.setText("75.05");
        weekRangeHigh.setText("145.02");
        
        volumeValue.setText("75.169M");
        avgVolumeValue.setText("71.527M");
        marketCapValue.setText("2.16T");
        dividendYieldValue.setText("0.70%");
        
        // Set up chart
        setupChart();
        
        // Set up market status
        updateMarketStatus();
        
        // Set up buttons
        backButton.setOnClickListener(v -> finish());
        buyButton.setOnClickListener(v -> {
            showTradeOrderDialog(true);
        });
        sellButton.setOnClickListener(v -> {
            showTradeOrderDialog(false);
        });
        watchlistButton.setOnClickListener(v -> {
            // TODO: Implement add to watchlist functionality
        });
    }

    private void initializeViews() {
        backButton = findViewById(R.id.backButton);
        companyLogo = findViewById(R.id.companyLogo);
        companyName = findViewById(R.id.companyName);
        priceText = findViewById(R.id.priceText);
        changeText = findViewById(R.id.changeText);
        priceChart = findViewById(R.id.priceChart);
        openPrice = findViewById(R.id.openPrice);
        previousClosePrice = findViewById(R.id.previousClosePrice);
        marketStatus = findViewById(R.id.marketStatus);
        buyButton = findViewById(R.id.buyButton);
        sellButton = findViewById(R.id.sellButton);
        watchlistButton = findViewById(R.id.watchlistButton);
        
        // Initialize range views
        daysRangeLow = findViewById(R.id.daysRangeLow);
        daysRangeHigh = findViewById(R.id.daysRangeHigh);
        weekRangeLow = findViewById(R.id.weekRangeLow);
        weekRangeHigh = findViewById(R.id.weekRangeHigh);
        
        // Initialize stats views
        volumeValue = findViewById(R.id.volumeValue);
        avgVolumeValue = findViewById(R.id.avgVolumeValue);
        marketCapValue = findViewById(R.id.marketCapValue);
        dividendYieldValue = findViewById(R.id.dividendYieldValue);
    }

    private void setupChart() {
        // Sample data for the chart
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 610));
        entries.add(new Entry(1, 615));
        entries.add(new Entry(2, 618));
        entries.add(new Entry(3, 621));
        entries.add(new Entry(4, 619));
        entries.add(new Entry(5, 617));

        LineDataSet dataSet = new LineDataSet(entries, "Price");
        dataSet.setColor(getResources().getColor(R.color.red)); // Red for downward trend
        dataSet.setLineWidth(2f);
        dataSet.setDrawCircles(false);
        dataSet.setDrawValues(false);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setDrawFilled(true);
        dataSet.setFillColor(getResources().getColor(R.color.red));
        dataSet.setFillAlpha(50);

        LineData lineData = new LineData(dataSet);
        priceChart.setData(lineData);
        priceChart.getDescription().setEnabled(false);
        priceChart.getLegend().setEnabled(false);
        priceChart.setDrawGridBackground(false);
        priceChart.setDrawBorders(false);
        priceChart.getAxisLeft().setEnabled(false);
        priceChart.getAxisRight().setEnabled(false);
        priceChart.getXAxis().setEnabled(false);
        priceChart.setTouchEnabled(false);
        priceChart.animateX(1000);
        priceChart.invalidate();
    }

    private void updateMarketStatus() {
        // This would normally be calculated based on actual market times
        // For now using sample text
        String marketStatusText = "Indian markets open in 3h 27m";
        marketStatus.setText(marketStatusText);
    }

    private void showTradeOrderDialog(boolean isBuyOrder) {
        TradeOrderDialog dialog = TradeOrderDialog.newInstance(
            companyName.getText().toString(),
            getIntent().getStringExtra("symbol"),
            priceText.getText().toString(),
            isBuyOrder
        );
        dialog.show(getSupportFragmentManager(), "TradeOrderDialog");
    }
} 