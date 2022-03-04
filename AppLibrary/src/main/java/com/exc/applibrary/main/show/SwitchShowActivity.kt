package com.exc.applibrary.main.show

//import com.bumptech.glide.request.RequestOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.exc.applibrary.databinding.ActivitySwitchShowBinding
import com.exc.applibrary.main.HttpListener
import com.exc.applibrary.main.HttpRequest
import com.exc.applibrary.main.HttpRequestLi
import com.exc.applibrary.main.customview.CustomDialog
import com.exc.applibrary.main.model.*
import com.exc.applibrary.main.ui.activity.SelectSiteActivity
import com.exc.applibrary.main.utils.CommonUtils
import com.exc.applibrary.main.utils.ToastUtils
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import zuo.biao.library.base.BaseActivity
import zuo.biao.library.interfaces.OnHttpResponseListener
import zuo.biao.library.ui.AlertDialog
import zuo.biao.library.util.JsonUtils
import zuo.biao.library.util.StringUtil
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

//节目切换
class SwitchShowActivity : BaseActivity(), OnHttpResponseListener {
    private lateinit var binding: ActivitySwitchShowBinding
    private lateinit var showListAdapter: ShowListAdapter

    //    private lateinit var orientationUtils: OrientationUtils
    var linearLayoutManager: LinearLayoutManager? = null
    val GET_SITE_INFO_ETAIL_CODE = 1
    val GET_SHOWING_LIST = 2
    val GET_SHOW_LIST = 3
    var TS3_FAST_CODE = 4
    val GET_SHOWING_LIST2 = 5

    private lateinit var mActivity: SwitchShowActivity
    private lateinit var loadingDialog: CustomDialog
    private lateinit var currentSiteDetail: SiteModel.DataBean
    private var firstShowingListShowUrl = ""
    var currentSiteId: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySwitchShowBinding.inflate(LayoutInflater.from(this))
        mActivity = this
        setContentView(binding.root)
        initEventBus(true)
        loadingDialog = CustomDialog(this)


