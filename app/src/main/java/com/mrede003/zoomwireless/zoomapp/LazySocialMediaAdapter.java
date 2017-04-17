package com.mrede003.zoomwireless.zoomapp;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mrede003 on 4/17/17.
 */

public class LazySocialMediaAdapter extends BaseAdapter{
    private Activity activity;
    private static LayoutInflater inflater=null;

    public LazySocialMediaAdapter(Activity a) {
        activity = a;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return 4;
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

        title.setText("Facebook");
        artist.setText("Like, Follow, and Share!");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            thumb_image.setImageDrawable(activity.getResources().getDrawable(R.drawable.facebook, activity.getApplicationContext().getTheme()));
        } else {
            thumb_image.setImageDrawable(activity.getResources().getDrawable(R.drawable.facebook));
        }
        return vi;
    }
}
