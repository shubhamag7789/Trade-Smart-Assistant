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
import java.util.UUID;

public class OrderConfirmationDialog extends BottomSheetDialogFragment {
    private static final String ARG_SYMBOL = "symbol";
    private static final String ARG_COMPANY_NAME = "company_name";
    private static final String ARG_PRICE = "price";
    private static final String ARG_UNITS = "units";
    private static final String ARG_IS_BUY = "is_buy";

    private String symbol;
    private String companyName;
    private String price;
    private int units;
    private boolean isBuy;

    public static OrderConfirmationDialog newInstance(String symbol, String companyName, 
                                                    String price, int units, boolean isBuy) {
        OrderConfirmationDialog fragment = new OrderConfirmationDialog();
        Bundle args = new Bundle();
        args.putString(ARG_SYMBOL, symbol);
        args.putString(ARG_COMPANY_NAME, companyName);
        args.putString(ARG_PRICE, price);
        args.putInt(ARG_UNITS, units);
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
            price = getArguments().getString(ARG_PRICE);
            units = getArguments().getInt(ARG_UNITS);
            isBuy = getArguments().getBoolean(ARG_IS_BUY);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_order_confirmation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        TextView stockInfoTextView = view.findViewById(R.id.stockInfoTextView);
        TextView unitsTextView = view.findViewById(R.id.unitsTextView);
        TextView totalAmountTextView = view.findViewById(R.id.totalAmountTextView);
        TextView orderIdTextView = view.findViewById(R.id.orderIdTextView);
        MaterialButton doneButton = view.findViewById(R.id.doneButton);

        // Calculate total amount
        double priceValue = Double.parseDouble(price.replace("$", "").replace(",", ""));
        double totalAmount = priceValue * units;

        // Set values
        stockInfoTextView.setText(String.format("%s • %s", symbol, companyName));
        unitsTextView.setText(String.format("Units: %d", units));
        totalAmountTextView.setText(String.format("Total Amount: $%.2f", totalAmount));
        orderIdTextView.setText(String.format("Order ID: %s", generateOrderId()));

        // Setup done button
        doneButton.setOnClickListener(v -> {
            dismiss();
            if (getActivity() != null) {
                getActivity().finish();
            }
        });
    }

    private String generateOrderId() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
} 