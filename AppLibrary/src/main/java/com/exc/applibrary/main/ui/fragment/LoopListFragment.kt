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
import zuo.biao.library.ui.AlertDialog
import zuo.biao.library.util.JsonUtils
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.arrayListOf
import kotlin.collections.set

@RequiresApi(Build.VERSION_CODES.N)
class LoopListFragment : BaseFragment(), View.OnClickListener {
    private var isShow: Boolean = false
    private lateinit var allList: ArrayList<LoopList.DataBean.ListBean>
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
        fun newInstance(nodeBean: NodeList.DataBean.ListBean): LoopListFragment {
            return LoopListFragment().setNodeBean(nodeBean)
        }
    }

    fun setNodeBean(nodeBean: NodeList.DataBean.ListBean): LoopListFragment {
        mNodeBean = nodeBean
        return this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setContentView(R.layout.fragment_loop_list)
        mActivity = requireActivity()
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
        allList = arrayListOf()
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

        HttpRequest.channel_allHttp(pageNum, requestBean, 1) { _: Int, resultJson: String?, _: Exception? ->
            mMessageLoader.dismiss()
            var loopListBean = JsonUtils.parseObject(resultJson, LoopList::class.java)
            CommonUtils.exitLogin(loopListBean.code, activity)
            if (loopListBean.code != 200) {
                showShortToast(loopListBean.message)
                return@channel_allHttp
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

    private val OPEN_ALL = 1
    private val CLOSE_ALL = 0
    override fun onClick(v: View) {
        when (v?.id) {
            R.id.tv_all_open -> {
                controlAll(OPEN_ALL)
            }
            R.id.tv_all_close -> {
                controlAll(CLOSE_ALL)

            }
        }
    }

    private fun controlAll(control: Int) {


        if (!::allList.isInitialized) {
            ToastUtils.showErrorToast(mActivity, "数据异常，请稍后再试")
            return
        }
        if (allList.size == 0) {
            ToastUtils.showErrorToast(mActivity, "暂无回路")
            return
        }

        var dialogCenterStr = ""
        var dialogPositive = ""

        when (control){
            OPEN_ALL ->{
                dialogCenterStr = "此操作将打开当前节点("+  mNodeBean.name +")下的所有回路，是否仍然执行？"
                dialogPositive = "开启"
            }
            CLOSE_ALL->{
                dialogCenterStr = "此操作将关闭当前节点("+  mNodeBean.name +")下的所有回路，是否仍然执行？"
                dialogPositive = "关闭"

            }

        }


        AlertDialog(mActivity, "", dialogCenterStr, true, dialogPositive, 0, AlertDialog.OnDialogButtonClickListener { requestCode: Int, isPositive: Boolean ->
            if (!isPositive) {
                return@OnDialogButtonClickListener
            }


            var loopList = ArrayList<HashMap<String, Any>>()

            repeat(allList.size) {
                var loop = HashMap<String, Any>()
                loop["controlId"] = allList[it].controlId
                loop["deviceAddress"] = allList[it].deviceAddress
                loop["dsn"] = allList[it].dsn
                loop["id"] = allList[it].id
                loop["name"] = allList[it].name
                loop["nid"] = allList[it].nid
                loop["tagId"] = allList[it].tagId
                loop["value"] = if (control == OPEN_ALL) 1 else 0
                loopList.add(loop)
            }
            mMessageLoader.show()
            HttpRequest.loopSwitch(loopList, 1) { _: Int, resultJson: String?, _: Exception? ->
                var baseBean = JsonUtils.parseObject(resultJson, BaseBean::class.java)
                if (baseBean.code != 200) {
                    ToastUtils.showErrorToast(context, baseBean.message)
                } else {
                    //切换成功不需要做任何操作
                    repeat(allList.size) {
                        allList[it].value == if (control == OPEN_ALL) 1 else 0
                        runUiThread {
                            mAdapter.setList(allList)
                        }
                    }
                }
                mMessageLoader.dismiss()
            }

        }).show()
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