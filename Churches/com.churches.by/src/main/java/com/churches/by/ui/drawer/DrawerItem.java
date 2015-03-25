package com.churches.by.ui.drawer;

import com.churches.by.R;

public enum DrawerItem {

    FAVORITES(R.drawable.ic_action_star_10, R.string.nav_drawer_favorites),
    LIST(R.drawable.ic_action_list_2, R.string.nav_drawer_list),
    MAP(R.drawable.ic_action_globe, R.string.nav_drawer_map),
    SETTINGS(R.drawable.ic_action_settings, R.string.nav_drawer_settings),
    ABOUT(0, R.string.nav_drawer_about);

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
