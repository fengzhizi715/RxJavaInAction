package com.safframework.study.retrofit.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.safframework.injectview.annotations.InjectView;
import com.safframework.log.L;
import com.safframework.study.retrofit.R;
import com.safframework.study.retrofit.app.BaseActivity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tony on 2017/9/29.
 */

public class TestRxAndroid1Activity extends BaseActivity {

    @InjectView(R.id.image)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_rxandroid1);

        initData();
    }

    private void initData() {

        Observable.create(new ObservableOnSubscribe<Bitmap>() {

            @Override
            public void subscribe(ObservableEmitter<Bitmap> e) throws Exception {

                L.i("e.onNext()");
                e.onNext(getBitmap());
            }
        })
                //设置数据加载在子线程中进行
                .subscribeOn(Schedulers.io())
                //设置图片加载在主线程中进行
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void accept(Bitmap bitmap) throws Exception {

                        if (bitmap != null) {
                            L.i("bitmap is not null");
                            imageView.setImageBitmap(bitmap);
                        }
                    }
                });
    }

    private Bitmap getBitmap() {
        HttpURLConnection con;

        try {
            URL url = new URL("http://n.sinaimg.cn/news/1_img/upload/60c98dca/20170929/KSE9-fymkwwk6862900.jpg");
            con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(20000);
            con.connect();
            if (con.getResponseCode() == 200) {
                return BitmapFactory.decodeStream(con.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
