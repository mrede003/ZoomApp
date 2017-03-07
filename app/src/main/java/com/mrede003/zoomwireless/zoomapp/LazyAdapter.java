package com.mrede003.zoomwireless.zoomapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mrede003 on 2/24/17.
 */

public class LazyAdapter extends BaseAdapter{
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
        TextView distanceAway=(TextView) vi.findViewById(R.id.distanceAway);

        // Setting all values in listview
        String distance="";
        if(theList.get(position).getMilesAway()==-1.0)
        {
            distance="N/A";
        }else{
            distance = String.format("%.2f", theList.get(position).getMilesAway());
        }
        distanceAway.setText(distance+" miles");
        title.setText(theList.get(position).getName());
        artist.setText(theList.get(position).getAddress());
        Picasso.with(activity)
                .load(theList.get(position).getStoreImgPre())
                .into(thumb_image);
        return vi;
    }

}

