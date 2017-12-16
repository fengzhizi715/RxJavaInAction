package com.safframework.study.operator.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.safframework.injectview.Injector;
import com.safframework.study.operator.R;
import com.safframework.study.operator.app.BaseFragment;

/**
 * Created by tony on 2017/12/16.
 */

public class ConditionalFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_conditional, container, false);
        Injector.injectInto(this, v);

        initViews();

        return v;
    }

    private void initViews() {
    }
}
