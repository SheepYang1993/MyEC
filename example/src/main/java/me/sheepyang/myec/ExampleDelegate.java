package me.sheepyang.myec;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import me.sheepyang.latte.delegates.LatteDelegate;
import me.sheepyang.latte.net.RestClient;
import me.sheepyang.latte.net.callback.IRequest;

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

    }

    private void testRestBuilder() {
        RestClient.builder()
                .url("")
                .params("", "")
                .request(new IRequest() {
                    @Override
                    public void onRequestStart() {

                    }

                    @Override
                    public void onRequestEnd() {

                    }
                })
                .build();
    }
}
