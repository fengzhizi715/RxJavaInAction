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
import com.safframework.tony.common.utils.Preconditions;
import com.safframework.utils.RxJavaUtils;

import java.util.List;

import io.reactivex.functions.Consumer;

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
                .subscribe(new Consumer<List<PM25Model>>() {
                    @Override
                    public void accept(List<PM25Model> pm25Models) throws Exception {

                        if (Preconditions.isNotBlank(pm25Models)){

                            for (PM25Model model:pm25Models){

                                if ("南门".equals(model.position_name)) {

                                    quality.setText("空气质量指数："+model.quality);
                                    pm2_5.setText("1小时内平均PM2.5："+model.pm2_5);
                                    pm2_5_24h.setText("24小时滑动平均PM2.5："+model.pm2_5_24h);
                                    break;
                                }
                            }
                        }
                    }
                });

        apiService.pm10(cityId,token)
                .compose(RxJavaUtils.<List<PM10Model>>maybeToMain())
                .subscribe(new Consumer<List<PM10Model>>() {
                    @Override
                    public void accept(List<PM10Model> pm10Models) throws Exception {

                        if (Preconditions.isNotBlank(pm10Models)){

                            for (PM10Model model:pm10Models){

                                if ("南门".equals(model.position_name)) {

                                    pm10.setText("1小时内平均PM10："+model.pm10);
                                    pm10_24h.setText("24小时滑动平均PM10："+model.pm10_24h);
                                    break;
                                }
                            }
                        }
                    }
                });
    }
}
