package com.knu.medifree.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ReservationAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> reservations;

    private ViewHolder mViewHolder;

    public ReservationAdapter(Context mContext, ArrayList<String> array_reservations) {
        this.mContext = mContext;
        this.reservations = array_reservations;
    }

    @Override
    public int getCount() {
        return reservations.size();
    }

    @Override
    public Object getItem(int position) {
        return reservations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
//            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_reservation_item, parent, false);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        // View에 Data 세팅
        mViewHolder.txt_name.setText(reservations.get(position));

        return convertView;
    }

    public class ViewHolder {
        private TextView txt_name;

        public ViewHolder(View convertView) {
            txt_name = (TextView) convertView.findViewById(R.id.txt_name);
        }
    }
}