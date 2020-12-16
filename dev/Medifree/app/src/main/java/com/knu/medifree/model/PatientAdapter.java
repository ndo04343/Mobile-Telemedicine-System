package com.knu.medifree.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


import com.knu.medifree.DHomeActivity;
import com.knu.medifree.R;
import com.knu.medifree.ResCheckActivity;
import com.knu.medifree.util.DBManager;

import java.util.ArrayList;

import static org.webrtc.ContextUtils.getApplicationContext;

public class PatientAdapter extends ArrayAdapter  {

    public PatientAdapter(Context context, ArrayList users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_request_item, parent, false);
        }

        // Get the data item for this position
        Patient patient = (Patient) getItem(position);
        // Lookup view for data population


        TextView listview_request_item_name = (TextView) convertView.findViewById(R.id.listview_request_item_name);
        TextView listview_request_item_time = (TextView) convertView.findViewById(R.id.listview_request_item_time);
        // Populate the data into the template view using the data object
        listview_request_item_name.setText(patient.getName());
        listview_request_item_time.setText(patient.getTime());
        // Return the completed view to render on screen


        return convertView;
    }

}
