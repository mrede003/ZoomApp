package com.mrede003.zoomwireless.zoomapp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private StoreList s;
    private PromoObjList p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        s=StoreList.getInstance();
        p=PromoObjList.getInstance();
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
    public void openLocations(View view)
    {
        Intent intent=new Intent(this, Locations.class);
        startActivity(intent);
    }
    public void openStore(View view)
    {
        Intent intent=new Intent(this, StoreView.class);
        startActivity(intent);
    }


}
