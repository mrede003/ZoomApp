package com.mrede003.zoomwireless.zoomapp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


/**
 * Created by mrede003 on 2/24/17.
 */
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class LazyAdapter extends BaseAdapter {
    private Activity activity;
    private static LayoutInflater inflater=null;
    private ArrayList<Store> theList;

    public LazyAdapter(Activity a, ArrayList<Store> theList) {
        this.theList=theList;
        activity = a;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return theList.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row, null);

        TextView title = (TextView)vi.findViewById(R.id.title); // title
        TextView artist = (TextView)vi.findViewById(R.id.artist); // artist name
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image

        // Setting all values in listview

        title.setText(theList.get(position).getName());
        artist.setText(theList.get(position).getAddress());
        Picasso.with(activity)
                .load(theList.get(position).getStoreImg())
                .into(thumb_image);
        return vi;
    }
}

