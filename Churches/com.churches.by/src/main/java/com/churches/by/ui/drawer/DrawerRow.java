package com.churches.by.ui.drawer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.churches.by.R;

public class DrawerRow implements DrawerItem {

    private final String title;
    private final int iconId;

    public DrawerRow(int iconId, String title) {
        this.title = title;
        this.iconId = iconId;
    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.row_item, container);

        TextView row = (TextView) view.findViewById(R.id.row_text);
        row.setText(title);

        ImageView image = (ImageView) view.findViewById(R.id.row_image);
        if (iconId > 0) {
            image.setImageResource(iconId);
        }

        return view;
    }

}
