package com.project.bikersden;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {



    Button deleteBtn;
    private ListView listViewProducts;


    List<Product> productList = new ArrayList<>();

    String[] prodNames = {"Frisco Sportster Gas Tank", "Keystone Handlebar", "Firestone Sawtooth Tires", "Shotgun Exhaust", "Ape Hanger Handlebar",
            "Shinko SR777 Tires", "Bates Baja Tires", "T-Bars Handlebars", "Cone Shovelhead Exhaust", "Wassell Peanut Gas Tank",
            "WX Split Gas Tanks", "Dyna Exhaust Pipe"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().hide();
        DatabaseManager db = new DatabaseManager(Cart.this);



        List<Product> newProductList = new ArrayList<>();



        listViewProducts = (ListView) findViewById(R.id.listViewProducts);

        ProductAdapter adapter = new ProductAdapter(Cart.this, newProductList);
        listViewProducts.setAdapter(adapter);

        productList = db.populateProductList();




        for(int i = 0; i < productList.size(); i++){
                Cart.Product p = productList.get(i);

                String name = p.getName();
                int id = p.getImageResourceId();
                String description = p.getDescription();
                String price = p.getPrice();

                if(name.equals(prodNames[0])){
                    newProductList.add(new Cart.Product(name,R.drawable.frisco,description,price));
                }else{
                    System.out.println("Hatdog");
                }
                if(name.equals(prodNames[1])){
                    newProductList.add(new Cart.Product(name,R.drawable.zbars,description,price));
                }
                if(name.equals(prodNames[2])){
                    newProductList.add(new Cart.Product(name,R.drawable.firestone,description,price));
                }
                if(name.equals(prodNames[3])){
                    newProductList.add(new Cart.Product(name,R.drawable.shotgun,description,price));
                }
                if(name.equals(prodNames[4])){
                    newProductList.add(new Cart.Product(name,R.drawable.apehanger,description,price));
                }
                if(name.equals(prodNames[5])){
                    newProductList.add(new Cart.Product(name,R.drawable.shinko,description,price));
                }
                if(name.equals(prodNames[6])){
                    newProductList.add(new Cart.Product(name,R.drawable.bates,description,price));
                }
                if(name.equals(prodNames[7])){
                    newProductList.add(new Cart.Product(name,R.drawable.tbars,description,price));
                }
                if(name.equals(prodNames[8])){
                    newProductList.add(new Cart.Product(name,R.drawable.coneshovelhead,description,price));
                }
                if(name.equals(prodNames[9])){
                    newProductList.add(new Cart.Product(name,R.drawable.peanut,description,price));
                }
                if(name.equals(prodNames[10])){
                    newProductList.add(new Cart.Product(name,R.drawable.split,description,price));
                }
                if(name.equals(prodNames[11])){
                    newProductList.add(new Cart.Product(name,R.drawable.dyna,description,price));
                }




            System.out.print("Name: "  + p.getName() + " Id: " + p.getImageResourceId() + " Description: " + p.getDescription() + " Price" +
                    ": " + p.getPrice() + "\n");

                deleteBtn = (Button) findViewById(R.id.buttonDelete);
                deleteBtn.setOnClickListener(new deleteBtnListerner());


        }





        System.out.println("Product List Size: " + productList.size());
        System.out.println("Hello World");






    }




    public class deleteBtnListerner implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            DatabaseManager db =  new DatabaseManager(Cart.this);
            if(db.deleteCart()){
                Toast.makeText(Cart.this, "Items has been Deleted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(Cart.this, "There is no items to be deleted", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public class ProductAdapter extends BaseAdapter {
        private Context context;
        private List<Product> productList;

        public ProductAdapter(Context context, List<Product> productList) {
            this.context = context;
            this.productList = productList;
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




    static class Product implements Serializable{
        private String name, description, price;
        private int imageResourceId;

        static String prodName = "";

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