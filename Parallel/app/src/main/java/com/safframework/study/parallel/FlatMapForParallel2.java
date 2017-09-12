package com.safframework.study.parallel;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tony on 2017/9/12.
 */
public class FlatMapForParallel2 {

    public static void main(String[] args) {

        int threadNum = Runtime.getRuntime().availableProcessors()+1;

        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
        final Scheduler scheduler = Schedulers.from(executor);
        Observable.range(1,100)
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        return Observable.just(integer)
                                .subscribeOn(scheduler)
                                .map(new Function<Integer, String>() {

                                    @Override
                                    public String apply(Integer integer) throws Exception {
                                        return integer.toString();
                                    }
                                });
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String str) throws Exception {

                        System.out.println(str);
                    }
                });
    }
}
