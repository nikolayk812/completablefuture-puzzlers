package org.devdays.completeablefuture.puzzlers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.awaitility.Awaitility.await;

/**
 * @author Nikolay Kuznetsov
 * @since 2017-04-17
 */
public class Test2 extends AbstractTest{

    //TODO: percentage
    @Test
    public void test_order() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "42", executor);
        future.thenRun(() -> System.out.println("here"));
        future.thenRun(() -> System.out.println("there"));

        await().until(future::isDone);
    }

    @Test
    public void name() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "42", executor);

        future.exceptionally(ex -> {
            System.out.println(ex);
            return "";
        }).thenRun(() -> {
            System.out.println("45");
        });
    }


}
