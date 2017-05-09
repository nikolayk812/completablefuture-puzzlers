package org.devdays.completeablefuture.puzzlers;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static java.lang.System.out;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static org.awaitility.Awaitility.await;

//thenRun() could be called in both main and executor thread
public class Quiz1 extends AbstractQuiz {

    @Test
    public void test() throws Exception {
        CompletableFuture<String> future =
                supplyAsync(() -> "42", executor);

        future.thenRun(() ->
                out.println(Thread.currentThread().getName()));

        await().until(future::isDone);
    }

}
