package com.project.bikersden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Shop extends AppCompatActivity {
    private ListView listViewProducts;
    private ProductAdapter adapter;
    private List<Product> productList;
    private EditText editTextSearch;
    private Button buttonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        listViewProducts = findViewById(R.id.listViewProducts);
        editTextSearch = findViewById(R.id.editTextSearch);
        buttonSearch = findViewById(R.id.buttonSearch);

        // Populate the product list (dummy data for demonstration)
        productList = new ArrayList<>();

        productList.add(new Product("Frisco Sportster Gas Tank", R.drawable.frisco,
                "Frisco Mount Sportster Gas Tank - Standard Width - Bayonet Filler - 2.5 gallon", "₱6000"));
        productList.add(new Product("Keystone Handlebar", R.drawable.zbars,
                "Keystone Handlebars - 7/8 inch - Black", "₱2200"));
        productList.add(new Product("Firestone Sawtooth Tires", R.drawable.firestone,
                "Firestone Deluxe Champion Motorcycle Tire 5.00 - 16", "₱4300"));
        productList.add(new Product("Shotgun Exhaust", R.drawable.shotgun,
                "Hi Lo Shotgun Exhaust Pipe Set 1986-2003 Harley-Davidson", "₱7000"));
        productList.add(new Product("Ape Hanger Handlebar", R.drawable.apehanger,
                "Mini Ape Hanger Handlebars - 1 inch - 9 inch Rise - Chrome", "₱2000"));
        productList.add(new Product("Shinko SR777 Tires", R.drawable.shinko,
                "SR777 Whitewall Rear Motorcycle Tire - 130/90B16", "₱5600"));
        productList.add(new Product("Bates Baja Tires", R.drawable.bates,
                "Baja 100 Tire 16\" - 5.00-16 71T", "₱4700"));
        productList.add(new Product("T-Bars Handlebars", R.drawable.tbars,
                "T-Bars Handlebars - 10 inch Rise - 1 inch - Chrome", "₱3000"));
        productList.add(new Product("Cone Shovelhead Exhaust", R.drawable.coneshovelhead,
                "Exhaust Pipe Set 2-into-1 1970-84 Harley Cone Shovelhead", "₱6600"));
        productList.add(new Product("Wassell Peanut Gas Tank", R.drawable.peanut,
                "Wassell Peanut Mid-Tunnel Gas Tank 2.1 gallon", "₱2900"));
        productList.add(new Product("WX Split Gas Tanks", R.drawable.split,
                "WX Split Gas Tanks - Bolt-On for 1936 - 1984 Big Twin", "₱3400"));
        productList.add(new Product("Dyna Exhaust Pipe", R.drawable.dyna,
                "W2 into 1 Exhaust System 1999-2005 H-D Dyna", "₱8000"));
        // ...

        // Create and set the adapter
        adapter = new ProductAdapter(this, productList);
        listViewProducts.setAdapter(adapter);


        // Add a TextWatcher to filter the list based on search query
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().toLowerCase();
                List<Product> filteredList = new ArrayList<>();

                for (Product product : productList) {
                    if (product.getName().toLowerCase().contains(query)) {
                        filteredList.add(product);
                    }
                }

                adapter.setProductList(filteredList);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Add a click listener to the search button to clear the search query
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSearch.setText("");
            }
        });
    }

    private class ProductAdapter extends BaseAdapter {
        private Context context;
        private List<Product> productList;

        ProductAdapter(Context context, List<Product> productList) {
            this.context = context;
            this.productList = productList;
        }

        void setProductList(List<Product> productList) {
            this.productList = productList;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return productList.size();
        }

        @Override
        public Object getItem(int position) {
            return productList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(R.layout.list_items, parent, false);
            }

            ImageView imageViewProduct = convertView.findViewById(R.id.imageViewProduct);
            TextView textViewProductName = convertView.findViewById(R.id.textViewProductName);
            TextView textViewProductDescription = convertView.findViewById(R.id.textViewProductDescription);
            TextView textViewPrice = convertView.findViewById(R.id.textViewPrice);

            Product product = productList.get(position);
            imageViewProduct.setImageResource(product.getImageResourceId());
            textViewProductName.setText(product.getName());
            textViewProductDescription.setText(product.getDescription());
            textViewPrice.setText(product.getPrice());

            return convertView;
        }
    }

    private static class Product {
        private String name, description, price;
        private int imageResourceId;

        Product(String name, int imageResourceId, String description, String price) {
            this.name = name;
            this.imageResourceId = imageResourceId;
            this.description = description;
            this.price = price;
        }

        String getName() {
            return name;
        }
        String getDescription() { return description; }
        String getPrice() { return price; }
        int getImageResourceId() {
            return imageResourceId;
        }
    }
}
