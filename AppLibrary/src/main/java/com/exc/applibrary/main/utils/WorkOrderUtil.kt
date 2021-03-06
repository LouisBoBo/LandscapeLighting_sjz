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
                    var ramake = "?????????" + itemWorkOrderDetail.remark
                    if (ramake.length > 20) {
                        ramake = ramake.substring(0, 20) + "..."
                    }
                    tvNote.text = ramake
                }
                var record: String = "????????????"

                when (itemWorkOrderDetail.statusId) {

                    1 -> {
                        record = "?????????????????????-??????"
                    }

                    2 -> {
                        record = "???????????????"
                    }
                    3 -> {
                        record = "?????????????????????-??????"
                    }
                    5 -> {
                        record = "???????????????????????????"
                    }
                    6 -> {
                        record = "??????????????????????????????????????????"
                    }
                    7 -> {
                        record = "?????????????????????-??????"
                    }
                    9 -> {
                        record = "????????????"
                    }
                    10 -> {
                        record = "?????????????????????-??????"
                    }
                    11 -> {
                        record = "????????????"
                    }
                    12 -> {
                        record = "???????????????"
                    }
                    13 -> {
                        record = "?????????????????????"
                    }
                    14 -> {
                        record = "?????????????????????"

                    }
                    15 -> {
                        record = "?????????" + itemWorkOrderDetail.urgeCount + "???????????????????????????"
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
             * ?????????????????????????????????
             * ???????????????2????????????????????????4????????????????????? ????????????
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
            if (loginInfo.data.userId == workOrderDetail.data.orderNew.creator) {//????????????????????????
                creatorIsOneself = true
            }
            var statusId = workOrderDetail.data.orderNew.statusId
            ll_bottom_btn.visibility = View.GONE
            //???????????????????????????????????????
            when {
                //?????????(??????)
                intArrayOf(9, 12).indexOf(statusId) != -1 -> {
                    /**
                     * ??????1???????????????
                     * ??????????????????????????????????????????
                     */

                    when (userLevel) {
                        CITY_MANAGER -> {//???????????????
                            if (creatorIsOneself) {//??????????????????????????????????????????
                                tv_bottom_left.visibility = View.VISIBLE
                                tv_bottom_left.text = "??????"
                                tv_bottom_left.setOnClickListener {
                                    initAudit(workOrderOperationSucListener)
                                }
                                tv_bottom_center_line.visibility = View.VISIBLE
                                tv_bottom_right.text = "??????"
                                tv_bottom_right.visibility = View.VISIBLE
                                tv_bottom_right.setOnClickListener {
                                    if (null == workOrderDetail) {
                                        showShortToast(mActivity, "?????????")
                                        return@setOnClickListener
                                    }
                                    if (workOrderDetail.code != 200) {
                                        showShortToast(mActivity, workOrderDetail.message)
                                        return@setOnClickListener
                                    }
                                    toActivity(mActivity, OrderEditActivity.createIntent(mActivity, workOrderDetail))
                                }
                            } else {//??????????????????
                                tv_bottom_left.visibility = View.VISIBLE
                                tv_bottom_left.text = "??????"
                                tv_bottom_left.setOnClickListener {
                                    initAudit(workOrderOperationSucListener)
                                }
                                tv_bottom_center_line.visibility = View.GONE
                                tv_bottom_right.visibility = View.GONE
                            }
                            ll_bottom_btn.visibility = View.VISIBLE
                        }
                        PARTITION_MANAGER -> {//???????????????
                            if (creatorIsOneself) {//??????????????????????????????????????????
                                tv_bottom_left.visibility = View.VISIBLE
                                tv_bottom_left.text = "??????"
                                tv_bottom_left.setOnClickListener {
                                    ToastUtils.showErrorToast(mActivity, "?????????????????????")
                                }
                                tv_bottom_center_line.visibility = View.VISIBLE
                                tv_bottom_right.text = "??????"
                                tv_bottom_right.visibility = View.VISIBLE
                                tv_bottom_right.setOnClickListener {
                                    if (null == workOrderDetail) {
                                        showShortToast(mActivity, "?????????")
                                        return@setOnClickListener
                                    }
                                    if (workOrderDetail.code != 200) {
                                        showShortToast(mActivity, workOrderDetail.message)
                                        return@setOnClickListener
                                    }
                                    toActivity(mActivity, OrderEditActivity.createIntent(mActivity, workOrderDetail))
                                }
                            } else {//??????????????????
                                tv_bottom_left.visibility = View.VISIBLE
                                tv_bottom_left.text = "??????"
                                tv_bottom_left.setOnClickListener {
                                    ToastUtils.showErrorToast(mActivity, "?????????????????????")
                                }
                                tv_bottom_center_line.visibility = View.GONE
                                tv_bottom_right.visibility = View.GONE
                            }

                            ll_bottom_btn.visibility = View.VISIBLE

                        }
                        OTHER_PERSONNEL -> { //????????????
                            tv_bottom_left.visibility = View.GONE
                            tv_bottom_center_line.visibility = View.GONE
                            tv_bottom_right.text = "??????"
                            tv_bottom_right.visibility = View.VISIBLE
                            tv_bottom_right.setOnClickListener {
                                if (null == workOrderDetail) {
                                    showShortToast(mActivity, "?????????")
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
                //?????????(??????)
                intArrayOf(1, 13, 14).indexOf(statusId) != -1 -> {
//                    showShortToast(mActivity, "?????????(??????)")
                    when (userLevel) {
                        CITY_MANAGER -> {//???????????????
                            if (isOverTime) { //???????????????
                                tv_bottom_left.text = "??????"
                                tv_bottom_center_line.visibility = View.VISIBLE
                                tv_bottom_right.text = "??????"
                                tv_bottom_right.visibility = View.VISIBLE
                                tv_bottom_left.setOnClickListener {
                                    ToastUtils.showErrorToast(mActivity, "?????????????????????")
                                }
                                tv_bottom_right.setOnClickListener {
                                    rushOrder(workOrderOperationSucListener)
                                }
                                ll_bottom_btn.visibility = View.VISIBLE
                            } else {
                                tv_bottom_left.text = "??????"
                                tv_bottom_center_line.visibility = View.GONE
                                tv_bottom_right.visibility = View.GONE
                                tv_bottom_left.setOnClickListener {
                                    ToastUtils.showErrorToast(mActivity, "?????????????????????")
                                }
                                ll_bottom_btn.visibility = View.VISIBLE
                            }
                        }
                        PARTITION_MANAGER -> {//???????????????
//                            if (isOverTime) { //?????????????????????
                            tv_bottom_left.text = "??????"
                            tv_bottom_center_line.visibility = View.VISIBLE
                            tv_bottom_right.text = "????????????"
                            tv_bottom_right.visibility = View.VISIBLE
                            tv_bottom_left.setOnClickListener {
                                initAudit(workOrderOperationSucListener)
                            }
                            tv_bottom_right.setOnClickListener {
                                errorReport(workOrderOperationSucListener)
                            }
                            ll_bottom_btn.visibility = View.VISIBLE
//                            } else {
//                                tv_bottom_left.text = "??????"
//                                tv_bottom_center_line.visibility = View.GONE
//                                tv_bottom_right.visibility = View.GONE
//                                tv_bottom_left.setOnClickListener {
//                                    initAudit(workOrderOperationSucListener)
//                                }
//                                ll_bottom_btn.visibility = View.VISIBLE
//                            }
                        }
                        OTHER_PERSONNEL -> { //???????????????????????????
                            ll_bottom_btn.visibility = View.GONE
                        }
                    }
                }
                //?????????
                intArrayOf(2).indexOf(statusId) != -1 -> {
                    //????????????????????????????????????????????????????????????????????????
                    if (creatorIsOneself) {
                        tv_bottom_left.text = "??????"
                        tv_bottom_center_line.visibility = View.GONE
                        tv_bottom_right.visibility = View.GONE
                        tv_bottom_left.setOnClickListener {
                            if (null == workOrderDetail) {
                                showShortToast(mActivity, "?????????")
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
                //?????????
                intArrayOf(3).indexOf(statusId) != -1 -> {
                    //?????????????????????????????????
                    ll_bottom_btn.visibility = View.GONE
                }
                //????????????
                intArrayOf(5).indexOf(statusId) != -1 -> {
                    //?????????????????????????????????

                    if (workOrderDetail.data.orderNew.operator == loginInfo.data.userId) {
                        tv_bottom_left.text = "????????????"
                        tv_bottom_center_line.visibility = View.GONE
                        tv_bottom_right.visibility = View.GONE
                        tv_bottom_left.setOnClickListener {
                            orderOperator()
                        }
                        ll_bottom_btn.visibility = View.VISIBLE
                    }

                }
                //?????????(??????)
                intArrayOf(6).indexOf(statusId) != -1 -> {
                    if (userLevel == PARTITION_MANAGER) {
                        tv_bottom_left.text = "??????"
                        tv_bottom_center_line.visibility = View.GONE
                        tv_bottom_right.visibility = View.GONE
                        tv_bottom_left.setOnClickListener {

                            //???????????????????????????????????????
                            HttpRequest.queryRoleListByPartitionId(workOrderDetail.data.orderNew.partitionId, REQUEST_CODE_QUERY_ROLE_LIST_BY_PARTITION, this)

                        }
                        ll_bottom_btn.visibility = View.VISIBLE
                    } else {
                        ll_bottom_btn.visibility = View.GONE
                    }

                }
                //?????????(??????)
                intArrayOf(10).indexOf(statusId) != -1 -> {

                    if (userLevel == CITY_MANAGER) {
                        tv_bottom_left.text = "??????"
                        tv_bottom_center_line.visibility = View.GONE
                        tv_bottom_right.visibility = View.GONE
                        tv_bottom_left.setOnClickListener {

                            doAuditCity()

                        }
                        ll_bottom_btn.visibility = View.VISIBLE
                    }

                }
                //????????????
                intArrayOf(7).indexOf(statusId) != -1 -> {
                    //??????????????????
                    ll_bottom_btn.visibility = View.GONE
                }
            }
        }

        /**
         * ????????????
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
            val layoutManager = GridLayoutManager(mActivity, 3) //?????????????????????????????????
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
                        //????????????
                        getPicPathFromDialog()
                    }
                    R.id.iv_close -> {
                        AlertDialog(mActivity, "??????", "??????????????????????????????", true, 0, AlertDialog.OnDialogButtonClickListener { requestCode: Int, isPositive: Boolean ->
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
                        ToastUtils.showErrorToast(mActivity, "????????????")
                        return@orderDoOperator
                    }
                    val baseBean = JsonUtils.parseObject(resultJson, BaseBean::class.java)
                    CommonUtils.exitLogin(baseBean.code, mActivity)
                    if (baseBean.code != 200) {
                        ToastUtils.showErrorToast(mActivity, baseBean.message)
                        return@orderDoOperator
                    }
                    showShortToast(mActivity, "??????????????????")
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
         * ??????????????????
         */
        private fun doAudit(roleList: ArrayList<OrderOperatorRoleData.DataDTO>, fistOperatorList: ArrayList<OrderOperatorListData.DataDTO>) {
            mRoleList = roleList//????????????
            mSelectOperatorList = fistOperatorList//???????????????????????????
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
            //????????????????????????????????????
            if (mRoleList.size > 0) {
                tv_role_name.text = mRoleList[0].name
            }
            //????????????????????????????????????
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

            var isTurnDown = 1//????????????
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
                        ToastUtils.showToast(mActivity, "????????????", false)
                        EventBus.getDefault().post(EventEnumBean.EDIT_ORDER_SUCCESS)

                    }
                }

            }
            bottomBTNdialog.addContentView(view, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT))
            bottomBTNdialog.show()


        }

        /**
         * ??????????????????
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
            var isTurnDown = 1//????????????
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
                        ToastUtils.showErrorToast(mActivity, "?????????????????????")
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
                        ToastUtils.showToast(mActivity, "????????????", false)
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
         * ????????????
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
            val layoutManager = GridLayoutManager(mActivity, 3) //?????????????????????????????????
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
                        //????????????
                        getPicPathFromDialog()
                    }
                    R.id.iv_close -> {
                        AlertDialog(mActivity, "??????", "??????????????????????????????", true, 0, AlertDialog.OnDialogButtonClickListener { requestCode: Int, isPositive: Boolean ->
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
                    ToastUtils.showErrorToast(mActivity, "?????????????????????")
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
                        ToastUtils.showErrorToast(mActivity, "????????????")
                        return@orderErrorReport
                    }
                    val baseBean = JsonUtils.parseObject(resultJson, BaseBean::class.java)
                    CommonUtils.exitLogin(baseBean.code, mActivity)
                    if (baseBean.code != 200) {
                        ToastUtils.showErrorToast(mActivity, baseBean.message)
                        return@orderErrorReport
                    }
                    showShortToast(mActivity, "????????????")
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
         * ????????????
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
//                ToastUtils.showToast(mActivity,"??????????????????" + it.hashCode(),false)
                if (isUpLoadImgIng) {
                    showShortToast(mActivity, "???????????????????????????")
                    return@OnSelectImgSucListener
                }
                isUpLoadImgIng = true
                UpLoadUtil.uploadImage(mActivity, it.image.originalPath, object : UpLoadUtil.UpLoadImgListener {
                    override fun upLoadSuccess(imgId: Int) {
                        isUpLoadImgIng = false
                        //??????fileIds?????????
                        var tempFile = OrderErrorReportImgData()
                        tempFile.isVirtual = false
                        tempFile.filename = it.image.originalPath
                        tempFile.id = imgId
                        tempFile.isXC = true
                        fileIds.add(fileIds.size - 1, tempFile)
                        if (fileIds.size > 6) {//??????6???
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
                            showShortToast(mActivity, "????????????????????????")
                        }

                    }
                })


            })


            bottomView.findViewById<View>(R.id.tv_cancel).setOnClickListener { v: View? -> choosePicBottomDialog.dismiss() }
            choosePicBottomDialog.show()
        }


        /**
         * ??????
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
                    ToastUtils.showErrorToast(mActivity, "?????????????????????")
                    return@setOnClickListener
                }
                var handleTime = et_time.text.toString().trim().toInt()
                HttpRequest.orderRushOrder(handleTime, workOrderDetail.data.orderNew.id, 1002)
                { i: Int?, resultJson: String??, exception: Exception? ->
                    if (StringUtil.isEmpty(resultJson)) {
                        ToastUtils.showErrorToast(mActivity, "????????????")
                        return@orderRushOrder
                    }
                    val baseBean = JsonUtils.parseObject(resultJson, BaseBean::class.java)
                    CommonUtils.exitLogin(baseBean.code, mActivity)
                    if (baseBean.code != 200) {
                        ToastUtils.showErrorToast(mActivity, baseBean.message)
                        return@orderRushOrder
                    }
                    ToastUtils.showToast(mActivity, "????????????", false)
                    bottomBTNdialog.dismiss()
                    EventBus.getDefault().post(EventEnumBean.EDIT_ORDER_SUCCESS)

                }
            }
            bottomBTNdialog.addContentView(view, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT))
            bottomBTNdialog.show()

        }

        /**
         * ??????
         */
        private fun initAudit(workOrderOperationSucListener: WorkOrderOperationSucListener) {
            var gradeIds = "0"
            if (intArrayOf(9, 12).indexOf(workOrderDetail.data.orderNew.statusId) != -1) {//????????????---????????????????????????
                gradeIds = "4"
            }
            if (intArrayOf(1, 13, 14).indexOf(workOrderDetail.data.orderNew.statusId) != -1) {//????????????---????????????????????????
                gradeIds = "5,6"
            }
            HttpRequest.orderCityInitAuditGetUserList(gradeIds, workOrderDetail.data.orderNew.partitionId, 1000) { i: Int?, resultJson: String??, exception: Exception? ->
                if (StringUtil.isEmpty(resultJson)) {
                    ToastUtils.showErrorToast(mActivity, "????????????")
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
                        tv_manager_name.text = "??????????????????"
                    }else{
                        tv_manager_name.text = "?????????????????????"
                        tv_manager_str.text = "????????????????????????"
                    }



                    var isTurnDown = 1//????????????
                    rg.setOnCheckedChangeListener { radioGroup: RadioGroup, checkdId: Int ->

                        when (checkdId) {
                            R.id.rb_ok -> {
                                isTurnDown = 1
                                ll_select_manager_root.visibility = View.VISIBLE
                                ll_time.visibility = View.VISIBLE
                                et_time.setText("")
                                et_time.hint = "???????????????"

                                if(userLevel == 1){
                                    tv_manager_name.text = "??????????????????"
                                }else{
                                    tv_manager_name.text = "?????????????????????"
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
                        //???????????????????????????

                        //????????????
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
                            //????????????????????????????????????
                            repeat(nameStrList.size) {
                                if (nameStrList[it] == selectManagerData?.name) {
                                    selectedIndex = it
                                }
                            }
                            var picker = SinglePicker(mActivity, nameStrList)
                            picker.setCanLoop(false) //???????????????
                            picker.setTopBackgroundColor(-0x111112)
                            picker.setTopHeight(50)
                            picker.setGravity(Gravity.BOTTOM)
                            picker.setTitleText("??????????????????")
                            if(userLevel == 1){
                                picker.setTitleText("??????????????????")
                            }else{
                                picker.setTitleText("?????????????????????")
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
//                config.color = Color.BLUE //?????????
//                config.alpha = 120 //????????????
                            config.isVisible = false

//        config.setRatio(1);//?????????
                            //        config.setRatio(1);//?????????
                            picker.setLineConfig(config)
                            picker.setItemWidth(200)
                            picker.setBackgroundColor(-0x1e1e1f)
                            //picker.setSelectedItem(isChinese ? "?????????" : "Virgo");
                            //picker.setSelectedItem(isChinese ? "?????????" : "Virgo");
                            picker.selectedIndex = selectedIndex

                            picker.setOnItemPickListener { index, item ->
//            showShortToast(mActivity, ("index=$index, item=$item"))


                                tv_manager_name.text = item
                                tv_manager_name.setTextColor(Color.parseColor("#000000"))
                                selectManagerData = orderAuditSelectManagerList.data[index]

                            }
                            picker.show()


//                            var attachPopupView = XPopup.Builder(mActivity)
//                                    .hasShadowBg(false) //                            .isDestroyOnDismiss(true) //???????????????????????????????????????????????????
//                                    //                        .isDarkTheme(true)
//                                    //                        .popupAnimation(PopupAnimation.ScaleAlphaFromCenter) //NoAnimation??????????????????
//                                    .isCenterHorizontal(true) //?????????????????????????????????
//                                    //                        .offsetY(-60)
//                                    //                        .offsetX(80)
//                                    .popupPosition(PopupPosition.Bottom) //???????????????????????????
//                                    .popupWidth(ll_select_manager.width)
//                                    .atView(ll_select_manager) // ?????????????????????View???????????????????????????????????????????????????
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
                                    ToastUtils.showErrorToast(mActivity, "??????????????????")
                                }else{
                                    ToastUtils.showErrorToast(mActivity, "?????????????????????")
                                }

                                return@setOnClickListener
                            }
                            if (StringUtil.isEmpty(et_time.text.toString().trim())) {
                                ToastUtils.showErrorToast(mActivity, "?????????????????????")
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
                                ToastUtils.showErrorToast(mActivity, "????????????")
                                return@orderInitAudit
                            }
                            val baseBean = JsonUtils.parseObject(resultJson, BaseBean::class.java)
                            CommonUtils.exitLogin(baseBean.code, mActivity)
                            if (baseBean.code != 200) {
                                ToastUtils.showErrorToast(mActivity, baseBean.message)
                                return@orderInitAudit
                            }
                            ToastUtils.showToast(mActivity, "????????????", false)
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
                ToastUtils.showErrorToast(mActivity, "????????????")
                return
            }
            when (requestCode) {
                REQUEST_CODE_QUERY_ROLE_LIST_BY_PARTITION -> {//??????????????????
                    val orderOperatorRoleData = JsonUtils.parseObject(resultJson, OrderOperatorRoleData::class.java)
                    CommonUtils.exitLogin(orderOperatorRoleData.code, mActivity)
                    if (orderOperatorRoleData.code != 200) {
                        ToastUtils.showErrorToast(mActivity, orderOperatorRoleData.message)
                    } else {
                        //???????????????????????????????????????????????????
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