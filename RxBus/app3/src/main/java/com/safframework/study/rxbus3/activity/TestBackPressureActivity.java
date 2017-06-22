package com.safframework.study.rxbus3.activity;

import android.os.Bundle;

import com.safframework.study.rxbus3.app.BaseActivity;
import com.safframework.study.rxbus3.domain.TestBackPressEvent;


/**
 * Created by Tony Shen on 2017/6/22.
 */

public class TestBackPressureActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (int i=0;;i++) {

            rxBus.post(new TestBackPressEvent());
        }
    }
}
