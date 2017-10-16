package com.safframework.study.imageloader.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.safframework.injectview.annotations.InjectView;
import com.safframework.study.imageloader.R;
import com.safframework.study.imageloader.adapter.PictureAdapter;
import com.safframework.study.imageloader.app.BaseActivity;
import com.safframework.study.imageloader.domain.Picture;
import com.safframework.study.imageloader.ui.DividerGridItemDecoration;
import com.safframework.study.imageloader.ui.GridRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.recyclerview)
    GridRecyclerView recyclerview;

    private List<Picture> data = new ArrayList<Picture>();

    private static RecyclerView.RecycledViewPool myPool = new RecyclerView.RecycledViewPool();

    static {
        myPool.setMaxRecycledViews(0, 10);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
    }

    private void initData() {


        Picture picture = new Picture();
        picture.title = "Mushroom after the rain";
        picture.url = "http://www.designerspics.com/wp-content/uploads/2014/06/Mushroom_after_the_rain__free_photo1.jpg";
        data.add(picture);

        picture = new Picture();
        picture.title = "Old Tree";
        picture.url = "http://www.designerspics.com/wp-content/uploads/2014/08/tree_free_photo.jpg";
        data.add(picture);

        GridLayoutManager manager = new GridLayoutManager(MainActivity.this, 2);
        manager.setRecycleChildrenOnDetach(true);
        recyclerview.setLayoutManager(manager);
        recyclerview.setAdapter(new PictureAdapter(data));
        recyclerview.addItemDecoration(new DividerGridItemDecoration(MainActivity.this));
        recyclerview.setRecycledViewPool(myPool);
    }
}
