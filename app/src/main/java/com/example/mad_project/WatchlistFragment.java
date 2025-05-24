package com.example.mad_project;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class WatchlistFragment extends Fragment {
    private RecyclerView watchlistRecyclerView;
    private WatchlistAdapter adapter;
    private List<WatchlistItem> allWatchlistItems;
    private EditText searchEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_watchlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize RecyclerView
        watchlistRecyclerView = view.findViewById(R.id.watchlistRecyclerView);
        watchlistRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Set up adapter with data
        allWatchlistItems = getSampleWatchlistItems();
        adapter = new WatchlistAdapter(new ArrayList<>(allWatchlistItems));
        watchlistRecyclerView.setAdapter(adapter);

        // Setup search if the EditText exists in the parent activity
        if (getActivity() != null) {
            searchEditText = getActivity().findViewById(R.id.searchEditText);
            if (searchEditText != null) {
                setupSearch();
            }
        }
    }

    private void setupSearch() {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                filterWatchlist(s.toString());
            }
        });
    }

    private void filterWatchlist(String query) {
        List<WatchlistItem> filteredList = new ArrayList<>();
        String lowercaseQuery = query.toLowerCase().trim();

        for (WatchlistItem item : allWatchlistItems) {
            if (item.getCompanyName().toLowerCase().contains(lowercaseQuery) ||
                item.getSymbol().toLowerCase().contains(lowercaseQuery)) {
                filteredList.add(item);
            }
        }

        adapter.updateItems(filteredList);
    }

    private List<WatchlistItem> getSampleWatchlistItems() {
        List<WatchlistItem> items = new ArrayList<>();
        
        // Add sample watchlist items
        items.add(new WatchlistItem("AAPL", "Apple Inc.", "$175.43", "+2.34%", R.drawable.ic_company_apple));
        items.add(new WatchlistItem("TSLA", "Tesla Inc.", "$238.45", "-1.23%", R.drawable.ic_company_tesla));
        items.add(new WatchlistItem("MSFT", "Microsoft Corp.", "$338.11", "+0.89%", R.drawable.ic_company_microsoft));
        items.add(new WatchlistItem("GOOGL", "Alphabet Inc.", "$125.23", "+1.56%", R.drawable.ic_company_google));
        items.add(new WatchlistItem("AMZN", "Amazon.com Inc.", "$145.68", "-0.45%", R.drawable.ic_company_amazon));
        
        return items;
    }
} 