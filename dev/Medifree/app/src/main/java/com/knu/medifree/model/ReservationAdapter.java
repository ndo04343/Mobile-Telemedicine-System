package com.knu.medifree.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.knu.medifree.R;

import java.util.ArrayList;

public class ReservationAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<Reservation> sample;

    public ReservationAdapter(Context context, ArrayList<Reservation> data) {
        mContext = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Reservation getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.listview_reservations, null);

        TextView patient_id = (TextView)view.findViewById(R.id.lay_res_patient_id);
        TextView doctor_id = (TextView)view.findViewById(R.id.lay_res_doctor_id);
        TextView date = (TextView)view.findViewById(R.id.lay_res_date);

        patient_id.setText("예약자 : " + sample.get(position).getPatient_name());
        doctor_id.setText("의사성명 : "+ sample.get(position).getDoctor_name());
        date.setText(sample.get(position).getDate());

        return view;
    }
}