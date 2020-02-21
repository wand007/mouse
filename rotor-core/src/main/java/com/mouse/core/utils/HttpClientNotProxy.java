package com.mouse.core.utils;

import okhttp3.*;
import okhttp3.MediaType;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-30
 */
public class HttpClientNotProxy {

    private static final OkHttpClient okHttpClient =
            new OkHttpClient().newBuilder().connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build();


    private HttpClientNotProxy() {
    }


    public static String post(String url, Map<String, String> param) throws IOException {
        FormBody.Builder builder = new FormBody.Builder();
        if (param != null) {
            for (String key : param.keySet()) {
                builder.add(key, param.get(key));
            }
        }
        RequestBody formBody = builder.build();
        Request request = new Request.Builder().url(url).post(formBody).build();

        Response response = execute(request);
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }


    /**
     * 同步post json数据
     *
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    public static String postJson(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = execute(request);
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }


    /**
     * 通用同步请求。
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static Response execute(Request request) throws IOException {
        return okHttpClient.newCall(request).execute();
    }

    /**
     * get请求
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String get(String url, Map<String, String> params, Map<String, String> addHeaders) throws IOException {
        Request.Builder reqBuild = new Request.Builder();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        if (!CollectionUtils.isEmpty(params)) {
            params.entrySet().forEach(e -> {
                urlBuilder.addQueryParameter(e.getKey(), e.getValue());
            });
        }
        if (!CollectionUtils.isEmpty(addHeaders)) {
            addHeaders.entrySet().forEach(e -> {
                reqBuild.addHeader(e.getKey(), e.getValue());
            });
        }

        reqBuild.url(urlBuilder.build());
        Request request = reqBuild.build();
        Response response = execute(request);
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * get请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String get(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = execute(request);
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }
}
