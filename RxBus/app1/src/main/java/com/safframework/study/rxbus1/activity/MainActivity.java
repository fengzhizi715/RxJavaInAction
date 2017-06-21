package com.safframework.study.rxbus1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.safframework.injectview.annotations.InjectView;
import com.safframework.study.rxbus1.R;
import com.safframework.study.rxbus1.app.BaseActivity;
import com.safframework.study.rxbus1.domain.TestBackPressEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.text1)
    TextView text1;

    @InjectView(R.id.text2)
    TextView text2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        registerEventBus();
    }

    private void initViews() {

        RxView.clicks(text1)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {

            }
        });

        RxView.clicks(text2)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {

                        Intent i = new Intent(MainActivity.this,TestBackPressureActivity.class);
                        startActivity(i);
                    }
                });
    }

    private void registerEventBus() {

        rxBus.toObservable(TestBackPressEvent.class).subscribe(new Consumer<TestBackPressEvent>() {
            @Override
            public void accept(@NonNull TestBackPressEvent testBackPressEvent) throws Exception {

                Log.i("MainActivity","testBackPressEvent");
            }
        });
    }
}
