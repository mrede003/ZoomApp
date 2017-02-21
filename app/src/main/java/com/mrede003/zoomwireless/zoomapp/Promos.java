package com.mrede003.zoomwireless.zoomapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class Promos extends ListActivity {

    private final String promoRef="promos";
    private ArrayList<String> nameList=new ArrayList<>();
    private ArrayList<PromoObj> promoList=new ArrayList<>();
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promos);
        Helper.setBlackStatus(this);

        setListViewFire();
        setPromoImage();
    }
    public void setListViewFire()
    {
        final ArrayAdapter<String> adapter=new ArrayAdapter<>(getListView().getContext(),
                R.layout.custom_textview, nameList);
        getListView().setAdapter(adapter);
        database=FirebaseDatabase.getInstance();
        myRef = database.getReference(promoRef);
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                PromoObj value=dataSnapshot.getValue(PromoObj.class);
                promoList.add(value);
                nameList.add(value.getName());
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

    public void setPromoImage()
    {
        lv= (ListView) findViewById(android.R.id.list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(Promos.this, PromoDisplay.class);
                intent.putExtra("IMG_NAME", promoList.get(position).getImgName());
                intent.putExtra("DESCRIPTION", promoList.get(position).getDescription());
                intent.putExtra("EXP_DATE", promoList.get(position).getExpDate());
                startActivity(intent);
            }
        });
    }

}
