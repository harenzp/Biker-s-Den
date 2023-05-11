package com.project.bikersden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText firstNameText, lastNameText, bdayText, phoneText, emailText,passwordText;
    private RadioButton male, female;
    private Button clear, submit;
    private String firstName, lastName, bday, phone, email, name, gender,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firstNameText = (EditText) findViewById(R.id.firstName);
        lastNameText = (EditText) findViewById(R.id.lastName);
        bdayText = (EditText) findViewById(R.id.bday);
        phoneText = (EditText) findViewById(R.id.phone);
        emailText = (EditText) findViewById(R.id.email);
        passwordText = (EditText) findViewById(R.id.password);
        male = (RadioButton) findViewById(R.id.btnMale);
        female = (RadioButton) findViewById(R.id.btnFemale);
        clear = (Button) findViewById(R.id.btnClear);
        submit = (Button) findViewById(R.id.btnSubmit);
        submit.setOnClickListener(this);
        clear.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {


        switch(view.getId()){
            case R.id.btnSubmit:
                firstName = firstNameText.getText().toString().trim();
                lastName = lastNameText.getText().toString().trim();
                name = firstName.concat(" ").concat(lastName);
                bday = bdayText.getText().toString().trim();
                phone = phoneText.getText().toString().trim();
                email = emailText.getText().toString().trim();
                password = passwordText.getText().toString().trim();
                if(male.isSelected()){
                    gender = "male";
                }else{
                    gender = "female";
                }
                DatabaseManager db = new DatabaseManager(SignUp.this);
                String success =  db.addAccount(name,bday,email,password,phone,gender);
                Toast.makeText(SignUp.this,success, Toast.LENGTH_SHORT).show();
                if(success.equals("Account Created")){
                    startActivity(new Intent(SignUp.this,MainActivity.class));
                }
                break;
            case R.id.btnClear:
                firstNameText.setText("");
                lastNameText.setText("");
                bdayText.setText("");
                phoneText.setText("");
                emailText.setText("");
                break;
        }

    }





}
