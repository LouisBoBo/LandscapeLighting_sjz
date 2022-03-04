package com.exc.applibrary.main.adapter

import android.os.Build
import android.widget.ImageView
import android.widget.RelativeLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.exc.applibrary.R
import com.exc.applibrary.main.model.SceneLeftListBean
import androidx.annotation.RequiresApi
import com.exc.applibrary.main.ui.dialog.SceneNodeDetailDialog
import org.greenrobot.eventbus.EventBus
import zuo.biao.library.util.StringUtil

@RequiresApi(Build.VERSION_CODES.N)
class SceneLeftQuickAdapter : BaseQuickAdapter<SceneLeftListBean.Data.list, BaseViewHolder>(R.layout.item_scene) {
    companion object {
        lateinit var clickItem: SceneLeftListBean.Data.list
    }

    override fun convert(holder: BaseViewHolder, item: SceneLeftListBean.Data.list) {

        if (!StringUtil.isEmpty(item.name)) {
            if (item.name.length > 10) {
                holder.setText(R.id.tv_name, item.name.substring(0, 9) + "...")
            } else {
                holder.setText(R.id.tv_name, item.name)
            }
        }else{
            holder.setText(R.id.tv_name, "")
        }
        if (!StringUtil.isEmpty(item.buildingName)) {
            if (item.buildingName.length > 10) {
                holder.setText(R.id.tv_build_name, item.buildingName.substring(0, 9) + "...")
            } else {
                holder.setText(R.id.tv_build_name, item.buildingName)
            }
        }else{
            holder.setText(R.id.tv_build_name, "暂无建筑物")
        }
        if (item.isOffline == 1) {
            holder.setImageResource(R.id.iv_left_icon, R.drawable.online)
            holder.setText(R.id.tv_online_name,"离线")
        } else {
            holder.setImageResource(R.id.iv_left_icon, R.drawable.offline)
            holder.setText(R.id.tv_online_name,"在线")
        }

        holder.getView<ImageView>(R.id.iv_next).setOnClickListener {
//            ToastUtils.showToast(context, "下发", false)
            clickItem = item
            EventBus.getDefault().post(clickItem)

        }
        holder.getView<RelativeLayout>(R.id.root).setOnClickListener {
            SceneNodeDetailDialog(context, item).show()
        }

    }

}