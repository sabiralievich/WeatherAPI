package ru.gb.rest;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.Objects;

// HOST https://openweathermap.org/
// API_KEY ada27606407517a4232c899f9f450d25
// example api.openweathermap.org/data/2.5/forecast?q={city name}&appid={API key}
public class WeatherRequest {
        private static final String HOST = "api.openweathermap.org";
        private static final String TYPE = "data";
        private static final String API_VERSION = "2.5";
        private static final String SERVICE = "forecast";
        private static final String CITY = "Saint Petersburg";
        private static final String STATE_CODE = "";
        private static final String COUNTRY_CODE = "RU";
        private static final String APPID = "ada27606407517a4232c899f9f450d25";
        private static final String LANG = "ru";
        private static final String UOM = "";
        private static final String CNT = "40";
        private static final String UNITS = "metric";
        private static String jsonResponse;

        public String getJsonResponse() {
            return jsonResponse;
        }

         public void requestFromServer(String city, String cnt) throws IOException {

            OkHttpClient client = new OkHttpClient();
            HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host(HOST)
                .addPathSegment(TYPE)
                .addPathSegment(API_VERSION)
                .addPathSegment(SERVICE)
                .addQueryParameter("q", city)
                .addQueryParameter("lang", LANG)
                .addQueryParameter("appid", APPID)
                .addQueryParameter("cnt", cnt)
                .addQueryParameter("units", UNITS)
                .build();

 //           System.out.println(url);
            Request requestHttp = new Request.Builder()
                .url(url)
                .build();
            jsonResponse = Objects.requireNonNull(client.newCall(requestHttp).execute().body()).string();
         //   System.out.println(jsonResponse);
    }

}
