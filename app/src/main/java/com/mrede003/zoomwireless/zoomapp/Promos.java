package com.mrede003.zoomwireless.zoomapp;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class Promos extends ListActivity {

    private ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promos);


        DatabaseHelper db=DatabaseHelper.getInstance(this);
        Cursor cursor=db.getAllData();
        if(cursor.getCount()==0) {
            Toast.makeText(Promos.this, "No data", Toast.LENGTH_LONG).show();
        }else{
            list=new ArrayList<>();
            while(cursor.moveToNext())
            {
                list.add(cursor.getString(0)+" "+cursor.getString(1));
            }
            ArrayAdapter<String> adapter=new ArrayAdapter<>(getListView().getContext(), android.R.layout.simple_list_item_1, list);
            getListView().setAdapter(adapter);
        }
    }

}
