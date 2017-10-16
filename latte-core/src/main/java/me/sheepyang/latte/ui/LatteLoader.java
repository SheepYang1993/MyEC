package me.sheepyang.latte.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import me.sheepyang.latte.R;
import me.sheepyang.latte.util.DimenUtil;

/**
 * @author SheepYang
 * @date on 2017-10-16 14:46
 * @describe Latte关于LoadingDialog工具类
 */

public class LatteLoader {
    public static final LoaderStyle DEFAULT_LOADER_STYLE = LoaderStyle.BallSpinFadeLoaderIndicator;
    private static final ArrayList<AppCompatDialog> LOADER_DIALOGS = new ArrayList<>();
    private static final int LOADER_SIZE_SCALE = 8;
    private static final int LOADER_HEIGHT_OFFSET_SCALE = 10;

    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADER_STYLE);
    }

    public static void showLoading(Context context, Enum<LoaderStyle> style) {
        showLoading(context, style.name());
    }

    public static void showLoading(Context context, String name) {
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.LoadingDialog);
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(context, name);
        dialog.setContentView(avLoadingIndicatorView);

        final Window dialogWindow = dialog.getWindow();
        if (dialogWindow != null) {
            final int deviceWidth = DimenUtil.getScreenWidth();
            final int deviceHeight = DimenUtil.getScreenHeight();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth / LOADER_SIZE_SCALE;
            lp.height = deviceHeight / LOADER_SIZE_SCALE;
            lp.height += deviceHeight / LOADER_HEIGHT_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        LOADER_DIALOGS.add(dialog);
        dialog.show();
    }

    public static void stopLoading() {
        for (AppCompatDialog dialog :
                LOADER_DIALOGS) {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                }
            }
        }
    }
}
