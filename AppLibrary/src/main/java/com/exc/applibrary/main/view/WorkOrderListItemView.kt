package com.exc.applibrary.main.view

import android.app.Activity
import android.os.Build
import android.text.Html
import android.text.Html.fromHtml
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.exc.applibrary.R
import com.exc.applibrary.main.model.WorkOrderList.Data.list.WorkOrderItem
import com.exc.applibrary.main.utils.DateUtil
import kotlinx.android.synthetic.main.acitvity_work_order_detail.*
import zuo.biao.library.base.BaseView

class WorkOrderListItemView(context: Activity?, parent: ViewGroup?) : BaseView<WorkOrderItem?>(context, R.layout.item_work_order_list, parent) {
    private var tv_order_name: TextView? = null
    private var tv_order_type: TextView? = null
    private var tv_order_time: TextView? = null
    private var tv_over_time: TextView? = null
    private var ll_over_time: LinearLayout? = null
    override fun createView(): View {
        tv_order_name = findView(R.id.tv_order_name) { v: View? -> }
        tv_order_type = findView(R.id.tv_order_type)
        tv_order_time = findView(R.id.tv_order_time)
        tv_over_time = findView(R.id.tv_over_time)
        ll_over_time = findView(R.id.ll_over_time)
        return super.createView()
    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun bindView(data_: WorkOrderItem?) {
        super.bindView(data_ ?: WorkOrderItem())
        tv_order_name!!.text = fromHtml(String.format("工单名称：<font color=\"#000000\">%s", data!!.name),Html.FROM_HTML_MODE_COMPACT)
        tv_order_type!!.text = fromHtml(String.format("故障类型：<font color=\"#000000\">%s", data!!.faultTypeName),Html.FROM_HTML_MODE_COMPACT)
        tv_order_time!!.text = fromHtml(String.format("创建时间：<font color=\"#000000\">%s", data!!.createTime),Html.FROM_HTML_MODE_COMPACT)


        if(data!!.overtime == 1 && data!!.statusId != 7){
            var overTime = DateUtil.dateToStamp(data!!.currentTime) - DateUtil.dateToStamp(data!!.expireTime)
            tv_over_time!!.text = "处理时间已超时" + DateUtil.FormatMilliseondToEndTimeNoMiao(overTime)
        }else{
            ll_over_time!!.visibility = View.GONE
        }


    }
}