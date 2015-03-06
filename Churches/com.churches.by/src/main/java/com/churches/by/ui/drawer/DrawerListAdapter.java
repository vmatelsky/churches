package com.churches.by.ui.drawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class DrawerListAdapter extends ArrayAdapter<DrawerItem> {

    private final LayoutInflater mInflater;

    public DrawerListAdapter(Context context, DrawerItem[] drawerItems) {
        super(context, android.R.layout.simple_list_item_activated_1, drawerItems);

        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            view = getItem(position).getView(mInflater, null);
        } else {
            view = convertView;
        }

        return view;
    }
}
