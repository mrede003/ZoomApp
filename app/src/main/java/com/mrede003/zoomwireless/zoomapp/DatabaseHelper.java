package com.mrede003.zoomwireless.zoomapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mrede003 on 2/14/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME="notifications.db";
    private static final String TABLE_NAME="notifications_table";
    private static final String COL_1="ID";
    private static final String COL_2="PIC";
    private static final String COL_3="RAN";
    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,PIC INTEGER,RAN INTEGER); ");
        } catch (Exception e) {
            System.out.println("shit aint working");
        }
        ContentValues values = new ContentValues();
        values.put(COL_2, 0);       //0 indicates to run the first picture
        values.put(COL_3, 0);       //0 indicates the app was just installed has never ran before
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);
    }
    public boolean incrementPic()
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2, 2);
        return -1!=db.insert(TABLE_NAME, null, contentValues);

    }
    public int getShop(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] { COL_2 }, COL_1 + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        return Integer.parseInt(cursor.getString(0));
    }


}
