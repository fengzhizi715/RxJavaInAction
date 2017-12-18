package com.safframework.study.operator.activity.create;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.jakewharton.rxbinding2.view.RxView;
import com.safframework.injectview.annotations.InjectExtra;
import com.safframework.injectview.annotations.InjectView;
import com.safframework.router.RouterRule;
import com.safframework.study.operator.R;
import com.safframework.study.operator.app.BaseActivity;
import com.safframework.study.operator.utils.RxUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import thereisnospon.codeview.CodeView;
import thereisnospon.codeview.CodeViewTheme;

/**
 * Created by tony on 2017/12/18.
 */
@RouterRule(url={"interval/:title"})
public class IntervalActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.codeview)
    CodeView codeView;

    @InjectExtra(key = "title")
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval);

        initData();
    }

    private void initData() {

        toolbar.setTitle("< "+ title);

        RxView.clicks(toolbar)
                .compose(RxUtils.useRxViewTransformer(this))
                .subscribe(new Consumer<Object>() {

                    @Override
                    public void accept(Object o) throws Exception {

                        finish();
                    }
                });

        codeView.setTheme(CodeViewTheme.ANDROIDSTUDIO).fillColor();

        StringBuilder code = new StringBuilder();
        code.append("Observable.interval(10,TimeUnit.SECONDS)")
                .append("\r\n")
                .append("    .subscribe(new Consumer<Long>() {")
                .append("\r\n")
                .append("        @Override")
                .append("\r\n")
                .append("        public void accept(Long aLong) throws Exception {")
                .append("\r\n")
                .append("            System.out.println(aLong);")
                .append("\r\n")
                .append("        }")
                .append("\r\n")
                .append("   });");

        codeView.showCode(code.toString());
    }
}
