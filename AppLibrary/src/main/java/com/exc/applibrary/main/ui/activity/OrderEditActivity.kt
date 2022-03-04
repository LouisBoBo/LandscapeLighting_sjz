package com.exc.applibrary.main.ui.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
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
import com.exc.applibrary.main.customview.CustomDialog
import com.exc.applibrary.main.model.*
import com.exc.applibrary.main.utils.UpLoadUtil
import com.exc.applibrary.main.utils.UpLoadUtil.UpLoadImgListener
import com.jph.takephoto.app.TakePhotoActivity
import com.jph.takephoto.model.TResult
import com.jph.takephoto.model.TakePhotoOptions
import kotlinx.android.synthetic.main.acitvity_add_edit_order.*
import kotlinx.android.synthetic.main.activity_haaderview.*
import org.greenrobot.eventbus.EventBus
import zuo.biao.library.base.BaseActivity
import zuo.biao.library.interfaces.OnHttpResponseListener
import zuo.biao.library.ui.AlertDialog
import zuo.biao.library.util.CommonUtil.showShortToast
import zuo.biao.library.util.JsonUtils
import zuo.biao.library.util.Log
import zuo.biao.library.util.StringUtil
import java.io.File


open class OrderEditActivity : TakePhotoActivity(), View.OnClickListener, OnHttpResponseListener {
    private lateinit var choosePicBottomDialog: BottomDialog
    private lateinit var customDialog: CustomDialog
    private val QUERY_PARTITION_REQUEST_CODE = 1
    private val QUERY_ERROR_TYPE_REQUEST_CODE = 2
    private val SUB_UPDATE_CODE = 3
    private lateinit var partitionModel: PartitionModel
    private lateinit var orderAllErrorType: OrderAllErrorType
    private lateinit var workOrderDetail: WorkOrderDetail
    private lateinit var imgAdapter: ImgAdapter
    private lateinit var mActivity: OrderEditActivity
    private val selectType_camare: Int = 0
    private val selectType_gallery: Int = 1
    private val selectType_file: Int = 2
    private var isUpLoadImgIng: Boolean = false
    private lateinit var tv_order_name: EditText

    private val showPicList: ArrayList<WorkOrderDetail.Data.OrderNew.OrderPics> = arrayListOf()


    companion object {
        const val WORKORDERDETAIL: String = "WorkOrderDetail"

        @JvmStatic
        fun createIntent(mActivity: Activity, workOrderDetail: WorkOrderDetail): Intent {
            var intent = Intent(mActivity, OrderEditActivity::class.java)
            intent.putExtra(WORKORDERDETAIL, workOrderDetail)
            return intent
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitvity_add_edit_order)
        mActivity = this
        customDialog = CustomDialog(this)
        workOrderDetail = intent.getSerializableExtra(WORKORDERDETAIL) as WorkOrderDetail
        initView()
        initData()
        initEvent()
    }

    fun initView() {
        header_left_img.setOnClickListener(this)
        tv_cancel.setOnClickListener(this)
        tv_order_type.setOnClickListener(this)
        tv_ok.setOnClickListener(this)
        tv_part.setOnClickListener(this)
        header_center_text.text = "编辑工单"
    }

    fun initData() {
        //查询分区信息
        customDialog.show()
        HttpRequest.getPartitionQuery("", "", QUERY_PARTITION_REQUEST_CODE, this)
    }

    fun initEvent() {
    }

