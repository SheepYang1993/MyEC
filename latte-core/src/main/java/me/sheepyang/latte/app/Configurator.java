package me.sheepyang.latte.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by SheepYang on 2017-10-13.
 */

public class Configurator {
    //使用WeakHashMap，一些未使用的键值对会被系统回收
    private static final HashMap<String, Object> LATTE_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    final HashMap<String, Object> getLatteConfigtrations() {
        return LATTE_CONFIGS;
    }


    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 加载配置
     */
    public final void configure() {
        initIcons();
        //配置文件已配置好
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    /**
     * 初始化图标
     */
    private void initIcons() {
        if (ICONS.size() > 0) {
            Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    private final void checkConfigtrations() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configtration is not read, call configure");
        }

    }

    @SuppressWarnings("unchecked")
    final <T> T getConfigtrations(Enum<ConfigType> key) {
        checkConfigtrations();
        return (T) LATTE_CONFIGS.get(key.name());
    }
}
