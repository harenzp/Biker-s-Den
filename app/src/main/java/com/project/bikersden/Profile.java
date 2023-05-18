package com.project.bikersden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends AppCompatActivity {


    Button homeBtn,logoutBtn;

    TextView welcomeText,nameText,bdayText,phoneText,genderText,gmailText,passText;
    ImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        homeBtn = findViewById(R.id.homeBtn);
        homeBtn.setOnClickListener(new homeBtnListener());

        logoutBtn = findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new logoutBtnLister());


        welcomeText = findViewById(R.id.welcomeId);
        welcomeText.setText("Welcome " + DatabaseManager.userName);

        nameText = (TextView) findViewById(R.id.nameId);
        nameText.setText("Name: " + DatabaseManager.userName);

        bdayText = (TextView) findViewById(R.id.bdayId);
        bdayText.setText("Birthday: " + DatabaseManager.userBday);

        phoneText = (TextView) findViewById(R.id.phoneId);
        phoneText.setText("Phone: " + DatabaseManager.userPhone);

        genderText = (TextView) findViewById(R.id.genderId);
        genderText.setText("Gender: " + DatabaseManager.userGender);


        gmailText = (TextView) findViewById(R.id.gmailId);
        gmailText.setText("Gmail: " + DatabaseManager.userGmail);

        passText = (TextView) findViewById(R.id.passwordId);
        passText.setText("Password: " + DatabaseManager.userPass);

        profile = (ImageView) findViewById(R.id.your_imageview_id);
        if(DatabaseManager.userGender.equals("Male")){
            profile.setImageResource(R.drawable.harenz);
        }


    }



    public class homeBtnListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            startActivity(new Intent(Profile.this,WelcomeScreen.class));
        }
    }

    public class logoutBtnLister implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            startActivity(new Intent(Profile.this,MainActivity.class));
        }
    }


}