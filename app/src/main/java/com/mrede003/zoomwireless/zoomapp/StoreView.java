package com.mrede003.zoomwireless.zoomapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    private String GOOGLE_RATE_LINK;

    private TextView mon;
    private TextView tues;
    private TextView wed;
    private TextView thur;
    private TextView fri;
    private TextView sat;
    private TextView sun;
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
            GOOGLE_RATE_LINK=(String) b.get("GOOGLE_RATE_LINK");
        }
        mon=(TextView) findViewById(R.id.storeHoursMon);
        tues=(TextView) findViewById(R.id.storeHoursTues);
        wed=(TextView) findViewById(R.id.storeHoursWed);
        thur=(TextView) findViewById(R.id.storeHoursThu);
        fri=(TextView) findViewById(R.id.storeHoursFri);
        sat=(TextView) findViewById(R.id.storeHoursSat);
        sun=(TextView) findViewById(R.id.storeHoursSun);

        mon.setText(CompanyList.getInstance().getCompany().getMonThursO()+" - "+
                CompanyList.getInstance().getCompany().getMonThursC());
        tues.setText(CompanyList.getInstance().getCompany().getMonThursO()+" - "+
                CompanyList.getInstance().getCompany().getMonThursC());
        wed.setText(CompanyList.getInstance().getCompany().getMonThursO()+" - "+
                CompanyList.getInstance().getCompany().getMonThursC());
        thur.setText(CompanyList.getInstance().getCompany().getMonThursO()+" - "+
                CompanyList.getInstance().getCompany().getMonThursC());
        fri.setText(CompanyList.getInstance().getCompany().getFriO()+" - "+
                CompanyList.getInstance().getCompany().getFriC());
        sat.setText(CompanyList.getInstance().getCompany().getSatO()+" - "+
                CompanyList.getInstance().getCompany().getSatC());
        sun.setText(CompanyList.getInstance().getCompany().getSunO()+" - "+
                CompanyList.getInstance().getCompany().getSunC());
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
    public void rate(View view)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(GOOGLE_RATE_LINK));
        startActivity(browserIntent);
    }
}
