package com.safframework.study.retrofit.activity;

import android.os.Bundle;

import com.safframework.study.retrofit.R;
import com.safframework.study.retrofit.app.BaseActivity;

/**
 * Created by tony on 2017/9/29.
 */

public class TestRetrofitActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_retrofit);

        initData();
    }

    private void initData() {
    }
}
