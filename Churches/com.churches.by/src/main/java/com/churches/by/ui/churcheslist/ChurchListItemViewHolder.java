package com.churches.by.ui.churcheslist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.churches.by.R;
import com.churches.by.data.model.Church;

public class ChurchListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final OnClickListener listener;
    private final ImageView imageView;
    private final TextView textView;
    private final TextView address;

    private Church church;

    public ChurchListItemViewHolder(View rowView, OnClickListener listener) {
        super(rowView);
        this.listener = listener;
        rowView.setOnClickListener(this);
        imageView = (ImageView) rowView.findViewById(R.id.church_image);
        textView = (TextView) rowView.findViewById(R.id.church_name);
        address = (TextView) rowView.findViewById(R.id.church_address);
    }

    public void bindChurch(Church church) {
        this.church = church;

        imageView.setImageBitmap(church.smallIcon());
        textView.setText(church.name());
        address.setText(church.address().toString());
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onChurchClicked(church);
        }
    }

    public interface OnClickListener {
        void onChurchClicked(Church church);
    }
}
