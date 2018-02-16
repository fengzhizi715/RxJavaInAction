package com.safframework.study.rxpermissons.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.safframework.injectview.annotations.InjectView;
import com.safframework.log.L;
import com.safframework.study.rxpermissons.R;
import com.safframework.study.rxpermissons.app.BaseActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.text1)
    TextView text1;

    @InjectView(R.id.text2)
    TextView text2;

    @InjectView(R.id.text3)
    TextView text3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    @SuppressLint("MissingPermission")
    private void initViews() {

        final RxPermissions rxPermissions = new RxPermissions(this);

        RxView.clicks(text1)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {

                        rxPermissions.request(Manifest.permission.CALL_PHONE)
                                .subscribe(new Consumer<Boolean>() {
                                    @Override
                                    public void accept(Boolean granted) throws Exception {

                                        if (granted) {

                                            Intent intent = new Intent(Intent.ACTION_CALL);
                                            intent.setData(Uri.parse("tel:" + "10000"));
                                            startActivity(intent);
                                        } else {

                                            L.i("授权失败");
                                        }
                                    }
                                });
                    }
                });

        RxView.clicks(text2)
                .compose(rxPermissions.ensure(Manifest.permission.CALL_PHONE))
                .subscribe(new Consumer<Boolean>() {

                    @Override
                    public void accept(@NonNull Boolean granted) throws Exception {

                        if (granted) {

                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:" + "10000"));
                            startActivity(intent);
                        } else {

                            L.i("授权失败");
                        }
                    }
                });

        RxView.clicks(text3)
                .compose(rxPermissions.ensure(Manifest.permission.CAMERA,
                        Manifest.permission.READ_PHONE_STATE))
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {

                        if (granted) {

                            Toast.makeText(MainActivity.this,"打开相机成功",Toast.LENGTH_SHORT).show();
                        } else {

                            L.i("授权失败");
                            Toast.makeText(MainActivity.this,"打开相机失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
