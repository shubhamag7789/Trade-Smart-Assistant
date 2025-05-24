package com.example.mad_project;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class WatchlistAdapter extends RecyclerView.Adapter<WatchlistAdapter.WatchlistViewHolder> {
    private List<WatchlistItem> watchlistItems;

    public WatchlistAdapter(List<WatchlistItem> watchlistItems) {
        this.watchlistItems = watchlistItems;
    }

    public void updateItems(List<WatchlistItem> newItems) {
        this.watchlistItems = newItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WatchlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_watchlist, parent, false);
        return new WatchlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WatchlistViewHolder holder, int position) {
        WatchlistItem item = watchlistItems.get(position);
        holder.bind(item);
        
        // Add click listener
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), StockDetailActivity.class);
            intent.putExtra("companyName", item.getCompanyName());
            intent.putExtra("symbol", item.getSymbol());
            intent.putExtra("price", item.getPrice());
            intent.putExtra("change", item.getChange());
            intent.putExtra("logoResId", item.getLogoResId());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return watchlistItems.size();
    }

    static class WatchlistViewHolder extends RecyclerView.ViewHolder {
        private ImageView companyLogo;
        private TextView companyName;
        private TextView symbolText;
        private TextView priceText;
        private TextView changeText;

        public WatchlistViewHolder(@NonNull View itemView) {
            super(itemView);
            companyLogo = itemView.findViewById(R.id.companyLogo);
            companyName = itemView.findViewById(R.id.companyName);
            symbolText = itemView.findViewById(R.id.symbolText);
            priceText = itemView.findViewById(R.id.priceText);
            changeText = itemView.findViewById(R.id.changeText);
        }

        public void bind(WatchlistItem item) {
            companyLogo.setImageResource(item.getLogoResId());
            companyName.setText(item.getCompanyName());
            symbolText.setText(item.getSymbol());
            priceText.setText(item.getPrice());
            changeText.setText(item.getChange());
            
            int textColor = item.isPositiveChange() 
                ? ContextCompat.getColor(itemView.getContext(), R.color.green)
                : ContextCompat.getColor(itemView.getContext(), R.color.red);
            changeText.setTextColor(textColor);
        }
    }
} 