package com.churches.by.ui.schedule;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.churches.by.R;
import com.churches.by.data.model.ScheduleItem;

import java.util.List;

public class ScheduleItemAdapter extends RecyclerView.Adapter<ScheduleItemVH> {

    private List<ScheduleItem> dataSet;

    public ScheduleItemAdapter(List<ScheduleItem> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public ScheduleItemVH onCreateViewHolder(ViewGroup parent, int position) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.schedule_item_row, parent, false);
        return new ScheduleItemVH(v);
    }

    @Override
    public void onBindViewHolder(ScheduleItemVH holder, int position) {
        holder.bindChurchEvent(position, dataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
