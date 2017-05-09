package org.devdays.completeablefuture.puzzlers;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static java.lang.System.out;
import static java.util.concurrent.CompletableFuture.allOf;
import static java.util.concurrent.CompletableFuture.completedFuture;
import static java.util.stream.Collectors.toList;


public class Quiz5 extends AbstractQuiz {

    @Test
    public void test() throws Exception {
        CompletableFuture<String> future1 = completedFuture("42");
        CompletableFuture<String> future2 = completedFuture("43");

        out.println(allOf(future1, future2).get());
    }

    @Test
    public void test2() throws Exception {
        CompletableFuture<String> future1 = completedFuture("42");
        CompletableFuture<String> future2 = completedFuture("43");

        CompletableFuture<List<String>> allFuture = allOf(future1, future2)
                .thenApply(_void ->
                        Stream.of(future1, future2)
                                .map(CompletableFuture::join)
                                .collect(toList()));

        out.println(allFuture);
    }

}
