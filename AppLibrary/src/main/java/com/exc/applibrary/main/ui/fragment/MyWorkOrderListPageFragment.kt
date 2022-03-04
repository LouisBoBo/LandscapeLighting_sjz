package com.exc.applibrary.main.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.exc.applibrary.main.HttpRequest
import com.exc.applibrary.main.adapter.WorkListAdapter
import com.exc.applibrary.main.model.EventEnumBean
import com.exc.applibrary.main.model.LoginInfo
import com.exc.applibrary.main.model.OrderCountEvent
import com.exc.applibrary.main.model.WorkOrderList
import com.exc.applibrary.main.model.WorkOrderList.Data.list.WorkOrderItem
import com.exc.applibrary.main.other.WorKOrderTitles
import com.exc.applibrary.main.ui.activity.WorkOrderDetailActivity
import com.exc.applibrary.main.utils.CommonUtils
import com.exc.applibrary.main.utils.Constant
import com.exc.applibrary.main.view.WorkOrderListItemView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import zuo.biao.library.base.BaseHttpRecyclerFragment
import zuo.biao.library.interfaces.AdapterCallBack
import zuo.biao.library.util.JsonUtils
import zuo.biao.library.util.PreferencesUtil
import zuo.biao.library.util.StringUtil

class MyWorkOrderListPageFragment : BaseHttpRecyclerFragment<WorkOrderItem?, WorkOrderListItemView?, WorkListAdapter?>() {
    private var dataList: MutableList<WorkOrderItem> = ArrayList()
    private var viewPagerPosition: Int = 0
    private var PAGE_TITLE = WorKOrderTitles.ALL
    private lateinit var mActivity: Activity

    companion object {
        private val PAGE_NAME_KEY = "PAGE_NAME_KEY"
        private val CURRENT_POS = "CURRENT_POS"

        fun getInstance(pageName: String?, position: Int): MyWorkOrderListPageFragment {
            val args = Bundle()
            args.putString(PAGE_NAME_KEY, pageName)
            args.putInt(CURRENT_POS, position)
            val pageFragment = MyWorkOrderListPageFragment()
            pageFragment.arguments = args
            return pageFragment
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEven(evenEnum: EventEnumBean) {
        if (evenEnum == EventEnumBean.EDIT_ORDER_SUCCESS) {
            srlBaseHttpRecycler.autoRefresh()
        }

        if (evenEnum == EventEnumBean.ORDER_STATUS_ID_3_TO5) {
            if (PAGE_TITLE == WorKOrderTitles.DAI_CHU_LI || PAGE_TITLE == WorKOrderTitles.ZHENG_ZAI_CHU_LI || PAGE_TITLE == WorKOrderTitles.ALL) {
                srlBaseHttpRecycler.autoRefresh()
            }
        }


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        argument = arguments
        mActivity = this.requireActivity()
        if (argument != null) {
            PAGE_TITLE = argument.getString(PAGE_NAME_KEY, WorKOrderTitles.ALL)
            viewPagerPosition = argument.getInt(CURRENT_POS);
        }
        initEventBus(true)
        initView()
        initData()
        initEvent()
        srlBaseHttpRecycler.autoRefresh()

//        loadData(1)
        return view
    }


    override fun getListAsync(page: Int) {
        var statusIds = arrayListOf<Int>()
        var overtime: Int = -1

        when (PAGE_TITLE) {
            WorKOrderTitles.ALL -> {
                overtime = -1
            }
            WorKOrderTitles.DAI_CHU_SHEN_SHI -> {
                statusIds.add(9)
                statusIds.add(12)
                overtime = 0
            }
            WorKOrderTitles.DAI_CHU_SHEN_QU -> {
                statusIds.add(1)
                statusIds.add(13)
                statusIds.add(14)
                overtime = -1
            }
            WorKOrderTitles.BEI_BOHUI -> {
                statusIds.add(2)
                overtime = 0


            }
            WorKOrderTitles.DAI_CHU_LI -> {
                statusIds.add(3)
                overtime = -1


            }
            WorKOrderTitles.ZHENG_ZAI_CHU_LI -> {
                statusIds.add(5)
                overtime = -1

            }
            WorKOrderTitles.DAI_SHEN_HE_QU -> {
                statusIds.add(6)
                overtime = -1

            }
            WorKOrderTitles.DAI_SHEN_HE_SHI -> {
                statusIds.add(10)
                overtime = -1


            }
            WorKOrderTitles.SHEN_HE_TONG_GUO -> {
                statusIds.add(7)
                overtime = -1

            }
            WorKOrderTitles.YI_CHAO_SHI -> {
                statusIds.add(-1)
                overtime = 1

            }

        }

        var loginInfo = JsonUtils.parseObject(PreferencesUtil.getString(mActivity, Constant.LOGIN_INFO_JSON), LoginInfo::class.java)
        HttpRequest.getMyWorkOrderList(loginInfo.data.userId, overtime, page + 1, statusIds, 1) { _: Int, resultJson: String?, e: Exception? ->
            Log.e("工单列表数据：", "" + resultJson)

            if (StringUtil.isEmpty(resultJson)) {
                showShortToast("数据异常")
                stopLoadData(page)
                return@getMyWorkOrderList
            }
            val workOrderList = JsonUtils.parseObject(resultJson, WorkOrderList::class.java)

            CommonUtils.exitLogin(workOrderList.code, activity)

            if (workOrderList.code != 200) {
                showShortToast(workOrderList.message)
                stopLoadData(page)
                return@getMyWorkOrderList
            }
            val list = workOrderList.data.list.list
            onLoadSucceed(page, list)
            if (page == 0) {
                var orderCountEvent = OrderCountEvent()
                orderCountEvent.count = workOrderList.data.list.total
                orderCountEvent.position = viewPagerPosition
                EventBus.getDefault().post(orderCountEvent)
            }
            if (page == 0 && list.size == 0) {
                tvNoData.text = "无工单数据"
                tvNoData.visibility = View.VISIBLE
            } else {
                tvNoData.visibility = View.GONE

            }

            if (page == 0) {
                dataList = list
            } else {
                dataList.addAll(list)
            }

        }
    }

    override fun parseArray(json: String): List<WorkOrderItem>? {
        return null
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
        if (id > 0) {
            toActivity(WorkOrderDetailActivity.createIntent(mActivity, dataList[position].id, dataList[position].statusId))
        }
    }


    override fun setList(list: MutableList<WorkOrderItem?>?) {
        setList(object : AdapterCallBack<WorkListAdapter?> {
            override fun createAdapter(): WorkListAdapter? {
                return WorkListAdapter(mActivity)
            }

            override fun refreshAdapter() {
                adapter!!.refresh(list)
            }
        })
    }

}