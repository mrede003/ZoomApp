package com.mrede003.zoomwireless.zoomapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class Locations extends AppCompatActivity {
    private ListView locationList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
        locationList=(ListView) findViewById(R.id.locationList);
        getSupportActionBar().hide();
        Helper.setBlackStatus(this);
        LazyAdapter lazy=new LazyAdapter(this,StoreList.getInstance().getStores());
        locationList.setAdapter(lazy);
    }
}
