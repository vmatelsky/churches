package com.vla3089.functional;

import java.util.ArrayList;
import java.util.List;

public class BaseSubscription<ListenerType> implements Subscription<ListenerType> {

    private List<ListenerType> listenersList = new ArrayList<>();

    public void subscribe(ListenerType listener) {
        if (!listenersList.contains(listener)) {
            listenersList.add(listener);
        }
    }

    public void unSubscribe(ListenerType listener) {
        if (listenersList.contains(listener)) {
            listenersList.remove(listener);
        }
    }

    public void notifyAll(Action action) {
        for(ListenerType listener : listenersList) {
            action.perform(listener);
        }
    }

    protected abstract class Action<ListenerType> {
        public abstract void perform(ListenerType listenerType);
    }

}
