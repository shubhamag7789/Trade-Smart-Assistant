package com.example.mad_project;

public class OnboardingPage {
    private int imageResId;
    private String title;
    private String description;

    public OnboardingPage(int imageResId, String title, String description) {
        this.imageResId = imageResId;
        this.title = title;
        this.description = description;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
} 