package com.example.mad_project;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import java.util.ArrayList;
import java.util.List;

public class AiSignalsAdapter extends RecyclerView.Adapter<AiSignalsAdapter.SignalViewHolder> {
    private List<AiSignalItem> items;
    private List<AiSignalItem> filteredItems;
    private FragmentActivity activity;

    public AiSignalsAdapter(List<AiSignalItem> items, FragmentActivity activity) {
        this.items = items;
        this.filteredItems = new ArrayList<>(items);
        this.activity = activity;
    }

    public void filterBySignalType(String signalType) {
        filteredItems = new ArrayList<>();
        for (AiSignalItem item : items) {
            if (item.getSignalType().equals(signalType)) {
                filteredItems.add(item);
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SignalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ai_signal, parent, false);
        return new SignalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SignalViewHolder holder, int position) {
        AiSignalItem item = filteredItems.get(position);
        
        holder.symbolTextView.setText(item.getSymbol());
        holder.companyNameTextView.setText(item.getCompanyName());
        holder.signalTypeTextView.setText(item.getSignalType());
        holder.priceChangeTextView.setText(item.getPriceChange());
        holder.confidenceProgressBar.setProgress(item.getConfidence());
        holder.confidenceValueTextView.setText(item.getConfidence() + "%");

        // Set colors based on signal type
        int signalColor;
        switch (item.getSignalType()) {
            case "BUY":
                signalColor = Color.parseColor("#4CAF50"); // Green
                break;
            case "SELL":
                signalColor = Color.parseColor("#F44336"); // Red
                break;
            default:
                signalColor = Color.parseColor("#FFC107"); // Yellow for HOLD
                break;
        }
        holder.signalTypeTextView.setTextColor(signalColor);

        // Set price change color
        boolean isPositive = item.getPriceChange().startsWith("+");
        holder.priceChangeTextView.setTextColor(isPositive ? 
                Color.parseColor("#4CAF50") : Color.parseColor("#F44336"));

        // Set click listener
        holder.itemView.setOnClickListener(v -> {
            try {
                if (activity != null && !activity.isFinishing()) {
                    TradeOrderDialog dialog = TradeOrderDialog.newInstance(
                        item.getSymbol(),
                        item.getCompanyName(),
                        item.getPriceChange(),
                        item.getSignalType().equals("BUY")
                    );
                    dialog.show(activity.getSupportFragmentManager(), "TradeOrderDialog");
                }
            } catch (Exception e) {
                Toast.makeText(holder.itemView.getContext(), 
                    "Unable to show trade dialog at this time", 
                    Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredItems.size();
    }

    static class SignalViewHolder extends RecyclerView.ViewHolder {
        TextView symbolTextView;
        TextView companyNameTextView;
        TextView signalTypeTextView;
        TextView priceChangeTextView;
        TextView confidenceValueTextView;
        LinearProgressIndicator confidenceProgressBar;

        SignalViewHolder(@NonNull View itemView) {
            super(itemView);
            symbolTextView = itemView.findViewById(R.id.symbolTextView);
            companyNameTextView = itemView.findViewById(R.id.companyNameTextView);
            signalTypeTextView = itemView.findViewById(R.id.signalTypeTextView);
            priceChangeTextView = itemView.findViewById(R.id.priceChangeTextView);
            confidenceValueTextView = itemView.findViewById(R.id.confidenceValueTextView);
            confidenceProgressBar = itemView.findViewById(R.id.confidenceProgressBar);
        }
    }
} 