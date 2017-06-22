package com.safframework.study.rxbus4.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.safframework.study.rxbus4.app.BaseActivity;
import com.safframework.study.rxbus4.domain.NormalEvent;
import com.safframework.study.rxbus4.domain.StickyEvent;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Tony Shen on 2017/6/22.
 */

public class TestStickyActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        registerEvents();
    }

    private void initData() {

        rxBus.postSticky(new StickyEvent());
        rxBus.post(new NormalEvent());
    }

    private void registerEvents() {

        rxBus.registerSticky(StickyEvent.class, AndroidSchedulers.mainThread(), new Consumer<StickyEvent>() {

            @Override
            public void accept(@NonNull StickyEvent event) throws Exception {

                Toast.makeText(TestStickyActivity.this,"this is StickyEvent",Toast.LENGTH_SHORT).show();
            }
        });

        rxBus.register(NormalEvent.class, AndroidSchedulers.mainThread(), new Consumer<NormalEvent>() {

            @Override
            public void accept(@NonNull NormalEvent event) throws Exception {

                Toast.makeText(TestStickyActivity.this,"this is NormalEvent",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
