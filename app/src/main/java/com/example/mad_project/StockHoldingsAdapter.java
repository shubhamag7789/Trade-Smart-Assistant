package com.example.mad_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StockHoldingsAdapter extends RecyclerView.Adapter<StockHoldingsAdapter.ViewHolder> {
    private List<StockHolding> holdings;

    public StockHoldingsAdapter(List<StockHolding> holdings) {
        this.holdings = holdings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_holding, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StockHolding holding = holdings.get(position);
        
        holder.stockSymbol.setText(holding.getSymbol());
        holder.stockName.setText(holding.getName());
        holder.stockQuantity.setText(holding.getFormattedQuantity());
        holder.stockValue.setText(holding.getFormattedTotalValue());
        holder.stockPrice.setText(holding.getFormattedCurrentPrice());
        
        // Set price change with color
        holder.stockChange.setText(holding.getFormattedPriceChange());
        int textColor = holding.getPriceChange() >= 0 ? 
                R.color.green_positive : R.color.red_negative;
        holder.stockChange.setTextColor(
                ContextCompat.getColor(holder.itemView.getContext(), textColor));
    }

    @Override
    public int getItemCount() {
        return holdings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView stockSymbol;
        TextView stockName;
        TextView stockQuantity;
        TextView stockValue;
        TextView stockChange;
        TextView stockPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stockSymbol = itemView.findViewById(R.id.stockSymbol);
            stockName = itemView.findViewById(R.id.stockName);
            stockQuantity = itemView.findViewById(R.id.stockQuantity);
            stockValue = itemView.findViewById(R.id.stockValue);
            stockChange = itemView.findViewById(R.id.stockChange);
            stockPrice = itemView.findViewById(R.id.stockPrice);
        }
    }
} 