package com.exc.applibrary.main.ui.fragment

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import android.widget.LinearLayout
import android.widget.TextView
import cn.addapp.pickers.picker.TimePicker
import com.exc.applibrary.R
import com.exc.applibrary.main.ui.dialog.SceneBatchXIAFATimingDialog
import com.exc.applibrary.main.ui.dialog.SceneNodeDetailByIdTimingDialog
import com.exc.applibrary.main.utils.DateUtil
import zuo.biao.library.base.BaseFragment
import zuo.biao.library.util.Log

class ScenePerWeekSelectFragment : BaseFragment() {

    private lateinit var exeTimeStr: String
    private lateinit var tv_exe_time: TextView
    private lateinit var mActivity: Activity
    private lateinit var nowTimeStr: String
    private lateinit var selectXSFZ: String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setContentView(R.layout.fragment_scene_per_week_select)
        mActivity = requireActivity()
        initView()
        initData()
        initEvent()
        return view
    }

    override fun initEvent() {
    }

    override fun initData() {
        exeTimeStr = DateUtil.FormatMillisecondShiFen(System.currentTimeMillis())
        selectXSFZ = "$exeTimeStr 00"
        nowTimeStr = DateUtil.FormatMillisecondForDay(System.currentTimeMillis())
//        SceneNodeDetailByIdTimingDialog.endDate = DateUtil.FormatMillisecond(System.currentTimeMillis())
        tv_exe_time.text = exeTimeStr

    }

    override fun initView() {
        tv_exe_time = findView(R.id.tv_exe_time)
        findView<LinearLayout>(R.id.ll_exe_time) {
            showSelectExeTimeDialog()

        }
        var tv_week1 = findView<CheckedTextView>(R.id.tv_week1)
        var tv_week2 = findView<CheckedTextView>(R.id.tv_week2)
        var tv_week3 = findView<CheckedTextView>(R.id.tv_week3)
        var tv_week4 = findView<CheckedTextView>(R.id.tv_week4)
        var tv_week5 = findView<CheckedTextView>(R.id.tv_week5)
        var tv_week6 = findView<CheckedTextView>(R.id.tv_week6)
        var tv_week7 = findView<CheckedTextView>(R.id.tv_week7)
        tv_week1.setOnClickListener { initWeekClick(weekNum = 1, checkedTextView = tv_week1) }
        tv_week2.setOnClickListener { initWeekClick(weekNum = 2, checkedTextView = tv_week2) }
        tv_week3.setOnClickListener { initWeekClick(weekNum = 3, checkedTextView = tv_week3) }
        tv_week4.setOnClickListener { initWeekClick(weekNum = 4, checkedTextView = tv_week4) }
        tv_week5.setOnClickListener { initWeekClick(weekNum = 5, checkedTextView = tv_week5) }
        tv_week6.setOnClickListener { initWeekClick(weekNum = 6, checkedTextView = tv_week6) }
        tv_week7.setOnClickListener { initWeekClick(weekNum = 7, checkedTextView = tv_week7) }
    }

    private fun initWeekClick(checkedTextView: CheckedTextView?, weekNum: Int) {
        checkedTextView!!.isChecked = !checkedTextView!!.isChecked
        if (checkedTextView!!.isChecked) {
            checkedTextView.setBackgroundResource(R.drawable.scene_selet_zhou_chek_bg)
            checkedTextView.setTextColor(Color.parseColor("#ffffff"))
            SceneNodeDetailByIdTimingDialog.cycleTypes.add(weekNum)
            SceneBatchXIAFATimingDialog.cycleTypes.add(weekNum)
        } else {
            checkedTextView.setBackgroundResource(R.drawable.scene_selet_zhou_unchek_bg)
            checkedTextView.setTextColor(Color.parseColor("#555555"))
            SceneNodeDetailByIdTimingDialog.cycleTypes.remove(weekNum)
            SceneBatchXIAFATimingDialog.cycleTypes.remove(weekNum)
        }
        Log.e("星期集合", SceneNodeDetailByIdTimingDialog.cycleTypes.toString())
    }

    private fun showSelectExeTimeDialog() {
        val picker = TimePicker(mActivity, TimePicker.HOUR_24)
        var listTime = exeTimeStr.split(":")
        picker.setRangeStart((listTime[0]).toInt(), (listTime[1]).toInt())
        picker.setTopLineVisible(false)
        picker.setLineVisible(false)
        picker.setOnTimePickListener { hour, minute ->
            tv_exe_time.text = "$hour:$minute"
            selectXSFZ = "$hour:$minute:00"
            SceneNodeDetailByIdTimingDialog.beginDate = nowTimeStr
            SceneNodeDetailByIdTimingDialog.time = selectXSFZ

            SceneBatchXIAFATimingDialog.beginDate = nowTimeStr
            SceneBatchXIAFATimingDialog.time = selectXSFZ



        }
        picker.show()
    }

}