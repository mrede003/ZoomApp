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
    private static final String COL_2="TITLE";
    private static final String COL_3="BODY";
    private static final String COL_4="DATE";

    private static DatabaseHelper sInstance;
    private SQLiteDatabase db;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
        db=getWritableDatabase();
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT); ");
        } catch (Exception e) {
            System.out.println("shit aint working");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name)
    {
        ContentValues cv=new ContentValues();
        cv.put(COL_2, name);
        if(db.insert(TABLE_NAME, null, cv)==-1) {
            return false;
        } else{
            return true;
        }
    }
    public Cursor getAllData()
    {
        return this.getWritableDatabase()
                .rawQuery("select * from "+TABLE_NAME, null);
    }
}
