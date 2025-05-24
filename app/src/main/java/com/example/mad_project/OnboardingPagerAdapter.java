package com.example.mad_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class OnboardingPagerAdapter extends RecyclerView.Adapter<OnboardingPagerAdapter.OnboardingViewHolder> {
    private List<OnboardingPage> pages;

    public OnboardingPagerAdapter(List<OnboardingPage> pages) {
        this.pages = pages;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingViewHolder(
            LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_onboarding_page,
                parent,
                false
            )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        holder.bind(pages.get(position));
    }

    @Override
    public int getItemCount() {
        return pages.size();
    }

    static class OnboardingViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView titleText;
        private TextView descriptionText;

        OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.onboardingImage);
            titleText = itemView.findViewById(R.id.onboardingTitle);
            descriptionText = itemView.findViewById(R.id.onboardingDescription);
        }

        void bind(OnboardingPage page) {
            imageView.setImageResource(page.getImageResId());
            titleText.setText(page.getTitle());
            descriptionText.setText(page.getDescription());
        }
    }
} 