package me.sheepyang.latte.ui;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * @author SheepYang
 * @date 2017-10-16 11:07
 * @describe LoadingView构造工具
 */

public class LoaderCreator {
    private static final WeakHashMap<String, Indicator> LOADER_MAP = new WeakHashMap<>();

    static AVLoadingIndicatorView create(Context context, String type) {
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOADER_MAP.get(type) == null) {
            final Indicator indicator = getIndicator(type);
            LOADER_MAP.put(type, indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADER_MAP.get(type));
        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        final StringBuilder indicatorClassName = new StringBuilder();
        if (!name.contains(".")) {
            indicatorClassName.append(AVLoadingIndicatorView.class.getPackage().getName());
            indicatorClassName.append(".indicators");
            indicatorClassName.append(".");
        }
        indicatorClassName.append(name);
        try {
            final Class<?> indicator = Class.forName(indicatorClassName.toString());
            return (Indicator) indicator.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
