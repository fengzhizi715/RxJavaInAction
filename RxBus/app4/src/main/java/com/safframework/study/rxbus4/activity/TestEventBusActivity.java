package com.safframework.study.rxbus4.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import com.safframework.study.rxbus4.R;
import com.safframework.study.rxbus4.app.BaseActivity;
import com.safframework.study.rxbus4.domain.Fragment1Event;
import com.safframework.study.rxbus4.domain.Fragment2Event;
import com.safframework.study.rxbus4.fragment.Fragment1;
import com.safframework.study.rxbus4.fragment.Fragment2;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Tony Shen on 2017/6/22.
 */

public class TestEventBusActivity extends BaseActivity {

    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_event_bus);

        initViews();
        registerEvents();
    }

    private void initViews() {

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();

        FragmentTransaction mTransactiont = getSupportFragmentManager().beginTransaction();
        mTransactiont.replace(R.id.layout1, fragment1, fragment1.getClass().getName());
        mTransactiont.replace(R.id.layout2, fragment2, fragment2.getClass().getName());
        mTransactiont.commit();
    }

    private void registerEvents() {

        compositeDisposable.add(rxBus.register(Fragment1Event.class, AndroidSchedulers.mainThread(),
                new Consumer<Fragment1Event>() {

            @Override
            public void accept(@NonNull Fragment1Event event) throws Exception {

                fragment2.getText2().setText("fragment2 已经接收到事件");
            }
        }));

        compositeDisposable.add(rxBus.register(Fragment2Event.class, AndroidSchedulers.mainThread(),
                new Consumer<Fragment2Event>() {

                    @Override
                    public void accept(@NonNull Fragment2Event event) throws Exception {

                        fragment1.getText1().setText("fragment1 已经接收到事件");
                    }
                }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
