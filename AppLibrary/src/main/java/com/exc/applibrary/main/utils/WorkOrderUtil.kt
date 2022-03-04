package com.exc.applibrary.main.utils

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.addapp.pickers.common.LineConfig
import cn.addapp.pickers.picker.SinglePicker
import cn.bingoogolapple.baseadapter.BGARecyclerViewAdapter
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper
import com.bumptech.glide.Glide
import com.exc.applibrary.R
import com.exc.applibrary.main.HttpRequest
import com.exc.applibrary.main.customview.BottomDialog
import com.exc.applibrary.main.model.*
import com.exc.applibrary.main.ui.activity.BrowsePicActivity
import com.exc.applibrary.main.ui.activity.OrderEditActivity
import com.exc.applibrary.main.ui.activity.WorkOrderDetailActivity
import com.exc.applibrary.main.utils.PickerUtil.Companion.initSelectOnStrPicker
import com.exc.applibrary.main.utils.PickerUtil.OnPickerSelectListener
import org.greenrobot.eventbus.EventBus
import zuo.biao.library.interfaces.OnHttpResponseListener
import zuo.biao.library.ui.AlertDialog
import zuo.biao.library.util.CommonUtil.showShortToast
import zuo.biao.library.util.CommonUtil.toActivity
import zuo.biao.library.util.JsonUtils
import zuo.biao.library.util.PreferencesUtil
import zuo.biao.library.util.StringUtil
import java.io.File


class WorkOrderUtil {


