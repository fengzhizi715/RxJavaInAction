package com.safframework.study.cv4j.activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.safframework.injectview.annotations.InjectView;
import com.safframework.study.cv4j.R;
import com.safframework.study.cv4j.adapter.GridViewFilterAdapter;
import com.safframework.study.cv4j.app.BaseActivity;
import com.safframework.study.cv4j.ui.DividerGridItemDecoration;
import com.safframework.study.cv4j.ui.GridRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tony on 2017/10/8.
 */

public class GridViewFilterActivity extends BaseActivity {

    @InjectView(R.id.recyclerview)
    GridRecyclerView recyclerview;

    private List<String> list = new ArrayList<>();

    private static RecyclerView.RecycledViewPool myPool = new RecyclerView.RecycledViewPool();

    static {
        myPool.setMaxRecycledViews(0, 10);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview_filter);

        initData();
    }

    private void initData() {
        Resources res = getResources();
        String[] filterNames = res.getStringArray(R.array.filterNames);
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.test_mm);
        for (String filter:filterNames) {
            list.add(filter);
        }

        GridLayoutManager manager = new GridLayoutManager(GridViewFilterActivity.this, 3);
        manager.setRecycleChildrenOnDetach(true);
        recyclerview.setLayoutManager(manager);
        recyclerview.setAdapter(new GridViewFilterAdapter(list,bitmap));
        recyclerview.addItemDecoration(new DividerGridItemDecoration(GridViewFilterActivity.this));
        recyclerview.setRecycledViewPool(myPool);
    }
}
