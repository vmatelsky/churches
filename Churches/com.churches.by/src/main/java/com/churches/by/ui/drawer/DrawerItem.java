package com.churches.by.ui.drawer;

import com.churches.by.R;

public enum DrawerItem {

    LIST(R.drawable.ic_action_list_2, R.string.nav_drawer_list),
    SETTINGS(R.drawable.ic_action_settings, R.string.nav_drawer_settings),
    ABOUT(R.drawable.ic_action_help, R.string.nav_drawer_about);

    DrawerItem(int iconId, int titleId) {
        this.iconId = iconId;
        this.titleId = titleId;
    }

    private final int iconId;
    private final int titleId;

    public int iconId() {
        return iconId;
    }

    public int titleId() {
        return titleId;
    }
}
