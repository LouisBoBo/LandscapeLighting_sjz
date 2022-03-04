package com.exc.applibrary.main.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class TestBean implements Serializable {
    /**
     * files : [{"filename":"111红色国旗.mp4","duration":"00:00:39.72","frames":993}]
     * partitionId : 1
     * mode : 4
     * siteId : 27
     */

    public String partitionId;
    public int mode;
    public int siteId;
    public List<FilesBean> files;

    @Data
    public static class FilesBean implements Serializable {
        /**
         * filename : 111红色国旗.mp4
         * duration : 00:00:39.72
         * frames : 993
         */

        public String filename;
        public String duration;
        public int frames;
    }
}
