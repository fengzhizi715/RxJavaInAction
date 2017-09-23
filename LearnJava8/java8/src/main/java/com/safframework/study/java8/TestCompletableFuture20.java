package com.safframework.study.java8;

import java.util.concurrent.CompletableFuture;

/**
 * Created by tony on 2017/9/23.
 */
public class TestCompletableFuture20 {

    public static void main(String[] args) {

        CompletableFuture.supplyAsync(() -> "hello world")
                .thenApply(s -> {
                    s = null;
                    int length = s.length();
                    return length;
                }).thenAccept(i -> System.out.println(i))
                .whenComplete((result, throwable) -> {

                    if (throwable != null) {
                       System.out.println("Unexpected error:"+throwable);
                    } else {
                        System.out.println(result);
                    }

                });
    }
}
