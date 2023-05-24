package com.project.bikersden;




import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.*;
import android.content.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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


    public static int currId = 0;


    public static final String columnPRODUCT = "product";

    public static final String columnProductId = "product_id";

    public static final String columnPicture = "product_picture";

    public static final String columnInfo = "product_info";


    public static final String columnPrice = "product_price";
    public static final String columnAccountId = "account_id";

    public static final String columnProductName = "product_name";



    private List<Cart.Product> productList = new ArrayList<>();




    public DatabaseManager(@Nullable Context context) {
       super(context,  "useraccounts.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String makeTable = "CREATE TABLE " + columnUSER + "(" + columnID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + columnNAME + " TEXT, " + columnBDAY +
                " TEXT, " + columnGMAIL + " TEXT, " + columnPassword +" TEXT, "+ columnPHONE + " TEXT, " + columnGENDER + " TEXT)";
        db.execSQL(makeTable);

        String makeProductTable = "CREATE TABLE " + columnPRODUCT + "(" +
                columnProductId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                columnProductName + " TEXT, " +
                columnPicture + " INTEGER, " +
                columnInfo + " TEXT, " +
                columnPrice + " TEXT, " +
                columnAccountId + " INTEGER, " +
                "FOREIGN KEY (" + columnAccountId + ") REFERENCES " +
                columnUSER + "(" + columnID + ")" +
                ");";

        db.execSQL(makeProductTable);

    }

        public List<Cart.Product> populateProductList() {
            SQLiteDatabase db = getReadableDatabase();
            String[] projection = {
                    columnProductName,
                    columnPicture,
                    columnInfo,
                    columnPrice,
                    columnAccountId
            };

            String selection = columnAccountId + " = ?";
            String[] selectionArgs = { String.valueOf(getCurrentId())};

            Cursor cursor = db.query(
                    columnPRODUCT,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String productName = cursor.getString(0);
                    int imageResource = cursor.getInt(1);
                    String description = cursor.getString(2);
                    String price = cursor.getString(3);

                    // Create a new Product object with retrieved data
                    Cart.Product product = new Cart.Product(productName, imageResource, description, price);

                    // Add the product to the ProductList
                    productList.add(product);

                } while (cursor.moveToNext());
                cursor.close();
            }

            db.close();




            return productList;
        }




    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public boolean addToCart(String name, int productImage, String description, String price) {
        SQLiteDatabase db = getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(columnProductName, name);
        values.put(columnPicture, productImage);
        values.put(columnInfo, description);
        values.put(columnPrice, price);
        values.put(columnAccountId,getCurrentId());


        long result = db.insert(columnPRODUCT, null, values);
        db.close();

        return result != -1;
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








    public int getCurrentId(){
        int id = 0;

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {columnID};
        String selection = columnGMAIL + " = ?";
        String[] selectionArgs = {userGmail};

        Cursor cursor =  db.query(columnUSER, projection, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
          currId = cursor.getInt(0);
        }
       return currId;
    }




}
