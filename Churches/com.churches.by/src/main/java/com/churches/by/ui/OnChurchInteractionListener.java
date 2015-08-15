package com.churches.by.ui;

import com.churches.by.data.model.Church;

public interface OnChurchInteractionListener {
    void onChurchClicked(Church church);

    void onSettingsClicked();
}
