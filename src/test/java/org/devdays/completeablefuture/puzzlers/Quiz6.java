package org.devdays.completeablefuture.puzzlers;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static java.lang.System.out;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static org.awaitility.Awaitility.await;

//no support for timeout in Java 8
public class Quiz6 extends AbstractQuiz {

    @Test
    public void test() throws Exception {
        CompletableFuture<Integer> future = supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return 0;
            }
            return 42;
        }, executor);

        future.whenComplete((result, e) -> {
            if (e == null) {
                out.println(result);
            } else if (e instanceof TimeoutException){
                out.println("Timeout: " + e);
            } else {
                out.println("Error: " + e);
            }
        });

        try {
            future.get(10, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            out.println("Timeout: " + e);
        } catch (Exception e) {
            out.println("Error: " + e);
        }

        scheduledExecutor.schedule(() ->
                future.completeExceptionally(
                        new TimeoutException()),
                1, TimeUnit.SECONDS);

        await().until(future::isDone);
    }

    @Test
    public void test2() throws Exception {
        CompletableFuture<Integer> future = supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return 0;
            }
            return 42;
        }, executor);

        try {
            future.get(10, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            out.println("Timeout: " + e);
        } catch (Exception e) {
            out.println("Error: " + e);
        }
    }

}
