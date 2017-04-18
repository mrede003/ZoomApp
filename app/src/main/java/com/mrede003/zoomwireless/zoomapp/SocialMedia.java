package com.mrede003.zoomwireless.zoomapp;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
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
        getSupportActionBar().hide();
        Helper.setBlackStatus(this);

        lv= (ListView) findViewById(R.id.socialMediaListView);
        setListView();
        listViewOnClick();
    }


    public void setListView()
    {
        LazySocialMediaAdapter adapter=new LazySocialMediaAdapter(this);
        lv.setAdapter(adapter);
    }
    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;

            boolean activated =  packageManager.getApplicationInfo("com.facebook.katana", 0).enabled;
            if(activated){
                if ((versionCode >= 3002850)) {
                    return "fb://facewebmodal/f?href=" + CompanyList.getInstance().getCompany().getFacebook_url();
                } else {
                    return "fb://page/" + CompanyList.getInstance().getCompany().getFacebook_page_id();
                }
            }else{
                return CompanyList.getInstance().getCompany().getFacebook_url();
            }
        } catch (PackageManager.NameNotFoundException e) {
            return CompanyList.getInstance().getCompany().getFacebook_url();
        }
    }
    public void openGPlus() {
        String profile=CompanyList.getInstance().getCompany().getGoogle_plus_id();
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setClassName("com.google.android.apps.plus",
                    "com.google.android.apps.plus.phone.UrlGatewayActivity");
            intent.putExtra("customAppUri", profile);
            startActivity(intent);
        } catch(ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/"+profile+"/posts")));
        }
    }
    public void openInsta()
    {
        String profile=CompanyList.getInstance().getCompany().getInsta_id();
        Uri uri = Uri.parse("http://instagram.com/_u/"+profile);
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/"+profile)));
        }
    }
    public void openTwitter()
    {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + CompanyList.getInstance().getCompany().getTwitter_username())));
        }catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/#!/" + CompanyList.getInstance().getCompany().getTwitter_username())));
        }
    }

    public void listViewOnClick()
    {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                switch (position){
                    case 0:
                        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                        String facebookUrl = getFacebookPageURL(getApplicationContext());
                        facebookIntent.setData(Uri.parse(facebookUrl));
                        startActivity(facebookIntent);
                        break;
                    case 1:
                        openTwitter();
                        break;

                    case 2:
                        openGPlus();
                        break;
                    case 3:
                        openInsta();
                        break;
                    default:
                        break;
            }}
        });

    }
}