    private fun initViewData() {

        tv_order_name  = findViewById(R.id.tv_order_name)

        tv_order_name.setText(workOrderDetail.data.orderNew.name)
        tv_order_name.isFocusable = false

        tv_order_type.text = workOrderDetail.data.orderNew.faultTypeName
        tv_part.text = workOrderDetail.data.orderNew.partitionName
        tv_address.setText(workOrderDetail.data.orderNew.addr)
        tv_describe.setText(workOrderDetail.data.orderNew.description)

        //拆分OrderPics，找到文件和图片的集合
        for (orderPics in workOrderDetail.data.orderNew.orderPics) {
            if (orderPics.fileType == 1) {
                showPicList.add(orderPics)
            }
        }

        if (showPicList.size < 6) { //最多有6张，不足6张时最后1张部位成添加按钮
            var tempOrderPic: WorkOrderDetail.Data.OrderNew.OrderPics = WorkOrderDetail.Data.OrderNew.OrderPics()
            tempOrderPic.isVirtual = true
            showPicList.add(tempOrderPic)
        }

        //处理图片
        if (showPicList.size != 0) {
            val layoutManager = GridLayoutManager(this, 3) //第二个参数为网格的列数
            img_recyclerview.layoutManager = layoutManager
            img_recyclerview.isNestedScrollingEnabled = false
            imgAdapter = ImgAdapter(img_recyclerview)
            img_recyclerview.adapter = imgAdapter
            imgAdapter.data = showPicList
            imgAdapter.setOnItemChildClickListener { _: ViewGroup, childView: View, position: Int ->
                when (childView.id) {
                    R.id.iv -> {
                        var browsPicIntent = Intent(mActivity, BrowsePicActivity::class.java)
                                .putExtra("picBen", showPicList[position])
                                .putExtra("isEdit", true)
                        startActivity(browsPicIntent)
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
                            showPicList.removeAt(position)
                            imgAdapter.data = showPicList
                        }).show()
                    }
                }
            }
            img_recyclerview.visibility = View.VISIBLE
        } else {
            img_recyclerview.visibility = View.GONE
        }

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.header_left_img -> {
                finish()
            }
            R.id.tv_cancel -> {
                finish()
            }
            //重选类型
            R.id.tv_order_type -> {
                chooseErrorType()
            }
            //重选分区
            R.id.tv_part -> {
                choosePart()
            }

            R.id.tv_ok -> {
                submit()
            }


        }
    }


    private fun choosePart() {

        val pickerItemStr = arrayListOf<String>()
        var selectedIndex = 0
        repeat(partitionModel.data.size) {
            if (workOrderDetail.data.orderNew.partitionId == partitionModel.data[it].id) {
                selectedIndex = it
            }
            pickerItemStr.add(partitionModel.data[it].name)
        }
        var picker = SinglePicker(this, pickerItemStr)
        picker.setCanLoop(false) //不禁用循环
        picker.setTopBackgroundColor(-0x111112)
        picker.setTopHeight(50)
        picker.setGravity(Gravity.BOTTOM)
        picker.setTitleText("请选择")
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
            workOrderDetail.data.orderNew.partitionId = partitionModel.data[index].id
            tv_part.text = item

        }
        picker.show()

    }


    private fun chooseErrorType() {

        val pickerItemStr = arrayListOf<String>()
        var selectedIndex = 0
        repeat(orderAllErrorType.data.list.size) {
            if (workOrderDetail.data.orderNew.faultTypeId == orderAllErrorType.data.list[it].faultTypeId) {
                selectedIndex = it
            }
            pickerItemStr.add(orderAllErrorType.data.list[it].faultTypeName)
        }
        var picker = SinglePicker(this, pickerItemStr)
        picker.setCanLoop(false) //不禁用循环
        picker.setTopBackgroundColor(-0x111112)
        picker.setTopHeight(50)
        picker.setGravity(Gravity.BOTTOM)
        picker.setTitleText("请选择")
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
            workOrderDetail.data.orderNew.faultTypeId = orderAllErrorType.data.list[index].faultTypeId
            tv_order_type.text = item

        }
        picker.show()

    }

    override fun onHttpResponse(requestCode: Int, resultJson: String?, e: Exception?) {
        customDialog.dismiss()
        when (requestCode) {
            QUERY_PARTITION_REQUEST_CODE -> {

                if (null == resultJson) {
                    showShortToast(mActivity, "请求异常，请检查网络")
                    return
                }
                partitionModel = JsonUtils.parseObject(resultJson, PartitionModel::class.java)
                if (partitionModel.code == 200) {
                    HttpRequest.getAllErrorType(QUERY_ERROR_TYPE_REQUEST_CODE, this)
                } else {
                    showShortToast(mActivity, partitionModel.message)
                }
            }
            QUERY_ERROR_TYPE_REQUEST_CODE -> {
                if (null == resultJson) {
                    showShortToast(mActivity, "请求异常，请检查网络")
                    return
                }
                orderAllErrorType = JsonUtils.parseObject(resultJson, OrderAllErrorType::class.java)
                if (orderAllErrorType.code == 200) {
                    initViewData()

                } else {
                    showShortToast(mActivity, partitionModel.message)
                }
            }
            SUB_UPDATE_CODE -> {
                if (null == resultJson) {
                    showShortToast(mActivity, "请求异常，请检查网络")
                    return
                }
                var result = JsonUtils.parseObject(resultJson, BaseBean::class.java)
                if (result.code == 200) {
                    showShortToast(mActivity, "工单修改成功")
                    EventBus.getDefault().post(EventEnumBean.EDIT_ORDER_SUCCESS)
                    finish()
                } else {
                    showShortToast(mActivity, partitionModel.message)
                }
            }
        }
    }


    class ImgAdapter(recyclerView: RecyclerView?) :
            BGARecyclerViewAdapter<WorkOrderDetail.Data.OrderNew.OrderPics>(recyclerView, R.layout.item_order_edit_img) {
        override fun fillData(helper: BGAViewHolderHelper?, position: Int, model: WorkOrderDetail.Data.OrderNew.OrderPics?) {
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

            imageView?.let {
                Glide.with(mContext)
                        .load(HttpRequest.SERVICES_FILE_PATH + model?.filename)
                        .into(it)
            }
            ll_add?.visibility = View.GONE
            imageView?.visibility = View.VISIBLE
            iv_close?.visibility = View.VISIBLE


        }

        override fun setItemChildListener(helper: BGAViewHolderHelper, viewType: Int) {
            helper.setItemChildClickListener(R.id.iv)
            helper.setItemChildClickListener(R.id.iv_close)
            helper.setItemChildClickListener(R.id.ll_add)
        }
    }

    fun getPicPathFromDialog() {
        val builder = BottomDialog.Builder(mActivity)
        builder.setContentView(R.layout.dialog_choose_phone_pic)
        choosePicBottomDialog = builder.create()
        choosePicBottomDialog.setCanceledOnTouchOutside(false)
        val bottomView = choosePicBottomDialog.contentView
        val iv_camera = bottomView.findViewById<ImageView>(R.id.iv_camera)
        val iv_gallery = bottomView.findViewById<ImageView>(R.id.iv_gallery)
        val iv_file = bottomView.findViewById<ImageView>(R.id.iv_file)
        val tv_camera = bottomView.findViewById<TextView>(R.id.tv_camera)
        val tv_gallery = bottomView.findViewById<TextView>(R.id.tv_gallery)
        val tv_file = bottomView.findViewById<TextView>(R.id.tv_file)


        iv_camera.setOnClickListener {
            choosePicOnClick(selectType_camare)
        }
        iv_gallery.setOnClickListener {
            choosePicOnClick(selectType_gallery)
        }
        iv_file.setOnClickListener {
            choosePicOnClick(selectType_file)
        }
        bottomView.findViewById<View>(R.id.tv_cancel).setOnClickListener { v: View? -> choosePicBottomDialog.dismiss() }
        choosePicBottomDialog.show()
    }

    private fun choosePicOnClick(selectType: Int) {

        choosePicBottomDialog.dismiss()


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

        when (selectType) {
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

    //选择图片的的回调
    override fun takeSuccess(result: TResult) {
        super.takeSuccess(result)

        if (isUpLoadImgIng) {
            showShortToast(mActivity, "图片上传中，请稍后")
            return
        }
        isUpLoadImgIng = true

        UpLoadUtil.uploadImage(mActivity, result.image.originalPath, object : UpLoadImgListener {
            override fun upLoadSuccess(imgId: Int) {
                isUpLoadImgIng = false
                //修改showPicList并刷新
                var picBen = WorkOrderDetail.Data.OrderNew.OrderPics()
                picBen.filename = result.image.originalPath
                picBen.id = imgId
                picBen.fileType = 1
                picBen.isXC = true
                showPicList.add(showPicList.size - 1, picBen)
                if (showPicList.size > 6) {//最多6张
                    showPicList.removeAt(showPicList.size - 1)
                }
                runOnUiThread {
                    imgAdapter.data = showPicList
                }
                choosePicBottomDialog.dismiss()
            }

            override fun upLoadFail() {
                isUpLoadImgIng = false
                runOnUiThread {
                    showShortToast(mActivity, "上传失败，请重试")
                }
            }
        })

    }

    private fun submit() {
        var nameStr = tv_order_name.text.toString()
        if (StringUtil.isEmpty(nameStr)) {
            showShortToast(mActivity, "请输入工单名称")
            return
        }
        workOrderDetail.data.orderNew.name = nameStr
        var addressStr = tv_address.text.toString()
        if (StringUtil.isEmpty(addressStr)) {
            showShortToast(mActivity, "请输入发生地址")
            return
        }
        workOrderDetail.data.orderNew.addr = addressStr

        var descStr = tv_describe.text.toString()
        if (StringUtil.isEmpty(descStr)) {
            showShortToast(mActivity, "请输入描述内容")
            return
        }
        workOrderDetail.data.orderNew.description = descStr

        customDialog.show()
        HttpRequest.upDateWorkOrder(workOrderDetail.data.orderNew, showPicList, SUB_UPDATE_CODE, this)


    }

    protected var exitAnim = zuo.biao.library.R.anim.right_push_out
    protected var enterAnim = zuo.biao.library.R.anim.fade


    override fun finish() {
        super.finish()
        runOnUiThread {
            if (enterAnim > 0 && exitAnim > 0) {
                try {
                    overridePendingTransition(enterAnim, exitAnim)
                } catch (e: java.lang.Exception) {
                }
            }
        }
    }
}