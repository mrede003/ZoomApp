package com.mrede003.zoomwireless.zoomapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }
    public void openPromos(View view)
    {
        Intent intent=new Intent(this, Promos.class);
        startActivity(intent);
    }
    public void openDataTest(View vew)
    {
        Intent intent=new Intent(this, DataTest.class);
        startActivity(intent);
    }
    public void singletonTest(View view)
    {
        DatabaseHelper db=DatabaseHelper.getInstance(this);
        boolean b=db.insertData("Main Activity");
        if(b==true){
            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
        }
    }
}
