package com.safframework.study.rxbus1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.safframework.injectview.Injector;
import com.safframework.injectview.annotations.InjectView;
import com.safframework.study.rxbus1.R;
import com.safframework.study.rxbus1.app.BaseFragment;
import com.safframework.study.rxbus1.domain.Fragment2Event;

import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Tony Shen on 2017/6/22.
 */

public class Fragment2 extends BaseFragment {

    @InjectView(R.id.text2)
    TextView text2;

    @InjectView(R.id.button2)
    Button button2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_2, container, false);

        Injector.injectInto(this, v);

        initViews();

        return v;
    }

    private void initViews() {

        RxView.clicks(button2)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {

                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        rxBus.post(new Fragment2Event());
                    }
                });
    }

    public TextView getText2() {
        return text2;
    }
}
