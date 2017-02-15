package com.mrede003.zoomwireless.zoomapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DataTest extends AppCompatActivity {

    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_test);
        db= DatabaseHelper.getInstance(this);
    }
    public void insertName(View view)
    {
        EditText editText = (EditText) findViewById(R.id.insertbutton);
        boolean b=db.insertData(editText.getText().toString());
        if(b==true){
                Toast.makeText(DataTest.this, "Data Inserted", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(DataTest.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
        }
    }
}
