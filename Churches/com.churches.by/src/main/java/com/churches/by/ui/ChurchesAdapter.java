package com.churches.by.ui;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.churches.by.R;
import com.churches.by.data.model.Church;

import java.util.List;

public class ChurchesAdapter extends RecyclerView.Adapter<ChurchesAdapter.ViewHolder> {
    private List<Church> dataSet;
    private final ViewHolder.ChurchesViewHolderClicks listener;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ChurchesViewHolderClicks listener;
        public ImageView imageView;
        public TextView textView;
        private Church church;

        public ViewHolder(View rowView, ChurchesViewHolderClicks listener) {
            super(rowView);
            this.listener = listener;
            rowView.setOnClickListener(this);
            imageView = (ImageView) rowView.findViewById(R.id.church_image);
            textView = (TextView) rowView.findViewById(R.id.church_name);
        }

        public void bindChurch(Church church) {
            this.church = church;
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onChurchClicked(church);
            }
        }

        public static interface ChurchesViewHolderClicks {
            public void onChurchClicked(Church church);
        }
    }

    public ChurchesAdapter(List<Church> dataSet, ViewHolder.ChurchesViewHolderClicks listener) {
        this.dataSet = dataSet;
        this.listener = listener;
    }

    @Override
    public ChurchesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.church_list_row_item, parent, false);
        return new ViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Church church = dataSet.get(position);
        holder.textView.setText(church.name());
        holder.bindChurch(church);

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}