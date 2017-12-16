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
 * Created by tony on 2017/12/15.
 */
@RouterRule(url={"create/:title"})
public class CreateActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.codeview)
    CodeView codeView;

    @InjectExtra(key = "title")
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

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
        code.append("Observable.create(new ObservableOnSubscribe<Integer>() {")
                .append("\r\n")
                .append("    @Override")
                .append("\r\n")
                .append("    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {")
                .append("\r\n")
                .append("          try {")
                .append("\r\n")
                .append("               if (!emitter.isDisposed()) {")
                .append("\r\n")
                .append("                     for (int i = 0; i < 10; i++) {")
                .append("\r\n")
                .append("                          emitter.onNext(i);")
                .append("\r\n")
                .append("                     }")
                .append("\r\n")
                .append("                     emitter.onComplete();")
                .append("\r\n")
                .append("               }")
                .append("\r\n")
                .append("           } catch (Exception e) {")
                .append("\r\n")
                .append("                emitter.onError(e);")
                .append("\r\n")
                .append("           } ")
                .append("\r\n")
                .append("       } ")
                .append("\r\n")
                .append("  }).subscribe(new Consumer<Integer>() {")
                .append("\r\n")
                .append("    @Override")
                .append("\r\n")
                .append("    public void accept(Integer integer) throws Exception {")
                .append("\r\n")
                .append("         System.out.println(\"Next: \" + integer);")
                .append("\r\n")
                .append("       } ")
                .append("\r\n")
                .append("   }, new Consumer<Throwable>() {")
                .append("\r\n")
                .append("    @Override")
                .append("\r\n")
                .append("    public void accept(Throwable throwable) throws Exception {")
                .append("\r\n")
                .append("         System.err.println(\"Error: \" + throwable.getMessage());")
                .append("\r\n")
                .append("       } ")
                .append("\r\n")
                .append("   }, new Action() {")
                .append("\r\n")
                .append("    @Override")
                .append("\r\n")
                .append("    public void run() throws Exception {")
                .append("\r\n")
                .append("         System.out.println(\"Sequence complete.\");")
                .append("\r\n")
                .append("       } ")
                .append("\r\n")
                .append("   });");


        codeView.showCode(code.toString());
    }

}
