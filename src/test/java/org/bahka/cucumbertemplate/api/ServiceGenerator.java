package org.bahka.cucumbertemplate.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

public class ServiceGenerator {
    private static final int DEFAULT_TIMEOUT = 60;
    private static final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static final HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE);

    public static <S> S createService(String endpoint, Class<S> service) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(endpoint + "/")
                .addConverterFactory(JacksonConverterFactory.create());

        httpClient.retryOnConnectionFailure(true);
        httpClient.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClient.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClient.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClient.addInterceptor(logging);
        httpClient.addInterceptor(chain -> {
            Request request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .build();
            return chain.proceed(request);
        });
        httpClient.followRedirects(false);

        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(service);
    }
}
