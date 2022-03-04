package com.exc.applibrary.main.ui.activity

import android.app.Activity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.exc.applibrary.R
import com.exc.applibrary.main.HttpRequest
import com.exc.applibrary.main.model.WorkOrderDetail
import java.io.File

class BrowsePicActivity : Activity() {
    private lateinit var pic_url: String
    private var isEdit: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE) //去掉标题栏
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_brows_pic)
        pic_url = intent.getStringExtra("pic_url").toString()
        isEdit = intent.getBooleanExtra("isEdit", false)
        var imageView = findViewById<ImageView>(R.id.iv)
        if (isEdit) {
            var picBen = intent.getSerializableExtra("picBen") as WorkOrderDetail.Data.OrderNew.OrderPics

            if (picBen?.isXC!!) {
                Glide.with(this).load(File(picBen?.filename)).into(imageView)
                return
            }
            imageView?.let {
                Glide.with(this)
                        .load(HttpRequest.SERVICES_FILE_PATH + picBen?.filename)
                        .into(it)
            }
            return
        }

        pic_url = HttpRequest.SERVICES_FILE_PATH + pic_url
        Glide.with(this)
                .load(pic_url)
                .into(findViewById(R.id.iv))
    }


}