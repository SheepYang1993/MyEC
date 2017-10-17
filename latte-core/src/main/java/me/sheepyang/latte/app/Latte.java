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

    /**
     * 获取全局上下文
     *
     * @return
     */
    public static Context getApplicationContext() {
        return (Context) getConfigurations().get(ConfigKeys.APPLICATION_CONTEXT);
    }
}
