package com.exc.applibrary.main.adapter

import android.os.Build
import androidx.annotation.RequiresApi
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.exc.applibrary.R
import com.exc.applibrary.main.eventbus.EventEnum
import com.exc.applibrary.main.eventbus.EventMessage
import com.exc.applibrary.main.eventbus.LoopItemSwitchBean
import com.exc.applibrary.main.model.LoopList
import com.exc.applibrary.main.model.SceneLeftListBean
import com.exc.applibrary.main.view.MyPSwitchView
import org.greenrobot.eventbus.EventBus

@RequiresApi(Build.VERSION_CODES.N)
class LoopItemAdapter : BaseQuickAdapter<LoopList.DataBean.ListBean, BaseViewHolder>(R.layout.item_loop) {
    companion object {
        lateinit var clickItem: SceneLeftListBean.Data.list
    }

    override fun convert(holder: BaseViewHolder, item: LoopList.DataBean.ListBean) {

        holder.setText(R.id.tv_name, item.name)
        holder.setText(R.id.tv_node_name, item.nodeName)
        var status_switch = holder.getView<MyPSwitchView>(R.id.status_switch)
        status_switch.setInitChecked(item.value == 1)
        var position = getItemPosition(item)

        status_switch.setOnSwitchCheckListener {
            var eventMessage = EventMessage<LoopItemSwitchBean>()
            var switchBen = LoopItemSwitchBean()
            switchBen.switchPosition = position
            switchBen.switchItem = item
            switchBen.isChecked = it
            eventMessage.data = switchBen
            eventMessage.eventEnum = EventEnum.LOOP_ITEM_STATUS_SWITCH
            EventBus.getDefault().post(eventMessage)
        }


    }


}