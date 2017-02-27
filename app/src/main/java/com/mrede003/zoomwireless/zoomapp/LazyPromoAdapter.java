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
 * Created by mrede003 on 2/27/17.
 */

public class LazyPromoAdapter extends BaseAdapter{
    private Activity activity;
    private static LayoutInflater inflater=null;
    private ArrayList<PromoObj> theList;

    public LazyPromoAdapter(Activity a, ArrayList<PromoObj> theList) {
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
            vi = inflater.inflate(R.layout.promo_list_row, null);

        TextView title = (TextView)vi.findViewById(R.id.promoListTitle); // title
        TextView artist = (TextView)vi.findViewById(R.id.promoListExp); // artist name
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.promoListImage); // thumb image

        // Setting all values in listview

        title.setText(theList.get(position).getName());
        artist.setText("Expires: "+theList.get(position).getExpDate());
        Picasso.with(activity)
                .load(theList.get(position).getPreviewImg())
                .into(thumb_image);
        return vi;
    }
}
