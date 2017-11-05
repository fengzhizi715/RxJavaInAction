package com.safframework.study.backpressure.activity;

import android.os.Bundle;
import android.util.Log;

import com.safframework.study.backpressure.app.BaseActivity;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by tony on 2017/11/5.
 */

public class NoBackpressureActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0;; i++) {   //无限循环发事件
                    subscriber.onNext(i);
                }
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d("NoBackpressureActivity", String.valueOf(integer));
            }
        });
    }
}
