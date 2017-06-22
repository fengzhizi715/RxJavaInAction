package com.safframework.study.rxbus1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.safframework.injectview.annotations.InjectView;
import com.safframework.study.rxbus1.R;
import com.safframework.study.rxbus1.app.BaseActivity;
import com.safframework.study.rxbus1.domain.TestCrossActivityEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.text1)
    TextView text1;

    @InjectView(R.id.text2)
    TextView text2;

    private Disposable disposable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        registerEvents();
    }

    private void initViews() {

        RxView.clicks(text1)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {

                Intent i = new Intent(MainActivity.this,TestEventBusActivity.class);
                startActivity(i);
            }
        });

        RxView.clicks(text2)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {

                        Intent i = new Intent(MainActivity.this,TestCrossActivity.class);
                        startActivity(i);
                    }
                });
    }

    private void registerEvents() {

        disposable = rxBus.toObservable(TestCrossActivityEvent.class)
                .subscribe(new Consumer<TestCrossActivityEvent>() {
            @Override
            public void accept(@NonNull TestCrossActivityEvent event) throws Exception {
                Toast.makeText(MainActivity.this,"来自MainActivity的Toast",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable!=null && !disposable.isDisposed()) {

            disposable.dispose();
        }
    }
}
