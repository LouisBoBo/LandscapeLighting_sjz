package com.exc.applibrary.main.message

import android.os.Build
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.exc.applibrary.R
import androidx.annotation.RequiresApi
import com.exc.applibrary.main.utils.DateUtil
import com.exc.applibrary.main.utils.ToastUtils

@RequiresApi(Build.VERSION_CODES.N)
class MessageListQuickAdapter : BaseQuickAdapter<MessageListBean.DataBean.ListBean, BaseViewHolder>(R.layout.item_message_list) {

    override fun convert(holder: BaseViewHolder, item: MessageListBean.DataBean.ListBean) {


        holder.getView<TextView>(R.id.messageText).text = item.headline

        var messageTime = DateUtil.dateToStamp(item.createTime)
        holder.getView<TextView>(R.id.tv_time).text = DateUtil.stampToDateS(messageTime.toString())

        var iv_status = holder.getView<ImageView>(R.id.iv_status)
        if (item.status == 1) {
            iv_status.setImageDrawable(context.getDrawable(R.drawable.icon_message_read))

        } else {
            iv_status.setImageDrawable(context.getDrawable(R.drawable.icon_message_no_read))

        }

    }


}