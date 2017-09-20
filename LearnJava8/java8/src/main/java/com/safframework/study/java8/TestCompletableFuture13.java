package com.safframework.study.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by tony on 2017/9/20.
 */
public class TestCompletableFuture13 {

    public static void main(String[] args) {

        CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> "100")
                .thenApply(s->s+"100")
                .handle((s, t) -> s != null ? Double.parseDouble(s) : 0);

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
