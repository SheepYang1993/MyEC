package me.sheepyang.latte.net;

import android.content.Context;

import java.io.File;
import java.util.WeakHashMap;

import me.sheepyang.latte.net.callback.IError;
import me.sheepyang.latte.net.callback.IFailure;
import me.sheepyang.latte.net.callback.IRequest;
import me.sheepyang.latte.net.callback.ISuccess;
import me.sheepyang.latte.ui.LatteLoader;
import me.sheepyang.latte.ui.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/10/15.
 */

public class RestClientBuilder {
    private String mUrl = null;
    private ISuccess mSuccess = null;
    private IFailure mFailure = null;
    private IError mError = null;
    private IRequest mRequest = null;
    private RequestBody mBody = null;
    private LoaderStyle mLoaderStyle = null;
    private Context mContext = null;
    private File mFile = null;
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mDownloadName = null;
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
        PARAMS.putAll(params);
        return this;
    }

    public RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LatteLoader.DEFAULT_LOADER_STYLE;
        return this;
    }

    public RestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public RestClientBuilder dir(String downloadDir) {
        this.mDownloadDir = downloadDir;
        return this;
    }

    public RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    public RestClientBuilder downloadName(String downloadName) {
        this.mDownloadName = downloadName;
        return this;
    }

    public RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public RestClient build() {
        return new RestClient(mUrl, PARAMS, mSuccess, mFailure, mError,
                mRequest, mLoaderStyle, mContext, mBody, mFile,
                mDownloadDir, mExtension, mDownloadName);
    }
}
