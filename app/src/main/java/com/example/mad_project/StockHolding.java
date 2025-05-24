package com.example.mad_project;

public class StockHolding {
    private String symbol;
    private String name;
    private int quantity;
    private double currentPrice;
    private double priceChange;
    private double totalValue;

    public StockHolding(String symbol, String name, int quantity, double currentPrice, double priceChange, double totalValue) {
        this.symbol = symbol;
        this.name = name;
        this.quantity = quantity;
        this.currentPrice = currentPrice;
        this.priceChange = priceChange;
        this.totalValue = totalValue;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public double getPriceChange() {
        return priceChange;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public String getFormattedPriceChange() {
        String prefix = priceChange >= 0 ? "+" : "";
        return String.format("%s$%.2f (%.2f%%)", prefix, Math.abs(priceChange), (priceChange / currentPrice) * 100);
    }

    public String getFormattedTotalValue() {
        return String.format("$%.2f", totalValue);
    }

    public String getFormattedCurrentPrice() {
        return String.format("$%.2f", currentPrice);
    }

    public String getFormattedQuantity() {
        return quantity + " shares";
    }
} 