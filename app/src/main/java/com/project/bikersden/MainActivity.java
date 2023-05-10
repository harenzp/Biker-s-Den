package com.project.bikersden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView newAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newAccount = (TextView) findViewById(R.id.newAccount);
        newAccount.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        startActivity(new Intent(MainActivity.this,SignUp.class));
    }




}

