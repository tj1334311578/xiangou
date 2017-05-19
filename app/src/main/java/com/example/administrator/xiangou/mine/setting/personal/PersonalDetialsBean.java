package com.example.administrator.xiangou.mine.setting.personal;

/**
 * Created by Administrator on 2017/5/16.
 */

public class PersonalDetialsBean {

    /**
     * data :
     * state : {"code":101,"msg":"值不能为空","debugMsg":"","url":"api/User/personals"}
     */

    private String data;
    private StateBean state;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
    }

    public static class StateBean {
        /**
         * code : 101
         * msg : 值不能为空
         * debugMsg :
         * url : api/User/personals
         */

        private int code;
        private String msg;
        private String debugMsg;
        private String url;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getDebugMsg() {
            return debugMsg;
        }

        public void setDebugMsg(String debugMsg) {
            this.debugMsg = debugMsg;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
