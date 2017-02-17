package com.mrede003.zoomwireless.zoomapp;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Promos extends ListActivity {

    private ArrayList<String> promoList=new ArrayList<>();
    private ArrayList<String> promoListImg=new ArrayList<>();
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promos);

        setListViewFire();
        downloadImgURL();
        setPromoImage();
    }
    //Sets up the list view with values from firebase
    public void setListViewFire()
    {
        final ArrayAdapter<String> adapter=new ArrayAdapter<>(getListView().getContext(),
                android.R.layout.simple_list_item_1, promoList);
        getListView().setAdapter(adapter);
        database=FirebaseDatabase.getInstance();
        myRef = database.getReference("Promos");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value=dataSnapshot.getValue(String.class);
                promoList.add(value);
                adapter.notifyDataSetChanged();
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
    public void downloadImgURL()
    {
        database=FirebaseDatabase.getInstance();
        myRef = database.getReference("PromosImg");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value=dataSnapshot.getValue(String.class);
                promoListImg.add(value);
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
    public void setPromoImage()
    {
        lv= (ListView) findViewById(android.R.id.list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(Promos.this, PromoDisplay.class);
                intent.putExtra("IMG_NAME", promoListImg.get(position));
                startActivity(intent);
            }
        });
    }

}
