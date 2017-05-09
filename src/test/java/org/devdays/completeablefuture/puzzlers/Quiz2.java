package org.devdays.completeablefuture.puzzlers;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static java.lang.System.out;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static org.awaitility.Awaitility.await;

//thenRunAsync() called in ForkJoinPool
public class Quiz2 extends AbstractQuiz {

    @Test
    public void test() throws Exception {
        CompletableFuture<Void> future = supplyAsync(() -> "42", executor)
                .thenRunAsync(() -> out.println(
                        Thread.currentThread().getName()));

        await().until(future::isDone);
    }

}
