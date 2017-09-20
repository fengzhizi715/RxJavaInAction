package com.safframework.study.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by tony on 2017/9/19.
 */
public class TestCompletableFuture7 {

    public static void main(String[] args) {

        CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> "10")
                .thenApply(Integer::parseInt)
                .thenApply(i->i*10.0);

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
