package ru.gb;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.Objects;
import java.util.PrimitiveIterator;

public class GetWeather {
    private static final String YANDEX_API_KEY = "05201440-baff-4d85-ab8e-ef55c380f464";
    private static final String LON = "30.315877";
    private static final String LAT = "59.939099";
    private static final String LANG = "ru_RU";
    private static final String HOST = "api.weather.yandex.ru";
    private static final String API_VERSION = "v2";
    private static final String SERVICE = "forecast";
    // https://api.weather.yandex.ru/v2/forecast/
    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient();

        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host(HOST)
                .addPathSegment(API_VERSION)
                .addPathSegment(SERVICE)
                .addQueryParameter("lat", LAT)
                .addQueryParameter("lon", LON)
                .addQueryParameter("lang", LANG)
                .build();
        System.out.println(url);
        Request requestHttp = new Request.Builder()
                .addHeader("X-Yandex-API-Key", YANDEX_API_KEY)
                .url(url)
                .build();

        String jsonResponse = Objects.requireNonNull(client.newCall(requestHttp).execute().body()).string();
        System.out.println(jsonResponse);
    }
}
