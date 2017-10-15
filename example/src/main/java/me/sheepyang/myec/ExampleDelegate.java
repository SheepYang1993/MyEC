package me.sheepyang.myec;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import me.sheepyang.latte.app.Latte;
import me.sheepyang.latte.delegates.LatteDelegate;
import me.sheepyang.latte.net.RestClient;
import me.sheepyang.latte.net.callback.IRequest;
import me.sheepyang.latte.net.callback.ISuccess;

/**
 * Created by Administrator on 2017/10/14.
 */

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {
        testRestBuilder();
    }

    private void testRestBuilder() {
        RestClient.builder()
                .url("http://www.4399.com")
                .request(new IRequest() {
                    @Override
                    public void onRequestStart() {

                    }

                    @Override
                    public void onRequestEnd() {

                    }
                })
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(Latte.getApplication(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .get();
    }
}
