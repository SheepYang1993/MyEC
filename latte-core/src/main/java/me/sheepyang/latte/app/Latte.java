package me.sheepyang.latte.app;

import android.content.Context;

import java.util.HashMap;

/**
 * @author SheepYang
 * @date on 2017-10-13
 * @describe Latte框架相关工具
 */

public final class Latte {
    public static Configurator init(Context context) {
        getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<Object, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigtrations();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    /**
     * 获取全局上下文
     *
     * @return
     */
    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }
}
