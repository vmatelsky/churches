package com.churches.by.ui.drawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.churches.by.R;

public class DrawerListAdapter extends ArrayAdapter<DrawerItem> {

    private final LayoutInflater mInflater;

    public DrawerListAdapter(Context context, DrawerItem[] drawerItems) {
        super(context, R.layout.row_item, drawerItems);

        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        DrawerItem item = getItem(position);

        if (convertView == null) {
            view = mInflater.inflate(R.layout.row_item, null);
        } else {
            view = convertView;
        }

        TextView row = (TextView) view.findViewById(R.id.row_title);
        row.setText(item.titleId());

        ImageView image = (ImageView) view.findViewById(R.id.row_image);
        if (item.iconId() > 0) {
            image.setImageResource(item.iconId());
        }

        return view;
    }
}
