package com.example.administrator.xiangou.mine.mystore.datamanager;

import java.util.List;

/**
 * 作者： tj on 2017/5/25.
 * <p>
 * 功能：
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */

public class TotalDataBean {

    @Override
    public String toString() {
        return "TotalDataBean{" +
                "data=" + data +
                ", state=" + state +
                '}';
    }

    /**
     * data : {"record":[{"browser":137,"month":"05","month_amount":6868.04,"month_order":3},{"browser":0,"month":"04","month_amount":0,"month_order":0},{"browser":0,"month":"03","month_amount":0,"month_order":0},{"browser":0,"month":"02","month_amount":0,"month_order":0},{"browser":0,"month":"01","month_amount":0,"month_order":0}],"year":[2016,2017]}
     * state : {"code":200,"msg":"获取成功","debugMsg":"","url":"api/Stores/data_manage"}
     */

    private DataBean data;
    private StateBean state;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
    }

    public static class DataBean {

        @Override
        public String toString() {
            return "DataBean{" +
                    "record=" + record +
                    ", year=" + year +
                    '}';
        }

        private List<RecordBean> record;
        private List<Integer> year;

        public List<RecordBean> getRecord() {
            return record;
        }

        public void setRecord(List<RecordBean> record) {
            this.record = record;
        }

        public List<Integer> getYear() {
            return year;
        }

        public void setYear(List<Integer> year) {
            this.year = year;
        }

        public static class RecordBean {
            @Override
            public String toString() {
                return "RecordBean{" +
                        "browser=" + browser +
                        ", month='" + month + '\'' +
                        ", month_amount=" + month_amount +
                        ", month_order=" + month_order +
                        '}';
            }

            /**
             * browser : 137
             * month : 05
             * month_amount : 6868.04
             * month_order : 3
             */

            private int browser;
            private String month;
            private double month_amount;
            private int month_order;

            public int getBrowser() {
                return browser;
            }

            public void setBrowser(int browser) {
                this.browser = browser;
            }

            public String getMonth() {
                return month;
            }

            public void setMonth(String month) {
                this.month = month;
            }

            public double getMonth_amount() {
                return month_amount;
            }

            public void setMonth_amount(double month_amount) {
                this.month_amount = month_amount;
            }

            public int getMonth_order() {
                return month_order;
            }

            public void setMonth_order(int month_order) {
                this.month_order = month_order;
            }
        }
    }

    public static class StateBean {

        @Override
        public String toString() {
            return "StateBean{" +
                    "code=" + code +
                    ", msg='" + msg + '\'' +
                    ", debugMsg='" + debugMsg + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }

        /**
         * code : 200
         * msg : 获取成功
         * debugMsg :
         * url : api/Stores/data_manage
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
