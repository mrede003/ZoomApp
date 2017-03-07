package com.mrede003.zoomwireless.zoomapp;

import android.content.Context;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by mrede003 on 3/6/17.
 */

public class CompanyList {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private static CompanyList singleton;
    private Company company;

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private CompanyList() {
        fireBaseDown();
    }
    public Company getCompany()
    {
        return company;
    }

    /* Static 'instance' method */
    public static CompanyList getInstance( ) {
        if (singleton == null) {
            singleton = new CompanyList();
        }
        return singleton;
    }
    private void fireBaseDown()
    {
        database=FirebaseDatabase.getInstance();
        myRef = database.getReference("company");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                company=dataSnapshot.getValue(Company.class);
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
