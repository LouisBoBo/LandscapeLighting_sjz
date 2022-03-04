package com.exc.applibrary.main.message

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.exc.applibrary.databinding.ActivityMyMessageDetailBinding
import com.exc.applibrary.main.HttpRequestLi
import com.exc.applibrary.main.model.BaseBean
import org.greenrobot.eventbus.EventBus
import zuo.biao.library.base.BaseActivity
import zuo.biao.library.util.JsonUtils
import zuo.biao.library.util.StringUtil
import java.lang.Exception

class MessageDetailActivity : BaseActivity() {

    private lateinit var messageItem: MessageListBean.DataBean.ListBean
    private lateinit var binding: ActivityMyMessageDetailBinding


    companion object {
        val messageItemKey = "messageItemKey"
        fun createIntent(mActivity: Activity, messageItem: MessageListBean.DataBean.ListBean): Intent {
            var intent = Intent(mActivity, MessageDetailActivity::class.java)
            intent.putExtra(messageItemKey, messageItem)
            return intent
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyMessageDetailBinding.inflate(inflater)
        setContentView(binding.root)

        messageItem = getIntent().getSerializableExtra(messageItemKey) as MessageListBean.DataBean.ListBean
        initView()
        initData()
        initEvent()


    }

    override fun initView() {
    }

    override fun initData() {
        binding.tvTime.text = messageItem.createTime
        binding.tvTips.text = messageItem.text


        //改变消息状态
        if (messageItem.status == 1) {
            return
        }

        HttpRequestLi.modifyMessageStatus(messageItem.id, 1) { _: Int, resultJson: String?, _: Exception? ->
            if (StringUtil.isEmpty(resultJson)) {
                showShortToast("数据异常")
                return@modifyMessageStatus
            }
            val baseBean = JsonUtils.parseObject(resultJson, BaseBean::class.java)
            if (baseBean.code == 200) {
                var eventUpdateMessageStatusBean = EventUpdateMessageStatusBean()
                eventUpdateMessageStatusBean.id = messageItem.id
                EventBus.getDefault().post(eventUpdateMessageStatusBean)
            }
        }

    }

    override fun initEvent() {
        binding.headerLeftImg.setOnClickListener {
            finish()
        }
    }
}