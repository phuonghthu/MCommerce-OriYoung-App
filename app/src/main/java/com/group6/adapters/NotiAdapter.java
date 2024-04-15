package com.group6.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.group6.oriyoung.R;

import java.util.ArrayList;

public class NotiAdapter extends BaseAdapter {

    Activity context;
    int item_noti;
    private ArrayList<ArrayList<String>> notifications;

    public NotiAdapter(Activity context, int item_noti, ArrayList<ArrayList<String>> notifications) {
        this.context = context;
        this.item_noti = item_noti;
        this.notifications = notifications;
    }

    @Override
    public int getCount() {
        return notifications.size();
    }

    @Override
    public Object getItem(int position) {
        return notifications.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_noti, parent, false);
            holder = new ViewHolder();
            holder.notiTitle = convertView.findViewById(R.id.notiTitle);
            holder.notiTime = convertView.findViewById(R.id.notiTime);
            holder.notiContent = convertView.findViewById(R.id.notiContent);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ArrayList<String> notification = notifications.get(position);
        holder.notiTitle.setText(notification.get(0));
        holder.notiTime.setText(notification.get(1));
        holder.notiContent.setText(notification.get(2));
        return convertView;
    }

    public static class ViewHolder {
        TextView notiTitle, notiTime, notiContent;
    }
}