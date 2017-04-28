package org.devdays.completeablefuture.puzzlers;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static java.lang.System.out;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static org.awaitility.Awaitility.await;

@Slf4j
public class Test1 extends AbstractTest {

    @Test
    public void test_thenRunCouldBeCalledInBothMainAndExecutorThread() throws Exception {
        CompletableFuture<String> future =
                supplyAsync(() -> "42", executor);

        future.thenRun(() ->
                out.println(Thread.currentThread().getName()));

        await().until(future::isDone);
    }


    @Test
    public void test_thenRunCalledInForkJoinPoolThread() throws Exception {
        CompletableFuture<String> future = supplyAsync(() -> {
            threadSleep(1000);
            return "42";
        });

        future.thenRun(() ->
                out.println(Thread.currentThread().getName()));

        await().until(future::isDone);
    }

}
