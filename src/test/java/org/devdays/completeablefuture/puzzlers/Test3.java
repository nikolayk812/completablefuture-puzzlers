package org.devdays.completeablefuture.puzzlers;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Nikolay Kuznetsov
 * @since 2017-04-17
 */
public class Test3 extends AbstractTest {

    @Test
    public void test_cancel_1() throws Exception {
        Future<String> future = executor.submit(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("interrupted");
                    break;
                }
            }
            return "42";
        });

        Thread.sleep(500);
        future.cancel(true);
        await().until(future::isDone);
    }

    @Test
    public void test_cancel() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("interrupted");
                    break;
                }
            }
            return "42";
        }, executor);

        Thread.sleep(500);
        future.cancel(true);

        await().until(future::isDone);
    }

}
