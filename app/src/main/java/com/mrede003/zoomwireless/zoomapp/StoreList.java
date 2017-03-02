package com.mrede003.zoomwireless.zoomapp;

import android.content.Context;
import android.location.LocationManager;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import im.delight.android.location.SimpleLocation;

/**
 * Created by mrede003 on 2/21/17.
 */

public class StoreList {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private static StoreList singleton;
    private ArrayList<Store> stores;

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private StoreList() {
        stores=new ArrayList<>();
        fireBaseDown();
    }

    /* Static 'instance' method */
    public static StoreList getInstance( ) {
        if (singleton == null) {
            singleton = new StoreList();
        }
        return singleton;
    }
    public ArrayList<Store> getStores()
    {
        return stores;
    }
    private void fireBaseDown()
    {
        database=FirebaseDatabase.getInstance();
        myRef = database.getReference("stores");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Store value=dataSnapshot.getValue(Store.class);
                value.getStaff().remove(0);     //cheap fix, need to figure out why firebase is bringing in null for first value in staff list
                stores.add(value);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void setDistanceAway(Context context)
    {
        LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled=true;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {}

        if(!gps_enabled) return;
        SimpleLocation location = new SimpleLocation(context);
        location.beginUpdates();
        final double latitude = location.getLatitude();
        final double longitude = location.getLongitude();
        if(latitude==0.0&&longitude==0.0) return;
        for(int i=0; i<stores.size(); i++) {
            double x = location.calculateDistance(latitude, longitude, stores.get(i).getLatitude(),
                    stores.get(i).getLongitude());
            stores.get(i).setMilesAway(x/1609.34);
        }
        location.endUpdates();

        //insertion sort cause its I cant imagine he'll ever have more than 1000 stores.

        Store temp;
        for (int i = 1; i < stores.size(); i++) {
            for(int j = i ; j > 0 ; j--){
                if(stores.get(j).getMilesAway()< stores.get(j-1).getMilesAway()){
                    temp = stores.get(j);
                    stores.set(j,stores.get(j-1));
                    stores.set(j-1, temp);
                }
            }
        }

    }
}
