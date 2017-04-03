package com.mrede003.zoomwireless.zoomapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class Promos extends Activity {

    private ArrayList<PromoObj> promoList;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promos);
        Helper.setBlackStatus(this);
        promoList=PromoObjList.getInstance().getPromos();
        lv= (ListView) findViewById(R.id.promoListView);
        setListViewFire();
        setPromoImage();
    }


    public void setListViewFire()
    {
        LazyPromoAdapter lazy=new LazyPromoAdapter(this,PromoObjList.getInstance().getPromos());
        lv.setAdapter(lazy);

    }

    public void setPromoImage()
    {
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
