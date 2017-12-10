package com.safframework.study.retrofit.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.safframework.injectview.annotations.InjectView;
import com.safframework.study.retrofit.R;
import com.safframework.study.retrofit.api.APIService;
import com.safframework.study.retrofit.app.BaseActivity;
import com.safframework.study.retrofit.config.Constant;
import com.safframework.study.retrofit.http.RetrofitManager;
import com.safframework.study.retrofit.model.PM10Model;
import com.safframework.study.retrofit.model.PM25Model;
import com.safframework.study.retrofit.model.SO2Model;
import com.safframework.study.retrofit.model.ZipObject;
import com.safframework.tony.common.utils.Preconditions;
import com.safframework.utils.RxJavaUtils;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Predicate;

/**
 * Created by tony on 2017/12/10.
 */

public class TestRetrofit2Activity extends BaseActivity {

    @InjectView(R.id.quality)
    TextView quality;

    @InjectView(R.id.pm2_5)
    TextView pm2_5;

    @InjectView(R.id.pm2_5_24h)
    TextView pm2_5_24h;

    @InjectView(R.id.pm10)
    TextView pm10;

    @InjectView(R.id.pm10_24h)
    TextView pm10_24h;

    @InjectView(R.id.so2)
    TextView so2;

    @InjectView(R.id.so2_24h)
    TextView so2_24h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_retrofit);

        initData();
    }

    private void initData() {

        APIService apiService = RetrofitManager.retrofit().create(APIService.class);

        Maybe<PM25Model> pm25Maybe = apiService.pm25(Constant.CITY_ID,Constant.TOKEN)
                .compose(RxJavaUtils.<List<PM25Model>>maybeToMain())
                .filter(new Predicate<List<PM25Model>>() {
                    @Override
                    public boolean test(List<PM25Model> pm25Models) throws Exception {

                        return Preconditions.isNotBlank(pm25Models);
                    }
                })
                .flatMap(new Function<List<PM25Model>, MaybeSource<PM25Model>>() {
                    @Override
                    public MaybeSource<PM25Model> apply(List<PM25Model> pm25Models) throws Exception {

                        for (PM25Model model:pm25Models){

                            if ("南门".equals(model.position_name)) {
                                return Maybe.just(model);
                            }
                        }

                        return null;
                    }
                });

        Maybe<PM10Model> pm10Maybe = apiService.pm10(Constant.CITY_ID,Constant.TOKEN)
                .compose(RxJavaUtils.<List<PM10Model>>maybeToMain())
                .filter(new Predicate<List<PM10Model>>() {
                    @Override
                    public boolean test(List<PM10Model> pm10Models) throws Exception {
                        return Preconditions.isNotBlank(pm10Models);
                    }
                })
                .flatMap(new Function<List<PM10Model>, MaybeSource<PM10Model>>() {
                    @Override
                    public MaybeSource<PM10Model> apply(List<PM10Model> pm10Models) throws Exception {

                        for (PM10Model model:pm10Models){

                            if ("南门".equals(model.position_name)) {

                                return Maybe.just(model);
                            }
                        }

                        return null;
                    }
                });

        Maybe<SO2Model> so2Maybe = apiService.so2(Constant.CITY_ID,Constant.TOKEN)
                .compose(RxJavaUtils.<List<SO2Model>>maybeToMain())
                .filter(new Predicate<List<SO2Model>>() {
                    @Override
                    public boolean test(List<SO2Model> so2Models) throws Exception {
                        return Preconditions.isNotBlank(so2Models);
                    }
                })
                .flatMap(new Function<List<SO2Model>, MaybeSource<SO2Model>>() {
                    @Override
                    public MaybeSource<SO2Model> apply(List<SO2Model> so2Models) throws Exception {

                        for (SO2Model model:so2Models){

                            if ("南门".equals(model.position_name)) {

                                return Maybe.just(model);
                            }
                        }

                        return null;
                    }
                });

        // 合并多个网络请求
        Maybe.zip(pm25Maybe, pm10Maybe, so2Maybe, new Function3<PM25Model, PM10Model, SO2Model, ZipObject>() {

            @Override
            public ZipObject apply(PM25Model pm25Model, PM10Model pm10Model, SO2Model so2Model) throws Exception {

                ZipObject zipObject = new ZipObject();
                zipObject.pm2_5_quality = pm25Model.quality;
                zipObject.pm2_5 = pm25Model.pm2_5;
                zipObject.pm2_5_24h = pm25Model.pm2_5_24h;

                zipObject.pm10 = pm10Model.pm10;
                zipObject.pm10_24h = pm10Model.pm10_24h;

                zipObject.so2 = so2Model.so2;
                zipObject.so2_24h = so2Model.so2_24h;

                return zipObject;
            }
        }).subscribe(new Consumer<ZipObject>() {
            @Override
            public void accept(ZipObject zipObject) throws Exception {

                quality.setText("空气质量指数：" + zipObject.pm2_5_quality);
                pm2_5.setText("PM2.5 1小时内平均：" + zipObject.pm2_5);
                pm2_5_24h.setText("PM2.5 24小时滑动平均：" + zipObject.pm2_5_24h);

                pm10.setText("PM10 1小时内平均："+zipObject.pm10);
                pm10_24h.setText("PM10 24小时滑动平均："+zipObject.pm10_24h);

                so2.setText("二氧化硫1小时平均："+zipObject.so2);
                so2_24h.setText("二氧化硫24小时滑动平均："+zipObject.so2_24h);

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println(throwable.getMessage());
            }
        });
    }
}
