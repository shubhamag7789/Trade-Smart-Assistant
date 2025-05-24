package com.example.mad_project;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StocksAdapter extends RecyclerView.Adapter<StocksAdapter.StockViewHolder> {
    private List<Stock> stocks;

    public StocksAdapter(List<Stock> stocks) {
        this.stocks = stocks;
    }

    @NonNull
    @Override
    public StockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_stock, parent, false);
        return new StockViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StockViewHolder holder, int position) {
        Stock stock = stocks.get(position);
        
        holder.symbolTextView.setText(stock.getSymbol());
        holder.nameTextView.setText(stock.getName());
        holder.priceTextView.setText(stock.getPrice());
        holder.changeTextView.setText(stock.getChange());
        
        // Set change color
        int changeColor = stock.isPositiveChange() ? 
                Color.parseColor("#4CAF50") : Color.parseColor("#F44336");
        holder.changeTextView.setTextColor(changeColor);
    }

    @Override
    public int getItemCount() {
        return stocks.size();
    }

    static class StockViewHolder extends RecyclerView.ViewHolder {
        TextView symbolTextView;
        TextView nameTextView;
        TextView priceTextView;
        TextView changeTextView;

        StockViewHolder(@NonNull View itemView) {
            super(itemView);
            symbolTextView = itemView.findViewById(R.id.symbolTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            changeTextView = itemView.findViewById(R.id.changeTextView);
        }
    }
} 