package me.sheepyang.myec;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

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
                .loader(getContext())
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
//                        Toast.makeText(Latte.getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .get();
    }
}
