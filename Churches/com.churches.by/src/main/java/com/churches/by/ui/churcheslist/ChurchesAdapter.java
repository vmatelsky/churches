package com.churches.by.ui.churcheslist;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.churches.by.R;
import com.churches.by.data.model.Church;

import java.util.List;

public class ChurchesAdapter extends RecyclerView.Adapter<ChurchListItemViewHolder> {
    private List<Church> dataSet;
    private final ChurchListItemViewHolder.OnClickListener listener;

    public ChurchesAdapter(List<Church> dataSet, ChurchListItemViewHolder.OnClickListener listener) {
        this.dataSet = dataSet;
        this.listener = listener;
    }

    @Override
    public ChurchListItemViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.church_list_row_item, parent, false);
        return new ChurchListItemViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(ChurchListItemViewHolder holder, int position) {
        holder.bindChurch(dataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}