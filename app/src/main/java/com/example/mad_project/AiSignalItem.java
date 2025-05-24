package com.example.mad_project;

public class AiSignalItem {
    private String symbol;
    private String companyName;
    private String signalType;
    private String priceChange;
    private int confidence;

    public AiSignalItem(String symbol, String companyName, String signalType, String priceChange, int confidence) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.signalType = signalType;
        this.priceChange = priceChange;
        this.confidence = confidence;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getSignalType() {
        return signalType;
    }

    public String getPriceChange() {
        return priceChange;
    }

    public int getConfidence() {
        return confidence;
    }
} 