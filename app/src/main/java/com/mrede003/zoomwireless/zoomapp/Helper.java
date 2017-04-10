package com.mrede003.zoomwireless.zoomapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import im.delight.android.location.SimpleLocation;

/**
 * Created by mrede003 on 2/20/17.
 */

public class Helper {
    public static void setBlackStatus(Activity act)
    {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            Window window = act.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(act, R.color.black));
        }
    }
    public static void displayCenterToast(Context c, String message, int length)
    {
        Toast toast = Toast.makeText(c, message, length);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        if( v != null) v.setGravity(Gravity.CENTER);
        toast.show();
    }
    public static void setCurrentLocation(Context c)
    {
        SimpleLocation simpleLocation=new SimpleLocation(c);
        double latitude=-1.0;
        double longitude=-1.0;
        int permissionCheck = ContextCompat.checkSelfPermission(c,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionCheck== PackageManager.PERMISSION_GRANTED)
        {
            if (simpleLocation.hasLocationEnabled()) {
                latitude = simpleLocation.getLatitude();
                longitude = simpleLocation.getLongitude();
            }
        }
        StoreList.getInstance().setLongLat(latitude,longitude, c);
    }
    @TargetApi(21)
    public static Notification getNotification(String content, String title, Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle(title);
        bigTextStyle.bigText(content);
        builder.setStyle(bigTextStyle);
        builder.setSmallIcon(R.drawable.clear_icon);
        Intent resultIntent = new Intent(context, MainActivity.class);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        builder.setContentIntent(resultPendingIntent);
        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(uri);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            builder.setColor(ContextCompat.getColor(context, R.color.zoomRed));

        return builder.build();
    }
}
