package com.exc.applibrary.main.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.exc.applibrary.R;


/**
 * ToastUtil 工具类
 */
public class ToastUtils {
    private static String oldMsg;
    protected static Toast toast = null;
    private static long oneTime = 0;
    private static long twoTime = 0;
    private static TextView showTv = null;
    private static View view;

    public static void showToast(Context context, String message, boolean needBigText) {
        if (needBigText) {
            showBigToast(context, message);
        } else {
            showNormalToast(context, message);
        }
    }

    /**
     * 显示正常大小字体的toast
     *
     * @param context
     * @param str
     */
    public static void showNormalToast(Context context, String str) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity) {
            if (((Activity) context).isDestroyed() || ((Activity) context).isFinishing()) {
                return;
            }
        }

        if (toast == null) {
            toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            view = LayoutInflater.from(context).inflate(R.layout.common_view_toast, null);
            showTv = view.findViewById(R.id.tv_show);
            showTv.setText(str);
            toast.setView(view);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (!TextUtils.isEmpty(str)) {
                if (str.equals(oldMsg)) {
                    if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                        toast.show();
                    }
                } else {
                    oldMsg = str;
                    if (showTv != null) {
                        showTv.setText(str);
                    }
                    toast.show();
                }
            }
            oneTime = twoTime;
        }
    }

    public  static void  showErrorToast(Context context, String str){
        ((Activity) context).runOnUiThread(() -> {
            showErrorToast1(context,str);
        });

    }
    private static void showErrorToast1(Context context, String str) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity) {
            if (((Activity) context).isDestroyed() || ((Activity) context).isFinishing()) {
                return;
            }
        }

        if (toast == null) {
            toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            view = LayoutInflater.from(context).inflate(R.layout.common_view_error_toast, null);

            showTv = view.findViewById(R.id.tv_show);
            showTv.setText(str);
            showTv.setTextColor(Color.parseColor("#FA3534"));
            toast.setView(view);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (!TextUtils.isEmpty(str)) {
                if (str.equals(oldMsg)) {
                    if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                        toast.show();
                    }
                } else {
                    oldMsg = str;
                    if (showTv != null) {
                        showTv.setText(str);
                    }
                    toast.show();
                }
            }
            oneTime = twoTime;
        }
    }





    /**
     * 显示加大蓝色字体的toast
     *
     * @param context
     * @param str
     */
    private static void showBigToast(Context context, String str) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity) {
            if (((Activity) context).isDestroyed() || ((Activity) context).isFinishing()) {
                return;
            }
        }

        if (toast == null) {
            toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            view = LayoutInflater.from(context).inflate(R.layout.common_view_toast, null);
            showTv = view.findViewById(R.id.tv_show);
            showTv.setText(str);
            toast.setView(view);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (!TextUtils.isEmpty(str)) {
                if (str.equals(oldMsg)) {
                    if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                        toast.show();
                    }
                } else {
                    oldMsg = str;
                    if (showTv != null) {
                        showTv.setText(str);
                    }
                    toast.show();
                }
            }
            oneTime = twoTime;
        }
    }

    public static void showToast(Context context, int resId, boolean needBigText) {
        showToast(context, context.getString(resId), needBigText);
    }
}
