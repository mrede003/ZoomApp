package com.mrede003.zoomwireless.zoomapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.GnssStatus;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import static android.location.GpsStatus.GPS_EVENT_STARTED;
import static android.location.GpsStatus.GPS_EVENT_STOPPED;

public class Locations extends AppCompatActivity{
    private ListView locationList;
    private LazyAdapter lazy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
        locationList = (ListView) findViewById(R.id.locationList);
        getSupportActionBar().hide();
        Helper.setBlackStatus(this);
        LazyAdapter lazy=new LazyAdapter(this,StoreList.getInstance().getStores());
        locationList.setAdapter(lazy);
        setStore();
    }

    public void setStore() {
        locationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(Locations.this, StoreView.class);
                intent.putExtra("IMG_NAME", StoreList.getInstance().getStores().get(position).getStoreImg());
                intent.putExtra("DESCRIPTION", StoreList.getInstance().getStores().get(position).getBio());
                intent.putExtra("ADDRESS", StoreList.getInstance().getStores().get(position).getAddress());
                intent.putExtra("PHONE_NUMBER", StoreList.getInstance().getStores().get(position).getPhoneNumber());
                startActivity(intent);
            }
        });
    }

}

