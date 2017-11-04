package com.safframework.study.operator.app;

import android.support.v7.app.AppCompatActivity;

import com.safframework.injectview.Injector;

/**
 * Created by tony on 2017/11/4.
 */

public class BaseActivity extends AppCompatActivity {

    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        Injector.injectInto(this);
    }
}
