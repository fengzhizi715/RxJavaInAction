package com.safframework.study.operator.app;

import android.app.Application;

import com.safframework.router.RouterManager;

/**
 * Created by tony on 2017/12/16.
 */

public class App extends Application {

    private static App mInstance = null;

    public static App getInstance() {
        return mInstance;
    }

    public void onCreate() {

        super.onCreate();

        mInstance = this;

        RouterManager.init(mInstance);               // 使用Router框架的话，这一步是必须的，用于初始化Router，RouterManager是由apt生成的。
    }
}
