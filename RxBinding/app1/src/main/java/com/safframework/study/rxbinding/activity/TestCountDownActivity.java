package com.safframework.study.rxbinding.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.safframework.injectview.annotations.InjectView;
import com.safframework.study.rxbinding.R;
import com.safframework.study.rxbinding.app.BaseActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tony on 2017/9/18.
 */

public class TestCountDownActivity extends BaseActivity {

    @InjectView(R.id.get_verification_code)
    TextView verificationCode;

    private long MAX_COUNT_TIME = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_countdown);

        initViews();
    }

    private void initViews() {

        RxView.clicks(verificationCode)
                .throttleFirst(MAX_COUNT_TIME, TimeUnit.SECONDS)
                .flatMap(new Function<Object, ObservableSource<Long>>() {
                    @Override
                    public ObservableSource<Long> apply(Object o) throws Exception {
                        //更新发送按钮的状态并初始化显现倒计时文字
                        RxView.enabled(verificationCode).accept(false);
                        RxTextView.text(verificationCode).accept("剩余 " + MAX_COUNT_TIME + " 秒");

                        // do something

                        //返回 N 秒内的倒计时观察者对象。
                        return Observable.interval(1, TimeUnit.SECONDS, Schedulers.io()).take(MAX_COUNT_TIME);
                    }
                })
                .map(new Function<Long, Long>() { //将递增数字替换成递减的倒计时数字
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return MAX_COUNT_TIME - (aLong + 1);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//切换到 Android 的主线程。
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {

                        if (aLong == 0) {
                            RxView.enabled(verificationCode).accept(true);
                            RxTextView.text(verificationCode).accept("获取验证码");
                        } else {
                            RxTextView.text(verificationCode).accept("剩余 " + aLong + " 秒");
                        }

                    }
                });

    }
}
