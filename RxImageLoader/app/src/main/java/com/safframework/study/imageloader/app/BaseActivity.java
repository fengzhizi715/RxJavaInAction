package com.safframework.study.imageloader.app;

import android.annotation.TargetApi;
import android.content.ComponentCallbacks2;
import android.support.v7.app.AppCompatActivity;

import com.safframework.injectview.Injector;
import com.safframework.study.imageloader.imageloader.Utils;

/**
 * Created by Tony Shen on 2017/6/23.
 */

public class BaseActivity extends AppCompatActivity {

    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        Injector.injectInto(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
//        app.imageLoader.clearMemCache();
    }

    @Override
    @TargetApi(14)
    public void onTrimMemory(int level) {
        if (Utils.isICSOrHigher()) {
            super.onTrimMemory(level);

            if (level >= ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW) {
//                app.imageLoader.clearMemCache();
            }
        }
    }
}
