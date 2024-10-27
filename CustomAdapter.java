package com.example.stockmarket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {
    private Context context;
    private List<String> items;

    public CustomAdapter(Context context, List<String> items) {
        super(context, R.layout.list_item_with_image, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_with_image, parent, false);
        }

        ImageView itemImage = convertView.findViewById(R.id.itemImage);
        TextView itemText = convertView.findViewById(R.id.itemText);

        String item = items.get(position);
        itemText.setText(item);

        // Set different images based on the item type
        if (item.toLowerCase().contains("nifty")) {
            itemImage.setImageResource(R.drawable.ic_nifty_image); // Ensure these drawables exist
        } else if (item.toLowerCase().contains("bank nifty")) {
            itemImage.setImageResource(R.drawable.ic_bank_nifty_image);
        } else if (item.toLowerCase().contains("bse")) {
            itemImage.setImageResource(R.drawable.ic_bse_image);
        } else {
            itemImage.setImageResource(R.drawable.img); // Default image
        }

        return convertView;
    }
}
