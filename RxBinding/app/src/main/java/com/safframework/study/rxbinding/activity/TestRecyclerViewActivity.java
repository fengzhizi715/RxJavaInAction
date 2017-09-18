package com.safframework.study.rxbinding.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView;
import com.safframework.injectview.annotations.InjectView;
import com.safframework.log.L;
import com.safframework.study.rxbinding.R;
import com.safframework.study.rxbinding.adapter.MyRecyclerAdapter;
import com.safframework.study.rxbinding.app.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by tony on 2017/9/18.
 */

public class TestRecyclerViewActivity extends BaseActivity {

    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;

    private List<String> mDatas;
    private MyRecyclerAdapter recycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        initViews();
        initData();
    }

    private void initViews() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerview.setLayoutManager(linearLayoutManager);

        RxRecyclerView
                .scrollStateChanges(recyclerview)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer scrollState) throws Exception {

                        L.i("scrollState = " + scrollState);
                    }
                });
    }

    private void initData() {

        mDatas = new ArrayList<>();
        for ( int i=0; i < 40; i++) {
            mDatas.add( "item "+i);
        }

        recycleAdapter = new MyRecyclerAdapter(TestRecyclerViewActivity.this,mDatas);
        recyclerview.setAdapter(recycleAdapter);
    }
}
