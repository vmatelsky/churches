package com.churches.by.ui.drawer;

import com.churches.by.R;

public enum DrawerItem {

    FAVORITES(0, R.string.nav_drawer_favorites),
    LIST(0, R.string.nav_drawer_list),
    MAP(0, R.string.nav_drawer_map),
    SETTINGS(0, R.string.nav_drawer_settings),
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
