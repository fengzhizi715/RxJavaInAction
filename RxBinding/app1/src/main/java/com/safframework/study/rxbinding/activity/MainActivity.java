package com.safframework.study.rxbinding.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.safframework.injectview.annotations.InjectView;
import com.safframework.study.rxbinding.R;
import com.safframework.study.rxbinding.app.BaseActivity;
import com.safframework.study.rxbinding.utils.RxUtils;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.text1)
    TextView text1;

    @InjectView(R.id.text2)
    TextView text2;

    @InjectView(R.id.text3)
    TextView text3;

    @InjectView(R.id.text4)
    TextView text4;

    @InjectView(R.id.text5)
    TextView text5;

    @InjectView(R.id.text6)
    TextView text6;

    @InjectView(R.id.text7)
    TextView text7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {

        RxView.clicks(text1)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {

                        Toast.makeText(MainActivity.this,"演示点击事件",Toast.LENGTH_SHORT).show();
                    }
                });

        RxView.longClicks(text2)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {

                        Toast.makeText(MainActivity.this,"演示长点击事件",Toast.LENGTH_SHORT).show();
                    }
                });

        RxView.clicks(text3)
                .compose(RxUtils.useRxViewTransformer(MainActivity.this))
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {

                        Toast.makeText(MainActivity.this,"防止重复点击",Toast.LENGTH_SHORT).show();
                    }
                });

        RxView.clicks(text4)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {

                        Intent i = new Intent(MainActivity.this,TestEditTextActivity.class);
                        startActivity(i);
                    }
                });

        RxView.clicks(text5)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {

                        Intent i = new Intent(MainActivity.this,TestCountDownActivity.class);
                        startActivity(i);
                    }
                });

        RxView.clicks(text6)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {

                        Intent i = new Intent(MainActivity.this,TestRecyclerViewActivity.class);
                        startActivity(i);
                    }
                });

        Observable clickObservable = RxView.clicks(text7).compose(RxUtils.useRxViewTransformer(MainActivity.this)).share();
        clickObservable.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {

                Toast.makeText(MainActivity.this,"对text7的第一次监听",Toast.LENGTH_SHORT).show();
            }
        });

        clickObservable.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {

                Toast.makeText(MainActivity.this,"对text7的第二次监听",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
