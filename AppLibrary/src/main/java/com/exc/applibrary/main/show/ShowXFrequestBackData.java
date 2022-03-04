package com.exc.applibrary.main.show;

import java.io.Serializable;

import lombok.Data;

@Data
public class ShowXFrequestBackData implements Serializable {

    /**
     * returnCode : 0
     * returnMsg : {"errCode":"","errDesc":""}
     * data : null
     */

    public int returnCode;
    public ReturnMsgBean returnMsg;
    public Object data;

    @Data
    public static class ReturnMsgBean implements Serializable {
        /**
         * errCode :
         * errDesc :
         */

        public String errCode;
        public String errDesc;
    }
}
