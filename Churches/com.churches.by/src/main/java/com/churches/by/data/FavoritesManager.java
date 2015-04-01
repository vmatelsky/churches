package com.churches.by.data;

import com.churches.by.data.model.Church;
import com.churches.by.data.model.receivers.ChurchesReceiver;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FavoritesManager {

    private static FavoritesManager instance = new FavoritesManager();

    public static FavoritesManager instance() {
        return instance;
    }

    private FavoritesManager() {

    }

    public List<Church> favorites() {
        return DataProvider.instance().favoritedChurches();
    }

    public void favoritesAsync(ChurchesReceiver favoritesObtainer) {
        DataProvider.instance().favoritedChurchesAsync(favoritesObtainer);
    }
}