    companion object : OnHttpResponseListener {

        lateinit var workOrderDetail: WorkOrderDetail
        lateinit var loginInfo: LoginInfo
        lateinit var mActivity: Activity
        var userLevel = 3
        val CLASS_MANE = "WorkOrderUtil"

        val REQUEST_CODE_QUERY_ROLE_LIST_BY_PARTITION = 2000
        val REQUEST_CODE_QUERY_OPERATOR_LIST_BY_ROLE = 2001

        val selectType_camare: Int = 0
        val selectType_gallery: Int = 1
        val selectType_file: Int = 2


        fun initTimeLine(inflater: LayoutInflater, workOrderDetail: WorkOrderDetail, ll_timeline: LinearLayout) {
            repeat(workOrderDetail.data.orderProcessList.size) {
                var itemWorkOrderDetail = workOrderDetail.data.orderProcessList[it]
                val view: View = inflater.inflate(R.layout.item_order_timeline, null)
                var itemBg: ImageView = view.findViewById(R.id.iv_bg)

                if (workOrderDetail.data.orderProcessList.size == 1) {
                    itemBg.setImageResource(R.drawable.odre_timeline_only1_bg9)
                } else {
                    if (it == 0) {
                        itemBg.setImageResource(R.drawable.odre_timeline_start_bg9)
                    }
                    if (it == workOrderDetail.data.orderProcessList.size - 1) {
                        itemBg.setImageResource(R.drawable.odre_timeline_end_bg9)
                    }

                }


                view.findViewById<TextView>(R.id.tv_name_time).text = itemWorkOrderDetail.operatorName + " " + itemWorkOrderDetail.ctime

                var tvNote = view.findViewById<TextView>(R.id.tv_note)
                if (!StringUtil.isEmpty(itemWorkOrderDetail.remark)) {
                    var ramake = "备注：" + itemWorkOrderDetail.remark
                    if (ramake.length > 20) {
                        ramake = ramake.substring(0, 20) + "..."
                    }
                    tvNote.text = ramake
                }
                var record: String = "未知记录"

                when (itemWorkOrderDetail.statusId) {

                    1 -> {
                        record = "市级管理员初审-通过"
                    }

                    2 -> {
                        record = "工单被驳回"
                    }
                    3 -> {
                        record = "区级管理员初审-通过"
                    }
                    5 -> {
                        record = "接收工单，正在处理"
                    }
                    6 -> {
                        record = "处理完成，等待区级管理员审核"
                    }
                    7 -> {
                        record = "市级管理员审核-通过"
                    }
                    9 -> {
                        record = "生成工单"
                    }
                    10 -> {
                        record = "区级管理员审核-通过"
                    }
                    11 -> {
                        record = "处理超时"
                    }
                    12 -> {
                        record = "问题申报中"
                    }
                    13 -> {
                        record = "问题申报已通过"
                    }
                    14 -> {
                        record = "问题申报已拒绝"

                    }
                    15 -> {
                        record = "工单第" + itemWorkOrderDetail.urgeCount + "次提醒，请尽快处理"
                    }
                }
                view.findViewById<TextView>(R.id.tv_record).text = record
                ll_timeline.addView(view)
            }
        }


        fun setBottomBtn(ll_bottom_btn: LinearLayout, tv_bottom_left: TextView,
                         tv_bottom_center_line: View, tv_bottom_right: TextView, isOverTime: Boolean,
                         workOrderOperationSucListener: WorkOrderOperationSucListener
        ) {

            /**
             * 根据工单状态和用户等级
             * 用户等级（2——市级管理员，4——区级管理员 和其他）
             */
            val CITY_MANAGER = 1
            val PARTITION_MANAGER = 2
            val OTHER_PERSONNEL = 3
            loginInfo = JsonUtils.parseObject(PreferencesUtil.getString(mActivity, Constant.LOGIN_INFO_JSON), LoginInfo::class.java)
            userLevel = OTHER_PERSONNEL
            if (WorkOrderDetailActivity.mUserData.data.roles[0].grade == 2) {
                userLevel = CITY_MANAGER
            }
            when (WorkOrderDetailActivity.mUserData.data.roles[0].grade) {
                2 -> userLevel = CITY_MANAGER
                4 -> userLevel = PARTITION_MANAGER
            }

            var creatorIsOneself = false
            if (loginInfo.data.userId == workOrderDetail.data.orderNew.creator) {//创建人是否是自己
                creatorIsOneself = true
            }
            var statusId = workOrderDetail.data.orderNew.statusId
            ll_bottom_btn.visibility = View.GONE
            //其他人员只能看到自己的工单
            when {
                //待初审(市级)
                intArrayOf(9, 12).indexOf(statusId) != -1 -> {
                    /**
                     * 必有1个初审按钮
                     * 待初审（市级）没有超时的情况
                     */

                    when (userLevel) {
                        CITY_MANAGER -> {//市级管理员
                            if (creatorIsOneself) {//如果创建人是自己就有编辑按钮
                                tv_bottom_left.visibility = View.VISIBLE
                                tv_bottom_left.text = "初审"
                                tv_bottom_left.setOnClickListener {
                                    initAudit(workOrderOperationSucListener)
                                }
                                tv_bottom_center_line.visibility = View.VISIBLE
                                tv_bottom_right.text = "编辑"
                                tv_bottom_right.visibility = View.VISIBLE
                                tv_bottom_right.setOnClickListener {
                                    if (null == workOrderDetail) {
                                        showShortToast(mActivity, "请稍后")
                                        return@setOnClickListener
                                    }
                                    if (workOrderDetail.code != 200) {
                                        showShortToast(mActivity, workOrderDetail.message)
                                        return@setOnClickListener
                                    }
                                    toActivity(mActivity, OrderEditActivity.createIntent(mActivity, workOrderDetail))
                                }
                            } else {//只有初审按钮
                                tv_bottom_left.visibility = View.VISIBLE
                                tv_bottom_left.text = "初审"
                                tv_bottom_left.setOnClickListener {
                                    initAudit(workOrderOperationSucListener)
                                }
                                tv_bottom_center_line.visibility = View.GONE
                                tv_bottom_right.visibility = View.GONE
                            }
                            ll_bottom_btn.visibility = View.VISIBLE
                        }
                        PARTITION_MANAGER -> {//区级管理员
                            if (creatorIsOneself) {//如果创建人是自己就有编辑按钮
                                tv_bottom_left.visibility = View.VISIBLE
                                tv_bottom_left.text = "初审"
                                tv_bottom_left.setOnClickListener {
                                    ToastUtils.showErrorToast(mActivity, "你没有权限操作")
                                }
                                tv_bottom_center_line.visibility = View.VISIBLE
                                tv_bottom_right.text = "编辑"
                                tv_bottom_right.visibility = View.VISIBLE
                                tv_bottom_right.setOnClickListener {
                                    if (null == workOrderDetail) {
                                        showShortToast(mActivity, "请稍后")
                                        return@setOnClickListener
                                    }
                                    if (workOrderDetail.code != 200) {
                                        showShortToast(mActivity, workOrderDetail.message)
                                        return@setOnClickListener
                                    }
                                    toActivity(mActivity, OrderEditActivity.createIntent(mActivity, workOrderDetail))
                                }
                            } else {//只有初审按钮
                                tv_bottom_left.visibility = View.VISIBLE
                                tv_bottom_left.text = "初审"
                                tv_bottom_left.setOnClickListener {
                                    ToastUtils.showErrorToast(mActivity, "你没有权限操作")
                                }
                                tv_bottom_center_line.visibility = View.GONE
                                tv_bottom_right.visibility = View.GONE
                            }

                            ll_bottom_btn.visibility = View.VISIBLE

                        }
                        OTHER_PERSONNEL -> { //其他人员
                            tv_bottom_left.visibility = View.GONE
                            tv_bottom_center_line.visibility = View.GONE
                            tv_bottom_right.text = "编辑"
                            tv_bottom_right.visibility = View.VISIBLE
                            tv_bottom_right.setOnClickListener {
                                if (null == workOrderDetail) {
                                    showShortToast(mActivity, "请稍后")
                                    return@setOnClickListener
                                }
                                if (workOrderDetail.code != 200) {
                                    showShortToast(mActivity, workOrderDetail.message)
                                    return@setOnClickListener
                                }
                                toActivity(mActivity, OrderEditActivity.createIntent(mActivity, workOrderDetail))
                            }
                            ll_bottom_btn.visibility = View.VISIBLE
                        }
                    }
                }
                //待初审(区级)
                intArrayOf(1, 13, 14).indexOf(statusId) != -1 -> {
//                    showShortToast(mActivity, "待初审(区级)")
                    when (userLevel) {
                        CITY_MANAGER -> {//市级管理员
                            if (isOverTime) { //初审，催单
                                tv_bottom_left.text = "初审"
                                tv_bottom_center_line.visibility = View.VISIBLE
                                tv_bottom_right.text = "催单"
                                tv_bottom_right.visibility = View.VISIBLE
                                tv_bottom_left.setOnClickListener {
                                    ToastUtils.showErrorToast(mActivity, "你没有权限操作")
                                }
                                tv_bottom_right.setOnClickListener {
                                    rushOrder(workOrderOperationSucListener)
                                }
                                ll_bottom_btn.visibility = View.VISIBLE
                            } else {
                                tv_bottom_left.text = "初审"
                                tv_bottom_center_line.visibility = View.GONE
                                tv_bottom_right.visibility = View.GONE
                                tv_bottom_left.setOnClickListener {
                                    ToastUtils.showErrorToast(mActivity, "你没有权限操作")
                                }
                                ll_bottom_btn.visibility = View.VISIBLE
                            }
                        }
                        PARTITION_MANAGER -> {//区级管理员
//                            if (isOverTime) { //初审，问题申报
                            tv_bottom_left.text = "初审"
                            tv_bottom_center_line.visibility = View.VISIBLE
                            tv_bottom_right.text = "问题申报"
                            tv_bottom_right.visibility = View.VISIBLE
                            tv_bottom_left.setOnClickListener {
                                initAudit(workOrderOperationSucListener)
                            }
                            tv_bottom_right.setOnClickListener {
                                errorReport(workOrderOperationSucListener)
                            }
                            ll_bottom_btn.visibility = View.VISIBLE
//                            } else {
//                                tv_bottom_left.text = "初审"
//                                tv_bottom_center_line.visibility = View.GONE
//                                tv_bottom_right.visibility = View.GONE
//                                tv_bottom_left.setOnClickListener {
//                                    initAudit(workOrderOperationSucListener)
//                                }
//                                ll_bottom_btn.visibility = View.VISIBLE
//                            }
                        }
                        OTHER_PERSONNEL -> { //其他人员不显示按钮
                            ll_bottom_btn.visibility = View.GONE
                        }
                    }
                }
                //被驳回
                intArrayOf(2).indexOf(statusId) != -1 -> {
                    //被驳回的工单只有自己有编辑按钮，人员没有任何按钮
                    if (creatorIsOneself) {
                        tv_bottom_left.text = "编辑"
                        tv_bottom_center_line.visibility = View.GONE
                        tv_bottom_right.visibility = View.GONE
                        tv_bottom_left.setOnClickListener {
                            if (null == workOrderDetail) {
                                showShortToast(mActivity, "请稍后")
                                return@setOnClickListener
                            }
                            if (workOrderDetail.code != 200) {
                                showShortToast(mActivity, workOrderDetail.message)
                                return@setOnClickListener
                            }
                            toActivity(mActivity, OrderEditActivity.createIntent(mActivity, workOrderDetail))
                        }
                        ll_bottom_btn.visibility = View.VISIBLE
                    } else {
                        ll_bottom_btn.visibility = View.GONE
                    }

                }
                //待处理
                intArrayOf(3).indexOf(statusId) != -1 -> {
                    //待处理的不显示任何按钮
                    ll_bottom_btn.visibility = View.GONE
                }
                //正在处理
                intArrayOf(5).indexOf(statusId) != -1 -> {
                    //只有处理人是自己有按钮

                    if (workOrderDetail.data.orderNew.operator == loginInfo.data.userId) {
                        tv_bottom_left.text = "工单处理"
                        tv_bottom_center_line.visibility = View.GONE
                        tv_bottom_right.visibility = View.GONE
                        tv_bottom_left.setOnClickListener {
                            orderOperator()
                        }
                        ll_bottom_btn.visibility = View.VISIBLE
                    }

                }
                //待审核(区级)
                intArrayOf(6).indexOf(statusId) != -1 -> {
                    if (userLevel == PARTITION_MANAGER) {
                        tv_bottom_left.text = "审核"
                        tv_bottom_center_line.visibility = View.GONE
                        tv_bottom_right.visibility = View.GONE
                        tv_bottom_left.setOnClickListener {

                            //查询审核信息后弹出审核弹窗
                            HttpRequest.queryRoleListByPartitionId(workOrderDetail.data.orderNew.partitionId, REQUEST_CODE_QUERY_ROLE_LIST_BY_PARTITION, this)

                        }
                        ll_bottom_btn.visibility = View.VISIBLE
                    } else {
                        ll_bottom_btn.visibility = View.GONE
                    }

                }
                //待审核(市级)
                intArrayOf(10).indexOf(statusId) != -1 -> {

                    if (userLevel == CITY_MANAGER) {
                        tv_bottom_left.text = "审核"
                        tv_bottom_center_line.visibility = View.GONE
                        tv_bottom_right.visibility = View.GONE
                        tv_bottom_left.setOnClickListener {

                            doAuditCity()

                        }
                        ll_bottom_btn.visibility = View.VISIBLE
                    }

                }
                //审核通过
                intArrayOf(7).indexOf(statusId) != -1 -> {
                    //没有任何按钮
                    ll_bottom_btn.visibility = View.GONE
                }
            }
        }

        /**
         * 工单处理
         */
        private fun orderOperator() {


            val mInflater = LayoutInflater.from(mActivity)
            bottomBTNdialog = Dialog(mActivity, R.style.MyDialog)
            val view: View = mInflater.inflate(R.layout.dialog_order_operator, null)
            bottomBTNdialog.setCanceledOnTouchOutside(false)
            view.findViewById<ImageView>(R.id.iv_close).setOnClickListener {
                bottomBTNdialog.dismiss()
            }
            view.findViewById<TextView>(R.id.btn_cancel).setOnClickListener {
                bottomBTNdialog.dismiss()
            }

            var et_describe = view.findViewById<EditText>(R.id.et_describe)

            var img_recyclerview = view.findViewById<RecyclerView>(R.id.img_recyclerview)
            val layoutManager = GridLayoutManager(mActivity, 3) //第二个参数为网格的列数
            img_recyclerview.layoutManager = layoutManager
            img_recyclerview.isNestedScrollingEnabled = false
            imgAdapter = ImgAdapter(img_recyclerview)
            img_recyclerview.adapter = imgAdapter
            fileIds.clear()

            var tempFile = OrderErrorReportImgData()
            tempFile.isVirtual = true
            fileIds.add(tempFile)


            imgAdapter.data = fileIds
            imgAdapter.setOnItemChildClickListener { _: ViewGroup, childView: View, position: Int ->
                when (childView.id) {
                    R.id.iv -> {
                        var picBen = WorkOrderDetail.Data.OrderNew.OrderPics()
                        picBen.filename = fileIds[position].filename
                        picBen.isXC = true
                        var browsPicIntent = Intent(mActivity, BrowsePicActivity::class.java)
                                .putExtra("picBen", picBen)
                                .putExtra("isEdit", true)
                        mActivity.startActivity(browsPicIntent)
                    }
                    R.id.ll_add -> {
                        //上传图片
                        getPicPathFromDialog()
                    }
                    R.id.iv_close -> {
                        AlertDialog(mActivity, "提示", "您确定要删除此项吗？", true, 0, AlertDialog.OnDialogButtonClickListener { requestCode: Int, isPositive: Boolean ->
                            if (!isPositive) {
                                return@OnDialogButtonClickListener
                            }
                            fileIds.removeAt(position)
                            imgAdapter.data = fileIds
                        }).show()
                    }
                }
            }
            view.findViewById<TextView>(R.id.btn_ok).setOnClickListener {
                var describeStr = et_describe.text.toString().trim()
                var fileIdsSubmit = arrayListOf<Int>()
                repeat(fileIds.size) {
                    if (!fileIds[it].isVirtual) {
                        fileIdsSubmit.add(fileIds[it].id)
                    }
                }
                HttpRequest.orderDoOperator(workOrderDetail.data.orderNew.id, fileIdsSubmit, describeStr, 1005)
                { i: Int?, resultJson: String??, exception: Exception? ->
                    if (StringUtil.isEmpty(resultJson)) {
                        ToastUtils.showErrorToast(mActivity, "数据异常")
                        return@orderDoOperator
                    }
                    val baseBean = JsonUtils.parseObject(resultJson, BaseBean::class.java)
                    CommonUtils.exitLogin(baseBean.code, mActivity)
                    if (baseBean.code != 200) {
                        ToastUtils.showErrorToast(mActivity, baseBean.message)
                        return@orderDoOperator
                    }
                    showShortToast(mActivity, "工单处理成功")
                    bottomBTNdialog.dismiss()
                    EventBus.getDefault().post(EventEnumBean.EDIT_ORDER_SUCCESS)

                }
            }
            bottomBTNdialog.addContentView(view, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT))
            bottomBTNdialog.show()

        }

