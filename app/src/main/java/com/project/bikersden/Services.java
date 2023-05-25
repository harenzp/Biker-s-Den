package com.project.bikersden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Services extends AppCompatActivity {

    Button proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        getSupportActionBar().hide();

        proceed = (Button) findViewById(R.id.proceed);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Services.this, "Schedule set. Just present your account details at the shop tomorrow.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Services.this, WelcomeScreen.class));
            }
        });
    }
}