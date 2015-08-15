package com.churches.by.ui.schedule;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.churches.by.R;
import com.churches.by.data.model.ScheduleItem;

public class ScheduleItemVH extends RecyclerView.ViewHolder {

    private final TextView textView;

    public ScheduleItemVH(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.schedule_item);
    }

    public void bindChurchEvent(int position, ScheduleItem item) {
        final String string = textView.getContext().getString(R.string.schedule_item_template, position, item.item());
        textView.setText(string);
    }
}
