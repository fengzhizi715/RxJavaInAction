package com.safframework.study.rxbinding.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.safframework.injectview.annotations.InjectView;
import com.safframework.study.rxbinding.R;
import com.safframework.study.rxbinding.app.BaseActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.text1)
    TextView text1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {

        RxView.clicks(text1)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {

                        Intent i = new Intent(MainActivity.this,TestClickActivity.class);
                        startActivity(i);
                    }
                });
    }
}
