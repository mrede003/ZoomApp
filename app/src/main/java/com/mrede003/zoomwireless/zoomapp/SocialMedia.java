package com.mrede003.zoomwireless.zoomapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class SocialMedia extends AppCompatActivity {

    private ArrayList<PromoObj> promoList;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);
        Helper.setBlackStatus(this);
        lv= (ListView) findViewById(R.id.socialMediaListView);
        setListView();
    }


    public void setListView()
    {
        LazySocialMediaAdapter adapter=new LazySocialMediaAdapter(this);
        lv.setAdapter(adapter);
    }

    public void listViewOnClick()
    {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

            }
        });
    }
}
