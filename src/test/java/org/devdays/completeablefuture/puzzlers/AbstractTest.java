package org.devdays.completeablefuture.puzzlers;

import org.junit.After;
import org.junit.Before;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Nikolay Kuznetsov
 * @since 2017-04-17
 */
public class AbstractTest {
    protected ExecutorService executor;

    @Before
    public void setUp() throws Exception {
        executor = Executors.newSingleThreadExecutor();
    }

    @After
    public void tearDown() throws Exception {
        executor.shutdownNow();
    }
}
