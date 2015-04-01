package com.churches.by;

import android.test.InstrumentationTestCase;

import com.churches.by.data.DataProvider;
import com.churches.by.data.model.Church;
import com.churches.by.data.model.receivers.ChurchesReceiver;
import com.churches.by.helpers.Await;
import com.churches.by.helpers.TestCaseWithMockito;

import org.mockito.ArgumentCaptor;
import org.mockito.MockSettings;
import org.mockito.invocation.DescribedInvocation;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.listeners.InvocationListener;
import org.mockito.listeners.MethodInvocationReport;
import org.mockito.stubbing.Answer;
import org.mockito.verification.VerificationMode;

import java.util.List;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.withSettings;

public class DataProviderTest extends TestCaseWithMockito {

    private DataProvider provider;

    @Override
    protected void setUp() {
        super.setUp();
        provider = DataProvider.instance();
    }

    public void testGetNotNullChurches() {
        List<Church> favorites = provider.churches();
        assertNotNull(favorites);
    }

    public void testGetFavoritesAsync() {
        final ChurchesReceiver churchesObserver = mock(ChurchesReceiver.class, async());
        provider.churchesAsync(churchesObserver);

        ArgumentCaptor<List> messageCaptor = ArgumentCaptor.forClass(List.class);
        verify(churchesObserver, await()).call(messageCaptor.capture());
    }

    private static VerificationMode await() {
        return new Await();
    }

    private static MockSettings async() {
        return withSettings().invocationListeners(
                new InvocationListener() {

                    @Override
                    public void reportInvocation(MethodInvocationReport methodInvocationReport) {
                        DescribedInvocation invocation = methodInvocationReport.getInvocation();
                        if (invocation instanceof InvocationOnMock) {
                            Object mock = ((InvocationOnMock) invocation).getMock();
                            synchronized (mock) {
                                mock.notifyAll();
                            }
                        }
                    }
                });
    }



}
