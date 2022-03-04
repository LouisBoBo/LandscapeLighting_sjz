package com.exc.applibrary.main.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.exc.applibrary.R;
import com.exc.applibrary.main.HttpRequest;
import com.exc.applibrary.main.MainActivity;
import com.exc.applibrary.main.customview.CustomDialog;
import com.exc.applibrary.main.customview.VerifyCode;
import com.exc.applibrary.main.model.LoginInfo;
import com.exc.applibrary.main.utils.Constant;
import com.exc.applibrary.main.utils.PreferencesUtil;

import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.interfaces.OnHttpResponseListener;
import zuo.biao.library.util.JsonUtils;
import zuo.biao.library.util.Log;
import zuo.biao.library.util.StringUtil;

import static android.os.Build.VERSION_CODES.LOLLIPOP;


public class TaiYuanLoginActivity extends BaseActivity implements OnHttpResponseListener {

    private final int LOGIN_REQUEST_CODE = 1;
    private View loginbutton;
    private EditText usernameEdit;
    private EditText passwordEdit;
    private EditText codeEdit;
    private VerifyCode codeimage;
    private CustomDialog customDialog;
    private TaiYuanLoginActivity mActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            // 透明状态栏
//            getWindow().addFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            // 透明导航栏
//            getWindow().addFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }





                Window window = getWindow();


        if (Build.VERSION.SDK_INT >= LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.parseColor("#00000000"));
//            int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            uiFlags = uiFlags | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

//            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN


//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.getDecorView().setSystemUiVisibility(uiFlags);


            window.setStatusBarColor( Color.TRANSPARENT);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }


        setContentView(R.layout.activity_login_taiyuan);
        mActivity = this;

        customDialog = new CustomDialog(this, "请稍后");

        initView();
        initData();
        setClick();


    }


    public void initView() {
        usernameEdit = findViewById(R.id.usernameEdit);
        passwordEdit = findViewById(R.id.passwrordEdit);
        codeEdit = findViewById(R.id.codeEdit);
        loginbutton = findViewById(R.id.loginbutton);
        codeimage = findViewById(R.id.codeimage);
    }

    public void initData() {

        if (!StringUtil.isEmpty(PreferencesUtil.getString(this, Constant.LOCATION_USER_NAME_KEY))) {
            usernameEdit.setText((PreferencesUtil.getString(this, Constant.LOCATION_USER_NAME_KEY)));
        }

        if (!StringUtil.isEmpty(PreferencesUtil.getString(this, Constant.LOCATION_PWD_KEY))) {
            passwordEdit.setText((PreferencesUtil.getString(this, Constant.LOCATION_PWD_KEY)));
        }
    }

    @Override
    public void initEvent() {

    }

    private void setClick() {

        //登录按钮点击事件
        loginbutton.setOnClickListener(v -> {

            if (usernameEdit.getText().toString().length() == 0) {
                showShortToast("请输入账号");
                return;
            }
            if (passwordEdit.getText().toString().length() == 0) {
                showShortToast("请输入密码");

                return;
            }
            if (codeEdit.getText().toString().length() == 0) {
                showShortToast("请输入验证码");
                return;
            }
            if (!codeimage.isEqualsIgnoreCase(codeEdit.getText().toString())) {
                showShortToast("验证码输入错误");
                return;
            }

            if (null != customDialog) {
                customDialog.dismiss();
                customDialog = new CustomDialog(mActivity);
                customDialog.show();
            }


            HttpRequest.login(usernameEdit.getText().toString(), passwordEdit.getText().toString(), LOGIN_REQUEST_CODE, mActivity);
        });

    }


    @Override
    public void onHttpResponse(int requestCode, String resultJson, Exception e) {
        if (requestCode == LOGIN_REQUEST_CODE) {
            customDialog.dismiss();
            Log.e("登录返回", "" + resultJson);
            if (null == resultJson) {
                showShortToast("登录异常，请检查网络");
                return;
            }
            LoginInfo loginInfo = JsonUtils.parseObject(resultJson, LoginInfo.class);
            if (loginInfo.getCode() != 200) {
                showShortToast(loginInfo.getMessage() + "");
                return;
            }
//            PhoneColdControlApp.loginInfo = loginInfo;
//            PhoneColdControlApp.userToken = loginInfo.getData().getToken();
            //保存登录登录信息到本地
            PreferencesUtil.putString(mActivity, Constant.LOGIN_INFO_JSON, resultJson);
            PreferencesUtil.putString(mActivity, Constant.USER_TOKEN, loginInfo.getData().getToken());
            PreferencesUtil.putInt(mActivity, Constant.USER_ID, loginInfo.getData().getUserId());
            //保存登录的账号和密码
            PreferencesUtil.putString(mActivity, Constant.LOCATION_USER_NAME_KEY, usernameEdit.getText().toString());
            PreferencesUtil.putString(mActivity, Constant.LOCATION_PWD_KEY, passwordEdit.getText().toString());
            toActivity(new Intent(mActivity, MainActivity.class));
            noAnimFinish();
        }

    }
}



