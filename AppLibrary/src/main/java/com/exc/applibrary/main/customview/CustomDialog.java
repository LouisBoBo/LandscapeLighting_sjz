package com.exc.applibrary.main.customview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;

import com.google.android.material.internal.ViewUtils;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class CustomDialog {
    private MyLoadingPopupView loadingPopup;
    private Context context;

    @SuppressLint("RestrictedApi")
    public CustomDialog(Context context, String string) {
        this.context = context;
        loadingPopup = new MyXPopup.Builder(context)
                .hasShadowBg(false)
                .dismissOnTouchOutside(false)
                .maxWidth((int) ViewUtils.dpToPx(context,88))
                .maxHeight((int) ViewUtils.dpToPx(context,88))
                .dismissOnBackPressed(true)
                .autoDismiss(false)
                .asLoading("");

    }


    @SuppressLint("RestrictedApi")
    public CustomDialog(Context context, String string,boolean showStr) {
        this.context = context;
        loadingPopup = new MyXPopup.Builder(context)
                .hasShadowBg(false)
                .dismissOnTouchOutside(false)
//                .maxWidth((int) ViewUtils.dpToPx(context,88))
//                .maxHeight((int) ViewUtils.dpToPx(context,88))
                .dismissOnBackPressed(true)
                .autoDismiss(false)
                .asLoading(string);

    }


    @SuppressLint("RestrictedApi")
    public CustomDialog(Context context) {
        this.context = context;
        loadingPopup = new MyXPopup.Builder(context)
                .hasShadowBg(false)
                .dismissOnTouchOutside(false)
                .maxWidth((int) ViewUtils.dpToPx(context,88))
                .maxHeight((int) ViewUtils.dpToPx(context,88))
                .dismissOnBackPressed(true)
                .autoDismiss(false)
                .asLoading("");

    }

    public void show() {
        if (null != loadingPopup) {
            loadingPopup.show();

            //秒后自动消失
//            final Timer t = new Timer();
//            t.schedule(new TimerTask() {
//                public void run() {
//                    ((Activity) context).runOnUiThread(() -> loadingPopup.dismiss());
//                }
//            }, 8000);
        }
    }

    public void dismiss() {
        if (null != loadingPopup) {
            ((Activity) context).runOnUiThread(() -> loadingPopup.dismiss());
        }
    }


//    public CustomDialog(Context context, String string) {
//
//        final LoadingPopupView loadingPopup = (LoadingPopupView) new XPopup.Builder(context)
//                .dismissOnBackPressed(false)
//                .asLoading("正在加载中")
//                .show();
//        loadingPopup.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                loadingPopup.setTitle("正在加载长度变化了");
//            }
//        }, 1000);
////                loadingPopup.smartDismiss();
////                loadingPopup.dismiss();
////        loadingPopup.delayDismissWith(4000, new Runnable() {
////            @Override
////            public void run() {
////                toast("我消失了！！！");
////            }
////        });

}



