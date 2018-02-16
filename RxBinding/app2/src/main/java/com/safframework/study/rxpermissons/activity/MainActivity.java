package com.safframework.study.rxpermissons.activity;

import com.safframework.study.rxpermissons.app.BaseActivity;

public class MainActivity extends BaseActivity {

//    @InjectView(R.id.text1)
//    TextView text1;
//
//    @InjectView(R.id.text2)
//    TextView text2;
//
//    @InjectView(R.id.text3)
//    TextView text3;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        initViews();
//    }
//
//    private void initViews() {
//
//        RxView.clicks(text1)
//                .subscribe(new Consumer<Object>() {
//                    @Override
//                    public void accept(@NonNull Object o) throws Exception {
//
//                        Toast.makeText(MainActivity.this,"演示点击事件",Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//        RxView.longClicks(text2)
//                .subscribe(new Consumer<Object>() {
//                    @Override
//                    public void accept(@NonNull Object o) throws Exception {
//
//                        Toast.makeText(MainActivity.this,"演示长点击事件",Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//        RxView.clicks(text3)
//                .compose(RxUtils.useRxViewTransformer(MainActivity.this))
//                .subscribe(new Consumer<Object>() {
//                    @Override
//                    public void accept(@NonNull Object o) throws Exception {
//
//                        Toast.makeText(MainActivity.this,"防止重复点击",Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
}
