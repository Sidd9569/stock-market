package com.example.stockmarket;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper; // Import ViewFlipper
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private TextView welcomeTextView;
    private EditText searchBar;
    private TextView accountMoneyTextView;
    private ListView portfolioListView;
    private ListView watchlistListView;
    private Button logoutButton;

    private double accountMoney = 1000.00; // Example starting balance
    private List<String> portfolioItems;
    private List<String> watchlistItems;
    private ArrayAdapter<String> watchlistAdapter;

    private Handler handler = new Handler();
    private Runnable marketPriceUpdater;
    private ViewFlipper viewFlipper; // Declare ViewFlipper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize views
        welcomeTextView = findViewById(R.id.welcomeTextView);
        searchBar = findViewById(R.id.searchBar);
        accountMoneyTextView = findViewById(R.id.accountMoneyTextView);
        portfolioListView = findViewById(R.id.portfolioListView);
        watchlistListView = findViewById(R.id.watchlistListView);
        logoutButton = findViewById(R.id.logoutButton);
        viewFlipper = findViewById(R.id.viewFlipper); // Initialize ViewFlipper

        // Set hint text color
        searchBar.setHintTextColor(Color.LTGRAY);

        // Set initial account money
        updateAccountMoney();

        // Sample data
        portfolioItems = new ArrayList<>();
        portfolioItems.add("AAPL - Apple Inc. - $150.00");
        portfolioItems.add("GOOGL - Alphabet Inc. - $2800.00");

        watchlistItems = new ArrayList<>();
        watchlistItems.add("Nifty - $15800.00");
        watchlistItems.add("Bank Nifty - $34500.00");
        watchlistItems.add("BSE - $50000.00");

        // Set adapters for ListViews
        portfolioListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, portfolioItems));
        watchlistAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, watchlistItems);
        watchlistListView.setAdapter(watchlistAdapter);

        // Update market prices
        marketPriceUpdater = new Runnable() {
            @Override
            public void run() {
                updateMarketPrices();
                handler.postDelayed(this, 15000);
            }
        };
        handler.post(marketPriceUpdater);

        // Set up search bar listener to launch search results page
        searchBar.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {

                String query = searchBar.getText().toString().trim();
                if (!query.isEmpty()) {
                    Intent intent = new Intent(DashboardActivity.this, SearchResultActivity.class);
                    intent.putExtra("searchQuery", query);
                    startActivity(intent);
                }
                return true;
            }
            return false;
        });

        // Set logout button click listener
        logoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        // Start flipping images every 3 seconds
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                viewFlipper.showNext(); // Show next image
                handler.postDelayed(this, 15000); // Repeat every 3 seconds
            }
        }, 15000);
    }

    private void updateAccountMoney() {
        accountMoneyTextView.setText("Account Balance: $" + String.format("%.2f", accountMoney));
    }

    private void updateMarketPrices() {
        for (int i = 0; i < watchlistItems.size(); i++) {
            watchlistItems.set(i, watchlistItems.get(i) + " - $" + String.format("%.2f", Math.random() * 1000));
        }
        watchlistAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(marketPriceUpdater);
    }
}

