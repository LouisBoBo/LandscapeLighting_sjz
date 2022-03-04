package com.exc.applibrary.main.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.bingoogolapple.baseadapter.BGARecyclerViewAdapter
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper
import com.bumptech.glide.Glide
import com.exc.applibrary.R
import com.exc.applibrary.main.BaseTakePhotoActivity
import com.exc.applibrary.main.HttpListener
import com.exc.applibrary.main.HttpRequest
import com.exc.applibrary.main.customview.CustomDialog
import com.exc.applibrary.main.model.*
import com.exc.applibrary.main.utils.*
import com.jph.takephoto.model.TResult
import com.jph.takephoto.model.TakePhotoOptions
import kotlinx.android.synthetic.main.acitvity_work_order_detail.*
import kotlinx.android.synthetic.main.activity_haaderview.*
import kotlinx.android.synthetic.main.activity_myinfo.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import zuo.biao.library.interfaces.OnHttpResponseListener
import zuo.biao.library.util.JsonUtils
import zuo.biao.library.util.PreferencesUtil
import zuo.biao.library.util.StringUtil
import java.io.File

@RequiresApi(Build.VERSION_CODES.N)
class WorkOrderDetailActivity : BaseTakePhotoActivity(), OnHttpResponseListener {
    private lateinit var workOrderDetail: WorkOrderDetail
    private var orderId: Int = 0
    private var statusId: Int = 0
    private val GET_ORDER_DETAIL: Int = 1
    private lateinit var imgAdapter: ImgAdapter
    private lateinit var mActivity: WorkOrderDetailActivity
    private var isOverTime: Boolean = false
    private lateinit var loadingDialog: CustomDialog


    companion object {
        lateinit var mUserData: UserData
        private const val orderIdKey: String = "orderId"
        private const val statusIdKey: String = "statusId"
        private lateinit var mOnSelectImgSucListener: OnSelectImgSucListener
        fun setOnSelectImgSucListener(onSelectImgSucListener: OnSelectImgSucListener) {
            this.mOnSelectImgSucListener = onSelectImgSucListener
        }

        @JvmStatic
        fun createIntent(mActivity: Activity, orderId: Int, statusId: Int): Intent {
            val intent = Intent(mActivity, WorkOrderDetailActivity::class.java)
            intent.putExtra(orderIdKey, orderId)
            intent.putExtra(statusIdKey, statusId)
            return intent
        }


    }

