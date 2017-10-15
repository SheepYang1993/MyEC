package me.sheepyang.latte.net;

import java.util.WeakHashMap;

import me.sheepyang.latte.net.callback.IError;
import me.sheepyang.latte.net.callback.IFailure;
import me.sheepyang.latte.net.callback.IRequest;
import me.sheepyang.latte.net.callback.ISuccess;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/10/14.
 */

public class RestClient {
    private final String URL;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final IRequest REQUEST;
    private final RequestBody BODY;
    private final static WeakHashMap<String, Object> PARAMS = RestCreator.getParams();

    public RestClient(String url,
                      WeakHashMap<String, Object> params,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      IRequest request,
                      RequestBody body) {
        this.URL = url;
        this.PARAMS.putAll(params);
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.REQUEST = request;
        this.BODY = body;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }
}
