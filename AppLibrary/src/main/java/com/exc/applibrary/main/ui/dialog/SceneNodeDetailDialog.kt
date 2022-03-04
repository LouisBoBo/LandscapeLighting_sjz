package com.exc.applibrary.main.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.exc.applibrary.R
import com.exc.applibrary.main.model.SceneLeftListBean
import kotlinx.android.synthetic.main.dialog_scene_left_item_detail.*
import zuo.biao.library.util.StringUtil

class SceneNodeDetailDialog(context: Context, private val mItem: SceneLeftListBean.Data.list?) : Dialog(context, R.style.dialog_bottom_full) {


    private lateinit var item: SceneLeftListBean.Data.list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_scene_left_item_detail)
        this.item = mItem!!
        setCanceledOnTouchOutside(true)
        setCancelable(true)
        val window = window
        window!!.setGravity(Gravity.BOTTOM)
        window.setWindowAnimations(R.style.share_animation)
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT) //设置横向全屏
        tv_node_name.text = item.name
        tv_node_num.text = item.num
        if(StringUtil.isEmpty(item.siteName)){
            tv_site_name.text = "该节点暂无对应站点"
        }else{
            tv_site_name.text = item.siteName
        }
        tv_note_fenqu.text = item.partitionName
        tv_build_name.text = item.buildingName
        if (item.isOffline == 1) {
            tv_status.text = "离线"
        } else {
            tv_status.text = "在线"
        }
        iv_close.setOnClickListener {
            dismiss()
        }
    }
}