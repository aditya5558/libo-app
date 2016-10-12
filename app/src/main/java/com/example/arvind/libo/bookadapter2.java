package com.example.arvind.libo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gayathri on 10/12/2016.
 */

public class bookadapter2 extends ArrayAdapter<Book> {
    ArrayList<Book> users;
    public bookadapter2 (Context context, ArrayList<Book> users) {
        super(context, 0, users);
        this.users= users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Book user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_bookadapter2, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
       // TextView third = (TextView) convertView.findViewById(R.id.third);
        // Populate the data into the template view using the data object
        tvName.setText(user.id);
        tvHome.setText(user.name);
//        if(user.is_issued.equals("0000"))
//        {
//            third.setText("Available");
//        }
//        else
//        {
//            third.setText("Issued");
//        }
//         Return the completed view to render on screen
        return convertView;
    }

    @Override
    public int getCount() {
        return users.size();
    }
}
