package com.safframework.study.operator.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.safframework.injectview.Injector;
import com.safframework.injectview.annotations.InjectView;
import com.safframework.router.Router;
import com.safframework.study.operator.R;
import com.safframework.study.operator.app.BaseFragment;
import com.safframework.study.operator.rxjava.RouterConsumer;
import com.safframework.study.operator.utils.RxUtils;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by tony on 2017/12/16.
 */

public class ConditionalFragment extends BaseFragment {

    @InjectView(R.id.text1)
    TextView ambView;

    @InjectView(R.id.text2)
    TextView defaultIfEmptyView;

    @InjectView(R.id.text3)
    TextView skipUntilView;

    @InjectView(R.id.text4)
    TextView skipWhileView;

    private RouterConsumer consumer = new RouterConsumer();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_conditional, container, false);
        Injector.injectInto(this, v);

        initViews();

        return v;
    }

    private void initViews() {

        RxView.clicks(ambView)
                .compose(RxUtils.routerUriTransformer(ambView))
                .compose(RxUtils.<String>useRxViewTransformer(ConditionalFragment.this))
                .subscribe(consumer);

        RxView.clicks(defaultIfEmptyView)
                .compose(RxUtils.routerUriTransformer(defaultIfEmptyView))
                .compose(RxUtils.<String>useRxViewTransformer(ConditionalFragment.this))
                .subscribe(consumer);
    }
}
