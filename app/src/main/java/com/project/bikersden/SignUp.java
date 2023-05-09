package com.project.bikersden;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class SignUp extends AppCompatActivity {

    private EditText firstNameText, lastNameText, bdayText, phoneText, emailText;
    private RadioButton male, female;
    private Button clear, submit;
    private String firstName, lastName, bday, phone, email, name, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firstNameText = findViewById(R.id.firstName);
        lastNameText = findViewById(R.id.lastName);
        bdayText = findViewById(R.id.bday);
        phoneText = findViewById(R.id.phone);
        emailText = findViewById(R.id.email);

        male = findViewById(R.id.btnMale);
        female = findViewById(R.id.btnFemale);

        clear = findViewById(R.id.btnClear);
        submit = findViewById(R.id.btnSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearData();
            }
        });

    }

    public void sendData(){
        firstName = firstNameText.getText().toString().trim();
        lastName = lastNameText.getText().toString().trim();
        name = firstName.concat(" ").concat(lastName);
        bday = bdayText.getText().toString().trim();
        phone = phoneText.getText().toString().trim();
        email = emailText.getText().toString().trim();

        if (female.isSelected()) gender = "Female";
        else gender = "Male";
    }

    public void clearData(){
        firstNameText.setText("");
        lastNameText.setText("");
        bdayText.setText("");
        phoneText.setText("");
        emailText.setText("");
    }

}
