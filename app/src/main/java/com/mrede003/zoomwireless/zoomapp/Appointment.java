package com.mrede003.zoomwireless.zoomapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Appointment extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private int mHour;
    private int mMinute;
    private String selectedStore;
    private String selectedRep;
    private String managerName;
    private String managerEmail;
    private String emailSubject;
    private String emailBody;
    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;
    private EditText timeView;
    private EditText dateView;
    private EditText nameView;
    private EditText phoneView;
    private EditText textArea;
    private StoreList s;
    private Spinner storeDropDown;
    private Spinner repDropDown;

    private final String fromEmail="mrede003@gmail.com";
    private final String fromPassword="Eddieboy45#";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        getSupportActionBar().hide();
        Helper.setBlackStatus(this);
        s=StoreList.getInstance();

        nameView= (EditText)findViewById(R.id.apptNameView);
        phoneView=(EditText)findViewById(R.id.apptPhoneView);
        textArea=(EditText)findViewById(R.id.apptDescriptionText);
        storeDropDown = (Spinner)findViewById(R.id.storeDropMenu);
        repDropDown = (Spinner)findViewById(R.id.employeeDropMenu);
        nameView.setInputType(InputType.TYPE_CLASS_TEXT);
        timeView=(EditText) findViewById(R.id.apptTimeView);
        dateView=(EditText) findViewById(R.id.apptDateView);
        selectedStore="";
        selectedRep="";
        managerName="";
        managerEmail="";
        setSpinners();
        myCalendar = Calendar.getInstance();
        showDatePickerDialog();

    }
    public void setSpinners()
    {
        String[] makeSelection=new String[1];
        makeSelection[0]="Please Choose a Store First";
        repDropDown.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, makeSelection));

        ArrayList<String> names=new ArrayList<>();
        names.add("Please Select a Store");
        for(int i=0; i<s.getStores().size();i++)
        {
            names.add(s.getStores().get(i).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, names);
        storeDropDown.setAdapter(adapter);
        storeDropDown.setSelection(0, false);
        storeDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView adapter, View v, int i, long lng) {
                if(i!=0) {
                    selectedStore = adapter.getItemAtPosition(i).toString();

                    setRepDropDown(i);
                }else{
                    selectedStore="";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {

            }
        });
    }
    public void setRepDropDown(int i)
    {
        ArrayList<String> staff=new ArrayList<>();
        staff.clear();
        staff.add(0, "Please Select a Rep");
        managerName=s.getStores().get(i-1).getManagerName();
        managerEmail=s.getStores().get(i-1).getEmail();
        staff.add(1,managerName+" (Store Manager)");
        staff.addAll(s.getStores().get(i-1).getStaff());
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, staff);
        repDropDown.setAdapter(adapter2);
        repDropDown.setSelection(0, false);
        repDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView adapter, View v, int i, long lng) {
                if(i!=0) {
                    selectedRep = adapter.getItemAtPosition(i).toString();
                }else{
                    selectedRep="";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {

            }
        });

    }
    public void showTimePickerDialog(View v) {
        TimePickerFragment newFragment = new TimePickerFragment();
        String tag="tag";
        newFragment.show(getSupportFragmentManager(), tag);
    }
    public void setAppointment(View view)
    {
        if(nameView.getText().toString().equals(null)||nameView.getText().toString().equals("")) {
            Helper.displayCenterToast(this, "Please enter your first and last name above.", Toast.LENGTH_SHORT);
            return;
        }
        if(dateView.getText().toString().equals(null)||dateView.getText().toString().equals("")) {
            Helper.displayCenterToast(this, "Please select a date above.", Toast.LENGTH_SHORT);
            return;
        }
        if(phoneView.getText().toString().equals(null)||phoneView.getText().toString().equals("")
                ||phoneView.getText().toString().length()<10) {
            Helper.displayCenterToast(this, "Please enter your 10 digit phone number.", Toast.LENGTH_SHORT);
            return;
        }
        if(timeView.getText().toString().equals(null)||timeView.getText().toString().equals("")) {
            Helper.displayCenterToast(this, "Please select a desired time.", Toast.LENGTH_SHORT);
            return;
        }
        if(selectedStore.equals(null)||selectedStore.equals(""))
        {
            Helper.displayCenterToast(this, "Please select a desired store.", Toast.LENGTH_SHORT);
            return;
        }
        if(selectedRep.equals(null)||selectedRep.equals(""))
        {
            Helper.displayCenterToast(this, "Please select a desired representative.", Toast.LENGTH_SHORT);
            return;
        }
        if(textArea.getText().toString().equals(null)||textArea.getText().toString().equals("")) {
            Helper.displayCenterToast(this, "Please give a brief description for you visit.", Toast.LENGTH_SHORT);
            return;
        }
        emailSubject="AUTOMATED: Requested Appointment with "+nameView.getText().toString()+" via the ZOOMApp";
        emailBody="Hello "+managerName+",\n" +
                "\n" +
                "\tAn appointment at your store location "+selectedStore+" has been\n" +
                "requested. Please call the customer ASAP to confirm or deny their request.\n" +
                "\n" +
                "NAME: "+nameView.getText().toString()+"\n" +
                "MTN: "+phoneView.getText().toString()+"\n" +
                "Requested Rep: "+selectedRep+"\n" +
                "Requested Date: "+dateView.getText().toString()+"\n" +
                "Requested Time: "+timeView.getText().toString()+"\n" +
                "Reason: "+textArea.getText().toString()+"\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "---------------------------------------------------------------------------- \n" +
                "This is an automated message sent by the Zoom Wireless App (TM).";
        ArrayList<String> toEmailList=new ArrayList<>();
        toEmailList.clear();
        toEmailList.add(managerEmail);
        new SendMailTask(this).execute(fromEmail,
                fromPassword, toEmailList, emailSubject, emailBody);

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
