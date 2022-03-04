package com.exc.sjz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.exc.applibrary.main.MainActivity;
import com.exc.applibrary.main.login.TaiYuanLoginActivity;
import com.exc.applibrary.main.utils.Constant;

import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.util.PreferencesUtil;
import zuo.biao.library.util.StringUtil;

import static android.os.Build.VERSION_CODES.LOLLIPOP;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏


        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#00000000"));
            int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            uiFlags = uiFlags | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

//            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN


            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(uiFlags);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }


//        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(() -> {
            String loginInFo = PreferencesUtil.getString(SplashActivity.this, Constant.LOGIN_INFO_JSON);
            if (!StringUtil.isEmpty(loginInFo)) {
//                LoginInfo loginInfo = JsonUtils.parseObject(loginInFo, LoginInfo.class);
//                PhoneColdControlApp.loginInfo = loginInfo;
//                PhoneColdControlApp.userToken = loginInfo.getData().getToken();
//                com.exc.applibrary.main.utils.PreferencesUtil.putString(this,Constant.USER_TOKEN,loginInfo.getData().getToken());
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                noAnimFinish();
                return;

            }
            startActivity(new Intent(SplashActivity.this, TaiYuanLoginActivity.class));
            noAnimFinish();
        }, 1000);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }
}
