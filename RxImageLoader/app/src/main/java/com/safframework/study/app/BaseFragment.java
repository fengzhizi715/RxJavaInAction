package com.safframework.study.app;

import android.support.v4.app.Fragment;

/**
 * Created by Tony Shen on 2017/6/23.
 */

public class BaseFragment extends Fragment {

    @Override
    public void onLowMemory() {
        super.onLowMemory();
//        app.imageLoader.clearMemCache();
    }
}
