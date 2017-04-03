package com.mrede003.zoomwireless.zoomapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback  {
    private boolean alreadyRan=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StoreList.getInstance();
        PromoObjList.getInstance();
        CompanyList.getInstance();
        if(!alreadyRan)
        {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        alreadyRan=true;
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        Helper.setBlackStatus(this);
        getLocationPermission();
    }
    public void openPromos(View view)
    {
        Intent intent=new Intent(this, Promos.class);
        startActivity(intent);
    }
    public void openAppointment(View view)
    {
        Intent intent=new Intent(this, Appointment.class);
        startActivity(intent);
    }
    public void openLocations(View view)
    {
        Helper.setCurrentLocation(this);
        Intent intent=new Intent(this, Locations.class);
        startActivity(intent);
    }
    public void openFacebook(View view)
    {
        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
        String facebookUrl = getFacebookPageURL(this);
        facebookIntent.setData(Uri.parse(facebookUrl));
        startActivity(facebookIntent);
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
    public void openTwitter(View view)
    {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + CompanyList.getInstance().getCompany().getTwitter_username())));
        }catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/#!/" + CompanyList.getInstance().getCompany().getTwitter_username())));
        }
    }

    public void getLocationPermission()
    {
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionCheck== PackageManager.PERMISSION_GRANTED)
        {
            statusCheck();
        }else{
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    statusCheck();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Some features will not work, please enable the Location permission", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Some features will not work without location, enable now?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
}




