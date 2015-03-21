package com.churches.by.ui.details;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.churches.by.R;
import com.churches.by.data.model.ChurchEvent;

import java.util.List;

public class ChurchEventAdapter extends RecyclerView.Adapter<ChurchEventViewHolder> {

    private List<ChurchEvent> dataSet;

    public ChurchEventAdapter(List<ChurchEvent> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public ChurchEventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.church_event_row, parent, false);
        return new ChurchEventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ChurchEventViewHolder holder, int position) {
        holder.bindChurchEvent(dataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
