package com.safframework.study.rxbus4.activity;

import android.os.Bundle;

import com.safframework.study.rxbus4.app.BaseActivity;
import com.safframework.study.rxbus4.domain.TestExceptionEvent;


/**
 * Created by Tony Shen on 2017/6/22.
 */

public class TestExceptionActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rxBus.post(new TestExceptionEvent());
    }
}
