package com.safframework.study.rxbus3.activity;

import android.os.Bundle;

import com.safframework.study.rxbus3.app.BaseActivity;
import com.safframework.study.rxbus3.domain.ExceptionEvent;

/**
 * Created by Tony Shen on 2017/6/22.
 */

public class TestExceptionActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rxBus.post(new ExceptionEvent());
    }
}
