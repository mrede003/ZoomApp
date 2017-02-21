package com.mrede003.zoomwireless.zoomapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Appointment extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;
    private EditText timeView;
    private EditText dateView;
    private int mHour;
    private int mMinute;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        getSupportActionBar().hide();
        Helper.setBlackStatus(this);
        timeView=(EditText) findViewById(R.id.apptTimeView);
        dateView=(EditText) findViewById(R.id.apptDateView);

        myCalendar = Calendar.getInstance();
        showDatePickerDialog();

    }
    public void showTimePickerDialog(View v) {
        TimePickerFragment newFragment = new TimePickerFragment();
        String tag="tag";
        newFragment.show(getSupportFragmentManager(), tag);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mHour = hourOfDay;
        mMinute = minute;
        if(mHour>11) {
            if(mHour!=12)
                mHour=mHour-12;
            if(mMinute<10){
                timeView.setText(mHour+":0"+mMinute+" PM");
            }else {
                timeView.setText(mHour + ":" + mMinute + " PM");
            }
        }else{
            if(mMinute<10) {
                timeView.setText(mHour + ":0" + mMinute + " AM");
            }else{
                timeView.setText(mHour + ":" + mMinute + " AM");
            }
        }
    }
    private void updateDateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dateView.setText(sdf.format(myCalendar.getTime()));
    }
    private void showDatePickerDialog() {
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateLabel();
            }
        };

        dateView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Appointment.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

}
