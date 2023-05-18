package com.project.bikersden;


import android.database.Cursor;
import android.database.sqlite.*;
import android.content.*;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;

import java.io.Closeable;

public class DatabaseManager extends SQLiteOpenHelper{


    public static final String columnUSER = "user";
    public static final String columnID = "ID";
    public static final String columnNAME = "name";
    public static final String columnBDAY = "bday";
    public static final String columnGMAIL = "gmail";
    public static final String columnPHONE = "phone";
    public static final String columnGENDER = "gender";

    public static final String columnPassword  = "password";


    public static String userGmail = "",userPass = "",userName = "",userBday = "",userPhone = "",userGender = "";



    public DatabaseManager(@Nullable Context context) {
       super(context,  "useraccounts.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String makeTable = "CREATE TABLE " + columnUSER + "(" + columnID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + columnNAME + " TEXT, " + columnBDAY +
                " TEXT, " + columnGMAIL + " TEXT, " + columnPassword +" TEXT, "+ columnPHONE + " TEXT, " + columnGENDER + " TEXT)";
        db.execSQL(makeTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public String addAccount(String name, String bday, String email,String password, String phone, String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(columnNAME, name);
        values.put(columnBDAY, bday);
        values.put(columnGMAIL, email);
        values.put(columnPassword,password);
        values.put(columnPHONE, phone);
        values.put(columnGENDER, gender);

        long result = db.insert(columnUSER,null,values);
        db.close();
            if(result == -1){
              return "Account Creation Failed";
            }
            return "Account Created";
    }

    public boolean loginAccount(String gmail, String password){


        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                "gmail",
                "password"
        };

        // Define the selection criteria
        String selection = "gmail = ? AND password = ?";
        String[] selectionArgs = { gmail, password };

        // Query the user table
        Cursor cursor = db.query(
                "user",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        // Check if the cursor has any rows
        boolean result = cursor.moveToFirst();

        // Close the cursor and database when finished
        cursor.close();
        db.close();

        // Return the result
        return result;


    }


    public static void setGmail(String name){
        userGmail = name;
    }

    public static void setPass(String password) {
        userPass = password;
    }


    public void getDataByName() {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {columnNAME, columnBDAY, columnPHONE, columnGENDER};
        String selection = columnGMAIL + " = ?";
        String[] selectionArgs = {userGmail};

        Cursor cursor =  db.query(columnUSER, projection, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            userName = cursor.getString(0);
            userBday = cursor.getString(1);
            userPhone = cursor.getString(2);
            userGender = cursor.getString(3);
        }



            db.close();
            cursor.close();

    }




}
