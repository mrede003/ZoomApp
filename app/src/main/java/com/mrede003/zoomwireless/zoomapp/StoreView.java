package com.mrede003.zoomwireless.zoomapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class StoreView extends AppCompatActivity {
    private ImageView storeMainView;
    private TextView addressBar;
    private TextView bio;
    private String DESCRIPTION;
    private String IMG_NAME;
    private String ADDRESS;
    private String PHONE_NUMBER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_view);
        getSupportActionBar().hide();
        Helper.setBlackStatus(this);
        setAddressBar();
        Intent in= getIntent();
        Bundle b = in.getExtras();

        if(b!=null)
        {
            DESCRIPTION=(String) b.get("DESCRIPTION");
            ADDRESS=(String) b.get("ADDRESS");
            IMG_NAME =(String) b.get("IMG_NAME");
            PHONE_NUMBER=(String) b.get("PHONE_NUMBER");
        }
        storeMainView=(ImageView) findViewById(R.id.storeViewMainImg);
        addressBar=(TextView) findViewById(R.id.storeViewAddress);
        bio=(TextView) findViewById(R.id.storeViewBio);
        Picasso.with(this)
                .load(IMG_NAME)
                .into(storeMainView);
        addressBar.setText(ADDRESS);
        bio.setText(DESCRIPTION);
    }
    public void setAddressBar()
    {
        final ImageView iv = (ImageView)findViewById(R.id.storeViewMainImg);
        final TextView tv2= (TextView)findViewById(R.id.storeViewAddress);
        ViewTreeObserver vto = iv.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                iv.getViewTreeObserver().removeOnPreDrawListener(this);
                int height = iv.getMeasuredHeight();
                height=height-tv2.getMeasuredHeight();
                RelativeLayout.LayoutParams parameter =  (RelativeLayout.LayoutParams) tv2.getLayoutParams();
                parameter.setMargins(parameter.leftMargin, height, parameter.rightMargin, parameter.bottomMargin); // left, top, right, bottom
                tv2.setLayoutParams(parameter);
                return true;
            }
        });
    }
    public void callStore(View view)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+PHONE_NUMBER));
        startActivity(intent);
    }
    public void navigate(View view)
    {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("google.navigation:q="+ADDRESS));
        startActivity(intent);
    }
}
