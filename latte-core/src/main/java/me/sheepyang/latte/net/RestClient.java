package me.sheepyang.latte.net;

import android.content.Context;

import java.io.File;
import java.util.WeakHashMap;

import me.sheepyang.latte.net.callback.IError;
import me.sheepyang.latte.net.callback.IFailure;
import me.sheepyang.latte.net.callback.IRequest;
import me.sheepyang.latte.net.callback.ISuccess;
import me.sheepyang.latte.net.callback.RequestCallBacks;
import me.sheepyang.latte.net.download.DownloadHandler;
import me.sheepyang.latte.ui.LatteLoader;
import me.sheepyang.latte.ui.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Administrator on 2017/10/14.
 * `
 *
 * @author SheepYang
 */

public class RestClient {
    private final String URL;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final IRequest REQUEST;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;
    private final RequestBody BODY;
    private final File FILE;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String DOWNLOAD_NAME;
    private final static WeakHashMap<String, Object> PARAMS = RestCreator.getParams();

    public RestClient(String url,
                      WeakHashMap<String, Object> params,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      IRequest request,
                      LoaderStyle loaderStyle,
                      Context context,
                      RequestBody body,
                      File file,
                      String downloadDir,
                      String extension,
                      String downloadName) {
        this.URL = url;
        RestClient.PARAMS.putAll(params);
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.REQUEST = request;
        this.LOADER_STYLE = loaderStyle;
        this.CONTEXT = context;
        this.BODY = body;
        this.FILE = file;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.DOWNLOAD_NAME = downloadName;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }


    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = service.upload(URL, body);
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallBacks());
        }
    }

    private Callback<String> getRequestCallBacks() {
        return new RequestCallBacks(REQUEST, SUCCESS, FAILURE, ERROR, LOADER_STYLE);
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

    public final void download() {
        new DownloadHandler(URL, REQUEST, DOWNLOAD_DIR, EXTENSION, DOWNLOAD_NAME,
                SUCCESS, FAILURE, ERROR)
                .handleDownload();
    }
}
