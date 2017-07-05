package com.safframework.study.rxbus2.activity;

import android.os.Bundle;

import com.safframework.study.rxbus2.app.BaseActivity;
import com.safframework.study.rxbus2.domain.CrossActivityEvent;

/**
 * Created by Tony Shen on 2017/6/22.
 */

public class TestCrossActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rxBus.post(new CrossActivityEvent());
    }
}
