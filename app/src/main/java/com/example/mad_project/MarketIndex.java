package com.example.mad_project;

public class MarketIndex {
    private String name;
    private String fullName;
    private String value;
    private String change;

    public MarketIndex(String name, String fullName, String value, String change) {
        this.name = name;
        this.fullName = fullName;
        this.value = value;
        this.change = change;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getValue() {
        return value;
    }

    public String getChange() {
        return change;
    }

    public boolean isPositiveChange() {
        return change.startsWith("+");
    }
} 