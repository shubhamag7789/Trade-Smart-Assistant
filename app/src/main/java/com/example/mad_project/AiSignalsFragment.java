package com.example.mad_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;
import java.util.List;

public class AiSignalsFragment extends Fragment {
    private RecyclerView signalsRecyclerView;
    private ChipGroup signalTypeChips;
    private AiSignalsAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ai_signals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        signalsRecyclerView = view.findViewById(R.id.signalsRecyclerView);
        signalTypeChips = view.findViewById(R.id.signalTypeChips);

        // Setup RecyclerView
        signalsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new AiSignalsAdapter(getDummyData(), (FragmentActivity) requireActivity());
        signalsRecyclerView.setAdapter(adapter);

        // Setup chip group listener
        signalTypeChips.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.buySignalsChip) {
                // Filter for buy signals
                adapter.filterBySignalType("BUY");
            } else if (checkedId == R.id.sellSignalsChip) {
                // Filter for sell signals
                adapter.filterBySignalType("SELL");
            } else if (checkedId == R.id.holdSignalsChip) {
                // Filter for hold signals
                adapter.filterBySignalType("HOLD");
            }
        });

        // Select buy signals by default
        signalTypeChips.check(R.id.buySignalsChip);
    }

    private List<AiSignalItem> getDummyData() {
        List<AiSignalItem> items = new ArrayList<>();
        
        // Add sample data
        items.add(new AiSignalItem("AAPL", "Apple Inc.", "BUY", "+2.34%", 92));
        items.add(new AiSignalItem("TSLA", "Tesla Inc.", "HOLD", "-3.45%", 87));
        items.add(new AiSignalItem("MSFT", "Microsoft Corp.", "BUY", "+1.23%", 95));
        items.add(new AiSignalItem("NVDA", "NVIDIA Corp.", "SELL", "-4.68%", 88));
        items.add(new AiSignalItem("AMZN", "Amazon.com Inc.", "BUY", "+1.56%", 91));
        
        return items;
    }
} 