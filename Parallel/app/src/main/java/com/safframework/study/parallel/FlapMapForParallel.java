package com.safframework.study.parallel;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tony on 2017/9/12.
 */
public class FlapMapForParallel {

    public static void main(String[] args) {

        Observable.range(1,100)
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        return Observable.just(integer)
                                .subscribeOn(Schedulers.computation())
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
