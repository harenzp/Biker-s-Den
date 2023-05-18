package com.project.bikersden;
import com.project.bikersden.Shop.Product;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductDescription extends AppCompatActivity {
    private ImageView imageViewProduct,viewCart;
    private TextView textViewProductName, textViewProductDescription;
    private Button buttonAddToCart, buttonAddReview;
    private EditText editTextReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);

        // Retrieve the serialized product object from intent extras
        Product product = (Product) getIntent().getSerializableExtra("product");

        // Initialize views
        imageViewProduct = findViewById(R.id.imageViewProduct);
        textViewProductName = findViewById(R.id.textViewProductName);
        textViewProductDescription = findViewById(R.id.textViewProductDescription);
        buttonAddToCart = findViewById(R.id.buttonAddToCart);
        editTextReview = findViewById(R.id.editTextReview);
        buttonAddReview = findViewById(R.id.buttonAddReview);
        viewCart = findViewById(R.id.carticon);

        // Set product details
        imageViewProduct.setImageResource(product.getImageResourceId());
        textViewProductName.setText(product.getName());
        textViewProductDescription.setText(product.getDescription());

        // view cart
        viewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // cart layout
                startActivity(new Intent(ProductDescription.this,Cart.class));
            }
        });

        // Set click listener for "Add to Cart" button
        buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add the product to the cart
                // Implement your logic here
            }
        });

        // Set click listener for "Add Review" button
        buttonAddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String review = editTextReview.getText().toString();
                // Add the review for the product
                // Implement your logic here
            }
        });
    }
}
