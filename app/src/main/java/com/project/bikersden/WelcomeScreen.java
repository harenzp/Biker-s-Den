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


    Button shopBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);


        shopBtn = (Button) findViewById(R.id.button2);

        shopBtn.setOnClickListener(new shopBtnListener());


    }




    public class shopBtnListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            startActivity(new Intent(WelcomeScreen.this,Shop.class));
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