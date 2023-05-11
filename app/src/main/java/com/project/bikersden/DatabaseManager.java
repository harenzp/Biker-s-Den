package com.project.bikersden;


import android.database.sqlite.*;
import android.content.*;

import androidx.annotation.Nullable;

public class DatabaseManager extends SQLiteOpenHelper{


    public static final String columnUSER = "user";
    public static final String columnID = "ID";
    public static final String columnNAME = "name";
    public static final String columnBDAY = "bday";
    public static final String columnGMAIL = "gmail";
    public static final String columnPHONE = "phone";
    public static final String columnGENDER = "gender";

    public DatabaseManager(@Nullable Context context) {
       super(context,  "accountuser.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String makeTable = "CREATE TABLE " + columnUSER + "(" + columnID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + columnNAME + " TEXT, " + columnBDAY +
                " TEXT, " + columnGMAIL + " TEXT, " + columnPHONE + " TEXT, " + columnGENDER + " TEXT)";
        db.execSQL(makeTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public String addAccount(String name, String bday, String gmail, String phone, String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(columnNAME, name);
        values.put(columnBDAY, bday);
        values.put(columnGMAIL, gmail);
        values.put(columnPHONE, phone);
        values.put(columnGENDER, gender);

        long result = db.insert(columnUSER,null,values);
            if(result == -1){
              return "Failed";
            }
            return "Success";
    }

}
