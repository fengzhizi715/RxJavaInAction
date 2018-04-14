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
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.safframework.study.operator.activity.create.TimerActivity.java
 * @author: Tony Shen
 * @date: 2018-04-10 00:49
 */
@RouterRule(url={"timer/:title"})
public class TimerActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.codeview)
    CodeView codeView;

    @InjectExtra(key = "title")
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

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

        codeView.showCode(code.toString());
    }
}
