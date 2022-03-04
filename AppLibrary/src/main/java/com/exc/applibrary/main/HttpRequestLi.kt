package com.exc.applibrary.main

import android.app.Activity
import com.exc.applibrary.main.HttpRequest.*
import com.exc.applibrary.main.model.BaseBean2
import zuo.biao.library.interfaces.OnHttpResponseListener

class
HttpRequestLi {
    companion object {

        //---------------端口-----------
        //	private static final String TAG = "HttpRequest";


//        val SHOW_VIDEO_SERVER_PATH = "http://192.168.111.129:60012/originalV/"


        //----------------------apiURL--------------------------------------------
        private val GET_SHOW_SHOWING_LIST_BY_SITE_ID = "$PLAY_PORT/script/playtime"


        private val GET_SHOW_SHOW_LIST_BY_SITE_ID = "$SERVICES_PORT3/api/site/srcVideo/"

        /**
         * 故障消息列表
         */
        private val MESSAGE_LIST = "${HttpRequest.SERVICES_PORT2}/api/notice"

        /**
         * 故障消息开关
         */
        private val MESSAGE_SWITCH = "${HttpRequest.SERVICES_PORT}/api/notice/messageStatus"

        /**
         * 修改故障消息状态
         */
        private val UPDATE_MESSAGE = "${HttpRequest.SERVICES_PORT2}/api/notice/"


        private val MONITORING_LIST = "${HttpRequest.SERVICES_PORT_MONITOR}/surveillance/cameraInfo/page"

        private val CURRENT_MONITOR_URL = "${HttpRequest.SERVICES_PORT_MONITOR}/surveillance/cameraInfo/previewURLs"


        private val GET_SITE = "${HttpRequest.SERVICES_PORT3}/api/site"


        //-------------------------------------method------------------------
        fun getShowShowingListBySiteId(siteId: Int, requestCode: Int, listener: OnHttpResponseListener?) {
            val request: MutableMap<String, Any> = HashMap()
            request["siteId"] = siteId
            request["nodeId"] = 0
            HttpManager.getInstance().post(request, HttpRequest.SERVICES_ADDRESS + GET_SHOW_SHOWING_LIST_BY_SITE_ID, requestCode, listener)
        }


        fun getShowShowListBySiteId(siteId: Int, requestCode: Int, listener: OnHttpResponseListener?) {
            val request: MutableMap<String, Any> = HashMap()
            HttpManager.getInstance().get(request, HttpRequest.SERVICES_ADDRESS + GET_SHOW_SHOW_LIST_BY_SITE_ID + siteId, requestCode, listener)
        }

        fun getMessageList(pageNum: Int, requestCode: Int, listener: OnHttpResponseListener?) {
            val request: MutableMap<String, Any> = HashMap()
            request["status"] = ""
            request["pageNum"] = pageNum
            request["pageSize"] = 20
            HttpManager.getInstance().get(request, HttpRequest.SERVICES_ADDRESS + MESSAGE_LIST, requestCode, listener)
        }

        fun switchMessage(activity: Activity, news: Int, httpListener: HttpListener<BaseBean2>?) {
            val request: MutableMap<String, Any> = HashMap()
            request["news"] = news
            HttpManager.getInstance().postBackModule(activity, request, HttpRequest.SERVICES_ADDRESS + MESSAGE_SWITCH, httpListener)
        }


        fun modifyMessageStatus(id: Int, requestCode: Int, listener: OnHttpResponseListener?) {
            val request: MutableMap<String, Any> = HashMap()

            HttpManager.getInstance().get(request, HttpRequest.SERVICES_ADDRESS + UPDATE_MESSAGE + id, requestCode, listener)
        }


        fun getMonitoringList(pageNo: Int, requestCode: Int, listener: OnHttpResponseListener?) {
            val request: MutableMap<String, Any> = HashMap()
            request["pageNo"] = pageNo
            request["pageSize"] = 1000

            HttpManager.getInstance().get(request, HttpRequest.SERVICES_ADDRESS + MONITORING_LIST, requestCode, listener)
        }

        fun getCurrentMonitor(cameraIndexCode: String, requestCode: Int, listener: OnHttpResponseListener?) {
            val request: MutableMap<String, Any> = HashMap()
            request["cameraIndexCode"] = cameraIndexCode
            request["streamType"] = 1
            request["protocol"] = "rtsp"
            request["transmode"] = 1
            request["expand"] = "streamform=rtp"
            HttpManager.getInstance().post(request, HttpRequest.SERVICES_ADDRESS + CURRENT_MONITOR_URL, requestCode, listener)
        }


        fun getSite(requestMap: HashMap<String, Any>, requestCode: Int, listener: OnHttpResponseListener?) {
            HttpManager.getInstance().get(requestMap, HttpRequest.SERVICES_ADDRESS + GET_SITE, requestCode, listener)
        }
    }
}