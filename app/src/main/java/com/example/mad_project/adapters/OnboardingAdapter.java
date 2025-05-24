package com.example.mad_project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.mad_project.R;
import com.example.mad_project.models.OnboardingItem;

import java.util.List;

public class OnboardingAdapter extends PagerAdapter {
    private Context context;
    private List<OnboardingItem> onboardingItems;

    public OnboardingAdapter(Context context, List<OnboardingItem> onboardingItems) {
        this.context = context;
        this.onboardingItems = onboardingItems;
    }

    @Override
    public int getCount() {
        return onboardingItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.onboarding_item, container, false);

        ImageView imageView = view.findViewById(R.id.imageOnboarding);
        TextView titleTextView = view.findViewById(R.id.textTitle);
        TextView descriptionTextView = view.findViewById(R.id.textDescription);

        OnboardingItem item = onboardingItems.get(position);
        imageView.setImageResource(item.getImage());
        titleTextView.setText(item.getTitle());
        descriptionTextView.setText(item.getDescription());

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
} 