package com.safframework.study.operator.utils

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.safframework.lifecycle.RxLifecycle
import com.safframework.utils.RxJavaUtils
import io.reactivex.ObservableTransformer

/**
 * Created by Tony Shen on 2017/7/20.
 */
object RxUtils {

    /**
     * 对RxView绑定的事件
     * 封装了防止重复点击和RxLifecycle的生命周期
     */
    @JvmStatic
    fun <T> useRxViewTransformer(targetActivity: AppCompatActivity): ObservableTransformer<T, T> {

        return ObservableTransformer { upstream ->
            upstream.compose(RxJavaUtils.preventDuplicateClicksTransformer())
                    .compose(RxLifecycle.bind(targetActivity).toLifecycleTransformer())
        }
    }

    /**
     * 对RxView绑定的事件
     * 封装了防止重复点击和RxLifecycle的生命周期
     */
    @JvmStatic
    fun <T> useRxViewTransformer(targetFragment: Fragment): ObservableTransformer<T, T> {

        return ObservableTransformer { upstream ->
            upstream.compose(RxJavaUtils.preventDuplicateClicksTransformer())
                    .compose(RxLifecycle.bind(targetFragment).toLifecycleTransformer())
        }
    }

    @JvmStatic
    fun <T> routerUriTransformer(textView: TextView): ObservableTransformer<T, String> {

        return ObservableTransformer { upstream ->
            upstream.map {
                it -> textView.getText().toString().toLowerCase() + "/" + textView.getText().toString()
            }
        }
    }
}