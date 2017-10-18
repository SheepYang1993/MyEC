package me.sheepyang.latte.net.rx;

import android.content.Context;

import java.io.File;
import java.util.WeakHashMap;

import me.sheepyang.latte.ui.LatteLoader;
import me.sheepyang.latte.ui.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/10/15.
 */

public class RxRestClientBuilder {
    private String mUrl = null;
    private RequestBody mBody = null;
    private LoaderStyle mLoaderStyle = null;
    private Context mContext = null;
    private File mFile = null;
    private static final WeakHashMap<String, Object> PARAMS = RxRestCreator.getParams();

    RxRestClientBuilder() {
    }

    public RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public RxRestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public RxRestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public RxRestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public RxRestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LatteLoader.DEFAULT_LOADER_STYLE;
        return this;
    }

    public RxRestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public RxRestClient build() {
        return new RxRestClient(mUrl, PARAMS, mLoaderStyle, mContext, mBody, mFile);
    }
}
