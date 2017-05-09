package org.devdays.completeablefuture.puzzlers;

import org.junit.After;
import org.junit.Before;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AbstractQuiz {
    private static Lock lock = new ReentrantLock(true);

    protected ExecutorService executor;
    protected ScheduledExecutorService scheduledExecutor;


    @Before
    public void setUp() throws Exception {
        executor = Executors.newSingleThreadExecutor(r ->
                new Thread(r, "executor"));
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    }

    @After
    public void tearDown() throws Exception {
        executor.shutdownNow();
        scheduledExecutor.shutdownNow();
    }

    public static void fairOut(String str) {
        lock.lock();
        try {
            System.out.println(str);
        } finally {
            lock.unlock();
        }
    }

}
