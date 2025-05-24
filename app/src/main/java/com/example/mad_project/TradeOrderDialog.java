package com.example.mad_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.Slider;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class TradeOrderDialog extends BottomSheetDialogFragment {
    private static final String ARG_SYMBOL = "symbol";
    private static final String ARG_COMPANY_NAME = "company_name";
    private static final String ARG_CURRENT_PRICE = "current_price";
    private static final String ARG_IS_BUY = "is_buy";

    private String symbol;
    private String companyName;
    private String currentPrice;
    private boolean isBuy;

    public static TradeOrderDialog newInstance(String symbol, String companyName, 
                                             String currentPrice, boolean isBuy) {
        TradeOrderDialog fragment = new TradeOrderDialog();
        Bundle args = new Bundle();
        args.putString(ARG_SYMBOL, symbol);
        args.putString(ARG_COMPANY_NAME, companyName);
        args.putString(ARG_CURRENT_PRICE, currentPrice);
        args.putBoolean(ARG_IS_BUY, isBuy);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            symbol = getArguments().getString(ARG_SYMBOL);
            companyName = getArguments().getString(ARG_COMPANY_NAME);
            currentPrice = getArguments().getString(ARG_CURRENT_PRICE);
            isBuy = getArguments().getBoolean(ARG_IS_BUY);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_trade_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        TextView titleTextView = view.findViewById(R.id.titleTextView);
        TextView stockInfoTextView = view.findViewById(R.id.stockInfoTextView);
        TextView unitsValueTextView = view.findViewById(R.id.unitsValueTextView);
        TextView lotsValueTextView = view.findViewById(R.id.lotsValueTextView);
        Slider unitsSlider = view.findViewById(R.id.unitsSlider);
        MaterialButton orderButton = view.findViewById(R.id.orderButton);

        // Set initial values
        titleTextView.setText(isBuy ? "Buy Order" : "Sell Order");
        stockInfoTextView.setText(String.format("%s • %s • %s", symbol, companyName, currentPrice));
        unitsValueTextView.setText("1000");
        updateLotsValue(1000, lotsValueTextView);
        orderButton.setText(isBuy ? "Place Buy Order" : "Place Sell Order");
        orderButton.setBackgroundTintList(getResources().getColorStateList(
            isBuy ? R.color.green_button : R.color.red_button, requireContext().getTheme()));

        // Setup slider listener
        unitsSlider.addOnChangeListener((slider, value, fromUser) -> {
            int units = (int) value;
            unitsValueTextView.setText(String.valueOf(units));
            updateLotsValue(units, lotsValueTextView);
        });

        // Setup order button
        orderButton.setOnClickListener(v -> {
            // Get the current units value
            int units = Integer.parseInt(unitsValueTextView.getText().toString());
            
            // Show confirmation dialog
            OrderConfirmationDialog confirmationDialog = OrderConfirmationDialog.newInstance(
                symbol,
                companyName,
                currentPrice,
                units,
                isBuy
            );
            confirmationDialog.show(getParentFragmentManager(), "OrderConfirmationDialog");
            
            // Dismiss this dialog
            dismiss();
        });
    }

    private void updateLotsValue(int units, TextView lotsValueTextView) {
        float lots = units / 100f;
        lotsValueTextView.setText(String.format("%.2f", lots));
    }
} 