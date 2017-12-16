package com.safframework.study.operator.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.safframework.injectview.Injector;
import com.safframework.injectview.annotations.InjectView;
import com.safframework.log.L;
import com.safframework.router.Router;
import com.safframework.study.operator.R;
import com.safframework.study.operator.app.BaseFragment;
import com.safframework.study.operator.utils.RxUtils;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by tony on 2017/11/4.
 */

public class CreateFragment extends BaseFragment {

    @InjectView(R.id.text1)
    TextView createView;

    @InjectView(R.id.text2)
    TextView justView;

    @InjectView(R.id.text3)
    TextView fromView;

    @InjectView(R.id.text4)
    TextView repeatView;

    @InjectView(R.id.text5)
    TextView deferView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create, container, false);
        Injector.injectInto(this, v);

        initViews();

        return v;
    }

    private void initViews() {
        RxView.clicks(createView)
                .map(new Function<Object, String>() {

                    @Override
                    public String apply(Object o) throws Exception {
                        return createView.getText().toString();
                    }
                })
                .compose(RxUtils.<String>useRxViewTransformer(CreateFragment.this))
                .subscribe(new Consumer<String>() {

                    @Override
                    public void accept(String s) throws Exception {

                        Router.getInstance().open("create/"+s);
                    }
                });

        RxView.clicks(justView)
                .map(new Function<Object, String>() {

                    @Override
                    public String apply(Object o) throws Exception {
                        return justView.getText().toString();
                    }
                })
                .compose(RxUtils.<String>useRxViewTransformer(CreateFragment.this))
                .subscribe(new Consumer<String>() {

                    @Override
                    public void accept(String s) throws Exception {

                        Router.getInstance().open("just/"+s);
                    }
                });

        RxView.clicks(fromView)
                .map(new Function<Object, String>() {

                    @Override
                    public String apply(Object o) throws Exception {
                        return fromView.getText().toString();
                    }
                })
                .compose(RxUtils.<String>useRxViewTransformer(CreateFragment.this))
                .subscribe(new Consumer<String>() {

                    @Override
                    public void accept(String s) throws Exception {

                        Router.getInstance().open("from/"+s);
                    }
                });

        RxView.clicks(repeatView)
                .map(new Function<Object, String>() {

                    @Override
                    public String apply(Object o) throws Exception {
                        return repeatView.getText().toString();
                    }
                })
                .compose(RxUtils.<String>useRxViewTransformer(CreateFragment.this))
                .subscribe(new Consumer<String>() {

                    @Override
                    public void accept(String s) throws Exception {

                        Router.getInstance().open("repeat/"+s);
                    }
                });

        RxView.clicks(deferView)
                .map(new Function<Object, String>() {

                    @Override
                    public String apply(Object o) throws Exception {
                        return deferView.getText().toString();
                    }
                })
                .compose(RxUtils.<String>useRxViewTransformer(CreateFragment.this))
                .subscribe(new Consumer<String>() {

                    @Override
                    public void accept(String s) throws Exception {

                        Router.getInstance().open("defer/"+s);
                    }
                });
    }
}
