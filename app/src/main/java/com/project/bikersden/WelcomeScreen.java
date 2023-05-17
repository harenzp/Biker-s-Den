package com.project.bikersden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeScreen extends AppCompatActivity {

    private Button shopBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        shopBtn = (Button) findViewById(R.id.button2);

        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button2:
                        Intent intent = new Intent(WelcomeScreen.this, Shop.class);
                        startActivity(intent);
                        break;
                }
            }
        };

        shopBtn.setOnClickListener(buttonClickListener);
    }
}