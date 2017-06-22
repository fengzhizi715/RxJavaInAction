package com.safframework.study.rximageloader;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import com.safframework.tony.common.reflect.Reflect;

/**
 * Created by Tony Shen on 2017/6/23.
 */

public class Utils {

    public static boolean isHoneycombOrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    public static boolean isICSOrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    @TargetApi(19)
    public static boolean isKitkatOrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    /**
     * 获取全局的context，也就是Application Context
     * @return
     */
    @TargetApi(14)
    public static Context getContext() {
        return Reflect.on("android.app.ActivityThread").call("currentApplication").get();
    }

    /**
     * 检查权限是否开启
     *
     * @param permission
     * @return true or false
     */
    public static boolean checkPermissions(Context context, String permission) {

        if (context==null) {
            if (Utils.isICSOrHigher()) {
                context = getContext();
            } else {
                return false;
            }
        }

        PackageManager localPackageManager = context.getApplicationContext().getPackageManager();
        return localPackageManager.checkPermission(permission, context.getApplicationContext().getPackageName()) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 判断是否存在sd卡
     * @return
     */
    public static boolean hasSdcard() {

        String status = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(status);
    }
}
