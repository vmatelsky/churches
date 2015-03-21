package com.churches.by.ui.details;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.churches.by.CAppliation;
import com.churches.by.LocaleManager;
import com.churches.by.R;
import com.churches.by.data.model.ChurchEvent;

import java.text.SimpleDateFormat;

public class ChurchEventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView textView;
    private final TextView address;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("H:mm:ss dd MMMM yyyy", LocaleManager.defaultLocale());

    private ChurchEvent churchEvent;

    public ChurchEventViewHolder(View rowView) {
        super(rowView);
        rowView.setOnClickListener(this);
        textView = (TextView) rowView.findViewById(R.id.church_event_name);
        address = (TextView) rowView.findViewById(R.id.church_event_time);
    }

    public void bindChurchEvent(ChurchEvent churchEvent) {
        this.churchEvent = churchEvent;

        textView.setText(churchEvent.title());
        address.setText(dateFormat.format(churchEvent.startDate()));
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(CAppliation.instance(), churchEvent.title(), Toast.LENGTH_LONG).show();
    }
}
