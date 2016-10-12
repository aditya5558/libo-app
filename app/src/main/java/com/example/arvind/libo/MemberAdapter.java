package com.example.arvind.libo;

/**
 * Created by Gayathri on 10/12/2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class MemberAdapter extends ArrayAdapter<Member> {

    ArrayList<Member> users;
    public MemberAdapter(Context context, ArrayList<Member> users) {
        super(context, 0, users);
        this.users= users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Member user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_item_user, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
        TextView third = (TextView) convertView.findViewById(R.id.third);
        TextView fourth = (TextView) convertView.findViewById(R.id.fourth);
        // Populate the data into the template view using the data object
        tvName.setText(user.id);
        tvHome.setText(user.name);
        third.setText(user.email);
        Date x=user.expiry_date;
//        fourth.setText(String.valueOf(x.getDate())+"/"+String.valueOf(x.getMonth())+'/'+String.valueOf(x.getYear()));
        fourth.setText(String.valueOf(x.toLocaleString()));
        // Return the completed view to render on screen
        return convertView;
    }

    @Override
    public int getCount() {
        return users.size();
    }
}