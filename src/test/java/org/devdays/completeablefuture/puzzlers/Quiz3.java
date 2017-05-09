package org.devdays.completeablefuture.puzzlers;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static org.awaitility.Awaitility.await;

//Order of actions
public class Quiz3 extends AbstractQuiz {

    //blue might go in front of red
    @Test
    public void test() throws Exception {
        CompletableFuture<String> future =
                supplyAsync(() -> "42");

        future.thenRun(() -> fairOut("red"));
        future.thenRun(() -> fairOut("blue"));

        await().until(future::isDone);
    }

    //always red, then blue
    @Test
    public void test2() throws Exception {
        CompletableFuture<String> future =
                supplyAsync(() -> "42");

        future
                .thenRun(() -> fairOut("red"))
                .thenRun(() -> fairOut("blue"));

        await().until(future::isDone);
    }

}
