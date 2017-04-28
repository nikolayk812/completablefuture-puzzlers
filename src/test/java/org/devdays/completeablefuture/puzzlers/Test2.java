package org.devdays.completeablefuture.puzzlers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.*;
import static java.util.concurrent.CompletableFuture.*;
import static org.awaitility.Awaitility.await;

public class Test2 extends AbstractTest {

    //TODO: percentage
    @Test
    public void test_order() throws Exception {
        CompletableFuture<String> future =
                supplyAsync(() -> "42");

        future.thenRun(() -> out.println("red"));
        future.thenRun(() -> out.println("blue"));

        await().until(future::isDone);
    }

    @Test
    public void test_order_2() throws Exception {
        CompletableFuture<String> future =
                supplyAsync(() -> "42");

        future
                .thenRun(() -> out.println("red"))
                .thenRun(() -> out.println("blue"));

        await().until(future::isDone);
    }

    @Test
    public void name() throws Exception {
        CompletableFuture<String> future = supplyAsync(() -> "42", executor);

        future.exceptionally(ex -> {
            out.println(ex);
            return "";
        }).thenRun(() -> {
            out.println("45");
        });
    }


}
