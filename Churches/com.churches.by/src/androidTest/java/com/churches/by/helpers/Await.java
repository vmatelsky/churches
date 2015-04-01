package com.churches.by.helpers;

import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoAssertionError;
import org.mockito.exceptions.verification.ArgumentsAreDifferent;
import org.mockito.exceptions.verification.TooLittleActualInvocations;
import org.mockito.exceptions.verification.WantedButNotInvoked;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.verification.VerificationMode;

public final class Await implements VerificationMode {

    private final VerificationMode delegate;

    public Await() {
        this(Mockito.times(1));
    }

    public Await(VerificationMode delegate) {
        this.delegate = delegate;
    }

    @Override
    public void verify(VerificationData data) {
        Object mock = data.getWanted().getInvocation().getMock();
        synchronized (mock) {
            while (true) {
                try {
                    delegate.verify(data);
                    break;
                } catch (ArgumentsAreDifferent | TooLittleActualInvocations | WantedButNotInvoked
                        | org.mockito.exceptions.verification.junit.ArgumentsAreDifferent e) {
                }
                try {
                    mock.wait();
                } catch (InterruptedException e) {
                    throw new MockitoAssertionError("interrupted");
                }
            }
        }
    }
}
