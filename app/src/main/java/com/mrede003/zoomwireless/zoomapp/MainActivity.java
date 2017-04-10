package com.mrede003.zoomwireless.zoomapp;


import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback  {
    private boolean alreadyRan=false;
    private SharedPreferences prefs=null;
    private RelativeLayout mainLayout;
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

        prefs = getSharedPreferences("com.mrede003.zoomwireless.zoomapp", MODE_PRIVATE);
        mainLayout =(RelativeLayout)findViewById(R.id.activity_main);
        setBackGround(prefs);
        getSupportActionBar().hide();
        Helper.setBlackStatus(this);
        getLocationPermission();
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (prefs.getBoolean("firstrun", true))
        {
            if(poorImplementation())
                scheduleNotification(Helper.getNotification(CompanyList.getInstance().getCompany().getNoti_message(),
                        CompanyList.getInstance().getCompany().getNoti_title(),this),
                        CompanyList.getInstance().getCompany().getNoti_delay());
            prefs.edit().putBoolean("firstrun", false).apply();
        }
    }
    //Because firebase is cool and easy, but missing so many useful APIs
    //This method checks to see if the 3 string values required for the notification are null
    //IE: Firebase hasn't downloaded them yet. If so forget the notifications all together
    //Why does it have to be on a seperate thread?????
    //Theres probably a way better way to do this but this will do for now
    public boolean poorImplementation()
    {
        if(CompanyList.getInstance().getCompany().getNoti_message()==null
        ||CompanyList.getInstance().getCompany().getNoti_title()==null
        ||CompanyList.getInstance().getCompany().getNoti_delay()==0)
            return false;
        return true;
    }
    public void setBackGround(SharedPreferences prefs)
    {
        if (prefs.getBoolean("firstrun", true))
        {
            prefs.edit().putInt("background", 1).apply();
        }else {
            int newNum = prefs.getInt("background", 1);
            if (newNum == 4) {
                newNum = 1;
            } else {
                newNum++;
            }
            prefs.edit().putInt("background", newNum).apply();
            Drawable newBackground;
            switch(newNum)
            {
                case 1: newBackground=ContextCompat.getDrawable(this, R.drawable.back_one);
                    break;
                case 2: newBackground=ContextCompat.getDrawable(this, R.drawable.back_two);
                    break;
                case 3: newBackground=ContextCompat.getDrawable(this, R.drawable.back_three);
                    break;
                case 4: newBackground=ContextCompat.getDrawable(this, R.drawable.back_four);
                    break;
                default: newBackground=ContextCompat.getDrawable(this, R.drawable.back_one);
                    break;
            }
            mainLayout.setBackground(newBackground);
        }
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

                    Toast.makeText(this, "Some features will not work, please enable the Location permission", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
    private void scheduleNotification(Notification notification, int delay) {

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
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




