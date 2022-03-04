package com.exc.applibrary.main.ui.activity

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import com.exc.applibrary.R
import kotlinx.android.synthetic.main.activity_about_us.*
import kotlinx.android.synthetic.main.activity_my_work_order.*
import kotlinx.android.synthetic.main.activity_my_work_order.header_center_text
import kotlinx.android.synthetic.main.activity_my_work_order.header_left_img
import zuo.biao.library.base.BaseActivity

class AboutUsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        initView()
        initData()
    }

    override fun initView() {
        header_center_text.text = "关于我们"
        header_left_img.setOnClickListener { finish() }

    }
    override fun initData() {
        try {
            val manager = this.packageManager
            val info = manager.getPackageInfo(this.packageName, 0)
            val version = info.versionName
            val appName = info.applicationInfo.loadLabel(packageManager)
            tv_app_name.text = "$appName"
            tv_version.text = "V$version"
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

    }
    override fun initEvent() {}
}