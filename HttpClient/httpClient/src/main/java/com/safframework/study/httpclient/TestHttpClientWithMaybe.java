package com.safframework.study.httpclient;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Created by tony on 2017/9/11.
 */
public class TestHttpClientWithMaybe {

    public static void main(String[] args) {

        Maybe.create(new MaybeOnSubscribe<String>() {

            @Override
            public void subscribe(@NonNull MaybeEmitter<String> e) throws Exception {
                String url = "http://www.163.com";
                e.onSuccess(url);
            }
        }).map(new Function<String, CloseableHttpResponse>() {

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
                int status_code = response.getStatusLine().getStatusCode();
                System.out.println("status_code = " + status_code);

                HttpEntity entity = response.getEntity();

                // 服务器返回内容
                String respStr = null;

                if (entity != null) {
                    respStr = EntityUtils.toString(entity, "UTF-8");
                }

                // 释放资源
                EntityUtils.consume(entity);

                System.out.println("respStr = " + respStr);
            }
        });
    }
}
