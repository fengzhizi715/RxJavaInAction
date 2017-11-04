package com.safframework.study.operator.app;

import android.support.v7.app.AppCompatActivity;

import com.safframework.injectview.Injector;

/**
 * Created by Tony Shen on 2017/3/12.
 */

public class BaseActivity extends AppCompatActivity {

    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        Injector.injectInto(this);
    }
}