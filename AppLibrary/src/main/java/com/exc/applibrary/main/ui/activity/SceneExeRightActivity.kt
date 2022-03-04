package com.exc.applibrary.main.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import cn.addapp.pickers.common.LineConfig
import cn.addapp.pickers.picker.SinglePicker
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.exc.applibrary.R
import com.exc.applibrary.main.HttpListener
import com.exc.applibrary.main.HttpRequest
import com.exc.applibrary.main.customview.CustomDialog
import com.exc.applibrary.main.customview.ImaginaryLineView
import com.exc.applibrary.main.customview.SpacesItemDecoration
import com.exc.applibrary.main.model.*
import com.exc.applibrary.main.ui.dialog.SceneBatchXIAFATimingDialog
import com.exc.applibrary.main.utils.CommonUtils
import com.exc.applibrary.main.utils.Constant
import com.exc.applibrary.main.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_scene_list.*
import kotlinx.android.synthetic.main.activity_scene_right_exe.*
import kotlinx.android.synthetic.main.activity_scene_right_exe.header_center_text
import kotlinx.android.synthetic.main.activity_scene_right_exe.header_left_img
import kotlinx.android.synthetic.main.activity_scene_right_exe.header_right_img
import kotlinx.android.synthetic.main.activity_scene_right_exe.mRecyclerView
import kotlinx.android.synthetic.main.activity_scene_right_exe.refreshLayout
import kotlinx.android.synthetic.main.activity_scene_right_exe.tv_no_data
import kotlinx.android.synthetic.main.fragment_scene.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import zuo.biao.library.base.BaseActivity
import zuo.biao.library.interfaces.OnHttpResponseListener
import zuo.biao.library.util.JsonUtils
import zuo.biao.library.util.PreferencesUtil
import zuo.biao.library.util.StringUtil

@RequiresApi(Build.VERSION_CODES.N)
class SceneExeRightActivity : BaseActivity(), View.OnClickListener, OnHttpResponseListener {
    private lateinit var sceneDetail: SceneChooseNodeListData.Data.list
    val REQUEST_LIST_CODE = 1
    val REQUEST_LIJIXIAFA_CODE = 2
    private lateinit var listAdapter: SceneQuickAdapter

    //    private var checkItemData: SceneDataListById.Data? = null
    private lateinit var loadingDialog: CustomDialog
    private var pageNum = 1
    private var listData = arrayListOf<ControllerByIdSceneNameBean.DataBean>()
    private var selectData = arrayListOf<ControllerByIdSceneNameBean.DataBean>()
    private var partitionId = -1
    private var siteId = -1
    private var buildingTypeSn = -1


    private lateinit var mActivity: Activity

