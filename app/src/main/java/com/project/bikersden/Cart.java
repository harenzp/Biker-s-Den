package com.project.bikersden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {



    private ListView listViewProducts;
    private ProductAdapter adapter;
    private List<Cart.Product> productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        DatabaseManager db = new DatabaseManager(Cart.this);


        listViewProducts = findViewById(R.id.listViewProducts);

        productList = db.populateProductList();


        adapter = new ProductAdapter(this, productList);
        listViewProducts.setAdapter(adapter);




    }


    private class ProductAdapter extends BaseAdapter {
        private Context context;
        private List<Cart.Product> productList;

        ProductAdapter(Context context, List<Cart.Product> productList) {
            this.context = context;
            this.productList = productList;
        }

        void setProductList(List<Cart.Product> productList) {
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

            Cart.Product product = productList.get(position);
            imageViewProduct.setImageResource(product.getImageResourceId());
            textViewProductName.setText(product.getName());
            textViewProductDescription.setText(product.getDescription());
            textViewPrice.setText(product.getPrice());

          /*  convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start ProductDescriptionActivity with the selected product
                    Intent intent = new Intent(context, ProductDescription.class);
                    intent.putExtra("product", product);
                    context.startActivity(intent);
                }
            });

           */

            return convertView;
        }
    }
    static class Product implements Serializable {
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