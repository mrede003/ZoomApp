package com.mrede003.zoomwireless.zoomapp;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Promos extends ListActivity {

    private static String[] promos={"BOGO", "Free Tablet", "$50 Motorola X"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promos);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getListView().getContext(), android.R.layout.simple_list_item_1, promos);
        getListView().setAdapter(adapter);
    }

}
