package com.project.bikersden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.FileObserver;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView newAccount;

    private EditText emailAddress,password;

    private Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newAccount = (TextView) findViewById(R.id.newAccount);
        newAccount.setOnClickListener(this);

        emailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress);
        password = (EditText) findViewById(R.id.editTextTextPassword);

        signIn = (Button) findViewById(R.id.button);
        signIn.setOnClickListener(this);



    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                String gmail = emailAddress.getText().toString().trim();
                String pass =  password.getText().toString().trim();
                DatabaseManager db = new DatabaseManager(MainActivity.this);
                if(db.loginAccount(gmail,pass)){
                    DatabaseManager.setGmail(gmail);
                    DatabaseManager.setPass(pass);
                    db.getDataByName();

                    Toast.makeText(MainActivity.this, "Welcome " + db.userName, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,WelcomeScreen.class));
                }else{
                    Toast.makeText(MainActivity.this, "Login Failed. Incorrect gmail or password", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.newAccount:
                startActivity(new Intent(MainActivity.this,SignUp.class));
                break;
        }



    }




}

