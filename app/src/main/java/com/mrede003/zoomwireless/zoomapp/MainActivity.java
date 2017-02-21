package com.mrede003.zoomwireless.zoomapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        Helper.setBlackStatus(this);
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

}