        private lateinit var mRoleList: ArrayList<OrderOperatorRoleData.DataDTO>
        private lateinit var mSelectOperatorList: ArrayList<OrderOperatorListData.DataDTO>

        /**
         * 审核（区级）
         */
        private fun doAudit(roleList: ArrayList<OrderOperatorRoleData.DataDTO>, fistOperatorList: ArrayList<OrderOperatorListData.DataDTO>) {
            mRoleList = roleList//角色列表
            mSelectOperatorList = fistOperatorList//已选中的处理人列表
            var selectOperatorIndex = -1
            val mInflater = LayoutInflater.from(mActivity)
            bottomBTNdialog = Dialog(mActivity, R.style.MyDialog)
            val view: View = mInflater.inflate(R.layout.dialog_order_do_audit, null)
            bottomBTNdialog.setCanceledOnTouchOutside(false)
            view.findViewById<ImageView>(R.id.iv_close).setOnClickListener {
                bottomBTNdialog.dismiss()
            }
            view.findViewById<TextView>(R.id.btn_cancel).setOnClickListener {
                bottomBTNdialog.dismiss()
            }
            var et_describe = view.findViewById<EditText>(R.id.et_describe)

            var ll_select_role_root = view.findViewById<LinearLayout>(R.id.ll_select_role_root)
            var ll_select_role = view.findViewById<LinearLayout>(R.id.ll_select_role)
            var tv_role_name = view.findViewById<TextView>(R.id.tv_role_name)

            var ll_select_operator_root = view.findViewById<LinearLayout>(R.id.ll_select_operator_root)
            var ll_select_operator = view.findViewById<LinearLayout>(R.id.ll_select_operator)
            var tv_operator_name = view.findViewById<TextView>(R.id.tv_operator_name)

            ll_select_role_root.visibility = View.GONE
            ll_select_operator_root.visibility = View.GONE
            //角色默认当前处理人的角色
            if (mRoleList.size > 0) {
                tv_role_name.text = mRoleList[0].name
            }
            //处理人默认选中当前处理人
//            if (mSelectOperatorList.size > 0) {
//                tv_operator_name.text = mSelectOperatorList[0].name
//            }
            tv_operator_name.text = workOrderDetail.data.orderNew.operatorName



            ll_select_role.setOnClickListener {
                var roleNameStrList = arrayListOf<String>()
                repeat(mRoleList.size) {
                    roleNameStrList.add(mRoleList[it].name)
                }
                initSelectOnStrPicker(true,mActivity, 0, roleNameStrList, object : OnPickerSelectListener {
                    override fun select(itemStr: String, index: Int) {
                        tv_role_name.text = itemStr
                        HttpRequest.queryOperatorListByRole(mRoleList[index].id, workOrderDetail.data.orderNew.partitionId, 3000, ) { _: Int?, resultJson: String?, _: Exception? ->
                            val orderOperatorListData = JsonUtils.parseObject(resultJson, OrderOperatorListData::class.java)
                            CommonUtils.exitLogin(orderOperatorListData.code, mActivity)
                            if (orderOperatorListData.code != 200) {
                                ToastUtils.showErrorToast(mActivity, orderOperatorListData.message)
                            } else {
                                mSelectOperatorList = orderOperatorListData.data
                                tv_operator_name.text = mSelectOperatorList[0].name
                            }
                        }
                    }
                })
            }

            ll_select_operator.setOnClickListener {
                var operatorNameStrList = arrayListOf<String>()
                repeat(mSelectOperatorList.size) {
                    operatorNameStrList.add(mSelectOperatorList[it].name)
                }
                initSelectOnStrPicker(false,mActivity, 0, operatorNameStrList, object : OnPickerSelectListener {
                    override fun select(itemStr: String, index: Int) {
                        tv_operator_name.text = itemStr
                        selectOperatorIndex = index
                    }
                })
            }

            var isTurnDown = 1//是否通过
            view.findViewById<RadioGroup>(R.id.rg).setOnCheckedChangeListener { radioGroup: RadioGroup, checkdId: Int ->

                when (checkdId) {
                    R.id.rb_ok -> {
                        isTurnDown = 1
                        ll_select_role_root.visibility = View.GONE
                        ll_select_operator_root.visibility = View.GONE
                    }
                    R.id.rb_no -> {
                        isTurnDown = 0
                        ll_select_role_root.visibility = View.VISIBLE
                        ll_select_operator_root.visibility = View.VISIBLE
                    }
                }
            }


            view.findViewById<TextView>(R.id.btn_ok).setOnClickListener {

                var operatorId: Int = if (selectOperatorIndex == -1) {
                    workOrderDetail.data.orderNew.operator
                } else {
                    mSelectOperatorList[selectOperatorIndex].id
                }
                HttpRequest.orderDoAuditPartition(isTurnDown, workOrderDetail.data.orderNew.id, operatorId, et_describe.text.toString().trim(),
                        3001, ) { _: Int?, resultJson: String?, _: Exception? ->
                    val baseBean = JsonUtils.parseObject(resultJson, BaseBean::class.java)
                    CommonUtils.exitLogin(baseBean.code, mActivity)
                    if (baseBean.code != 200) {
                        ToastUtils.showErrorToast(mActivity, baseBean.message)
                    } else {
                        ToastUtils.showToast(mActivity, "审核成功", false)
                        EventBus.getDefault().post(EventEnumBean.EDIT_ORDER_SUCCESS)

                    }
                }

            }
            bottomBTNdialog.addContentView(view, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT))
            bottomBTNdialog.show()


        }

        /**
         * 审核（市级）
         */
        private fun doAuditCity() {
            val mInflater = LayoutInflater.from(mActivity)
            bottomBTNdialog = Dialog(mActivity, R.style.MyDialog)
            val view: View = mInflater.inflate(R.layout.dialog_order_do_audit_city, null)
            bottomBTNdialog.setCanceledOnTouchOutside(false)
            view.findViewById<ImageView>(R.id.iv_close).setOnClickListener {
                bottomBTNdialog.dismiss()
            }
            view.findViewById<TextView>(R.id.btn_cancel).setOnClickListener {
                bottomBTNdialog.dismiss()
            }
            var ll_time = view.findViewById<LinearLayout>(R.id.ll_time)
            var et_describe = view.findViewById<EditText>(R.id.et_describe)
            var et_time = view.findViewById<EditText>(R.id.et_time)
            ll_time.visibility = View.GONE
            var rg = view.findViewById<RadioGroup>(R.id.rg)
            var isTurnDown = 1//是否通过
            rg.setOnCheckedChangeListener { radioGroup: RadioGroup, checkdId: Int ->
                when (checkdId) {
                    R.id.rb_ok -> {
                        isTurnDown = 1
                        ll_time.visibility = View.GONE

                    }
                    R.id.rb_no -> {
                        isTurnDown = 0
                        ll_time.visibility = View.VISIBLE
                    }
                }

            }
            view.findViewById<TextView>(R.id.btn_ok).setOnClickListener {
                if (isTurnDown == 0) {
                    if (StringUtil.isEmpty(et_time.text.toString().trim())) {
                        ToastUtils.showErrorToast(mActivity, "请输入完成时长")
                        return@setOnClickListener
                    }
                }
                var handleTime = 0
                if (isTurnDown == 0) {
                    handleTime = et_time.text.toString().trim().toInt()
                }
                HttpRequest.orderDoAuditCity(isTurnDown, handleTime, workOrderDetail.data.orderNew.id, et_describe.text.toString().trim(),
                        4000, ) { _: Int?, resultJson: String?, _: Exception? ->
                    val baseBean = JsonUtils.parseObject(resultJson, BaseBean::class.java)
                    CommonUtils.exitLogin(baseBean.code, mActivity)
                    if (baseBean.code != 200) {
                        ToastUtils.showErrorToast(mActivity, baseBean.message)
                    } else {
                        ToastUtils.showToast(mActivity, "审核成功", false)
                        EventBus.getDefault().post(EventEnumBean.EDIT_ORDER_SUCCESS)

                    }
                }
            }
            bottomBTNdialog.addContentView(view, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT))
            bottomBTNdialog.show()


        }

        lateinit var bottomBTNdialog: Dialog


        var fileIds = arrayListOf<OrderErrorReportImgData>()
        lateinit var imgAdapter: ImgAdapter

        var isUpLoadImgIng: Boolean = false

        /**
         * 问题申报
         */
        private fun errorReport(workOrderOperationSucListener: WorkOrderOperationSucListener) {
            val mInflater = LayoutInflater.from(mActivity)
            bottomBTNdialog = Dialog(mActivity, R.style.MyDialog)
            val view: View = mInflater.inflate(R.layout.dialog_order_error_report, null)
            bottomBTNdialog.setCanceledOnTouchOutside(false)
            view.findViewById<ImageView>(R.id.iv_close).setOnClickListener {
                bottomBTNdialog.dismiss()
            }
            view.findViewById<TextView>(R.id.btn_cancel).setOnClickListener {
                bottomBTNdialog.dismiss()
            }

            var et_describe = view.findViewById<EditText>(R.id.et_describe)

            var img_recyclerview = view.findViewById<RecyclerView>(R.id.img_recyclerview)
            val layoutManager = GridLayoutManager(mActivity, 3) //第二个参数为网格的列数
            img_recyclerview.layoutManager = layoutManager
            img_recyclerview.isNestedScrollingEnabled = false
            imgAdapter = ImgAdapter(img_recyclerview)
            img_recyclerview.adapter = imgAdapter
            fileIds.clear()
            var tempFile = OrderErrorReportImgData()
            tempFile.isVirtual = true
            fileIds.add(tempFile)


            imgAdapter.data = fileIds
            imgAdapter.setOnItemChildClickListener { _: ViewGroup, childView: View, position: Int ->
                when (childView.id) {
                    R.id.iv -> {
                        var picBen = WorkOrderDetail.Data.OrderNew.OrderPics()
                        picBen.filename = fileIds[position].filename
                        picBen.isXC = true
                        var browsPicIntent = Intent(mActivity, BrowsePicActivity::class.java)
                                .putExtra("picBen", picBen)
                                .putExtra("isEdit", true)
                        mActivity.startActivity(browsPicIntent)
                    }
                    R.id.ll_add -> {
                        //上传图片
                        getPicPathFromDialog()
                    }
                    R.id.iv_close -> {
                        AlertDialog(mActivity, "提示", "您确定要删除此项吗？", true, 0, AlertDialog.OnDialogButtonClickListener { requestCode: Int, isPositive: Boolean ->
                            if (!isPositive) {
                                return@OnDialogButtonClickListener
                            }
                            fileIds.removeAt(position)
                            imgAdapter.data = fileIds
                        }).show()
                    }
                }
            }
            view.findViewById<TextView>(R.id.btn_ok).setOnClickListener {
                if (StringUtil.isEmpty(et_describe.text.toString().trim())) {
                    ToastUtils.showErrorToast(mActivity, "请输入申报问题")
                    return@setOnClickListener
                }
                var describeStr = et_describe.text.toString().trim()

                var fileIdsSubmit = arrayListOf<Int>()
                repeat(fileIds.size) {
                    if (!fileIds[it].isVirtual) {
                        fileIdsSubmit.add(fileIds[it].id)
                    }
                }
                HttpRequest.orderErrorReport(describeStr, workOrderDetail.data.orderNew.id, fileIdsSubmit, 1005)
                { i: Int?, resultJson: String??, exception: Exception? ->
                    if (StringUtil.isEmpty(resultJson)) {
                        ToastUtils.showErrorToast(mActivity, "数据异常")
                        return@orderErrorReport
                    }
                    val baseBean = JsonUtils.parseObject(resultJson, BaseBean::class.java)
                    CommonUtils.exitLogin(baseBean.code, mActivity)
                    if (baseBean.code != 200) {
                        ToastUtils.showErrorToast(mActivity, baseBean.message)
                        return@orderErrorReport
                    }
                    showShortToast(mActivity, "申报成功")
                    bottomBTNdialog.dismiss()
                    EventBus.getDefault().post(EventEnumBean.EDIT_ORDER_SUCCESS)

                }
            }
            bottomBTNdialog.addContentView(view, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT))
            bottomBTNdialog.show()
        }

        class ImgAdapter(recyclerView: RecyclerView?) :
                BGARecyclerViewAdapter<OrderErrorReportImgData>(recyclerView, R.layout.item_order_edit_img) {
            override fun fillData(helper: BGAViewHolderHelper?, position: Int, model: OrderErrorReportImgData?) {
                val imageView = helper?.getImageView(R.id.iv)
                val ll_add = helper?.getView<LinearLayout>(R.id.ll_add)
                val iv_close = helper?.getImageView(R.id.iv_close)


                if (model?.isXC!!) {
                    Glide.with(mContext).load(File(model?.filename)).into(imageView!!)
                    imageView?.visibility = View.VISIBLE
                    iv_close?.visibility = View.VISIBLE
                    ll_add?.visibility = View.GONE
                    return
                }


                if (model?.isVirtual!!) {
                    ll_add?.visibility = View.VISIBLE
                    iv_close?.visibility = View.GONE
                    imageView?.visibility = View.GONE
                    return
                }


            }

            override fun setItemChildListener(helper: BGAViewHolderHelper, viewType: Int) {
                helper.setItemChildClickListener(R.id.iv)
                helper.setItemChildClickListener(R.id.iv_close)
                helper.setItemChildClickListener(R.id.ll_add)
            }
        }

        private lateinit var choosePicBottomDialog: BottomDialog

        /**
         * 上传图片
         */
        private fun getPicPathFromDialog() {
            val builder = BottomDialog.Builder(mActivity)
            builder.setContentView(R.layout.dialog_choose_phone_pic)
            choosePicBottomDialog = builder.create()
            choosePicBottomDialog.setCanceledOnTouchOutside(false)
            val bottomView = choosePicBottomDialog.contentView
            val iv_camera = bottomView.findViewById<ImageView>(R.id.iv_camera)
            val iv_gallery = bottomView.findViewById<ImageView>(R.id.iv_gallery)

            iv_camera.setOnClickListener {
                choosePicBottomDialog.dismiss()

                var upLoadEventBean = UpLoadEventBean()
                upLoadEventBean.className = CLASS_MANE
                upLoadEventBean.type = selectType_camare
                EventBus.getDefault().post(upLoadEventBean)

            }
            iv_gallery.setOnClickListener {
                choosePicBottomDialog.dismiss()
                var upLoadEventBean = UpLoadEventBean()
                upLoadEventBean.className = CLASS_MANE
                upLoadEventBean.type = selectType_gallery
                EventBus.getDefault().post(upLoadEventBean)

            }
            WorkOrderDetailActivity.setOnSelectImgSucListener(OnSelectImgSucListener {
//                ToastUtils.showToast(mActivity,"选择图片成功" + it.hashCode(),false)
                if (isUpLoadImgIng) {
                    showShortToast(mActivity, "图片上传中，请稍后")
                    return@OnSelectImgSucListener
                }
                isUpLoadImgIng = true
                UpLoadUtil.uploadImage(mActivity, it.image.originalPath, object : UpLoadUtil.UpLoadImgListener {
                    override fun upLoadSuccess(imgId: Int) {
                        isUpLoadImgIng = false
                        //修改fileIds并刷新
                        var tempFile = OrderErrorReportImgData()
                        tempFile.isVirtual = false
                        tempFile.filename = it.image.originalPath
                        tempFile.id = imgId
                        tempFile.isXC = true
                        fileIds.add(fileIds.size - 1, tempFile)
                        if (fileIds.size > 6) {//最多6张
                            fileIds.removeAt(fileIds.size - 1)
                        }
                        mActivity.runOnUiThread {
                            imgAdapter.data = fileIds
                        }
                        choosePicBottomDialog.dismiss()
                    }

                    override fun upLoadFail() {
                        isUpLoadImgIng = false
                        mActivity.runOnUiThread {
                            showShortToast(mActivity, "上传失败，请重试")
                        }

                    }
                })


            })


            bottomView.findViewById<View>(R.id.tv_cancel).setOnClickListener { v: View? -> choosePicBottomDialog.dismiss() }
            choosePicBottomDialog.show()
        }


        /**
         * 催单
         */
        private fun rushOrder(workOrderOperationSucListener: WorkOrderOperationSucListener) {
            val mInflater = LayoutInflater.from(mActivity)
            bottomBTNdialog = Dialog(mActivity, R.style.MyDialog)
            val view: View = mInflater.inflate(R.layout.dialog_order_rush_order, null)
            bottomBTNdialog.setCanceledOnTouchOutside(false)
            var et_time = view.findViewById<EditText>(R.id.et_time)
            view.findViewById<ImageView>(R.id.iv_close).setOnClickListener {
                bottomBTNdialog.dismiss()
            }
            view.findViewById<TextView>(R.id.btn_cancel).setOnClickListener {
                bottomBTNdialog.dismiss()
            }
            view.findViewById<TextView>(R.id.btn_ok).setOnClickListener {
                if (StringUtil.isEmpty(et_time.text.toString().trim())) {
                    ToastUtils.showErrorToast(mActivity, "请输入完成时长")
                    return@setOnClickListener
                }
                var handleTime = et_time.text.toString().trim().toInt()
                HttpRequest.orderRushOrder(handleTime, workOrderDetail.data.orderNew.id, 1002)
                { i: Int?, resultJson: String??, exception: Exception? ->
                    if (StringUtil.isEmpty(resultJson)) {
                        ToastUtils.showErrorToast(mActivity, "数据异常")
                        return@orderRushOrder
                    }
                    val baseBean = JsonUtils.parseObject(resultJson, BaseBean::class.java)
                    CommonUtils.exitLogin(baseBean.code, mActivity)
                    if (baseBean.code != 200) {
                        ToastUtils.showErrorToast(mActivity, baseBean.message)
                        return@orderRushOrder
                    }
                    ToastUtils.showToast(mActivity, "催单成功", false)
                    bottomBTNdialog.dismiss()
                    EventBus.getDefault().post(EventEnumBean.EDIT_ORDER_SUCCESS)

                }
            }
            bottomBTNdialog.addContentView(view, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT))
            bottomBTNdialog.show()

        }

        /**
         * 初审
         */
        private fun initAudit(workOrderOperationSucListener: WorkOrderOperationSucListener) {
            var gradeIds = "0"
            if (intArrayOf(9, 12).indexOf(workOrderDetail.data.orderNew.statusId) != -1) {//市级初审---查询区级的管理员
                gradeIds = "4"
            }
            if (intArrayOf(1, 13, 14).indexOf(workOrderDetail.data.orderNew.statusId) != -1) {//市级初审---查询区级的管理员
                gradeIds = "5,6"
            }
            HttpRequest.orderCityInitAuditGetUserList(gradeIds, workOrderDetail.data.orderNew.partitionId, 1000) { i: Int?, resultJson: String??, exception: Exception? ->
                if (StringUtil.isEmpty(resultJson)) {
                    ToastUtils.showErrorToast(mActivity, "数据异常")
                    return@orderCityInitAuditGetUserList
                }
                val orderAuditSelectManagerList = JsonUtils.parseObject(resultJson, OrderAuditSelectManagerList::class.java)
                CommonUtils.exitLogin(orderAuditSelectManagerList.code, mActivity)
                if (orderAuditSelectManagerList.code != 200) {
                    ToastUtils.showErrorToast(mActivity, orderAuditSelectManagerList.message)
                } else {
                    var selectManagerData: OrderAuditSelectManagerList.Data? = null
                    val mInflater = LayoutInflater.from(mActivity)
                    bottomBTNdialog = Dialog(mActivity, R.style.MyDialog)
                    val view: View = mInflater.inflate(R.layout.dialog_order_audit, null)
                    bottomBTNdialog.setCanceledOnTouchOutside(false)
                    var ll_select_manager = view.findViewById<LinearLayout>(R.id.ll_select_manager)
                    var ll_select_manager_root = view.findViewById<LinearLayout>(R.id.ll_select_manager_root)
                    var ll_time = view.findViewById<LinearLayout>(R.id.ll_time)
                    var tv_manager_name = view.findViewById<TextView>(R.id.tv_manager_name)
                    var et_time = view.findViewById<EditText>(R.id.et_time)
                    var et_describe = view.findViewById<EditText>(R.id.et_describe)
                    var btn_ok = view.findViewById<TextView>(R.id.btn_ok)
                    var tv_manager_str = view.findViewById<TextView>(R.id.tv_manager_str)

                    var rg = view.findViewById<RadioGroup>(R.id.rg)

                    if(userLevel == 1){
                        tv_manager_name.text = "请选择管理员"
                    }else{
                        tv_manager_name.text = "请选择处理人员"
                        tv_manager_str.text = "请选择处理人员："
                    }



                    var isTurnDown = 1//是否通过
                    rg.setOnCheckedChangeListener { radioGroup: RadioGroup, checkdId: Int ->

                        when (checkdId) {
                            R.id.rb_ok -> {
                                isTurnDown = 1
                                ll_select_manager_root.visibility = View.VISIBLE
                                ll_time.visibility = View.VISIBLE
                                et_time.setText("")
                                et_time.hint = "请输入时长"

                                if(userLevel == 1){
                                    tv_manager_name.text = "请选择管理员"
                                }else{
                                    tv_manager_name.text = "请选择处理人员"
                                }

                                tv_manager_name.setTextColor(Color.parseColor("#555555"))
                                selectManagerData = null
//                                selectManagerData = orderAuditSelectManagerList.data[0]


                            }
                            R.id.rb_no -> {
                                isTurnDown = 0
                                ll_select_manager_root.visibility = View.GONE
                                ll_time.visibility = View.GONE
                            }


                        }

                    }


                    view.findViewById<ImageView>(R.id.iv_close).setOnClickListener {
                        bottomBTNdialog.dismiss()
                    }
                    view.findViewById<TextView>(R.id.btn_cancel).setOnClickListener {
                        bottomBTNdialog.dismiss()
                    }

                    if (orderAuditSelectManagerList.data.size == 0) {
                        tv_manager_name.setText("       ")
                    } else {
                        //默认选第一个管理员

                        //数量测试
//                        orderAuditSelectManagerList.data.addAll(orderAuditSelectManagerList.data)
                        selectManagerData = orderAuditSelectManagerList.data[0]
                        tv_manager_name.text = selectManagerData?.name
                        tv_manager_name.setTextColor(Color.parseColor("#000000"))

                        var nameStrList = arrayOfNulls<String>(orderAuditSelectManagerList.data.size)
                        repeat(orderAuditSelectManagerList.data.size) {
                            nameStrList[it] = orderAuditSelectManagerList.data[it].name
                        }
                        ll_select_manager.setOnClickListener {
                            var selectedIndex = 0
                            //找到之前已经选择的管理员
                            repeat(nameStrList.size) {
                                if (nameStrList[it] == selectManagerData?.name) {
                                    selectedIndex = it
                                }
                            }
                            var picker = SinglePicker(mActivity, nameStrList)
                            picker.setCanLoop(false) //不禁用循环
                            picker.setTopBackgroundColor(-0x111112)
                            picker.setTopHeight(50)
                            picker.setGravity(Gravity.BOTTOM)
                            picker.setTitleText("请选择管理员")
                            if(userLevel == 1){
                                picker.setTitleText("请选择管理员")
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
//            showShortToast(mActivity, ("index=$index, item=$item"))


                                tv_manager_name.text = item
                                tv_manager_name.setTextColor(Color.parseColor("#000000"))
                                selectManagerData = orderAuditSelectManagerList.data[index]

                            }
                            picker.show()


//                            var attachPopupView = XPopup.Builder(mActivity)
//                                    .hasShadowBg(false) //                            .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
//                                    //                        .isDarkTheme(true)
//                                    //                        .popupAnimation(PopupAnimation.ScaleAlphaFromCenter) //NoAnimation表示禁用动画
//                                    .isCenterHorizontal(true) //是否与目标水平居中对齐
//                                    //                        .offsetY(-60)
//                                    //                        .offsetX(80)
//                                    .popupPosition(PopupPosition.Bottom) //手动指定弹窗的位置
//                                    .popupWidth(ll_select_manager.width)
//                                    .atView(ll_select_manager) // 依附于所点击的View，内部会自动判断在上方或者下方显示
//                                    .asAttachList(nameStrList, intArrayOf(), { position, text ->
//                                        tv_manager_name.text = text
//                                        tv_manager_name.setTextColor(Color.parseColor("#000000"))
//                                        selectManagerData = orderAuditSelectManagerList.data[position]
//                                    }, 0, 0)
//                            attachPopupView.show()
                        }

                    }


                    btn_ok.setOnClickListener {
                        if (isTurnDown == 1) {
                            if (null == selectManagerData) {
                                if(userLevel == 1){
                                    ToastUtils.showErrorToast(mActivity, "请选择管理员")
                                }else{
                                    ToastUtils.showErrorToast(mActivity, "请选择处理人员")
                                }

                                return@setOnClickListener
                            }
                            if (StringUtil.isEmpty(et_time.text.toString().trim())) {
                                ToastUtils.showErrorToast(mActivity, "请输入完成时长")
                                return@setOnClickListener
                            }
                        }
                        var handleTime = 0

                        if (isTurnDown == 1) {
                            handleTime = et_time.text.toString().trim().toInt()
                        }


                        HttpRequest.orderInitAudit(userLevel, isTurnDown,
                                selectManagerData!!.id,
                                handleTime,
                                et_describe.text.toString().trim(),
                                workOrderDetail.data.orderNew.id,
                                selectManagerData!!.roleId, 1001)
                        { i: Int?, resultJson: String??, exception: Exception? ->
                            if (StringUtil.isEmpty(resultJson)) {
                                ToastUtils.showErrorToast(mActivity, "数据异常")
                                return@orderInitAudit
                            }
                            val baseBean = JsonUtils.parseObject(resultJson, BaseBean::class.java)
                            CommonUtils.exitLogin(baseBean.code, mActivity)
                            if (baseBean.code != 200) {
                                ToastUtils.showErrorToast(mActivity, baseBean.message)
                                return@orderInitAudit
                            }
                            ToastUtils.showToast(mActivity, "审核成功", false)
                            bottomBTNdialog.dismiss()
                            EventBus.getDefault().post(EventEnumBean.EDIT_ORDER_SUCCESS)

                        }
                    }
                    bottomBTNdialog.addContentView(view, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT))
                    bottomBTNdialog.show()

                }
            }
        }


        override fun onHttpResponse(requestCode: Int, resultJson: String?, e: Exception?) {
            if (StringUtil.isEmpty(resultJson)) {
                ToastUtils.showErrorToast(mActivity, "数据异常")
                return
            }
            when (requestCode) {
                REQUEST_CODE_QUERY_ROLE_LIST_BY_PARTITION -> {//查询处理角色
                    val orderOperatorRoleData = JsonUtils.parseObject(resultJson, OrderOperatorRoleData::class.java)
                    CommonUtils.exitLogin(orderOperatorRoleData.code, mActivity)
                    if (orderOperatorRoleData.code != 200) {
                        ToastUtils.showErrorToast(mActivity, orderOperatorRoleData.message)
                    } else {
                        //查询默认的第一中角色的处理人员列表
                        if (orderOperatorRoleData.data.size > 0) {
                            mRoleList = orderOperatorRoleData.data
                            HttpRequest.queryOperatorListByRole(orderOperatorRoleData.data[0].id, workOrderDetail.data.orderNew.partitionId, REQUEST_CODE_QUERY_OPERATOR_LIST_BY_ROLE, this)
                        } else {
                            doAudit(arrayListOf(), arrayListOf())
                        }
                    }

                }

                REQUEST_CODE_QUERY_OPERATOR_LIST_BY_ROLE -> {

                    val orderOperatorListData = JsonUtils.parseObject(resultJson, OrderOperatorListData::class.java)
                    CommonUtils.exitLogin(orderOperatorListData.code, mActivity)
                    if (orderOperatorListData.code != 200) {
                        ToastUtils.showErrorToast(mActivity, orderOperatorListData.message)
                    } else {
                        if (orderOperatorListData.data.size > 0) {
                            doAudit(mRoleList, orderOperatorListData.data)
                        } else {
                            doAudit(mRoleList, arrayListOf())
                        }
                    }
                }

            }

        }

    }

}