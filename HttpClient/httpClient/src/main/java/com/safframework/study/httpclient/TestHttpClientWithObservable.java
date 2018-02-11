package com.safframework.study.httpclient;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Created by tony on 2017/9/11.
 */
public class TestHttpClientWithObservable {

    public static void main(String[] args) {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {

                String url = "http://www.163.com";
                e.onNext(url);
            }
        }).map(new io.reactivex.functions.Function<String, CloseableHttpResponse>() {

            @Override
            public CloseableHttpResponse apply(@NonNull String url) throws Exception {

                CloseableHttpClient client = HttpClients.createDefault();

                HttpGet get = new HttpGet(url);

                return client.execute(get);
            }
        }).subscribe(new Consumer<CloseableHttpResponse>() {
            @Override
            public void accept(CloseableHttpResponse response) throws Exception {

                // 服务器返回码
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println("statusCode = " + statusCode);

                HttpEntity entity = response.getEntity();

                // 服务器返回内容
                String respStr = null;

                if (entity != null) {
                    respStr = EntityUtils.toString(entity, "UTF-8");
                }

                System.out.println(respStr);

                // 释放资源
                EntityUtils.consume(entity);
            }
        });

    }
}
