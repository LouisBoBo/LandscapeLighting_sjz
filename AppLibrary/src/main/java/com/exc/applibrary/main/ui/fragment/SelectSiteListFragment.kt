package com.exc.applibrary.main.ui.fragment

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.exc.applibrary.R
import com.exc.applibrary.main.HttpRequestLi
import com.exc.applibrary.main.adapter.FlexboxLayoutAdapter
import com.exc.applibrary.main.model.*
import com.exc.applibrary.main.ui.activity.SearchLoopActivity
import com.exc.applibrary.main.ui.activity.SelectSiteActivity
import com.exc.applibrary.main.ui.activity.SelectSiteActivity.Companion.selectPartitionId
import com.exc.applibrary.main.ui.activity.SelectSiteActivity.Companion.selectSiteId
import com.exc.applibrary.main.utils.CommonUtils
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlinx.android.synthetic.main.fragment_scene.*
import zuo.biao.library.base.BaseFragment
import zuo.biao.library.util.JsonUtils
import zuo.biao.library.util.StringUtil
import java.util.*
import kotlin.collections.set

@RequiresApi(Build.VERSION_CODES.N)
class SelectSiteListFragment : BaseFragment(), View.OnClickListener {
    private lateinit var allList: ArrayList<PartitionList.DataBean>

    private lateinit var mAdapter3: FlexboxLayoutAdapter


    private var pageNum = 1
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var tv_no_data: TextView

    private lateinit var mActivity: Activity
    private var partitionId = -1
    private var siteId = -1
    private lateinit var mSiteBean: PartitionList.DataBean


    companion object {
        fun newInstance(siteBean: PartitionList.DataBean): SelectSiteListFragment {
            return SelectSiteListFragment().setNodeBean(siteBean)
        }
    }

    fun setNodeBean(siteBean: PartitionList.DataBean): SelectSiteListFragment {
        mSiteBean = siteBean
        return this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setContentView(R.layout.fragment_loop_site_list)
        mActivity = requireActivity()
        initView()
        initData()
        initEvent()
        return view
    }

    override fun initView() {
        mRecyclerView = findView(R.id.recyclerView)
        tv_no_data = findView(R.id.tv_no_data)
    }

    override fun initData() {
        allList = arrayListOf()
        initListData()
    }

    private fun initListData() {
        var requestMap = HashMap<String, Any>()
        if (mSiteBean.id >= 0) {
            requestMap["partitionId"] = mSiteBean.id
        }
        requestMap["pageSize"] = 5999
        HttpRequestLi.getSite(requestMap, 1) { _: Int, resultJson: String?, _: Exception? ->
            var siteList = JsonUtils.parseObject(resultJson, SiteList::class.java)
            CommonUtils.exitLogin(siteList.code, activity)
            if (siteList.code != 200) {
                showShortToast(siteList.message)
                return@getSite
            }
            if (null != siteList.data.list && siteList.data.list.size > 0) {
                var siteNameArray = arrayListOf<String>()
                repeat(siteList.data.list.size) {
//                    siteNameArray[it] = siteList.data.list[it].name
                    siteNameArray.add(siteList.data.list[it].name)
                }
                mRecyclerView.layoutManager = getFlexboxLayoutManager(mActivity)
                mAdapter3 = FlexboxLayoutAdapter(siteNameArray).setCancelable(true)
                mRecyclerView.adapter = mAdapter3
                mAdapter3.setOnItemClickListener { _, _, position ->
                    if (mAdapter3.select(position)) {
//                        SearchLoopActivity.selectPartitionId = if (StringUtil.isEmpty(mAdapter3.selectContent)) -1 else mSiteBean.id
                        SelectSiteActivity.selectPartitionId = if (StringUtil.isEmpty(mAdapter3.selectContent)) -1 else mSiteBean.id
//                        SearchLoopActivity.selectSiteId = if (StringUtil.isEmpty(mAdapter3.selectContent)) -1 else siteList.data.list[position].id
                        SelectSiteActivity.selectSiteId = if (StringUtil.isEmpty(mAdapter3.selectContent)) -1 else siteList.data.list[position].id
                    }
                }
                tv_no_data.visibility = View.GONE
            } else {
                tv_no_data.visibility = View.VISIBLE

            }


        }
    }

    override fun initEvent() {
    }


    override fun onClick(v: View) {
        when (v?.id) {

        }
    }


    fun getFlexboxLayoutManager(context: Context?): FlexboxLayoutManager? {
        //设置布局管理器
        val flexboxLayoutManager = FlexboxLayoutManager(context)
        //flexDirection 属性决定主轴的方向（即项目的排列方向）。类似 LinearLayout 的 vertical 和 horizontal:
        // 主轴为水平方向，起点在左端。
        flexboxLayoutManager.flexDirection = FlexDirection.ROW
        //flexWrap 默认情况下 Flex 跟 LinearLayout 一样，都是不带换行排列的，但是flexWrap属性可以支持换行排列:
        // 按正常方向换行
        flexboxLayoutManager.flexWrap = FlexWrap.WRAP
        //justifyContent 属性定义了项目在主轴上的对齐方式:
        // 交叉轴的起点对齐
        flexboxLayoutManager.justifyContent = JustifyContent.FLEX_START
        return flexboxLayoutManager
    }


}