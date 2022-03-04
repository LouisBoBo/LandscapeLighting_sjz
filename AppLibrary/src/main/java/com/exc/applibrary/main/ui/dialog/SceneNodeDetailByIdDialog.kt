package com.exc.applibrary.main.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import com.exc.applibrary.R
import com.exc.applibrary.main.model.SceneDataListById
import kotlinx.android.synthetic.main.dialog_scene_detail_by_id.*

class SceneNodeDetailByIdDialog(context: Context, private val mItem: SceneDataListById.Data?) : Dialog(context, R.style.dialog_bottom_full) {
    private lateinit var item: SceneDataListById.Data
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_scene_detail_by_id)
        this.item = mItem!!
        setCanceledOnTouchOutside(true)
        setCancelable(true)
        val window = window
        window!!.setGravity(Gravity.BOTTOM)
        window.setWindowAnimations(R.style.share_animation)
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT) //设置横向全屏
        tv_node_name.text = item.nodeName
        tv_status.text = item.name
        iv_close.setOnClickListener {
            dismiss()
        }
    }
}