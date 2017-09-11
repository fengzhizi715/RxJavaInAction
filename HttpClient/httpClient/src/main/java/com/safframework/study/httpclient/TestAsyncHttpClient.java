package com.safframework.study.httpclient;

import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;

/**
 * Created by tony on 2017/9/11.
 */
public class TestAsyncHttpClient {

    public static void main(String[] args) {

        AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
        asyncHttpClient.prepareGet("http://www.163.com/").execute(new AsyncCompletionHandler<Response>(){

            @Override
            public Response onCompleted(Response response) throws Exception{

                System.out.println(response.getResponseBody());
                return response;
            }

            @Override
            public void onThrowable(Throwable t){
            }
        });
    }
}
