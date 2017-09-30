package com.safframework.study.retrofit.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.safframework.injectview.annotations.InjectView;
import com.safframework.study.retrofit.R;
import com.safframework.study.retrofit.api.APIService;
import com.safframework.study.retrofit.app.BaseActivity;
import com.safframework.study.retrofit.http.RetrofitManager;
import com.safframework.study.retrofit.model.PM10Model;
import com.safframework.study.retrofit.model.PM25Model;
import com.safframework.study.retrofit.model.SO2Model;
import com.safframework.tony.common.utils.Preconditions;
import com.safframework.utils.RxJavaUtils;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by tony on 2017/9/29.
 */

public class TestRetrofitActivity extends BaseActivity {

    private String cityId = "苏州";
    private String token = "5j1znBVAsnSf5xQyNQyq";

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

        apiService.pm25(cityId,token)
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
                })
                .subscribe(new Consumer<PM25Model>() {
                    @Override
                    public void accept(PM25Model model) throws Exception {

                        if (model!=null) {
                            quality.setText("空气质量指数："+model.quality);
                            pm2_5.setText("PM2.5 1小时内平均："+model.pm2_5);
                            pm2_5_24h.setText("PM2.5 24小时滑动平均："+model.pm2_5_24h);
                        }
                    }
                });

        apiService.pm10(cityId,token)
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
                })
                .subscribe(new Consumer<PM10Model>() {
                    @Override
                    public void accept(PM10Model model) throws Exception {

                        if (model!=null) {

                            pm10.setText("PM10 1小时内平均："+model.pm10);
                            pm10_24h.setText("PM10 24小时滑动平均："+model.pm10_24h);
                        }
                    }
                });

        apiService.so2(cityId,token)
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
                })
                .subscribe(new Consumer<SO2Model>() {
                    @Override
                    public void accept(SO2Model model) throws Exception {

                        if (model!=null) {

                            so2.setText("二氧化硫1小时平均："+model.so2);
                            so2_24h.setText("二氧化硫24小时滑动平均："+model.so2_24h);
                        }
                    }
                });
    }
}
