package com.safframework.study.cv4j.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.cv4j.core.filters.NatureFilter;
import com.cv4j.rxjava.RxImageData;
import com.safframework.injectview.annotations.InjectView;
import com.safframework.study.cv4j.R;
import com.safframework.study.cv4j.app.BaseActivity;

/**
 * Created by tony on 2017/10/8.
 */

public class FilterActivity extends BaseActivity {

    @InjectView(R.id.image0)
    ImageView image0;

    @InjectView(R.id.image)
    ImageView image;

    RxImageData rxImageData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        initData();
    }

    private void initData() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.test_io);
        image0.setImageBitmap(bitmap);

        rxImageData = RxImageData.bitmap(bitmap);

        rxImageData.addFilter(new NatureFilter())
                .into(image);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rxImageData.recycle();
    }
}
