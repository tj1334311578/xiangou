package com.example.administrator.xiangou.login;

/**
 * Created by zhouzongyao on 2017/3/23.
 */

public class Captcha {
    /**
     * data :
     * state : {"code":200,"msg":"注册成功","debugMsg":"","url":"api/Register/register"}
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
         * code : 200
         * msg : 注册成功
         * debugMsg :
         * url : api/Register/register
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
