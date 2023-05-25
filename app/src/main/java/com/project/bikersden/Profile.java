package com.project.bikersden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {


    Button homeBtn,logoutBtn,deleteAccountButton,editPicture;

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


        deleteAccountButton = (Button) findViewById(R.id.deleteAccountBtn);
        deleteAccountButton.setOnClickListener(new deleteAccountListener());


        editPicture = (Button) findViewById(R.id.editProfileBtn);
        editPicture.setOnClickListener(new editProfileBtnListener());


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
            profile.setImageResource(R.drawable.gian);
        }



    }




    public class editProfileBtnListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {



            if(DatabaseManager.userGender.equals("Male")){


                for (int i = 0; i < 7; i++) {
                    int x = (int) (Math.random() * 10);
                    if (x == 0) profile.setImageResource(R.drawable.johnnybert);
                    if (x == 1) profile.setImageResource(R.drawable.rhyss);
                    if (x == 2) profile.setImageResource(R.drawable.nize);
                    if (x == 3) profile.setImageResource(R.drawable.gian2);
                    if (x == 4) profile.setImageResource(R.drawable.harenz);
                    if (x == 5) profile.setImageResource(R.drawable.gian);
                    if (x == 6) profile.setImageResource(R.drawable.bert);
                }




            }else{

            }
        }
    }

    public class deleteAccountListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            DatabaseManager db = new DatabaseManager(Profile.this);
            if(db.deleteAccount()){
                Toast.makeText(Profile.this, "Acount Deleted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Profile.this,MainActivity.class));
            }
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