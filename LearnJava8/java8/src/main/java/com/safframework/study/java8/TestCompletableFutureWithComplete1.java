package com.safframework.study.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


/**
 * future调用complete()
 * Created by tony on 2017/9/16.
 */
public class TestCompletableFutureWithComplete1 {

    public static void main(String[] args) {
        CompletableFuture<String> future  = CompletableFuture.supplyAsync(() -> "Hello");

        future.complete("World");

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
