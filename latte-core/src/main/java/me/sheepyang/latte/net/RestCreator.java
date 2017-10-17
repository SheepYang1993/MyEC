package me.sheepyang.latte.net;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import me.sheepyang.latte.app.ConfigKeys;
import me.sheepyang.latte.app.Latte;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * Created by Administrator on 2017/10/14.
 */

public class RestCreator {

    private static final class ParamsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    private static final class RetrofitHolder {
        private static final String BASE_HOST = (String) Latte.getConfigurations().get(ConfigKeys.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_HOST)
                .client(OkhttpHolder.OKHTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private static final class OkhttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OKHTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private final static class RestServiceHolder {
        private final static RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }
}
