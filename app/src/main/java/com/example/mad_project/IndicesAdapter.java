package com.example.mad_project;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import java.util.ArrayList;
import java.util.List;

public class IndicesAdapter extends RecyclerView.Adapter<IndicesAdapter.IndexViewHolder> {
    private List<MarketIndex> indices;

    public IndicesAdapter(List<MarketIndex> indices) {
        this.indices = indices;
    }

    @NonNull
    @Override
    public IndexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_market_index, parent, false);
        return new IndexViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IndexViewHolder holder, int position) {
        MarketIndex index = indices.get(position);
        
        holder.indexName.setText(index.getName());
        holder.indexFullName.setText(index.getFullName());
        holder.indexValue.setText(index.getValue());
        holder.indexChange.setText(index.getChange());
        
        // Set change color
        int changeColor = index.isPositiveChange() ? 
                Color.parseColor("#4CAF50") : Color.parseColor("#F44336");
        holder.indexChange.setTextColor(changeColor);

        // Setup mini chart
        setupMiniChart(holder.miniChart, changeColor);
    }

    @Override
    public int getItemCount() {
        return indices.size();
    }

    private void setupMiniChart(LineChart chart, int lineColor) {
        // Sample data
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 100));
        entries.add(new Entry(1, 105));
        entries.add(new Entry(2, 102));
        entries.add(new Entry(3, 110));
        entries.add(new Entry(4, 108));
        entries.add(new Entry(5, 115));

        LineDataSet dataSet = new LineDataSet(entries, "");
        dataSet.setColor(lineColor);
        dataSet.setLineWidth(1.5f);
        dataSet.setDrawCircles(false);
        dataSet.setDrawValues(false);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);
        chart.setDrawGridBackground(false);
        chart.setDrawBorders(false);
        chart.getAxisLeft().setEnabled(false);
        chart.getAxisRight().setEnabled(false);
        chart.getXAxis().setEnabled(false);
        chart.setTouchEnabled(false);
        chart.setScaleEnabled(false);
        chart.animateX(1000);
        chart.invalidate();
    }

    static class IndexViewHolder extends RecyclerView.ViewHolder {
        TextView indexName;
        TextView indexFullName;
        TextView indexValue;
        TextView indexChange;
        LineChart miniChart;

        IndexViewHolder(@NonNull View itemView) {
            super(itemView);
            indexName = itemView.findViewById(R.id.indexName);
            indexFullName = itemView.findViewById(R.id.indexFullName);
            indexValue = itemView.findViewById(R.id.indexValue);
            indexChange = itemView.findViewById(R.id.indexChange);
            miniChart = itemView.findViewById(R.id.miniChart);
        }
    }
} 