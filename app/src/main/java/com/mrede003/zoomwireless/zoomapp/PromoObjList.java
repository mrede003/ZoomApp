package com.mrede003.zoomwireless.zoomapp;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by mrede003 on 2/27/17.
 */

public class PromoObjList {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private static PromoObjList singleton;
    private ArrayList<PromoObj> promos;

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private PromoObjList() {
        promos=new ArrayList<>();
        fireBaseDown();
    }

    /* Static 'instance' method */
    public static PromoObjList getInstance( ) {
        if (singleton == null) {
            singleton = new PromoObjList();
        }
        return singleton;
    }
    public ArrayList<PromoObj> getPromos()
    {
        return promos;
    }
    private void fireBaseDown()
    {
        database=FirebaseDatabase.getInstance();
        myRef = database.getReference("promos");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                PromoObj value=dataSnapshot.getValue(PromoObj.class);
                promos.add(value);
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
