package com.mrede003.zoomwireless.zoomapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class PromoDisplay extends AppCompatActivity {
    private final int MY_PERMISSIONS_REQUEST_SEND_SMS=1;
    private String IMG_NAME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo_display);
        getSupportActionBar().hide();
        Intent in= getIntent();
        Bundle b = in.getExtras();

        if(b!=null)
        {
            IMG_NAME =(String) b.get("IMG_NAME");
        }
        changePic();
    }
    public void changePic()
    {
        ImageView imageView= (ImageView) findViewById(R.id.promoImage);
        Picasso.with(this)
                .load(IMG_NAME)
            .into(imageView);
    }

    public void sendSMS(String phoneNo, String msg) {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                    Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
        }
    }
    public void extractPhoneInfo(View view)
    {
        EditText e=(EditText) findViewById(R.id.phoneNumberText);
        String testMessage="Hello, if you're receiving this then you are receiving a test message" +
                " from my totally awesome app. #KTHXBYE";
        sendSMS(e.getText().toString(), testMessage);
    }
}
