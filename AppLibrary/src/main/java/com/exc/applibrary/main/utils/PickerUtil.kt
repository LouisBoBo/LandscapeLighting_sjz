package com.exc.applibrary.main.utils

import android.app.Activity
import android.view.Gravity
import cn.addapp.pickers.common.LineConfig
import cn.addapp.pickers.picker.SinglePicker

class PickerUtil {
    interface OnPickerSelectListener {
        fun select(itemStr: String, index: Int)
    }

    companion object {
        fun initSelectOnStrPicker(isRole:Boolean, mActivity: Activity, selectedIndex: Int, pickerNameList: ArrayList<String>, onPickerSelectListener: OnPickerSelectListener) {
            var picker = SinglePicker(mActivity, pickerNameList)
            picker.setCanLoop(false) //不禁用循环
            picker.setTopBackgroundColor(-0x111112)
            picker.setTopHeight(50)
            picker.setGravity(Gravity.BOTTOM)

            if(isRole){
                picker.setTitleText("请选择处理角色")
            }else{
                picker.setTitleText("请选择处理人员")
            }
            picker.setTitleTextColor(-0x666667)
            picker.setTitleTextSize(12)
            picker.setCancelTextColor(-0xcc4a1b)
            picker.setCancelTextSize(13)
            picker.setSubmitTextColor(-0xcc4a1b)
            picker.setSubmitTextSize(13)
            picker.setSelectedTextColor(-0x120000)
            picker.setUnSelectedTextColor(-0x666667)
            val config = LineConfig()
//                config.color = Color.BLUE //线颜色
//                config.alpha = 120 //线透明度
            config.isVisible = false
//        config.setRatio(1);//线比率
            //        config.setRatio(1);//线比率
            picker.setLineConfig(config)
            picker.setItemWidth(200)
            picker.setBackgroundColor(-0x1e1e1f)
            //picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
            //picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
            picker.selectedIndex = selectedIndex
            picker.setOnItemPickListener { index, item ->

                onPickerSelectListener.select(item!!, index)
//            showShortToast(mActivity, ("index=$index, item=$item"))
//            tv_role_name.text = item
//
//                    tv_manager_name.text = item
//                    tv_manager_name.setTextColor(Color.parseColor("#000000"))
//                    selectManagerData = orderAuditSelectManagerList.data[index]

            }
            picker.show()

        }

    }
}