package com.example.mad_project;

public class WatchlistItem {
    private String companyName;
    private String symbol;
    private String price;
    private String change;
    private int logoResId;

    public WatchlistItem(String companyName, String symbol, String price, String change, int logoResId) {
        this.companyName = companyName;
        this.symbol = symbol;
        this.price = price;
        this.change = change;
        this.logoResId = logoResId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getPrice() {
        return price;
    }

    public String getChange() {
        return change;
    }

    public int getLogoResId() {
        return logoResId;
    }

    public boolean isPositiveChange() {
        return change.startsWith("+");
    }
} 