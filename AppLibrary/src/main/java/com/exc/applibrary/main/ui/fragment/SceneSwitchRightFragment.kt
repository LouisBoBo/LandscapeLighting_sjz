package com.exc.applibrary.main.ui.fragment

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exc.applibrary.R
import com.exc.applibrary.main.HttpRequest
import com.exc.applibrary.main.adapter.SceneRightQuickAdapter
import com.exc.applibrary.main.customview.CustomDialog
import com.exc.applibrary.main.customview.SpacesItemDecoration
import com.exc.applibrary.main.model.SceneChooseNodeListData
import com.exc.applibrary.main.ui.activity.SceneExeRightActivity
import com.exc.applibrary.main.utils.CommonUtils
import com.exc.applibrary.main.utils.ToastUtils
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import zuo.biao.library.base.BaseFragment
import zuo.biao.library.util.JsonUtils
import zuo.biao.library.util.StringUtil

@RequiresApi(Build.VERSION_CODES.N)
class SceneSwitchRightFragment : BaseFragment() {
    private lateinit var mAdapter: SceneRightQuickAdapter
    private var pageNum = 1
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var refreshLayout: SmartRefreshLayout
    private lateinit var loadingDialog: CustomDialog
    private lateinit var mActivity: Activity


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setContentView(R.layout.fragment_scene_right)
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
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.addItemDecoration(SpacesItemDecoration(16))
    }

    override fun initData() {
        mAdapter = SceneRightQuickAdapter()
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
        HttpRequest.getGetSceneRightList(pageNum, 1) { _: Int, resultJson: String?, _: Exception? ->
            loadingDialog.dismiss()
            if (StringUtil.isEmpty(resultJson)) {
                showShortToast("数据异常")
                return@getGetSceneRightList
            }
            val sceneChooseNodeListData = JsonUtils.parseObject(resultJson, SceneChooseNodeListData::class.java)
            if(sceneChooseNodeListData == null){
                showShortToast("数据异常")
                return@getGetSceneRightList
            }
            CommonUtils.exitLogin(sceneChooseNodeListData.code, activity)
            if (sceneChooseNodeListData.code != 200) {
                ToastUtils.showErrorToast(mActivity, sceneChooseNodeListData.message)
                return@getGetSceneRightList
            }
            if (null != sceneChooseNodeListData.data.list && sceneChooseNodeListData.data.list.size > 0) {
                if (pageNum == 1) {
                    mAdapter.setList(sceneChooseNodeListData.data.list)
                } else {
                    mAdapter.addData(sceneChooseNodeListData.data.list)
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
    fun onEven(clickItem: SceneChooseNodeListData.Data.list) {
        toActivity(SceneExeRightActivity.createIntent(mActivity, clickItem))
    }

    override fun initEvent() {}
}