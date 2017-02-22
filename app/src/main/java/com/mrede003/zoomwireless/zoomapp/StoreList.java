package com.mrede003.zoomwireless.zoomapp;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

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
}
