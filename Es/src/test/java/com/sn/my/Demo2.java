package com.sn.my;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;

import java.util.Collections;
import java.util.concurrent.CountDownLatch;

public class Demo2 {
    public static void main(String[] args) throws Exception{
        RestClient restClient = RestClient.builder(new HttpHost("localhost",9200,"http"),
                new HttpHost("localhost",9201,"http"))
                .build();
        HttpEntity entity = new NStringEntity(
                "{\n" +
                        "    \"user\" : \"kimchy\",\n" +
                        "    \"post_date\" : \"2009-11-15T14:12:12\",\n" +
                        "    \"message\" : \"trying out Elasticsearch\"\n" +
                        "}", ContentType.APPLICATION_JSON);
        int numRequests = 10;
        final CountDownLatch latch = new CountDownLatch(numRequests);

        for(int i=0;i<numRequests;i++) {
            restClient.performRequestAsync("PUT", "/twitter/tweet/" + i,
                    Collections.<String, String>emptyMap(), entity, new ResponseListener() {
                        @Override
                        public void onSuccess(Response response) {
                            System.out.println(response);
                            latch.countDown();
                        }

                        @Override
                        public void onFailure(Exception exception) {
                            latch.countDown();
                        }
                    });
        }

        latch.await();
    }
}
