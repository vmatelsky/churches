package com.vla3089.functional;

public class ReceiverSubscription<Type> extends BaseSubscription<Receiver<Type>> implements Receiver<Type> {

    @Override
    public void receive(Type value) {
        notifyAll(new Action<Type>() {
            @Override
            public void perform(Type value) {
                receive(value);
            }
        });
    }

}
