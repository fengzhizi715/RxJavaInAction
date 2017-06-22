package com.safframework.study.rxbus2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.safframework.injectview.Injector;
import com.safframework.injectview.annotations.InjectView;
import com.safframework.study.rxbus2.R;
import com.safframework.study.rxbus2.app.BaseFragment;
import com.safframework.study.rxbus2.domain.Fragment1Event;

import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Tony Shen on 2017/6/22.
 */

public class Fragment1 extends BaseFragment {


    @InjectView(R.id.text1)
    TextView text1;

    @InjectView(R.id.button1)
    Button button1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_1, container, false);

        Injector.injectInto(this, v);

        initViews();

        return v;
    }

    private void initViews() {

        RxView.clicks(button1)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {

                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        rxBus.post(new Fragment1Event());
                    }
                });
    }

    public TextView getText1() {
        return text1;
    }
}
