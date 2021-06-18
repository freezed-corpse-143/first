package com.example.first;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter {
    public MyAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Rate> list) {
        super(context, resource, list);
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        Rate rateItem = (Rate)getItem(position);
        TextView title = (TextView) itemView.findViewById(R.id.ItemTitle);
        TextView detail = (TextView) itemView.findViewById(R.id.ItemDetail);
        title.setText(rateItem.getName());
        detail.setText(rateItem.getRate());
        return itemView;
    }
}
