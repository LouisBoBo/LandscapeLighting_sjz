package com.exc.applibrary.main.ui.fragment

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import cn.addapp.pickers.picker.DatePicker
import cn.addapp.pickers.picker.DatePicker.OnYearMonthDayPickListener
import cn.addapp.pickers.picker.TimePicker
import com.exc.applibrary.R
import com.exc.applibrary.main.ui.dialog.SceneBatchXIAFATimingDialog
import com.exc.applibrary.main.ui.dialog.SceneNodeDetailByIdTimingDialog
import com.exc.applibrary.main.utils.DateUtil
import org.greenrobot.eventbus.EventBus
import zuo.biao.library.base.BaseFragment

class ScenePerTimeSelectFragment : BaseFragment(), View.OnClickListener {


    private lateinit var exeTimeStr: String
    private lateinit var startTimeStr: String
    private lateinit var endTimeStr: String
    private lateinit var nowTimeStr: String
    private lateinit var selectXSFZ: String

    private lateinit var mActivity: Activity
    private lateinit var tv_exe_time: TextView
    private lateinit var tv_start_time: TextView
    private lateinit var tv_end_time: TextView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setContentView(R.layout.fragment_scene_per_time_select)
        mActivity = requireActivity()
        initView()
        initData()
        initEvent()
        return view
    }

    override fun initView() {
        tv_exe_time = findView(R.id.tv_exe_time)
        tv_start_time = findView(R.id.tv_start_time)
        tv_end_time = findView(R.id.tv_end_time)
    }


    override fun initEvent() {
        findView<LinearLayout>(R.id.ll_exe_time, this)
        findView<LinearLayout>(R.id.ll_start_time, this)
        findView<LinearLayout>(R.id.ll_end_time, this)
    }

    override fun initData() {
        exeTimeStr = DateUtil.FormatMillisecondShiFen(System.currentTimeMillis())
        startTimeStr = DateUtil.FormatMillisecondForDay(System.currentTimeMillis())
        endTimeStr = DateUtil.FormatMillisecondForDay(System.currentTimeMillis())
        nowTimeStr = DateUtil.FormatMillisecondForDay(System.currentTimeMillis())


//        SceneNodeDetailByIdTimingDialog.beginDate = DateUtil.FormatMillisecond(System.currentTimeMillis())
//        SceneNodeDetailByIdTimingDialog.endDate = DateUtil.FormatMillisecond(System.currentTimeMillis())
//
//        SceneBatchXIAFATimingDialog.beginDate = DateUtil.FormatMillisecond(System.currentTimeMillis())
//        SceneBatchXIAFATimingDialog.endDate = DateUtil.FormatMillisecond(System.currentTimeMillis())


        SceneNodeDetailByIdTimingDialog.time = "$exeTimeStr:00"
        SceneNodeDetailByIdTimingDialog.beginDate = startTimeStr
        SceneNodeDetailByIdTimingDialog.endDate = endTimeStr

        SceneBatchXIAFATimingDialog.time = "$exeTimeStr:00"
        SceneBatchXIAFATimingDialog.beginDate = startTimeStr
        SceneBatchXIAFATimingDialog.endDate = endTimeStr




        tv_exe_time.text = exeTimeStr
        selectXSFZ = "$exeTimeStr:00"
        tv_start_time.text = startTimeStr
        tv_end_time.text = endTimeStr
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.ll_exe_time -> {
                showSelectExeTimeDialog()
            }
            R.id.ll_start_time -> {
                showSelectStartTimeDialog()
            }
            R.id.ll_end_time -> {
                showSelectEndTimeDialog()
            }
        }

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
            SceneNodeDetailByIdTimingDialog.beginDate = startTimeStr
            SceneNodeDetailByIdTimingDialog.endDate = endTimeStr
            SceneNodeDetailByIdTimingDialog.time = selectXSFZ
            SceneBatchXIAFATimingDialog.beginDate = startTimeStr
            SceneBatchXIAFATimingDialog.endDate = endTimeStr
            SceneBatchXIAFATimingDialog.time = selectXSFZ

        }
        picker.show()
    }

    private fun showSelectStartTimeDialog() {
        val picker = DatePicker(mActivity)
        picker.setTopPadding(15)
        picker.setRangeStart(2000, 1, 1)
        picker.setRangeEnd(2050, 1, 1)
        var listTime = startTimeStr.split("-")
        picker.setSelectedItem(listTime[0].toInt(), listTime[1].toInt(), listTime[2].toInt())
        picker.setWeightEnable(true)
        picker.setLineColor(Color.BLACK)
        picker.setLineVisible(false)
        picker.setOnDatePickListener(OnYearMonthDayPickListener { year, month, day ->
//            showShortToast("$year-$month-$day")
            var resultTime = "$year-$month-$day"

//            tv_start_time.text = startTimeStr
            //开始日期不能小于当前日期
            if (DateUtil.dateToStampOnlyDay(resultTime) < DateUtil.dateToStampOnlyDay(nowTimeStr)) {
                showShortToast("开始日期不能小于当前日期")
                picker.setSelectedItem(listTime[0].toInt(), listTime[1].toInt(), listTime[2].toInt())
                return@OnYearMonthDayPickListener
            }
            startTimeStr = resultTime
            tv_start_time.text = startTimeStr

            SceneNodeDetailByIdTimingDialog.beginDate = startTimeStr
            SceneNodeDetailByIdTimingDialog.endDate = endTimeStr
            SceneNodeDetailByIdTimingDialog.time = selectXSFZ

            SceneBatchXIAFATimingDialog.beginDate = startTimeStr
            SceneBatchXIAFATimingDialog.endDate = endTimeStr
            SceneBatchXIAFATimingDialog.time = selectXSFZ


        })
        picker.show()
    }

    private fun showSelectEndTimeDialog() {

        val picker = DatePicker(mActivity)
        picker.setTopPadding(15)
        picker.setRangeStart(2000, 1, 1)
        picker.setRangeEnd(2050, 1, 1)
        var listTime = endTimeStr.split("-")
        picker.setSelectedItem(listTime[0].toInt(), listTime[1].toInt(), listTime[2].toInt())
        picker.setWeightEnable(true)
        picker.setLineColor(Color.BLACK)
        picker.setLineVisible(false)
        picker.setOnDatePickListener(OnYearMonthDayPickListener { year, month, day ->

            var resultTime = "$year-$month-$day"
            //开始日期不能小于设置的开始日期
            if (DateUtil.dateToStampOnlyDay(resultTime) < DateUtil.dateToStampOnlyDay(nowTimeStr)) {
                showShortToast("结束日期不能小于开始日期")
                //设置为当前已设置的开始日期
                var listTime = startTimeStr.split("-")
                picker.setSelectedItem(listTime[0].toInt(), listTime[1].toInt(), listTime[2].toInt())
                return@OnYearMonthDayPickListener
            }
            endTimeStr = resultTime
            tv_end_time.text = endTimeStr

            SceneNodeDetailByIdTimingDialog.beginDate = startTimeStr
            SceneNodeDetailByIdTimingDialog.endDate = endTimeStr
            SceneNodeDetailByIdTimingDialog.time = selectXSFZ


            SceneBatchXIAFATimingDialog.beginDate = startTimeStr
            SceneBatchXIAFATimingDialog.endDate = endTimeStr
            SceneBatchXIAFATimingDialog.time = selectXSFZ
        })

        picker.show()

    }


}