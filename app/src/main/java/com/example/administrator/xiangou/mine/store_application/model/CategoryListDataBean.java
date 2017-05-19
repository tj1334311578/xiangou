package com.example.administrator.xiangou.mine.store_application.model;

import java.util.List;

/**
 * @author zhouzongyao
 * @Description 主营类别列表数据实体类
 * @email 18482195579@163.com
 * @Date 2017-05-12 10:33
 */
public class CategoryListDataBean {

    /**
     * data : [{"name":"电器","cat_id":2},{"name":"家居","cat_id":4},{"name":"男装","cat_id":5},{"name":"化妆","cat_id":6},{"name":"箱包","cat_id":7},{"name":"母婴","cat_id":10},{"name":"女装","cat_id":844},{"name":"鞋子","cat_id":845},{"name":"宠物","cat_id":846}]
     * state : {"code":200,"msg":"获取成功","debugMsg":"","url":"api/Stores/getcate"}
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
         * url : api/Stores/getcate
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
         * name : 电器
         * cat_id : 2
         */

        private String name;
        private int cat_id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCat_id() {
            return cat_id;
        }

        public void setCat_id(int cat_id) {
            this.cat_id = cat_id;
        }
    }
}
