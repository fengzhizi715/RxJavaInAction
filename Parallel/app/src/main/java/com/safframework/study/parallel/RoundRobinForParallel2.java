package com.safframework.study.parallel;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by tony on 2017/9/12.
 */
public class RoundRobinForParallel2 {

    public static void main(String[] args) {

        final AtomicInteger batch = new AtomicInteger(0);

        int threadNum = 5;

        final ExecutorService executor = Executors.newFixedThreadPool(threadNum);
        final Scheduler scheduler = Schedulers.from(executor);

        Observable.range(1,100)
                .groupBy(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        return batch.getAndIncrement() % threadNum;
                    }
                })
                .flatMap(new Function<GroupedObservable<Integer, Integer>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull GroupedObservable<Integer, Integer> integerIntegerGroupedObservable) throws Exception {
                        return integerIntegerGroupedObservable.observeOn(scheduler)
                                .map(new Function<Integer, String>() {

                                    @Override
                                    public String apply(@NonNull Integer integer) throws Exception {
                                        return integer.toString();
                                    }
                                });
                    }
                })
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        System.out.println(o);
                    }
                });
    }
}
