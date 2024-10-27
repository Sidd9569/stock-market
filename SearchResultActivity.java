package com.example.stockmarket;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    private ImageView niftyChartImageView;
    private ImageView bankNiftyChartImageView;
    private ImageView bseChartImageView;
    private TextView stockNameTextView;
    private TextView stockValueTextView;
    private EditText quantityEditText;
    private EditText stopLossEditText;
    private ListView searchResultListView;
    private Button submitButton;

    private List<String> filteredItems;
    private CustomAdapter adapter;
    private Handler handler = new Handler();
    private Runnable priceUpdater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        // Initialize views
        niftyChartImageView = findViewById(R.id.niftyChartImageView);
        bankNiftyChartImageView = findViewById(R.id.bankNiftyChartImageView);
        bseChartImageView = findViewById(R.id.bseChartImageView);
        stockNameTextView = findViewById(R.id.stockNameTextView);
        stockValueTextView = findViewById(R.id.stockValueTextView);
        quantityEditText = findViewById(R.id.quantityEditText);
        stopLossEditText = findViewById(R.id.stopLossEditText);
        searchResultListView = findViewById(R.id.searchResultListView);
        submitButton = findViewById(R.id.submitButton);

        // Get search query from intent
        String searchQuery = getIntent().getStringExtra("searchQuery");

        // Initialize the list and adapter
        filteredItems = new ArrayList<>();
        adapter = new CustomAdapter(this, filteredItems);
        searchResultListView.setAdapter(adapter);

        // Populate initial search results and display stock name
        filterSearchResults(searchQuery);

        // Start dynamic price updates
        priceUpdater = new Runnable() {
            @Override
            public void run() {
                updatePrices();
                handler.postDelayed(this, 3000); // Update every 3 seconds
            }
        };
        handler.post(priceUpdater);

        // Handle submit button click
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSubmit();
            }
        });
    }

    private void filterSearchResults(String query) {
        filteredItems.clear();
        niftyChartImageView.setVisibility(View.GONE);
        bankNiftyChartImageView.setVisibility(View.GONE);
        bseChartImageView.setVisibility(View.GONE);
        stockNameTextView.setText("No results found"); // Default message

        switch (query.toLowerCase()) {
            case "nifty":
                stockNameTextView.setText("Nifty");
                filteredItems.add("Nifty - $" + (Math.random() * 1000));
                niftyChartImageView.setVisibility(View.VISIBLE);
                break;
            case "bank nifty":
                stockNameTextView.setText("Bank Nifty");
                filteredItems.add("Bank Nifty - $" + (Math.random() * 1000));
                bankNiftyChartImageView.setVisibility(View.VISIBLE);
                break;
            case "bse":
                stockNameTextView.setText("BSE");
                filteredItems.add("BSE - $" + (Math.random() * 1000));
                bseChartImageView.setVisibility(View.VISIBLE);
                break;
        }

        adapter.notifyDataSetChanged();
    }

    private void updatePrices() {
        for (int i = 0; i < filteredItems.size(); i++) {
            String item = filteredItems.get(i).split(" - ")[0];
            String newPrice = "$" + String.format("%.2f", Math.random() * 1000);
            filteredItems.set(i, item + " - " + newPrice);
        }

        // Update the displayed stock value for the first item (if exists)
        if (!filteredItems.isEmpty()) {
            stockValueTextView.setText("Value: " + filteredItems.get(0).split(" - ")[1]); // Display updated value
        }

        adapter.notifyDataSetChanged();
    }

    private void handleSubmit() {
        String quantity = quantityEditText.getText().toString();
        String stopLoss = stopLossEditText.getText().toString();

        if (quantity.isEmpty() || stopLoss.isEmpty()) {
            // Show an error message or toast if either field is empty
            // Example: Toast.makeText(this, "Please enter quantity and stop loss", Toast.LENGTH_SHORT).show();
            return;
        }

        // Handle the logic for submission, e.g., placing an order
        // Example:
        // int quantityValue = Integer.parseInt(quantity);
        // double stopLossValue = Double.parseDouble(stopLoss);
        // PlaceOrder(quantityValue, stopLossValue);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(priceUpdater);
    }
}
