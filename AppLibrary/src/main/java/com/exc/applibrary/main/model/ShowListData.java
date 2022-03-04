package com.exc.applibrary.main.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ShowListData implements Serializable {

    /**
     * code : 200
     * data : [{"audId":null,"auditId":"20210426160107008","auditTime":"2021-04-26 16:01:07","auditor":"超级管理员","createTime":"2021-04-12 20:19:53","creator":"超级管理员","duration":"00:00:39.72","frameNumber":993,"framerate":"25","ftpId":1,"height":"1080","id":8,"isDelete":0,"name":"111红色国旗.mp4","partId":1,"status":1,"type":"mp4","updateTime":null,"updator":null,"vidId":"6d58bad5-95c5-44cc-be19-ae8884530ba0","width":"1920"},{"audId":null,"auditId":"20210422171556447","auditTime":"2021-04-22 17:15:56","auditor":"超级管理员","createTime":"2021-04-22 17:15:35","creator":"超级管理员","duration":"00:01:43.80","frameNumber":2595,"framerate":"25","ftpId":1,"height":"720","id":9,"isDelete":0,"name":"0918小金鱼.mp4","partId":1,"status":1,"type":"mp4","updateTime":null,"updator":null,"vidId":"9bfd5af3-4e3d-41bf-acba-300d5d7c9b19","width":"1280"}]
     * message : SUCCESS
     */

    public int code;
    public String message;
    public ArrayList<DataBean> data;

    @Data
    public static class DataBean implements Serializable {
        /**
         * audId : null
         * auditId : 20210426160107008
         * auditTime : 2021-04-26 16:01:07
         * auditor : 超级管理员
         * createTime : 2021-04-12 20:19:53
         * creator : 超级管理员
         * duration : 00:00:39.72
         * frameNumber : 993
         * framerate : 25
         * ftpId : 1
         * height : 1080
         * id : 8
         * isDelete : 0
         * name : 111红色国旗.mp4
         * partId : 1
         * status : 1
         * type : mp4
         * updateTime : null
         * updator : null
         * vidId : 6d58bad5-95c5-44cc-be19-ae8884530ba0
         * width : 1920
         */

        public Object audId;
        public String auditId;
        public String auditTime;
        public String auditor;
        public String createTime;
        public String creator;
        public String duration;
        public int frameNumber;
        public String framerate;
        public int ftpId;
        public String height;
        public int id;
        public int isDelete;
        public String name;
        public int partId;
        public int status;
        public String type;
        public Object updateTime;
        public Object updator;
        public String vidId;
        public String width;
    }
}
