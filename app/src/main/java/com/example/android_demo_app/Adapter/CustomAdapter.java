package com.example.android_demo_app.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android_demo_app.ModelClass.PlaceDetails;
import com.example.android_demo_app.R;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Context context; //context
    private ArrayList<PlaceDetails> itemList; //data source of the list adapter
    public static CustomAdapter.OnItemClickListener mItemClickListener;

    //public constructor
    public CustomAdapter(Context context, ArrayList<PlaceDetails> itemList) {
        this.context = context;
        this.itemList = itemList;

    }

    @Override
    public int getCount() {
        return itemList.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.sub_list_view, parent, false);
        }

        //get current item to be displayed
        PlaceDetails placeItem = (PlaceDetails) getItem(position);

        //get the TextView for item name and item description
        TextView title = convertView.findViewById(R.id.title_vew);
        TextView description = convertView.findViewById(R.id.description_view);
        // get the reference of ImageView
        ImageView image = convertView.findViewById(R.id.image_view);

        if (placeItem.getDescription() == null) {
            description.setText("No Data ");
        } else {
            description.setText(placeItem.getDescription());
        }
        if (placeItem.getTitle() == null) {
            title.setText("No Data ");
        } else {
            title.setText(placeItem.getTitle());
        }
        if (placeItem.getImageHref() == null) {
        //Do nothing
        } else {
            Glide.with(context)
                    .load(placeItem.getImageHref())
                    .into(image);
        }

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(v, position);
            }
        });
        // returns the view for the current row
        return convertView;
    }

    public interface OnItemClickListener {

        public void onItemClick(View view, int position);

    }

    public void SetOnItemClickListener(CustomAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

}
