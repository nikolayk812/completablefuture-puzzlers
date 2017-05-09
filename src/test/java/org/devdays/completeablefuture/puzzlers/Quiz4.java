package org.devdays.completeablefuture.puzzlers;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static java.lang.System.out;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static org.awaitility.Awaitility.await;

public class Quiz4 extends AbstractQuiz {

    @Test
    public void test_future_cancel() throws Exception {
        Future<String> future = executor.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    out.println("Interrupted!");
                    Thread.currentThread().interrupt();
                }
            }
            return "42";
        });

        Thread.sleep(500);
        future.cancel(true);

        await().until(future::isDone);
    }

    @Test
    public void test_completablefuture_cancel() throws Exception {
        CompletableFuture<String> future = supplyAsync(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    out.println("Interrupted");
                    Thread.currentThread().interrupt();
                }
            }
            return "42";
        }, executor);

        Thread.sleep(500);
        future.cancel(true);

        await().until(future::isDone);
    }

}
