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
import android.widget.Toast;

public class ProductDescription extends AppCompatActivity {
    private ImageView imageViewProduct,viewCart;
    private TextView textViewProductName, textViewProductDescription,textViewProductPrice;
    private Button buttonAddToCart, buttonAddReview;
    private EditText editTextReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);
        getSupportActionBar().hide();

        // Retrieve the serialized product object from intent extras
        Product product = (Product) getIntent().getSerializableExtra("product");

        // Initialize views
        imageViewProduct = findViewById(R.id.imageViewProduct);
        textViewProductName = findViewById(R.id.textViewProductName);
        textViewProductDescription = findViewById(R.id.textViewProductDescription);
        textViewProductPrice  = findViewById(R.id.textPrice);
        buttonAddToCart = findViewById(R.id.buttonAddToCart);
        editTextReview = findViewById(R.id.editTextReview);
        buttonAddReview = findViewById(R.id.buttonAddReview);
        viewCart = findViewById(R.id.carticon);

        // Set product details
        imageViewProduct.setImageResource(product.getImageResourceId());
        textViewProductName.setText(product.getName());
        textViewProductDescription.setText(product.getDescription());
        textViewProductPrice.setText(product.getPrice());

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
                DatabaseManager db = new DatabaseManager(ProductDescription.this);
                int id = imageViewProduct.getId();
                String name = textViewProductName.getText().toString();
                String description = textViewProductDescription.getText().toString();
                String price = textViewProductPrice.getText().toString();
                if(db.addToCart(name,id,description,price)){
                    Toast.makeText(ProductDescription.this,"Added to Cart",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ProductDescription.this,"Failed",Toast.LENGTH_SHORT).show();
                }
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
