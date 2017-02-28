package com.mrede003.zoomwireless.zoomapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StoreView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_view);
        getSupportActionBar().hide();
        Helper.setBlackStatus(this);
        setAddressBar();
    }
    public void setAddressBar()
    {
        final ImageView iv = (ImageView)findViewById(R.id.storeViewMainImg);
        final TextView tv = (TextView)findViewById(R.id.storeViewTitle);
        final TextView tv2= (TextView)findViewById(R.id.storeViewAddress);
        ViewTreeObserver vto = iv.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                iv.getViewTreeObserver().removeOnPreDrawListener(this);
                int height = iv.getMeasuredHeight()+tv.getMeasuredHeight();
                height=height-tv2.getMeasuredHeight();
                RelativeLayout.LayoutParams parameter =  (RelativeLayout.LayoutParams) tv2.getLayoutParams();
                parameter.setMargins(parameter.leftMargin, height, parameter.rightMargin, parameter.bottomMargin); // left, top, right, bottom
                tv2.setLayoutParams(parameter);
                return true;
            }
        });
    }
}
