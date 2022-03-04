package com.exc.applibrary.main.ui.activity

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.alibaba.fastjson.JSONObject
import com.exc.applibrary.R
import com.exc.applibrary.main.HttpListener
import com.exc.applibrary.main.HttpRequest
import com.exc.applibrary.main.customview.CustomDialog
import com.exc.applibrary.main.model.LoginInfo
import com.exc.applibrary.main.model.UserData
import com.exc.applibrary.main.utils.Constant
import kotlinx.android.synthetic.main.activity_myinfo.*
import zuo.biao.library.base.BaseActivity
import zuo.biao.library.interfaces.OnHttpResponseListener
import zuo.biao.library.util.JsonUtils
import zuo.biao.library.util.PreferencesUtil

class MyInfoActivity : BaseActivity(), OnHttpResponseListener {
    private lateinit var mActivity: Activity
    private val REQUSET_USER_DATA_CODE = 1
    private val REQUSET_USER_UPDATE_CODE = 2;
    private lateinit var loadingDialog:CustomDialog
    private var info_id = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myinfo)
        loadingDialog = CustomDialog(this)
        mActivity = this
        initView()
        initData()
    }


    override fun initView() {

//        name_baseview.setOnClickListener {
//            submit_info.visibility = View.VISIBLE;
//            ed_name.visibility = View.VISIBLE;
//            ed_name.hint = tv_name.text;
//            tv_name.visibility = View.GONE;
//        }
//
//        sex_baseview.setOnClickListener {
//            submit_info.visibility = View.VISIBLE;
//            tv_sex.visibility = View.GONE;
//            select_sexview.visibility = View.VISIBLE;
//        }
//        phone_baseview.setOnClickListener {
//            submit_info.visibility = View.VISIBLE;
//            ed_phone.visibility = View.VISIBLE;
//            ed_phone.hint = tv_phone.text;
//            tv_phone.visibility = View.GONE;
//        }
//
//        email_baseview.setOnClickListener {
//            submit_info.visibility = View.VISIBLE;
//            ed_email.visibility = View.VISIBLE;
//            ed_email.hint = tv_email.text
//            tv_email.visibility = View.GONE;
//        }
//
//        btn_cancle.setOnClickListener {
//            btn_cancle.isSelected = true;
//            btn_confirm.isSelected = false;
//
//            hideEditView();
//        }
//
//        btn_confirm.setOnClickListener {
//            btn_confirm.isSelected = true;
//            btn_cancle.isSelected = false;
//
//            var name = ed_name.text;
//            var phone = ed_phone.text;
//            var email = ed_email.text;
//            var sex = 2;
//            if(select_boy.isSelected){
//                sex = 0;
//            }
//            if(select_girl.isSelected){
//                sex = 1;
//            }
//            HttpRequest.updateUserHttp(info_id.toString(),email.toString() ,sex.toString(),name.toString(),phone.toString(),REQUSET_USER_UPDATE_CODE, this);
//        }
//
//        select_boy.setOnClickListener {
//            select_boy.isSelected = true;
//            select_girl.isSelected = false;
//        }
//
//        select_girl.setOnClickListener {
//            select_girl.isSelected = true;
//            select_boy.isSelected = false;
//        }
    }

    fun hideEditView(){
        submit_info.visibility = View.GONE;
        ed_name.visibility = View.GONE;
        select_sexview.visibility = View.GONE;
        ed_phone.visibility = View.GONE;
        ed_email.visibility = View.GONE;

        tv_name.visibility = View.VISIBLE;
        tv_sex.visibility = View.VISIBLE;
        tv_phone.visibility = View.VISIBLE;
        tv_email.visibility = View.VISIBLE;
    }
    override fun initData() {
        loadingDialog.show()
        var loginInfo = JsonUtils.parseObject(PreferencesUtil.getString(mActivity, Constant.LOGIN_INFO_JSON), LoginInfo::class.java)

        HttpRequest.getUserBackModule(mActivity,loginInfo.data.userId, object : HttpListener<UserData>(){
            override fun onSuccess(userData: UserData) {
                loadingDialog.dismiss()
                info_id = userData.data.id;
                tv_name.text = userData.data.name
                tv_sex.text = if (userData.data.gender == 1)  "女" else "男"
                tv_partition.text = userData.data.partition.name
                tv_role.text = userData.data.roles[0].name
                tv_phone.text = userData.data.phone
                tv_email.text = userData.data.email

                if(userData.data.gender == 0){
                    select_boy.isSelected = true;
                }else if(userData.data.gender == 1){
                    select_girl.isSelected = true;
                }
            }

            override fun onError() {
                loadingDialog.dismiss()
            }

        })
    }

    override fun initEvent() {}
    override fun onHttpResponse(requestCode: Int, resultJson: String?, e: Exception?) {
        if(requestCode == REQUSET_USER_UPDATE_CODE){
            if (null == resultJson) {
                showShortToast("请求异常，请检查网络")
                return
            }

            val jsonObj = JSONObject.parseObject(resultJson)
            val code = jsonObj.getString("code")
            var message = jsonObj.getString("message")
            if (code == "200") {
                hideEditView();
                initData();
                showShortToast("修改成功");
            } else {
                showShortToast(message)
            }
        }

    }

}