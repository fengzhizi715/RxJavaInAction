package com.safframework.study.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by tony on 2017/9/11.
 */
public class TestHttpClient {

    public static void main(String[] args) {

        HttpEntity entity = null;

        try {
            String url = "http://www.163.com";

            // 使用默认配置创建httpclient的实例
            CloseableHttpClient client = HttpClients.createDefault();

            HttpGet get = new HttpGet(url);

            CloseableHttpResponse response = client.execute(get);

            // 服务器返回码
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("statusCode = " + statusCode);

            // 服务器响应成功
            if (statusCode==200) {
                // 服务器返回内容
                String respStr = null;
                entity = response.getEntity();
                if(entity != null) {
                    respStr = EntityUtils.toString(entity, "UTF-8");
                }
                System.out.println(respStr);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (entity!=null) {
                // 释放资源
                try {
                    EntityUtils.consume(entity);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
