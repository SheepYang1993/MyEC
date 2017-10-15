package me.sheepyang.latte.net;

import java.util.WeakHashMap;

import me.sheepyang.latte.net.callback.IError;
import me.sheepyang.latte.net.callback.IFailure;
import me.sheepyang.latte.net.callback.IRequest;
import me.sheepyang.latte.net.callback.ISuccess;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/10/15.
 */

public class RestClientBuilder {
    private String mUrl;
    private ISuccess mSuccess;
    private IFailure mFailure;
    private IError mError;
    private IRequest mRequest;
    private RequestBody mBody;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();

    RestClientBuilder() {
    }

    public RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public RestClientBuilder success(ISuccess success) {
        this.mSuccess = success;
        return this;
    }

    public RestClientBuilder failure(IFailure failure) {
        this.mFailure = failure;
        return this;
    }

    public RestClientBuilder error(IError error) {
        this.mError = error;
        return this;
    }

    public RestClientBuilder request(IRequest request) {
        this.mRequest = request;
        return this;
    }

    public RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public RestClientBuilder params(WeakHashMap<String, Object> params) {
        this.PARAMS.putAll(params);
        return this;
    }

    public RestClientBuilder params(String key, Object value) {
        this.PARAMS.put(key, value);
        return this;
    }

    public RestClient build() {
        return new RestClient(mUrl, PARAMS, mSuccess, mFailure, mError, mRequest, mBody);
    }
}
