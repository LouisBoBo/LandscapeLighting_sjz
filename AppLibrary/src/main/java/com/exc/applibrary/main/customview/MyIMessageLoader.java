package com.exc.applibrary.main.customview;

import android.content.Context;

import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.progress.loading.IMessageLoader;


public class MyIMessageLoader {
    private IMessageLoader mMessageLoader;

    public MyIMessageLoader(Context context) {
        if (mMessageLoader == null) {
            mMessageLoader = WidgetUtils.getMiniLoadingDialog(context);
            mMessageLoader.setCancelable(true);
        }
    }

    public void show() {
        mMessageLoader.show();
    }

    public void dismiss() {
        mMessageLoader.dismiss();
    }
}
