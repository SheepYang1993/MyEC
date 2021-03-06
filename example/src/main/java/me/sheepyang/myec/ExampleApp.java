package me.sheepyang.myec;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import me.sheepyang.latte.app.Latte;
import me.sheepyang.latte.ec.icon.FontEcModule;
import me.sheepyang.latte.net.Interceptors.DebugInterceptor;

/**
 * Created by SheepYang on 2017-10-13.
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .withApiHost("https://easy-mock.com")
                .configure();
    }
}
