package com.mrede003.zoomwireless.zoomapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PromoDisplay extends AppCompatActivity {
    private String IMG_NAME;
    private String DESCRIPTION;
    private String EXP_DATE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo_display);
        getSupportActionBar().hide();
        Helper.setBlackStatus(this);
        Intent in= getIntent();
        Bundle b = in.getExtras();

        if(b!=null)
        {
            DESCRIPTION=(String) b.get("DESCRIPTION");
            EXP_DATE=(String) b.get("EXP_DATE");
            IMG_NAME =(String) b.get("IMG_NAME");
        }
        TextView descriptionView= (TextView) findViewById(R.id.promoDescriptionView);
        descriptionView.setText(DESCRIPTION+"\n\nExpires: "+EXP_DATE);
        changePic();
    }
    public void changePic()
    {
        ImageView imageView= (ImageView) findViewById(R.id.promoImage);
        Picasso.with(this)
                .load(IMG_NAME)
            .into(imageView);
    }
    public void showLocations(View view)
    {
        Helper.setCurrentLocation(this);
        Intent intent=new Intent(this, Locations.class);
        startActivity(intent);
    }

}
