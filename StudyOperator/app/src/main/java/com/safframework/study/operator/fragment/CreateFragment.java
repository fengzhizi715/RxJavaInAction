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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create, container, false);
        Injector.injectInto(this, v);

        initViews();

        return v;
    }

    private void initViews() {
        RxView.clicks(createView)
                .compose(RxUtils.useRxViewTransformer(CreateFragment.this))
                .subscribe(new Consumer<Object>() {

                    @Override
                    public void accept(Object o) throws Exception {

                        Router.getInstance().open("create/"+createView.getText());
                    }
                });

        RxView.clicks(justView)
                .compose(RxUtils.useRxViewTransformer(CreateFragment.this))
                .subscribe(new Consumer<Object>() {

                    @Override
                    public void accept(Object o) throws Exception {

                        Router.getInstance().open("just/"+justView.getText());
                    }
                });

        RxView.clicks(fromView)
                .compose(RxUtils.useRxViewTransformer(CreateFragment.this))
                .subscribe(new Consumer<Object>() {

                    @Override
                    public void accept(Object o) throws Exception {

                        Router.getInstance().open("from/"+fromView.getText());
                    }
                });

        RxView.clicks(repeatView)
                .compose(RxUtils.useRxViewTransformer(CreateFragment.this))
                .subscribe(new Consumer<Object>() {

                    @Override
                    public void accept(Object o) throws Exception {

                        Router.getInstance().open("repeat/"+repeatView.getText());
                    }
                });
    }
}
