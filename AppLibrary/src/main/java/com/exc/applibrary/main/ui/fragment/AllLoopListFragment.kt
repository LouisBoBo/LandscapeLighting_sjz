package com.exc.applibrary.main.ui.fragment

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exc.applibrary.R
import com.exc.applibrary.main.HttpRequest
import com.exc.applibrary.main.adapter.LoopItemAdapter
import com.exc.applibrary.main.customview.SpacesItemDecoration
import com.exc.applibrary.main.eventbus.EventMessage
import com.exc.applibrary.main.eventbus.LoopItemSwitchBean
import com.exc.applibrary.main.model.*
import com.exc.applibrary.main.ui.activity.LoopControlNewActivity
import com.exc.applibrary.main.utils.CommonUtils
import com.exc.applibrary.main.utils.ToastUtils
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import kotlinx.android.synthetic.main.fragment_scene.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import zuo.biao.library.base.BaseFragment
import zuo.biao.library.util.JsonUtils
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.MutableList
import kotlin.collections.set

@RequiresApi(Build.VERSION_CODES.N)
class AllLoopListFragment : BaseFragment(), View.OnClickListener {
    private var isShow: Boolean = false
    private lateinit var allList: MutableList<LoopList.DataBean.ListBean>
    private lateinit var mAdapter: LoopItemAdapter
    private var pageNum = 1
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var refreshLayout: SmartRefreshLayout
    private lateinit var tv_no_data: TextView
    private lateinit var tv_all_open: TextView
    private lateinit var tv_all_close: TextView

    private lateinit var mActivity: Activity
    private lateinit var mNodeBean: NodeList.DataBean.ListBean



    companion object {
        fun newInstance(nodeBean: NodeList.DataBean.ListBean): AllLoopListFragment {
            return AllLoopListFragment().setNodeBean(nodeBean)
        }
    }

    fun setNodeBean(nodeBean: NodeList.DataBean.ListBean): AllLoopListFragment {
        mNodeBean = nodeBean
        return this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setContentView(R.layout.fragment_loop_list)
        mActivity = this.requireActivity()
        initEventBus(true)
        initView()
        initData()
        initEvent()
        return view
    }

    override fun initView() {
        mRecyclerView = findView(R.id.recyclerView)
        refreshLayout = findView(R.id.refreshLayout)
        tv_no_data = findView(R.id.tv_no_data)
        tv_all_open = findView(R.id.tv_all_open)
        tv_all_close = findView(R.id.tv_all_close)

        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.addItemDecoration(SpacesItemDecoration(16))
    }

    override fun initData() {

        if (mNodeBean.id != -1) {//全部节点
            tv_all_open.visibility = View.VISIBLE
            tv_all_close.visibility = View.VISIBLE
        }
        mAdapter = LoopItemAdapter()
        mRecyclerView.adapter = mAdapter
        refreshLayout.setOnRefreshListener {
            pageNum = 1
            initListData()
        }
        refreshLayout.setOnLoadmoreListener {
            pageNum++
            initListData()
        }
        mMessageLoader.show()
        initListData()
    }

    private fun initListData() {

        var requestBean = LoopControlNewActivity.loopSearchBean
        requestBean.nid = mNodeBean.id

        HttpRequest.getAllLoopList(pageNum,requestBean, 1) { _: Int, resultJson: String?, _: Exception? ->
            mMessageLoader.dismiss()
            var loopListBean = JsonUtils.parseObject(resultJson, LoopList::class.java)
            CommonUtils.exitLogin(loopListBean.code, activity)
            if (loopListBean.code != 200) {
                showShortToast(loopListBean.message)
                return@getAllLoopList
            }
            if (null != loopListBean.data.list && loopListBean.data.list.size > 0) {
                if (pageNum == 1) {

                    allList = loopListBean.data.list
                    mAdapter.setList(allList)
                } else {
                    allList.addAll(loopListBean.data.list)
                    mAdapter.addData(allList)
                }
                refreshLayout.visibility = View.VISIBLE
                tv_no_data.visibility = View.GONE
            } else {
                if (pageNum == 1) {
                    refreshLayout.visibility = View.GONE
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


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEven(eventMessage: EventMessage<LoopItemSwitchBean>) {//单个回路切换
        if (!isShow) {
            return
        }
        var switchItem = eventMessage.data.switchItem
        var isChecked = eventMessage.data.isChecked
        var loopList = ArrayList<HashMap<String, Any>>()
        var loop = HashMap<String, Any>()
        loop["controlId"] = switchItem.controlId
        loop["deviceAddress"] = switchItem.deviceAddress
        loop["dsn"] = switchItem.dsn
        loop["id"] = switchItem.id
        loop["name"] = switchItem.name
        loop["nid"] = switchItem.nid
        loop["tagId"] = switchItem.tagId
        loop["value"] = if (isChecked) 1 else 0
        loopList.add(loop)
        mMessageLoader.show()
        HttpRequest.loopSwitch(loopList, 1) { _: Int, resultJson: String?, _: Exception? ->
            var baseBean = JsonUtils.parseObject(resultJson, BaseBean::class.java)
            if (baseBean.code != 200) {
                ToastUtils.showErrorToast(context, baseBean.message)
                repeat(allList.size) {
                    if (it == eventMessage.data.switchPosition) {
                        allList[it].value = switchItem.value
                    }
                }
                runUiThread {
                    mAdapter.setList(allList)
                }
            } else {
                //切换成功不需要做任何操作
            }
            mMessageLoader.dismiss()
        }
    }

    override fun initEvent() {
        tv_all_open.setOnClickListener(this)
        tv_all_close.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v?.id) {
            R.id.tv_all_open -> {
                showShortToast("全部开启")
            }
            R.id.tv_all_close -> {
                showShortToast("关闭关闭")

            }
        }
    }


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if ((isVisibleToUser && isResumed)) {
            onResume()
        } else if (!isVisibleToUser) {
            onPause()
        }
    }

    override fun onResume() {
        super.onResume()
        if (userVisibleHint) {
            isShow = true
        }
    }

    override fun onPause() {
        super.onPause()
        isShow = false
    }


}