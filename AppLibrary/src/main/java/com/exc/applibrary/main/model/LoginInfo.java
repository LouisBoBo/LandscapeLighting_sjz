package com.exc.applibrary.main.model;


public class LoginInfo {
    /**
     * code : 200
     * message : SUCCESS
     */

    private int code;
    private DataDTO data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataDTO {
        /**
         * site : 1277
         * roleGrade : 1
         * partitionId : 1
         * roleId : 1
         * roleName : 超级管理员
         * userName : 超级管理员
         * userId : 1
         * token : eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicGhvbmUiOiIxNTk4ODg4ODg4OCIsImlhdCI6MTYwODEwNjM1NCwiZXhwIjo0ODI0NTc4MjY0N30.l0QcDtDr3IgjNmIt4DxzHcfZSvDllGL8c6-mfl5HRNQ
         */
        private int userId;
        private String token;



        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }


    }
}
