package com.vla3089.functional;

public interface Subscription<Type> {

    void subscribe(Type listener);

    void unSubscribe(Type listener);

}
