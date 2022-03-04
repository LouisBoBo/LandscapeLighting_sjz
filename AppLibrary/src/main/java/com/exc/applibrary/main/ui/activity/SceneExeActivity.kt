package com.exc.applibrary.main.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.exc.applibrary.R
import com.exc.applibrary.main.HttpRequest
import com.exc.applibrary.main.customview.CustomDialog
import com.exc.applibrary.main.model.BaseBean
import com.exc.applibrary.main.model.OrderCountEvent
import com.exc.applibrary.main.model.SceneDataListById
import com.exc.applibrary.main.ui.dialog.SceneNodeDetailByIdDialog
import com.exc.applibrary.main.ui.dialog.SceneNodeDetailByIdTimingDialog
import com.exc.applibrary.main.ui.dialog.SceneNodeDetailByIdTimingDialog.Companion.showDialog
import com.exc.applibrary.main.utils.CommonUtils
import kotlinx.android.synthetic.main.activity_haaderview.*
import kotlinx.android.synthetic.main.activity_haaderview.header_center_text
import kotlinx.android.synthetic.main.activity_haaderview.header_left_img
import kotlinx.android.synthetic.main.activity_my_work_order.*
import kotlinx.android.synthetic.main.activity_scene_exe.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import zuo.biao.library.base.BaseActivity
import zuo.biao.library.interfaces.OnHttpResponseListener
import zuo.biao.library.util.JsonUtils
import zuo.biao.library.util.StringUtil
import kotlin.properties.Delegates

class SceneExeActivity : BaseActivity(), View.OnClickListener, OnHttpResponseListener {
    private var id by Delegates.notNull<Int>()
    lateinit var nodeName: String
    val REQUEST_LIST_CODE = 1
    val REQUEST_LIJIXIAFA_CODE = 2
    private lateinit var listAdapter: SceneQuickAdapter
    private var sceneListData = arrayListOf<SceneDataListById.Data>()
    private var checkItemData: SceneDataListById.Data? = null
    private lateinit var loadingDialog: CustomDialog

    companion object {
        private const val nodeIdKey: String = "nodeId"
        private const val nodeNameKey: String = "nodeName"

        @JvmStatic
        fun createIntent(mActivity: Activity, nodeId: Int, nodeName: String): Intent {
            val intent = Intent(mActivity, SceneExeActivity::class.java)
            intent.putExtra(nodeIdKey, nodeId)
            intent.putExtra(nodeNameKey, nodeName)
            return intent
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEven(result: String) {
        if (result == SceneNodeDetailByIdTimingDialog.XIAFA_SUCCESS) {
            repeat(sceneListData.size) {
                sceneListData[it].isCheck = 0
                listAdapter.setList(sceneListData)
                checkItemData = null
            }
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scene_exe)
        initEventBus(true)
        id = getIntent().getIntExtra(nodeIdKey, -1)
        nodeName = getIntent().getStringExtra("nodeName").toString()
        loadingDialog = CustomDialog(this)
        initView()
        initData()
        initEvent()
    }

    override fun initData() {
        val layoutManager = GridLayoutManager(this, 2) //第二个参数为网格的列数
        mRecyclerView.layoutManager = layoutManager
        listAdapter = SceneQuickAdapter()
        mRecyclerView.adapter = listAdapter
        if (id < 0) {
            showShortToast("数据有误")
            return
        }
        listAdapter.setOnItemClickListener { _, _, position ->
            repeat(sceneListData.size) {
                if (position == it) {//单选
                    sceneListData[it].isCheck = 1
                } else {
                    sceneListData[it].isCheck = 0
                }
                listAdapter.setList(sceneListData)
            }
            checkItemData = sceneListData[position]
            SceneNodeDetailByIdDialog(context, checkItemData).show()

        }
        loadingDialog.show()
        HttpRequest.getSceneById(id, REQUEST_LIST_CODE, this)
    }

    override fun initEvent() {
        ll_lijixiafa.setOnClickListener(this)
        ll_dingshixiafa.setOnClickListener(this)
        header_left_img.setOnClickListener(this)
    }

    override fun initView() {
        header_center_text.text = "场景执行"
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.header_left_img -> {
                finish()
            }
            R.id.ll_lijixiafa -> {
                if (null == checkItemData) {
                    showShortToast("请先选择场景")
                    return
                }
                loadingDialog.show()
                HttpRequest.sceneLijixiafa(id, checkItemData, REQUEST_LIJIXIAFA_CODE, this)
            }
            R.id.ll_dingshixiafa -> {//定时下发
                if (null == checkItemData) {
                    showShortToast("请先选择场景")
                    return
                }
                showDialog(id, checkItemData, fragmentManager)

            }
        }
    }

    override fun onHttpResponse(requestCode: Int, resultJson: String?, e: Exception?) {

        loadingDialog.dismiss()

        if (StringUtil.isEmpty(resultJson)) {
            showShortToast("数据异常")
            return
        }
        when (requestCode) {
            REQUEST_LIST_CODE -> {
                val sceneDataListById = JsonUtils.parseObject(resultJson, SceneDataListById::class.java)
                CommonUtils.exitLogin(sceneDataListById.code, activity)
                if (sceneDataListById.code != 200) {
                    showShortToast(sceneDataListById.message)
                    return
                }

                if(sceneDataListById.data.size > 0 ){
                    tv_no_data.visibility = View.GONE
                }else{
                    tv_no_data.text = "暂无场景"
                    tv_no_data.visibility = View.VISIBLE

                }

                //只保留isEdit = 1的数据
                repeat(sceneDataListById.data.size) {
                    if (sceneDataListById.data[it].isEdit == 1) {
                        sceneDataListById.data[it].nodeName = nodeName
                        sceneListData.add(sceneDataListById.data[it])
                    }
                }




                listAdapter.setList(sceneListData)
            }
            REQUEST_LIJIXIAFA_CODE -> {//立即下发返回
                val sceneDataListById = JsonUtils.parseObject(resultJson, BaseBean::class.java)
                CommonUtils.exitLogin(sceneDataListById.code, activity)
                if (sceneDataListById.code != 200) {
                    showShortToast(sceneDataListById.message)
                } else {
                    showShortToast("下发场景成功")
                }

            }

        }

    }

    class SceneQuickAdapter : BaseQuickAdapter<SceneDataListById.Data, BaseViewHolder>(R.layout.item_scene_exe_list) {
        override fun convert(holder: BaseViewHolder, item: SceneDataListById.Data) {
            if (item.nodeName.length > 9) {
                holder.setText(R.id.tv_build_name, item.nodeName.substring(0, 8) + "...")
            } else {
                holder.setText(R.id.tv_build_name, item.nodeName)
            }
            holder.setText(R.id.tv_status, item.name)
            if (item.isCheck == 1) {
                holder.getView<LinearLayout>(R.id.root).setBackgroundResource(R.drawable.scene_left_list_item_check_bg_shape)
            } else {
                holder.getView<LinearLayout>(R.id.root).setBackgroundResource(R.drawable.scene_left_list_item_bg_shape)
            }
        }
    }
}