    companion object {
        private const val sceneDetailKey: String = "sceneDetail"

        @JvmStatic
        fun createIntent(mActivity: Activity, sceneDetail: SceneChooseNodeListData.Data.list): Intent {
            val intent = Intent(mActivity, SceneExeRightActivity::class.java)
            intent.putExtra(sceneDetailKey, sceneDetail)
            return intent
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEven(result: String) {
        if (result == SceneBatchXIAFATimingDialog.XIAFA_SUCCESS) {
            repeat(listData.size) {
                listData[it].value = 0
            }
            selectData.clear()
            listAdapter.setList(listData)


        }
    }

    /**
     * 选择建筑用的，目前已经不存在了
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEven(selectBuildModelSceneRightDetail: SelectBuildModelSceneRightDetail) {
        if (selectBuildModelSceneRightDetail.site_id > 0) {
            partitionId = selectBuildModelSceneRightDetail.partition_id
            siteId = selectBuildModelSceneRightDetail.site_id
            pageNum = 1
            initListData()
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scene_right_exe)
        mActivity = this
        initEventBus(true)
        sceneDetail = getIntent().getSerializableExtra(sceneDetailKey) as SceneChooseNodeListData.Data.list
        loadingDialog = CustomDialog(this)
        initView()
        initData()
        initEvent()
    }

    override fun initData() {
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.addItemDecoration(SpacesItemDecoration(16))
        listAdapter = SceneQuickAdapter()
        mRecyclerView.adapter = listAdapter
        refreshLayout.isEnableRefresh = false
//        if (id < 0) {
//            showShortToast("数据有误")
//            return
//        }
        listAdapter.setOnItemClickListener { _, _, position ->
            repeat(listData.size) {
                if (position == it) {//多选
                    if (listData[it].value == 1) {
                        listData[it].value = 0
                    } else {
                        listData[it].value = 1
                    }
                }
                listAdapter.setList(listData)
            }
//            checkItemData = sceneListData[position]
//            SceneNodeDetailByIdDialog(context, checkItemData).show()

        }
//        refreshLayout.setOnRefreshListener {
//            pageNum = 1
//            initListData()
//        }
//        refreshLayout.setOnLoadmoreListener {
//            pageNum++
//            initListData()
//        }
        loadingDialog.show()
        initListData()
        cb_all.setOnCheckedChangeListener { _, isChecked ->
            repeat(listData.size) {
                if (isChecked) {
                    listData[it].value = 1
                } else {
                    listData[it].value = 0
                }
            }
            if (listData.size > 0) {
                listAdapter.setList(listData)
            }
        }


        setOnClickListener()

    }

    fun setOnClickListener() {
        tv_select_build_type.setOnClickListener {

            val pickerItemStr = arrayListOf<String>()
            var selectedIndex = 0
            pickerItemStr.add("全部建筑")
            pickerItemStr.add("联机建筑")
            pickerItemStr.add("动态建筑")
            pickerItemStr.add("开关建筑")
            var picker = SinglePicker(mActivity, pickerItemStr)
            picker.setCanLoop(false) //不禁用循环
            picker.setTopBackgroundColor(-0x111112)
            picker.setTopHeight(50)
            picker.setGravity(Gravity.CENTER)
            picker.setTitleText("请选择建筑类型")
            picker.setTitleTextColor(Color.parseColor("#000000"))
            picker.setTitleTextSize(20)
            picker.setCancelTextColor(Color.parseColor("#202F4A"))
            picker.setCancelTextSize(20)
            picker.setSubmitTextColor(Color.parseColor("#202F4A"))
            picker.setSubmitTextSize(20)
            picker.setHeight(1000)
            picker.setTextSize(20)
            picker.setSelectedTextColor(-0x120000)
            picker.setUnSelectedTextColor(-0x666667)
            val config = LineConfig()
//                config.color = Color.BLUE //线颜色
//                config.alpha = 120 //线透明度
            config.isVisible = false

//        config.setRatio(1);//线比率
            //        config.setRatio(1);//线比率
            picker.setLineConfig(config)
            picker.setItemWidth(300)
            picker.setBackgroundColor(-0x1e1e1f)
            //picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
            //picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
            picker.selectedIndex = selectedIndex
            picker.setOnItemPickListener { index, item ->
//            showShortToast(mActivity, ("index=$index, item=$item"))
//                createOrderBen.faultTypeId = orderAllErrorType.data.list[index].faultTypeId
                tv_select_build_type.text = item


                when (item) {
                    "全部建筑" -> {
                        buildingTypeSn = -1
                    }
                    "联机建筑" -> {
                        buildingTypeSn = 3
                    }
                    "动态建筑" -> {
                        buildingTypeSn = 4
                    }
                    "开关建筑" -> {
                        buildingTypeSn = 5
                    }

                }

            }
            picker.show()


        }

        iv_select_build_type.setOnClickListener {
            tv_select_build_type.performClick()

        }

        tv_select_site_2.setOnClickListener {

            HttpRequest.getSiteList(mActivity, object : HttpListener<SceneSiteList>() {
                override fun onSuccess(siteList: SceneSiteList) {
                    if (siteList.data == null) {
                        ToastUtils.showErrorToast(mActivity, "暂无数据")
                        return
                    }
                    val pickerItemStr = arrayListOf<String>()
                    var selectedIndex = 0

                    var allSiteDataBean = SceneSiteList.DataBean()
                    allSiteDataBean.name = "全部站点"
                    allSiteDataBean.id = -1
                    siteList.data.add(0, allSiteDataBean)
                    repeat(siteList.data.size) {
                        pickerItemStr.add(siteList.data[it].name)
                    }
                    if (pickerItemStr.size == 0) {
                        ToastUtils.showErrorToast(mActivity, "暂无数据")
                        return
                    }
                    var picker = SinglePicker(mActivity, pickerItemStr)
                    picker.setCanLoop(false) //不禁用循环
                    picker.setTopBackgroundColor(-0x111112)
                    picker.setTopHeight(50)
                    picker.setGravity(Gravity.CENTER)
                    picker.setTitleText("请选择站点")
                    picker.setTitleTextColor(Color.parseColor("#000000"))
                    picker.setTitleTextSize(20)
                    picker.setCancelTextColor(Color.parseColor("#202F4A"))
                    picker.setCancelTextSize(20)
                    picker.setSubmitTextColor(Color.parseColor("#202F4A"))
                    picker.setSubmitTextSize(20)
                    picker.setHeight(1000)
                    picker.setTextSize(20)
                    picker.setSelectedTextColor(-0x120000)
                    picker.setUnSelectedTextColor(-0x666667)
                    val config = LineConfig()
//                config.color = Color.BLUE //线颜色
//                config.alpha = 120 //线透明度
                    config.isVisible = false

//        config.setRatio(1);//线比率
                    //        config.setRatio(1);//线比率
                    picker.setLineConfig(config)
                    picker.setItemWidth(300)
                    picker.setBackgroundColor(-0x1e1e1f)
                    //picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
                    //picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
                    picker.selectedIndex = selectedIndex
                    picker.setOnItemPickListener { index, item ->
//            showShortToast(mActivity, ("index=$index, item=$item"))
//                createOrderBen.faultTypeId = orderAllErrorType.data.list[index].faultTypeId
                        tv_select_site_2.text = item
                        siteId = siteList.data[index].id
                    }
                    picker.show()
                }

                override fun onError() {
                }

            })


        }

        iv_sanjiao_2.setOnClickListener {
            tv_select_site_2.performClick()

        }

        btn_condition_ok_2.setOnClickListener {

            pageNum =1
            initListData()

        }
    }
    private fun initListData() {
        HttpRequest.getPartitionList(this, object : HttpListener<PartitionList>() {
            override fun onSuccess(partitionList: PartitionList) {
                if (partitionList.data.size > 0) {
                    HttpRequest.getControllerByIdSceneName(buildingTypeSn,partitionList.data[0].id, siteId, sceneDetail, REQUEST_LIST_CODE) { requestCode, resultJson, e ->
                        loadingDialog.dismiss()
                        if (StringUtil.isEmpty(resultJson)) {
                            ToastUtils.showErrorToast(mActivity, "数据异常")
                            return@getControllerByIdSceneName
                        }
                        val controllerByIdSceneNameBean = JsonUtils.parseObject(resultJson, ControllerByIdSceneNameBean::class.java)
                        CommonUtils.exitLogin(controllerByIdSceneNameBean.code, activity)
                        if (null != controllerByIdSceneNameBean.data && controllerByIdSceneNameBean.data.size > 0) {
                            if (pageNum == 1) {
                                listData.clear()
                                listData.addAll(controllerByIdSceneNameBean.data)
                                listAdapter.setList(listData)
                            } else {
                                listData.addAll(controllerByIdSceneNameBean.data)
                                listAdapter.addData(controllerByIdSceneNameBean.data)
                            }
                            tv_no_data.visibility = View.GONE

                        } else {
                            if (pageNum == 1) {
                                tv_no_data.visibility = View.VISIBLE
                            }
                        }



                        if (pageNum == 1) {
                            refreshLayout.finishRefresh()
                        } else {
                            refreshLayout.finishLoadmore()
                        }


                    }
                }

            }

            override fun onError() {
            }
        })


    }

    override fun initEvent() {
        ll_lijixiafa.setOnClickListener(this)
        ll_dingshixiafa.setOnClickListener(this)
        header_left_img.setOnClickListener(this)
        header_right_img.setOnClickListener(this)
    }

    override fun initView() {
        header_center_text.text = "场景执行"
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.header_left_img -> {
                finish()
            }
            R.id.header_right_img -> {
                toActivity(Intent(this, SearchBuildingActivity::class.java)
                        .putExtra("fromSceneRightDetail", true
                        )
                )
            }
            R.id.ll_lijixiafa -> {
                //找出已经勾选的item
                selectData.clear()
                repeat(listData.size) {
                    if (listData[it].value == 1) {
                        selectData.add(listData[it])
                    }
                }
                if (selectData.size == 0) {
                    showShortToast("请先选择场景")
                    return
                }

                loadingDialog.show()
                HttpRequest.scenePIliangXiafa(selectData, REQUEST_LIJIXIAFA_CODE, this)
            }
            R.id.ll_dingshixiafa -> {//定时下发
                //找出已经勾选的item
                selectData.clear()
                repeat(listData.size) {
                    if (listData[it].value == 1) {
                        selectData.add(listData[it])
                    }
                }
                if (selectData.size == 0) {
                    showShortToast("请先选择场景")
                    return
                }
                SceneBatchXIAFATimingDialog.showDialog(selectData, fragmentManager)

            }
        }
    }

    override fun onHttpResponse(requestCode: Int, resultJson: String?, e: Exception?) {
        loadingDialog.dismiss()
        if (StringUtil.isEmpty(resultJson)) {
            ToastUtils.showErrorToast(this, "数据异常")
            return
        }
        when (requestCode) {
//            REQUEST_LIST_CODE -> {
//
//                if (StringUtil.isEmpty(resultJson)) {
//                    ToastUtils.showErrorToast(this, "数据异常")
//                    return
//                }
//                val controllerByIdSceneNameBean = JsonUtils.parseObject(resultJson, ControllerByIdSceneNameBean::class.java)
//                CommonUtils.exitLogin(controllerByIdSceneNameBean.code, activity)
//                if (null != controllerByIdSceneNameBean.data && controllerByIdSceneNameBean.data.size > 0) {
//                    if (pageNum == 1) {
//                        listData.clear()
//                        listData.addAll(controllerByIdSceneNameBean.data)
//                        listAdapter.setList(listData)
//                    } else {
//                        listData.addAll(controllerByIdSceneNameBean.data)
//                        listAdapter.addData(controllerByIdSceneNameBean.data)
//                    }
//                    tv_no_data.visibility = View.GONE
//
//                } else {
//                    if (pageNum == 1) {
//                        tv_no_data.visibility = View.VISIBLE
//                    }
//                }
//
//
//
//                if (pageNum == 1) {
//                    refreshLayout.finishRefresh()
//                } else {
//                    refreshLayout.finishLoadmore()
//                }
//            }
            REQUEST_LIJIXIAFA_CODE -> {//立即下发返回


                val xiaFaFailData = JsonUtils.parseObject(resultJson, SceneRightExeXiaFaFailData::class.java)
                CommonUtils.exitLogin(xiaFaFailData.code, activity)

                // TODO: 2021/3/25 0025 增加toast内容

                if (xiaFaFailData.code != 200) {
                    if (xiaFaFailData.data == null || xiaFaFailData.data.nodeNames.size == 0) {
                        ToastUtils.showErrorToast(this, xiaFaFailData.message)
                        return
                    }
                    var failStr = ""
                    repeat(xiaFaFailData.data.nodeNames.size) {
                        failStr = failStr + xiaFaFailData.data.nodeNames[it] + "\n"
                    }

                    ToastUtils.showErrorToast(this, failStr + "\n" +
                            xiaFaFailData.message
                    )
//                    if (selectData.size > 1) {
//                        ToastUtils.showErrorToast(this, selectData[0].nodeName + "等" + selectData.size + "个节点控制失败")
//
//                    } else {
//                        ToastUtils.showErrorToast(this, selectData[0].nodeName + "节点控制失败")
//
//                    }
                } else {
                    showShortToast("下发场景成功")
                }
                //清除掉所有可已经勾选
                repeat(listData.size) {
                    listData[it].value = 0
                }
                selectData.clear()
                runUiThread {
                    listAdapter.setList(listData)
                }
            }

        }

    }

    class SceneQuickAdapter : BaseQuickAdapter<ControllerByIdSceneNameBean.DataBean, BaseViewHolder>(R.layout.item_scene_exe_right_list) {
        @SuppressLint("ResourceAsColor")
        override fun convert(holder: BaseViewHolder, item: ControllerByIdSceneNameBean.DataBean) {

            var shuxian = holder.getView<ImaginaryLineView>(R.id.xuxian)
            shuxian.setLineAttribute(0xff555555.toInt(), 2F, floatArrayOf(8f, 8f, 8f, 8f));

            var iv_check = holder.getView<ImageView>(R.id.iv_check)
            var view_left = holder.getView<View>(R.id.view_left)
            if (item.value == 1) {
                iv_check.setImageResource(R.drawable.node_check)
                view_left.setBackgroundResource(R.drawable.scene_left_list_item_select_bg_shape)
            } else {
                iv_check.setImageResource(R.drawable.node_no_check)
                view_left.setBackgroundResource(R.drawable.scene_left_list_item_no_select_bg_shape)
            }
            holder.getView<TextView>(R.id.tv_node_name)!!.text = Html.fromHtml(String.format("节点名称：<font color=\"#ffffff\">%s", item!!.nodeName), Html.FROM_HTML_MODE_COMPACT)
            holder.getView<TextView>(R.id.tv_scene_name)!!.text = Html.fromHtml(String.format("场景名称：<font color=\"#ffffff\">%s", item!!.name), Html.FROM_HTML_MODE_COMPACT)
            holder.getView<TextView>(R.id.tv_part_name)!!.text = Html.fromHtml(String.format("分区名称：<font color=\"#ffffff\">%s", item!!.partitionName), Html.FROM_HTML_MODE_COMPACT)
        }
    }
}