package com.churches.by;

import android.test.InstrumentationTestCase;

import com.churches.by.data.FavoritesManager;
import com.churches.by.data.model.Church;
import com.churches.by.data.model.receivers.ChurchesReceiver;
import com.churches.by.helpers.TestCaseWithMockito;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FavoritesManagerTest extends TestCaseWithMockito {

    private FavoritesManager manager;

    @Override
    protected void setUp() {
        super.setUp();
        manager = FavoritesManager.instance();
    }

    public void testGetNotNullFavorites() {
        List<Church> favorites = manager.favorites();
        assertNotNull(favorites);
    }

    public void testGetFavoritesAsync() {
        ChurchesReceiver favoritesObtainer = mock(ChurchesReceiver.class);
        manager.favoritesAsync(favoritesObtainer);
        verify(favoritesObtainer);
    }
}
