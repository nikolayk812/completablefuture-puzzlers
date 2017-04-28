package org.devdays.completeablefuture.puzzlers;

import org.junit.After;
import org.junit.Before;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AbstractTest {
    protected ExecutorService executor;

    @Before
    public void setUp() throws Exception {
        //TODO: thread name
        executor = Executors.newSingleThreadExecutor();
    }

    @After
    public void tearDown() throws Exception {
        executor.shutdownNow();
    }

    protected static void threadSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
