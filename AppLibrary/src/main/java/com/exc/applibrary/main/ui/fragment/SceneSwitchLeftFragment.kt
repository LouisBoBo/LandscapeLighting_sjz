package com.exc.applibrary.main.ui.fragment

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.addapp.pickers.common.LineConfig
import cn.addapp.pickers.picker.SinglePicker
import com.exc.applibrary.R
import com.exc.applibrary.main.HttpListener
import com.exc.applibrary.main.HttpRequest
import com.exc.applibrary.main.adapter.SceneLeftQuickAdapter
import com.exc.applibrary.main.customview.CustomDialog
import com.exc.applibrary.main.customview.SpacesItemDecoration
import com.exc.applibrary.main.model.SceneLeftListBean
import com.exc.applibrary.main.model.SceneSiteList
import com.exc.applibrary.main.model.SelectBuildModelScene
import com.exc.applibrary.main.model.UserData
import com.exc.applibrary.main.ui.activity.SceneExeActivity
import com.exc.applibrary.main.utils.CommonUtils
import com.exc.applibrary.main.utils.ToastUtils
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import kotlinx.android.synthetic.main.fragment_scene.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import zuo.biao.library.base.BaseFragment
import zuo.biao.library.util.JsonUtils
import zuo.biao.library.util.Log
import zuo.biao.library.util.StringUtil

@RequiresApi(Build.VERSION_CODES.N)
class SceneSwitchLeftFragment : BaseFragment() {
    private lateinit var mAdapter: SceneLeftQuickAdapter
    private var pageNum = 1
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var refreshLayout: SmartRefreshLayout
    private lateinit var loadingDialog: CustomDialog
    private lateinit var iv_sanjiao: ImageView
    private lateinit var tv_no_data: TextView
    private lateinit var tv_select_site: TextView
    private lateinit var btn_condition_ok: TextView
    private lateinit var et_condition_node: EditText
    private lateinit var mActivity: Activity
    private var partitionId = -1
    private var siteId = -1
    private var selectNodeName = ""


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setContentView(R.layout.fragment_scene)
        loadingDialog = CustomDialog(activity)
        mActivity = this.requireActivity()
        initEventBus(true)
        initView()
        initData()
        initEvent()
        return view
    }

    override fun initView() {
        mRecyclerView = findView(R.id.mRecyclerView)
        refreshLayout = findView(R.id.refreshLayout)
        tv_no_data = findView(R.id.tv_no_data)
        tv_select_site = findView(R.id.tv_select_site)
        btn_condition_ok = findView(R.id.btn_condition_ok)
        et_condition_node = findView(R.id.et_condition_node)
        iv_sanjiao = findView(R.id.iv_sanjiao)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.addItemDecoration(SpacesItemDecoration(16))

    }

    override fun initData() {
        mAdapter = SceneLeftQuickAdapter()
        mRecyclerView.adapter = mAdapter
        refreshLayout.setOnRefreshListener {
            pageNum = 1
            initListData()
        }
        refreshLayout.setOnLoadmoreListener {
            pageNum++
            initListData()
        }
        loadingDialog.show()
        initListData()
    }

    private fun initListData() {
        selectNodeName = et_condition_node.text.trim().toString()
        HttpRequest.getGetSceneLeftList(siteId, selectNodeName, pageNum, 1) { _: Int, resultJson: String?, _: Exception? ->
            loadingDialog.dismiss()
            if (StringUtil.isEmpty(resultJson)) {
                showShortToast("数据异常")
                return@getGetSceneLeftList
            }
            val sceneLeftListBean = JsonUtils.parseObject(resultJson, SceneLeftListBean::class.java)
            if(sceneLeftListBean == null){
                showShortToast("数据异常")
                return@getGetSceneLeftList
            }
            CommonUtils.exitLogin(sceneLeftListBean.code, activity)
            if(sceneLeftListBean.code != 200){
                ToastUtils.showErrorToast(mActivity,sceneLeftListBean.message)
                return@getGetSceneLeftList
            }

            if (null != sceneLeftListBean.data.list && sceneLeftListBean.data.list.size > 0) {
                if (pageNum == 1) {
                    mAdapter.setList(sceneLeftListBean.data.list)
                } else {
                    mAdapter.addData(sceneLeftListBean.data.list)
                }
                refreshLayout.visibility = View.VISIBLE
                tv_no_data.visibility = View.GONE


            } else {
                if (pageNum == 1) {
                    refreshLayout.visibility = View.GONE
                    tv_no_data.visibility = View.VISIBLE
                }else{
                    ToastUtils.showToast(mActivity,"已无更多数据~",false)
                }
            }
            if (pageNum == 1) {
                refreshLayout.finishRefresh()
            } else {
                refreshLayout.finishLoadmore()
            }

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEven(clickItem: SceneLeftListBean.Data.list) {
        toActivity(SceneExeActivity.createIntent(mActivity, clickItem.id, clickItem.name))
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEven(selectBuildModelScene: SelectBuildModelScene) {
        if (selectBuildModelScene.site_id > 0) {
            partitionId = selectBuildModelScene.partition_id
            siteId = selectBuildModelScene.site_id
            pageNum = 1
            initListData()
        }

    }


    override fun initEvent() {
        //选择站点
        tv_select_site.setOnClickListener {
            HttpRequest.getSiteList(mActivity, object : HttpListener<SceneSiteList>() {
                override fun onSuccess(siteList: SceneSiteList) {
                    if (siteList.data == null) {
                        ToastUtils.showErrorToast(mActivity, "暂无数据")
                        return
                    }
                    val pickerItemStr = arrayListOf<String>()
                    var selectedIndex = 0

                    var allSiteDataBean = SceneSiteList.DataBean()
                    allSiteDataBean.name =  "全部站点"
                    allSiteDataBean.id = -1
                    siteList.data.add(0,allSiteDataBean)
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
                        tv_select_site.text = item
                        siteId = siteList.data[index].id

                    }
                    picker.show()


                }

                override fun onError() {
                }

            })

        }

        iv_sanjiao.setOnClickListener {

            tv_select_site.performClick()
        }

        //确定过滤条件
        btn_condition_ok.setOnClickListener {
            pageNum = 1
            loadingDialog.show()
            initListData()
        }


    }
}