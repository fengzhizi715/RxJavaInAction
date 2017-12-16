package com.safframework.study.operator.activity.conditional;

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
@RouterRule(url={"amb/:title"})
public class AmbActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.codeview)
    CodeView codeView;

    @InjectExtra(key = "title")
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amb);

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
        code.append("Observable.ambArray(")
                .append("\r\n")
                .append("    Observable.just(1,2,3),")
                .append("\r\n")
                .append("    Observable.just(4,5,6))")
                .append("\r\n")
                .append("    .subscribe(new Consumer<Integer>() {")
                .append("\r\n")
                .append("        @Override")
                .append("\r\n")
                .append("        public void accept(Integer integer) throws Exception {")
                .append("\r\n")
                .append("             System.out.println(\"integer:\"+integer);")
                .append("\r\n")
                .append("        }")
                .append("\r\n")
                .append("    });");

        codeView.showCode(code.toString());
    }
}
