package apitest.framework.utils;

import io.qameta.allure.okhttp3.AllureOkHttp3;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private RetrofitUtils() {
    }

    private static final String API_BASE_URL = "http://api.languagelayer.com";
    private static Retrofit instance;

    public static <T> T createApiEndPoint(Class<T> clazz) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new AllureOkHttp3())
                .build();
        return initRetrofit(API_BASE_URL, okHttpClient).create(clazz);
    }

    private static Retrofit initRetrofit(String baseUrl, OkHttpClient client) {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return instance;
    }
}
