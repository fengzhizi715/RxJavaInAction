package com.safframework.study.java8;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by tony on 2017/9/21.
 */
public class TestCompletableFuture17 {

    public static void main(String[] args) {

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "tony");

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "cafei");

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "aaron");

        CompletableFuture.allOf(future1, future2, future3)
                .thenApply(v ->
                Stream.of(future1, future2, future3)
                        .map(CompletableFuture::join)
                        .collect(Collectors.joining(" ")))
                .thenAccept(System.out::print);

    }
}
