package com.exc.applibrary.main.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.exc.applibrary.R
import com.exc.applibrary.databinding.ActivityControlNewBinding
import com.exc.applibrary.main.HttpRequest
import com.exc.applibrary.main.customview.CustomDialog
import com.exc.applibrary.main.model.LoopSearchEventBean
import com.exc.applibrary.main.model.NodeList
import com.exc.applibrary.main.ui.activity.ControlTitlePagerAdapter.CommonLeftBean
import com.exc.applibrary.main.ui.fragment.AllLoopListFragment
import com.exc.applibrary.main.ui.fragment.LoopListFragment
import com.exc.applibrary.main.utils.CommonUtils
import com.xuexiang.xui.widget.tabbar.VerticalTabLayout
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import zuo.biao.library.base.BaseActivity
import zuo.biao.library.interfaces.OnHttpResponseListener
import zuo.biao.library.util.JsonUtils
import zuo.biao.library.util.StringUtil

class LoopControlNewActivity : BaseActivity(), OnHttpResponseListener {
    private lateinit var nodeList: NodeList
    private lateinit var binding: ActivityControlNewBinding
    private var controlTitlePagerAdapter: ControlTitlePagerAdapter? = null
    private val REQUEST_NODE_CODE = 1000
    private lateinit var loadingDialog: CustomDialog
    private var isSearchResult = false
    private var commonLeftBean = arrayListOf<CommonLeftBean>()
    private var mFragmentList = arrayListOf<Fragment>()


    companion object {
        lateinit var loopSearchBean: LoopSearchEventBean
        lateinit var instance: LoopControlNewActivity

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityControlNewBinding.inflate(inflater)
        setContentView(binding!!.root)
        loadingDialog = CustomDialog(this)
        instance = this


        controlTitlePagerAdapter = ControlTitlePagerAdapter(this, supportFragmentManager,
                mFragmentList, commonLeftBean)
        binding!!.viewpager.adapter = controlTitlePagerAdapter
        binding!!.tabLayout.setupWithViewPager(binding!!.viewpager)


        if (loopSearchBean.isInit) {
            loopSearchBean.nid = -1
            loopSearchBean.channelTypeId = -1
            loopSearchBean.partitionId = -1
            loopSearchBean.siteId = -1
            loopSearchBean.status = -1
            loopSearchBean.offline = -1
        }


        initView()
        initData()
        initEvent()
    }

    override fun initView() {


    }

    override fun initData() {
        //查询节点列表
        loadingDialog.show()
        HttpRequest.getNodeList(loopSearchBean, REQUEST_NODE_CODE, this)
    }

    override fun initEvent() {
        binding.headerLeftImg.setOnClickListener {
            finish()
        }
        binding.headerRightImg.setOnClickListener {
            toActivity(Intent(this, SearchLoopActivity::class.java))
        }


    }

    override fun onHttpResponse(requestCode: Int, resultJson: String?, e: Exception?) {
        loadingDialog.dismiss()
        if (StringUtil.isEmpty(resultJson)) {
            showShortToast("数据异常")
            return
        }
        when (requestCode) {
            REQUEST_NODE_CODE -> {
                if(resultJson != null){
                    nodeList = JsonUtils.parseObject(resultJson, NodeList::class.java)
                    CommonUtils.exitLogin(nodeList.code, activity)
                    if (nodeList.code != 200) {
                        showShortToast(nodeList.message)
                        return
                    }
                    initNodeList()
                }
            }
        }
    }

    private fun initNodeList() {
        //模拟第一个选项（全部节点）
        var allNode = NodeList.DataBean.ListBean()
        allNode.name = "全部节点"
        allNode.id = -1
        allNode.isOffline = -1 //在线状态
        nodeList.data.list.add(0, allNode)

        //填充节点列表
        if (isSearchResult) {
            mFragmentList.clear()
            commonLeftBean.clear()
        }
        repeat(nodeList.data.list.size) {
            if (nodeList.data.list[it].id == -1) {
                mFragmentList.add(AllLoopListFragment.newInstance(nodeList.data.list[it]))
            } else {
                mFragmentList.add(LoopListFragment.newInstance(nodeList.data.list[it]))

            }
            when (nodeList.data.list[it].isOffline) {
                1 -> {//离线的
                    commonLeftBean.add(CommonLeftBean(nodeList.data.list[it].name, R.drawable.icon_wifi_red))
                }
                0 -> {//在线的
                    commonLeftBean.add(CommonLeftBean(nodeList.data.list[it].name, R.drawable.icon_wifi_green))
                }
                -1 -> {//全部的
                    commonLeftBean.add(CommonLeftBean(nodeList.data.list[it].name, -1))
                }
            }
        }


        controlTitlePagerAdapter?.notifyDataSetChanged()


    }
}