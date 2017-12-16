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

import io.reactivex.functions.Consumer;
import thereisnospon.codeview.CodeView;
import thereisnospon.codeview.CodeViewTheme;

/**
 * Created by tony on 2017/12/16.
 */
@RouterRule(url={"defer/:title"})
public class DeferActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.codeview)
    CodeView codeView;

    @InjectExtra(key = "title")
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defer);

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
        code.append("Observable observable = Observable.defer(new Callable<ObservableSource<? extends String>>() {")
                .append("\r\n")
                .append("    @Override")
                .append("\r\n")
                .append("    public ObservableSource<? extends String> call() throws Exception {")
                .append("\r\n")
                .append("        return Observable.just(\"hello defer\");")
                .append("\r\n")
                .append("    }")
                .append("\r\n")
                .append("});")
                .append("\r\n\r\n")
                .append("observable.subscribe(new Consumer<String>() {")
                .append("\r\n")
                .append("    @Override")
                .append("\r\n")
                .append("    public void accept(String str) throws Exception {")
                .append("\r\n")
                .append("        System.out.println(str);")
                .append("\r\n")
                .append("    }")
                .append("\r\n")
                .append("});");

        codeView.showCode(code.toString());
    }
}
