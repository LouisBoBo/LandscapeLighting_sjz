package com.exc.applibrary.main.message

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.exc.applibrary.databinding.ActivityMyMessageBinding
import com.exc.applibrary.main.HttpListener
import com.exc.applibrary.main.HttpRequestLi
import com.exc.applibrary.main.customview.CustomDialog
import com.exc.applibrary.main.model.BaseBean
import com.exc.applibrary.main.model.BaseBean2
import com.exc.applibrary.main.utils.CommonUtils
import com.exc.applibrary.main.utils.ToastUtils
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import zuo.biao.library.base.BaseActivity
import zuo.biao.library.ui.AlertDialog
import zuo.biao.library.util.JsonUtils
import zuo.biao.library.util.StringUtil
import java.util.*
import kotlin.collections.ArrayList

@RequiresApi(Build.VERSION_CODES.N)
class MyMessageListActivity : BaseActivity() {
    private lateinit var binding: ActivityMyMessageBinding
    private lateinit var mAdapter: MessageListQuickAdapter
    private var pageNum = 1
    private lateinit var loadingDialog: CustomDialog
    var listData = arrayListOf<MessageListBean.DataBean.ListBean>()
    var fistInitListEnd = false
    var messageSwitchStatus = 0


    /**
     * 更新消息状态成功———手动修改状态为已读
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEven(eventUpdateMessageStatusBean: EventUpdateMessageStatusBean) {
        repeat(listData.size) {
            if (eventUpdateMessageStatusBean.id == listData[it].id) {
                listData[it].status = 1
            }
        }
        mAdapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyMessageBinding.inflate(inflater)
        initEventBus(true)
        loadingDialog = CustomDialog(activity)
        setContentView(binding.root)

        initView()
        initData()
        initEvent()

    }


    override fun initView() {
        binding.mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mAdapter = MessageListQuickAdapter()
        binding.mRecyclerView.adapter = mAdapter
        binding.refreshLayout.setOnRefreshListener {
            pageNum = 1
            initListData()
        }
        binding.refreshLayout.setOnLoadmoreListener {
            pageNum++
            initListData()
        }
        mAdapter.setOnItemClickListener { _, _, position ->
            toActivity(MessageDetailActivity.createIntent(activity, listData[position]))
        }


        loadingDialog.show()
    }


    override fun initData() {
        initListData()

    }


    private fun initListData() {
        HttpRequestLi.getMessageList(pageNum, 1) { _: Int, resultJson: String?, _: Exception? ->
            loadingDialog.dismiss()
            if (StringUtil.isEmpty(resultJson)) {
                showShortToast("数据异常")
                return@getMessageList
            }
            val messageListBean = JsonUtils.parseObject(resultJson, MessageListBean::class.java)
            CommonUtils.exitLogin(messageListBean.code, activity)
            if (null != messageListBean.data) {
                // TODO: 2021/3/24 0024  消息开关
//                binding.viewSwitch1.isChecked = messageListBean.data.state != 1
//                if (pageNum == 1) {
//                    messageSwitchStatus = messageListBean.data.state
//                }
            }


            if (null != messageListBean.data.list && messageListBean.data.list.size > 0) {
                if (pageNum == 1) {
                    fistInitListEnd = true
                    listData = messageListBean.data.list
                    mAdapter.setList(messageListBean.data.list)
                } else {
                    listData.addAll(messageListBean.data.list)
                    mAdapter.addData(messageListBean.data.list)
                }
                binding.refreshLayout.visibility = View.VISIBLE
                binding.tvNoData.visibility = View.GONE


            } else {
                if (pageNum == 1) {
                    binding.refreshLayout.visibility = View.GONE
                    binding.tvNoData.visibility = View.VISIBLE
                }
            }
            if (pageNum == 1) {
                binding.refreshLayout.finishRefresh()
            } else {
                binding.refreshLayout.finishLoadmore()
            }

        }
    }

    override fun initEvent() {
        binding.headerLeftImg.setOnClickListener {
            finish()
        }
        binding.shuazi.setOnClickListener {
            if (!fistInitListEnd) {
                showShortToast("初始化中，请稍后再试")
                return@setOnClickListener
            }
            if (listData.size == 0) {
                showShortToast("暂无消息")
                return@setOnClickListener
            }

            HttpRequestLi.modifyMessageStatus(-1, 2) { _: Int, resultJson: String?, _: java.lang.Exception? ->
                if (StringUtil.isEmpty(resultJson)) {
                    showShortToast("数据异常")
                    return@modifyMessageStatus
                }
                val baseBean = JsonUtils.parseObject(resultJson, BaseBean::class.java)
                if (baseBean.code != 200) {
                    ToastUtils.showErrorToast(activity, baseBean.message)
                    return@modifyMessageStatus
                }

                repeat(listData.size) {
                    listData[it].status = 1
                }
                mAdapter.notifyDataSetChanged()
                showShortToast("已清除未读消息！")
            }


        }

        binding.viewSwitch1.setOnSwitchCheckListener {
            if(messageSwitchStatus == 0){//在开启的状态下再开启就不用任何操作
                if(it){
                    return@setOnSwitchCheckListener
                }
            }
            if(messageSwitchStatus == 1){//在关闭的状态下再关闭就不用任何操作
                if(!it){
                    return@setOnSwitchCheckListener
                }
            }
            var strPositive = "关闭"
            var message = "您即将关闭消息通知。关闭后，将清除历史消息；同时您将不会在此接收到故障提醒信息！"

            if (it) {//当前是关闭
                strPositive = "打开"
                message = "您即将打开消息通知。打开后，您将会在此接收并查看故障提醒信息！"

            }
            AlertDialog(this, "提示", message, true, strPositive, 0, AlertDialog.OnDialogButtonClickListener { requestCode: Int, isPositive: Boolean ->
                if (!isPositive) {
                    binding.viewSwitch1.isChecked = !it
                    return@OnDialogButtonClickListener
                }
                //点击了确定
                var news = if (it) 0 else 1
                HttpRequestLi.switchMessage(this, news, object : HttpListener<BaseBean2>() {
                    override fun onSuccess(result: BaseBean2?) {
                        binding.viewSwitch1.isChecked = result!!.data != 1
                        messageSwitchStatus = result!!.data
                        if(messageSwitchStatus == 1){//关闭后重新查询
                            pageNum == 1
                            initListData()
                        }

                    }
                    override fun onError() {
                    }
                })
            }).show()

        }

    }


}