package org.devdays.completeablefuture.puzzlers;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static org.awaitility.Awaitility.await;

@Slf4j
public class Test1 extends AbstractTest {

    @Test
    public void test_main() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "42", executor);
        future.thenRun(() ->
                log.info("{}", Thread.currentThread().getName()));

        await().until(future::isDone);
    }

    @Test
    public void test_pool() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "42";
        }, executor);

        future.thenRun(() -> System.out.println(Thread.currentThread().getName()));

        await().until(future::isDone);
    }

    @Test
    public void test_fjp() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "42";
        });

        future.thenRun(() -> System.out.println(Thread.currentThread().getName()));

        await().until(future::isDone);
    }
}
