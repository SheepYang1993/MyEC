package me.sheepyang.latte.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import me.sheepyang.latte.app.Latte;

/**
 * @author SheepYang
 * @date on 2017-10-16 15:04
 * @describe 尺寸相关工具类
 */

public class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
