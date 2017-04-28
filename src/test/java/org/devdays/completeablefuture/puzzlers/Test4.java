package org.devdays.completeablefuture.puzzlers;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;


public class Test4 extends AbstractTest {
    @Test
    public void name() throws Exception {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "42", executor);
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "43", executor);


        System.out.println(CompletableFuture.allOf(future1, future2).get());

//        String combined = Stream.of(future1, future2, future3)
//                .map(CompletableFuture::join)
//                .collect(Collectors.joining(" "));
    }
}
