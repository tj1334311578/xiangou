package com.example.administrator.xiangou.mine.followpage.followstore;

import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */

public class FollowStoreBean {

    /**
     * data : [{"name":"xiangou","logo":"/public/upload/logo/2017/05-24/CpFXYuLNyVJ7Tdt6fr4zumE3c.png","did":3,"count":1},{"name":"test","logo":"/public/upload/logo/2017/05-24/CpFXYuLNyVJ7Tdt6fr4zumE3c.png","did":2,"count":1}]
     * state : {"code":200,"msg":"获取成功","debugMsg":"","url":"api/Collect/collect_store"}
     */

    private StateBean state;
    private List<DataBean> data;

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class StateBean {
        /**
         * code : 200
         * msg : 获取成功
         * debugMsg :
         * url : api/Collect/collect_store
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

    public static class DataBean {
        /**
         * name : xiangou
         * logo : /public/upload/logo/2017/05-24/CpFXYuLNyVJ7Tdt6fr4zumE3c.png
         * did : 3
         * count : 1
         */

        private String name;
        private String logo;
        private int did;
        private int count;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public int getDid() {
            return did;
        }

        public void setDid(int did) {
            this.did = did;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
