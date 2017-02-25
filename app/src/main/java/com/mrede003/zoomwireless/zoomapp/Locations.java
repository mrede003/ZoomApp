package com.mrede003.zoomwireless.zoomapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class Locations extends AppCompatActivity {
    private ListView locationList;
    private StoreList s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
        locationList=(ListView) findViewById(R.id.locationList);

        s=StoreList.getInstance();
        LazyAdapter lazy=new LazyAdapter(this,s.getStores());
        locationList.setAdapter(lazy);
        Helper.setBlackStatus(this);
    }
}
