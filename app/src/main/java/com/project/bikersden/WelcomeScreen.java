package com.project.bikersden;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class WelcomeScreen extends AppCompatActivity {


    Button shopBtn, servicesBtn, bikeSetupsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);


        shopBtn = (Button) findViewById(R.id.shop);
        shopBtn.setOnClickListener(new shopBtnListener());

        servicesBtn = (Button) findViewById(R.id.services);
        servicesBtn.setOnClickListener(new servicesBtnListener());

        bikeSetupsBtn = (Button) findViewById(R.id.browsebikes);
        bikeSetupsBtn.setOnClickListener(new bikeSetupsBtnListener());


    }




    public class shopBtnListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            startActivity(new Intent(WelcomeScreen.this,Shop.class));
        }
    }

    public class servicesBtnListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            startActivity(new Intent(WelcomeScreen.this,Services.class));
        }
    }

    public class bikeSetupsBtnListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            startActivity(new Intent(WelcomeScreen.this, BikeSetups.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.account,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.accountId:
                startActivity(new Intent(WelcomeScreen.this,Profile.class));
                break;
        }
        return true;
    }


}