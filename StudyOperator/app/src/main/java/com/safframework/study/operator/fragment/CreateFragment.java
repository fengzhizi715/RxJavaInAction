package com.safframework.study.operator.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.safframework.injectview.Injector;
import com.safframework.study.operator.R;
import com.safframework.study.operator.app.BaseFragment;

/**
 * Created by tony on 2017/11/4.
 */

public class CreateFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create, container, false);
        Injector.injectInto(this, v);

        return v;
    }
}