        initView()
        initData()
        initEvent()

    }


    override fun initData() {
        loadingDialog.show()

        //默认选择第一个分区的第一个站点
        HttpRequest.getSiteList2(mActivity, object : HttpListener<ShowSwitchSiteList>() {
            override fun onSuccess(siteList: ShowSwitchSiteList) {
                if (siteList.data == null) {
                    ToastUtils.showErrorToast(mActivity, "暂无数据")
                    return
                }

                if (siteList.data.size == 0) {
                    ToastUtils.showErrorToast(mActivity, "暂无数据")
                    return
                }
                currentSiteId = siteList.data.list[0].id
                //查询当前站点信息
                HttpRequest.siteGet(currentSiteId.toString(), GET_SITE_INFO_ETAIL_CODE, this@SwitchShowActivity)

            }

            override fun onError() {
            }

        })


    }


    private fun initHeader() {
        if (!StringUtil.isEmpty(firstShowingListShowUrl)) {
            var headerVideoTitle = firstShowingListShowUrl.split(".")[0]
            firstShowingListShowUrl = videoUrlEncoder(firstShowingListShowUrl)
            var headerVideoPath = HttpRequest.SHOW_ING_VIDEO_SERVER_PATH + firstShowingListShowUrl
            initHeaderVideo(headerVideoPath, headerVideoTitle)
        }
        //查询节目列表
        loadingDialog.show()
        HttpRequestLi.getShowShowListBySiteId(currentSiteDetail.id, GET_SHOW_LIST, this)
    }

    private fun initHeaderVideo(headerVideoPath: String, headerVideoTitle: String) {


        binding.headerVideoPlayer.setUp(headerVideoPath, false, headerVideoTitle)
        binding.headerVideoPlayer.isLooping = true
        binding.headerVideoPlayer.backButton.visibility = View.GONE
        binding.headerVideoPlayer.setTitle(headerVideoTitle)

        //初始化不打开外部的旋转
//        orientationUtils.isEnable = false
//        val imageView = ImageView(this)
//        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
//        var requestOptions = RequestOptions()
//        requestOptions.frame(1 * 1000 * 8000)
//        Glide.with(this).setDefaultRequestOptions(requestOptions).load(headerVideoPath).into(imageView)
        val gsyVideoOption = GSYVideoOptionBuilder()
        gsyVideoOption
                .setIsTouchWiget(true)
//                .setThumbImageView(imageView)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setAutoFullWithSize(false)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setUrl(headerVideoPath)
                .setNeedShowWifiTip(false)
                .setCacheWithPlay(false)
                .setNeedShowWifiTip(false)
//                .setVideoTitle("测试视频")


                .build(binding.headerVideoPlayer)
        binding.headerVideoPlayer.fullscreenButton.visibility = View.GONE


        binding.headerVideoPlayer.startPlayLogic()




        binding.llNoVideoPlayIng.visibility = View.GONE
        binding.llPlaying.visibility = View.VISIBLE
        loadingDialog.dismiss()


    }


    private fun videoUrlEncoder(url: String): String {
        //被转码后的url
        var result = ""

        val index = url.indexOf("?")
        result = url.substring(0, index + 1)
        val temp = url.substring(index + 1)
        try {
            //URLEncode转码会将& ： / = 等一些特殊字符转码,(但是这个字符  只有在作为参数值  时需要转码;例如url中的&具有参数连接的作用，此时就不能被转码)
            var encode: String = URLEncoder.encode(temp, "utf-8")
            println(encode)
            encode = encode.replace("%3D", "=")
            encode = encode.replace("%2F", "/")
            encode = encode.replace("+", "%20")
            encode = encode.replace("%26", "&")
            result += encode


        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        return result
    }


    override fun initEvent() {
        binding.mHeaderLeftImg.setOnClickListener { finish() }
        binding.tvSelectSite.setOnClickListener {

            //和场景的共用
            toActivity(Intent(this, SelectSiteActivity::class.java)


            )


        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEven(selectSiteEventBean: SelectBuildModelScene) {
        if (selectSiteEventBean.site_id >= 0) {
            currentSiteId = selectSiteEventBean.site_id
            HttpRequest.siteGet(currentSiteId.toString(), GET_SITE_INFO_ETAIL_CODE, this@SwitchShowActivity)
        }
    }

    //切换节目
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEven(showXFShowBean: ShowQHShowBean) {
//        showShortToast("切换到："+showXFShowBean.name)
        loadingDialog.show()

        var snsS = arrayListOf<ShowQHShowBean>()
        snsS.add(showXFShowBean)

        val site_id: String = currentSiteDetail.id.toString()
        val partition_id: String = currentSiteDetail.partitionId.toString()
        var num: String = "currentSiteDetail.nodes.get(0).num";
        if(currentSiteDetail.nodes.size>0){
            num = currentSiteDetail.nodes.get(0).num;
        }

        HttpRequest.ts3ScriptsFastHttp2(partition_id, site_id, num,snsS, TS3_FAST_CODE, this)

    }


    override fun initView() {
        showListAdapter = ShowListAdapter()
        linearLayoutManager = LinearLayoutManager(this)
        binding.mRecyclerViewShowList.layoutManager = linearLayoutManager
        binding.mRecyclerViewShowList.adapter = showListAdapter


    }


    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
    }


    override fun onHttpResponse(requestCode: Int, resultJson: String?, e: Exception?) {
        if (StringUtil.isEmpty(resultJson)) {
            loadingDialog.dismiss()
            ToastUtils.showErrorToast(mActivity, "数据异常")
            return
        }
        when (requestCode) {
            GET_SITE_INFO_ETAIL_CODE -> {//站点信息
                val siteModel = JsonUtils.parseObject(resultJson, SiteModel::class.java)
                CommonUtils.exitLogin(siteModel.code, mActivity)
                if (siteModel.code != 200) {
                    loadingDialog.dismiss()
                    ToastUtils.showErrorToast(mActivity, siteModel.message)
                    return
                }

                currentSiteDetail = siteModel.data
                binding.mTvSiteName.text = "站点：" + currentSiteDetail.name

                //查询当前正在播放的节目-----暂时改为查询列表列表
                loadingDialog.show()
                HttpRequestLi.getShowShowingListBySiteId(currentSiteDetail.id, GET_SHOWING_LIST, this)


            }
            GET_SHOWING_LIST -> {//正在播放的列表
                loadingDialog.dismiss()
                val showIngListData = JsonUtils.parseObject(resultJson, ShowIngListData::class.java)
                if(showIngListData != null) {
                    CommonUtils.exitLogin(showIngListData.code, mActivity)

                    if (null != showIngListData.nowPlayFileList && showIngListData.nowPlayFileList.size > 0) {
                        repeat(showIngListData.nowPlayFileList.size) {
                            if (!StringUtil.isEmpty(showIngListData.nowPlayFileList[it])) {
                                firstShowingListShowUrl = showIngListData.nowPlayFileList[it]
                                return@repeat
                            }
                        }
                        if (StringUtil.isEmpty(firstShowingListShowUrl)) {
                            binding.llNoVideoPlayIng.visibility = View.VISIBLE
                            binding.llPlaying.visibility = View.GONE
                            //查询节目列表
                            loadingDialog.show()
                            HttpRequestLi.getShowShowListBySiteId(currentSiteDetail.id, GET_SHOW_LIST, this)
                        } else {
                            initHeader()//填充正在播放的节目
                        }
                    } else {//没有正在播放的节目
                        binding.llNoVideoPlayIng.visibility = View.VISIBLE
                        binding.llPlaying.visibility = View.GONE
                        //查询节目列表
                        loadingDialog.show()
                        HttpRequestLi.getShowShowListBySiteId(currentSiteDetail.id, GET_SHOW_LIST, this)
                    }
                }

            }

            GET_SHOW_LIST -> {//节目列表
                loadingDialog.dismiss()
                val showIngListData = JsonUtils.parseObject(resultJson, ShowListData::class.java)
                if(showIngListData != null) {
                    CommonUtils.exitLogin(showIngListData.code, mActivity)
                    if (showIngListData.code != 200) {

                        ToastUtils.showErrorToast(mActivity, showIngListData.message)
                        return
                    }
                    var showList = showIngListData.data
                    showListAdapter.setList(showList)

                    if (showList.size == 0) {
                        binding.llNoVideoList.visibility = View.VISIBLE
                        binding.mRecyclerViewShowList.visibility = View.GONE
                    } else {
                        binding.llNoVideoList.visibility = View.GONE
                        binding.mRecyclerViewShowList.visibility = View.VISIBLE
                    }
                }
            }

            TS3_FAST_CODE -> {
                loadingDialog.dismiss()


                val showXFrequestBackData = JsonUtils.parseObject(resultJson, ShowXFrequestBackData::class.java)

                if(showXFrequestBackData.returnCode !=0){
                    ToastUtils.showErrorToast(mActivity,"切换失败")
                    return
                }
                showShortToast("切换成功")

                //更新正在播放的节目
                //切换后重新查询当前正在播放的节目
                HttpRequestLi.getShowShowingListBySiteId(currentSiteDetail.id, GET_SHOWING_LIST2, this)
            }
            GET_SHOWING_LIST2 -> {//切换后重新查询当前正在播放的节目结果
                val showIngListData = JsonUtils.parseObject(resultJson, ShowIngListData::class.java)
                CommonUtils.exitLogin(showIngListData.code, mActivity)
                loadingDialog.dismiss()
                if (showIngListData.code != 200) {
                    binding.llNoVideoPlayIng.visibility = View.VISIBLE
                    binding.llPlaying.visibility = View.GONE

                }
                if (null != showIngListData.nowPlayFileList && showIngListData.nowPlayFileList.size > 0) {
                    repeat(showIngListData.nowPlayFileList.size) {
                        if (!StringUtil.isEmpty(showIngListData.nowPlayFileList[it])) {
                            firstShowingListShowUrl = showIngListData.nowPlayFileList[it]
                            return@repeat
                        }
                    }
                    if (StringUtil.isEmpty(firstShowingListShowUrl)) {
                        binding.llNoVideoPlayIng.visibility = View.VISIBLE
                        binding.llPlaying.visibility = View.GONE
                    }
                } else {//没有正在播放的节目
                    binding.llNoVideoPlayIng.visibility = View.VISIBLE
                    binding.llPlaying.visibility = View.GONE
                }


                if (!StringUtil.isEmpty(firstShowingListShowUrl)) {
                    var headerVideoTitle = firstShowingListShowUrl.split(".")[0]
                    firstShowingListShowUrl = videoUrlEncoder(firstShowingListShowUrl)
                    var headerVideoPath = HttpRequest.SHOW_ING_VIDEO_SERVER_PATH + firstShowingListShowUrl

                    initHeaderVideo(headerVideoPath, headerVideoTitle)
                }

            }


        }
    }


}