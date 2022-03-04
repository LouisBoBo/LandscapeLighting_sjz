package com.exc.applibrary.main.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.exc.applibrary.R;
import com.exc.applibrary.databinding.ActivityPwdChangeBinding;
import com.exc.applibrary.main.HttpRequest;
import com.exc.applibrary.main.customview.CustomDialog;
import com.exc.applibrary.main.customview.YFHeaderView;
import com.exc.applibrary.main.utils.Constant;
import com.exc.applibrary.main.utils.PreferencesUtil;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.interfaces.OnHttpResponseListener;

public class PwdChangeActivity extends BaseActivity implements OnHttpResponseListener {

    private final int PWDCHANGE_REQUEST_CODE = 1;

    private CustomDialog customDialog;
    private ActivityPwdChangeBinding binding;
    private  PwdChangeActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = this;
        binding = ActivityPwdChangeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
        initData();
        initEvent();
    }

    @Override
    public void initView() {

        customDialog = new CustomDialog(getActivity());

        //提交新设置的密码
        binding.submitPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(binding.etOldpwd.getText().length() ==0 || binding.etNewpwd.getText().length() ==0 || binding.etCfmpwd.getText().length() ==0){
                    binding.tvTiptitle.setText("请完善您的输入");
                    return;
                }else if(binding.etOldpwd.getText().length() < 10 || binding.etNewpwd.getText().length() < 10 || binding.etCfmpwd.getText().length() < 10) {
                    binding.tvTiptitle.setText("密码长度不对");
                    return;
                }else if(binding.etOldpwd.getText().length() > 16 || binding.etNewpwd.getText().length() > 16 || binding.etCfmpwd.getText().length() > 16) {
                    binding.tvTiptitle.setText("密码长度不对");
                    return;
                }else if(!binding.etNewpwd.getText().toString().equals(binding.etCfmpwd.getText().toString())){
                    binding.tvTiptitle.setText("两次输入密码不一致");
                    return;
                }

                int user_id = PreferencesUtil.getInt(getActivity(), Constant.USER_ID);
                String phone = PreferencesUtil.getString(getActivity(),Constant.LOCATION_USER_NAME_KEY);
                customDialogShow();
                HttpRequest.pwdChangeHttp(String.valueOf(user_id),binding.etCfmpwd.getText().toString(),phone,PWDCHANGE_REQUEST_CODE,PwdChangeActivity.this::onHttpResponse);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onHttpResponse(int requestCode, String resultJson, Exception e) {
        if(requestCode == PWDCHANGE_REQUEST_CODE){
            customDialog.dismiss();
            if (null == resultJson) {
                showShortToast("请求异常，请检查网络");
                return;
            }

            JSONObject jsonObj = JSONObject.parseObject(resultJson);
            String code = jsonObj.getString("code");
            String message = jsonObj.getString("message");
            if(code.equals("200")){
                binding.tvTiptitle.setText("修改成功");
                showShortToast("修改成功");

                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            mainActivity.finish();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }

                    }
                }).start();
            }else {
                binding.tvTiptitle.setText(message);
            }
        }
    }

    //加载提示
    public void customDialogShow(){
        if (null != customDialog) {
            customDialog.dismiss();
            customDialog = new CustomDialog(this);
            customDialog.show();
        }
    }

}
