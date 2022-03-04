package com.exc.applibrary.main.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.exc.applibrary.databinding.ActivitySelectSiteBinding
import com.exc.applibrary.main.HttpListener
import com.exc.applibrary.main.HttpRequest
import com.exc.applibrary.main.customview.CustomDialog
import com.exc.applibrary.main.model.*
import com.exc.applibrary.main.ui.activity.ControlTitlePagerAdapter.CommonLeftBean
import com.exc.applibrary.main.ui.fragment.SelectSiteListFragment
import com.exc.applibrary.main.utils.CommonUtils
import org.greenrobot.eventbus.EventBus
import zuo.biao.library.base.BaseActivity
import zuo.biao.library.interfaces.OnHttpResponseListener
import zuo.biao.library.util.JsonUtils
import zuo.biao.library.util.StringUtil

class SelectSiteActivity : BaseActivity(), OnHttpResponseListener {
    private var partList = arrayListOf<PartitionList.DataBean>()
    private lateinit var loopTypeList: MutableList<TypeRealyModel.DataBean>
    private lateinit var binding: ActivitySelectSiteBinding
    private var controlTitlePagerAdapter: ControlTitlePagerAdapter? = null
    private val mFragmentList = ArrayList<Fragment>()
    private val REQUEST_TYPEREALY_CODE = 1001
    private lateinit var loadingDialog: CustomDialog

    /**
     * 搜索条件是否被修改过
     */
    var isModified = false


    companion object {
        /**
         * 选择的分区id
         */
        var selectPartitionId = -1

        /**
         * 选择的站点id
         */
        var selectSiteId = -1


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectSiteBinding.inflate(inflater)
        setContentView(binding!!.root)
        loadingDialog = CustomDialog(this)

        initView()
        initData()
        initEvent()
    }

    override fun initView() {

    }

    override fun initData() {

        //查询回路类型列表
        loadingDialog.show()
        HttpRequest.channel_type_relayHttp(REQUEST_TYPEREALY_CODE, this)


    }

    override fun initEvent() {
        binding.headerLeftImg.setOnClickListener {
            finish()
        }
        binding.headerRightImg.setOnClickListener {
            var selectSiteEventBean = SelectBuildModelScene()

            if (selectSiteId >= 0) {
                if (selectPartitionId >= 0) {
                    selectSiteEventBean.partition_id = selectPartitionId
                }
                selectSiteEventBean.site_id = selectSiteId
                isModified = true
            }


            if (!isModified) {
                finish()
                return@setOnClickListener
            }

            EventBus.getDefault().post(selectSiteEventBean)


            finish()


        }


    }

    override fun onHttpResponse(requestCode: Int, resultJson: String?, e: Exception?) {
        loadingDialog.dismiss()
        if (StringUtil.isEmpty(resultJson)) {
            showShortToast("数据异常")
            return
        }
        when (requestCode) {
            REQUEST_TYPEREALY_CODE -> {//查询回路类型列表
                var loopTypeListData = JsonUtils.parseObject(resultJson, TypeRealyModel::class.java)
                loopTypeList = loopTypeListData.data
                CommonUtils.exitLogin(loopTypeListData.code, activity)
                if (loopTypeListData.code != 200) {
                    showShortToast(loopTypeListData.message)
                    return
                }
                queryPartData()
            }
        }
    }


    /**
     * 查询分区列表
     */
    private fun queryPartData() {
        HttpRequest.getPartitionList(this, object : HttpListener<PartitionList>() {
            override fun onSuccess(partitionList: PartitionList) {
                if (partitionList.data.size > 0) {
                    partList.addAll(partitionList.data)
                }
                initPartList()
            }

            override fun onError() {
            }
        })
    }

    /**
     * 填充分区列表
     */
    private fun initPartList() {
        //模拟第一个选项（全部节点）
        var allPartBean = PartitionList.DataBean()
        allPartBean.id = -1
        allPartBean.name = "全部分区"
        partList.add(0, allPartBean)
        val commonLeftBean: ArrayList<CommonLeftBean> = ArrayList()
        repeat(partList.size) {
            mFragmentList.add(SelectSiteListFragment.newInstance(partList[it]))
            commonLeftBean.add(CommonLeftBean(partList[it].name, -1))
        }
        controlTitlePagerAdapter = ControlTitlePagerAdapter(this, supportFragmentManager,
                mFragmentList, commonLeftBean)
        binding!!.viewpager.adapter = controlTitlePagerAdapter
        binding!!.tabLayout.setupWithViewPager(binding!!.viewpager)

    }


}