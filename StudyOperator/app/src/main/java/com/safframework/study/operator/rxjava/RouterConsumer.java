package com.safframework.study.operator.rxjava;

import com.safframework.router.Router;

import io.reactivex.functions.Consumer;

/**
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.safframework.study.operator.rxjava.RouterConsumer.java
 * @author: Tony Shen
 * @date: 2018-04-09 14:54
 */
public class RouterConsumer implements Consumer<String> {

    @Override
    public void accept(String uri) throws Exception {

        Router.getInstance().open(uri);
    }
}
