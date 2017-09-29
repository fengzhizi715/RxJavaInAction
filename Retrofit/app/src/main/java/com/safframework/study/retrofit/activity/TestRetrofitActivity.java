package com.safframework.study.retrofit.activity;

import android.os.Bundle;

import com.safframework.study.retrofit.R;
import com.safframework.study.retrofit.api.APIService;
import com.safframework.study.retrofit.app.BaseActivity;
import com.safframework.study.retrofit.http.RetrofitManager;
import com.safframework.study.retrofit.model.PM25Model;
import com.safframework.utils.RxJavaUtils;

import java.util.List;

import io.reactivex.functions.Consumer;

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

        APIService apiService = RetrofitManager.retrofit().create(APIService.class);
        apiService.pm25("苏州","5j1znBVAsnSf5xQyNQyq")
                .compose(RxJavaUtils.<List<PM25Model>>maybeToMain())
                .subscribe(new Consumer<List<PM25Model>>() {
                    @Override
                    public void accept(List<PM25Model> pm25Models) throws Exception {

                    }
                });
    }
}
