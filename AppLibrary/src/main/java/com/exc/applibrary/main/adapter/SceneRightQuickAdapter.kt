package com.exc.applibrary.main.adapter

import android.os.Build
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.exc.applibrary.R
import com.exc.applibrary.main.model.SceneChooseNodeListData
import com.exc.applibrary.main.ui.dialog.SceneSceneDetailDialog
import org.greenrobot.eventbus.EventBus
import zuo.biao.library.util.StringUtil

@RequiresApi(Build.VERSION_CODES.N)
class SceneRightQuickAdapter : BaseQuickAdapter<SceneChooseNodeListData.Data.list, BaseViewHolder>(R.layout.item_right_scene) {
    companion object {
        lateinit var clickItem: SceneChooseNodeListData.Data.list
    }

    override fun convert(holder: BaseViewHolder, item: SceneChooseNodeListData.Data.list) {

        if (!StringUtil.isEmpty(item.name)) {
            if (item.name.length > 10) {
                holder.setText(R.id.tv_scene_name, item.name.substring(0, 9) + "...")
            } else {
                holder.setText(R.id.tv_scene_name, item.name)
            }
        } else {
            holder.setText(R.id.tv_scene_name, "无名称")

        }



        holder.getView<ImageView>(R.id.iv_next).setOnClickListener {
            clickItem = item
            EventBus.getDefault().post(clickItem)
        }
        holder.getView<RelativeLayout>(R.id.root).setOnClickListener {
            SceneSceneDetailDialog(context, item).show()
        }

    }

}