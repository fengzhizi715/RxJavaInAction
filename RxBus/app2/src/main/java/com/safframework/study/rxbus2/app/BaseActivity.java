package com.safframework.study.rxbus2.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.safframework.injectview.Injector;
import com.safframework.study.rxbus2.RxBus;

/**
 * Created by Tony Shen on 2017/6/22.
 */

public class BaseActivity extends AppCompatActivity {

    protected RxBus rxBus;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rxBus = RxBus.get();
    }

    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        Injector.injectInto(this);
    }
}