    override fun takeSuccess(result: TResult?) {
        super.takeSuccess(result)
        mOnSelectImgSucListener.success(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mActivity = this
        loadingDialog = CustomDialog(this)
        setContentView(R.layout.acitvity_work_order_detail)
        initEventBus(true)

        orderId = getIntent().getIntExtra(orderIdKey, 0)
        statusId = getIntent().getIntExtra(statusIdKey, 0)
        initView()
        initData()

    }

    override fun initView() {
        header_center_text.text = "工单详情"
        header_left_img.setOnClickListener {
            finish()
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEven(evenEnum: EventEnumBean) {
        if (evenEnum == EventEnumBean.EDIT_ORDER_SUCCESS) {
            finish()
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvenUpdate(upLoadEventBean: UpLoadEventBean) {
        if (upLoadEventBean.className == WorkOrderUtil.CLASS_MANE) {
            var takePhoto = takePhoto
            val file = File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg")
            if (!file.parentFile.exists()) {
                file.parentFile.mkdirs()
            }
            val imageUri = Uri.fromFile(file)
            takePhoto.onEnableCompress(null, false)
            val builder = TakePhotoOptions.Builder()
            builder.setWithOwnGallery(true)
            takePhoto.setTakePhotoOptions(builder.create())
            when (upLoadEventBean.type) {
                0 -> {//拍照
                    takePhoto.onPickFromCapture(imageUri)
                }
                1 -> {//相册
                    takePhoto.onPickFromGallery()
                }

                2 -> {//文件
                    takePhoto.onPickFromDocuments()
                }
            }

        }
    }


    override fun initData() {
        loadingDialog.show()
        HttpRequest.getWorkOrderDetail(orderId, GET_ORDER_DETAIL, this)
    }

    override fun initEvent() {
    }


    @SuppressLint("SetTextI18n")
    private fun setViewData(workOrderDetail: WorkOrderDetail) {
        tv_order_name.text = workOrderDetail.data.orderNew.name
        tv_order_type.text = workOrderDetail.data.orderNew.faultTypeName
        tv_fenqu_name.text = workOrderDetail.data.orderNew.partitionName
        tv_address.text = workOrderDetail.data.orderNew.addr
        tv_describe.text = workOrderDetail.data.orderNew.description
        tv_create_person.text = workOrderDetail.data.orderNew.creatorName
        tv_order_time.text = workOrderDetail.data.orderNew.createTime

        if (StringUtil.isEmpty(workOrderDetail.data.orderNew.handleTime)) {
            ll_operator_person.visibility = View.GONE
            ll_operator_time.visibility = View.GONE
        } else {
            tv_operator_person.text = workOrderDetail.data.orderNew.operatorName + ""
            tv_operator_time.text = workOrderDetail.data.orderNew.handleTime + "小时"
        }

        //待处理变成正在处理的状态
        if (statusId == 3 && workOrderDetail.data.orderNew.statusId == 5) {
            EventBus.getDefault().post(EventEnumBean.ORDER_STATUS_ID_3_TO5)
        }



        if (workOrderDetail.data.orderNew.overtime == 1 && workOrderDetail.data.orderNew.statusId != 7) {
            var overTime = DateUtil.dateToStamp(workOrderDetail.data.currentTime) - DateUtil.dateToStamp(workOrderDetail.data.orderNew.expireTime)
            tv_top_overtime.text = "处理时间已超时" + DateUtil.FormatMilliseondToEndTimeNoMiao(overTime)
            tv_top_overtime.visibility = View.VISIBLE
            isOverTime = true
        }
        //拆分OrderPics，找到文件和图片的集合
        val picList: ArrayList<WorkOrderDetail.Data.OrderNew.OrderPics> = arrayListOf()
        val fileList: ArrayList<WorkOrderDetail.Data.OrderNew.OrderPics> = arrayListOf()
        for (orderPics in workOrderDetail.data.orderNew.orderPics) {
            if (orderPics.fileType == 1) {
                picList.add(orderPics)
            }
            if (orderPics.fileType == 2) {
                fileList.add(orderPics)
            }
        }
        //处理图片
        if (picList.size != 0) {
            val layoutManager = GridLayoutManager(this, 2) //第二个参数为网格的列数
            img_recyclerview.layoutManager = layoutManager
//            img_recyclerview.addItemDecoration( SpaceItemDecoration(50));
            img_recyclerview.isNestedScrollingEnabled = false
            imgAdapter = ImgAdapter(img_recyclerview)
            img_recyclerview.adapter = imgAdapter
            imgAdapter.data = picList

            imgAdapter.setOnItemChildClickListener { _: ViewGroup, childView: View, position: Int ->
                if (childView.id == R.id.iv) {
                    startActivity(Intent(mActivity, BrowsePicActivity::class.java)
                            .putExtra("pic_url", picList[position].filename)
                    )
                }
            }
            tv_no_pic.visibility = View.GONE
            img_recyclerview.visibility = View.VISIBLE
        } else {
            tv_no_pic.visibility = View.VISIBLE
            img_recyclerview.visibility = View.GONE
        }
        //处理文件
        if (fileList.size != 0) {
            ll_file_link.removeAllViews()
            for (mFileList in fileList) {
                val view: View = inflater.inflate(R.layout.order_detial_file_text, null)
                var link = mFileList.filename
                link = "http://192.168.112.78:9701/$link"
                var name = mFileList.realname
                val html = "<a href='" +
                        link +
                        "" +
                        "'>" +
                        name +
                        "</a>"
                CommonUtils.textLinkClick(context, html, view.findViewById(R.id.tv_link))
                ll_file_link.addView(view)
            }
            ll_timeline.removeAllViews()
            tv_no_file.visibility = View.GONE
            ll_file_link.visibility = View.VISIBLE
        } else {
            tv_no_file.visibility = View.VISIBLE
            ll_file_link.visibility = View.GONE
        }
        WorkOrderUtil.initTimeLine(inflater, workOrderDetail, ll_timeline)
    }

    class ImgAdapter(recyclerView: RecyclerView?) :
            BGARecyclerViewAdapter<WorkOrderDetail.Data.OrderNew.OrderPics>(recyclerView, R.layout.item_order_detail_img) {
        override fun fillData(helper: BGAViewHolderHelper?, position: Int, model: WorkOrderDetail.Data.OrderNew.OrderPics?) {
            val imageView = helper?.getImageView(R.id.iv)
            imageView?.let {
                Glide.with(mContext)
                        .load(HttpRequest.SERVICES_FILE_PATH + model?.filename)
                        .into(it)
            }
        }

        override fun setItemChildListener(helper: BGAViewHolderHelper, viewType: Int) {
            helper.setItemChildClickListener(R.id.iv)

        }
    }

    override fun onHttpResponse(requestCode: Int, resultJson: String?, e: Exception?) {
        when (requestCode) {
            GET_ORDER_DETAIL -> {
                var loginInfo = JsonUtils.parseObject(PreferencesUtil.getString(mActivity, Constant.LOGIN_INFO_JSON), LoginInfo::class.java)
                HttpRequest.getUserBackModule(mActivity, loginInfo.data.userId, object : HttpListener<UserData>() {
                    override fun onSuccess(userData: UserData) {
                        loadingDialog.dismiss()

                        mUserData = userData;


                        if (null == resultJson) {
                            showShortToast("数据异常")
                            return
                        }


                        workOrderDetail = JsonUtils.parseObject(resultJson, WorkOrderDetail::class.java)

                        CommonUtils.exitLogin(workOrderDetail.code, activity)
                        if (workOrderDetail.code != 200) {
                            showShortToast(workOrderDetail.message + "")
                            return
                        }
                        setViewData(workOrderDetail)

                        //底部按钮处理
                        WorkOrderUtil.workOrderDetail = workOrderDetail
                        WorkOrderUtil.mActivity = mActivity
                        WorkOrderUtil.setBottomBtn(ll_bottom_btn, tv_bottom_left, tv_bottom_center_line, tv_bottom_right, isOverTime, WorkOrderOperationSucListener {
                            showShortToast("操作成功了")
                        })

                    }

                    override fun onError() {
                        loadingDialog.dismiss()
                    }

                })


            }
        }
    }
}