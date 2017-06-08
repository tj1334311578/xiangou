package com.example.administrator.xiangou.login;

/**
 * Created by zhouzongyao on 2017/3/18.
 */

public class LoginBean{

    /**
     * data : {"user_id":2625,"sex":0,"mobile":"18482195579","nickname":"xg_38649699sn","type":1,"status":null,"head_pic":null,"coupon_count":0,"follow":0,"waitPay":0,"waitSend":0,"waitReceive":0,"order_count":0,"refund":0,"experience":11}
     * state : {"code":200,"msg":"登录成功","debugMsg":"","url":"api/Login/login"}
     */

    private DataBean data;
    private StateBean state;
    /**
     * data : {"user_id":2629,"sex":0,"mobile":"18482195579","nickname":"xg_10509136sn","type":1,"status":0,"head_pic":null,"coupon_count":0,"follow":0,"waitPay":0,"waitSend":0,"waitReceive":0,"waitCcomment":0,"order_count":0,"refund":0,"experience":11}
     */

    @Override
    public String toString() {
        return "LoginBean{" +
                "data=" + data +
                ", state=" + state +
                '}';
    }

    public DataBean getData() {
        return data;
    }

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
    }


    public void setData(DataBean data) {
        this.data = data;
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
         * msg : 登录成功
         * debugMsg :
         * url : api/Login/login
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
         * user_id : 2629
         * sex : 0
         * mobile : 18482195579
         * nickname : xg_10509136sn
         * type : 1
         * status : 0
         * head_pic : null
         * coupon_count : 0
         * follow : 0
         * waitPay : 0
         * waitSend : 0
         * waitReceive : 0
         * waitCcomment : 0
         * order_count : 0
         * refund : 0
         * experience : 11
         */

        private int user_id;
        private int sex;
        private String mobile;
        private String nickname;
        private int type;
        private int status;
        private String head_pic;
        private int coupon_count;
        private int follow;
        private int waitPay;
        private int waitSend;
        private int waitReceive;
        private int waitCcomment;
        private int order_count;
        private int refund;
        private int experience;
        private int level;

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getHead_pic() {
            return head_pic;
        }

        public void setHead_pic(String head_pic) {
            this.head_pic = head_pic;
        }

        public int getCoupon_count() {
            return coupon_count;
        }

        public void setCoupon_count(int coupon_count) {
            this.coupon_count = coupon_count;
        }

        public int getFollow() {
            return follow;
        }

        public void setFollow(int follow) {
            this.follow = follow;
        }

        public int getWaitPay() {
            return waitPay;
        }

        public void setWaitPay(int waitPay) {
            this.waitPay = waitPay;
        }

        public int getWaitSend() {
            return waitSend;
        }

        public void setWaitSend(int waitSend) {
            this.waitSend = waitSend;
        }

        public int getWaitReceive() {
            return waitReceive;
        }

        public void setWaitReceive(int waitReceive) {
            this.waitReceive = waitReceive;
        }

        public int getWaitCcomment() {
            return waitCcomment;
        }

        public void setWaitCcomment(int waitCcomment) {
            this.waitCcomment = waitCcomment;
        }

        public int getOrder_count() {
            return order_count;
        }

        public void setOrder_count(int order_count) {
            this.order_count = order_count;
        }

        public int getRefund() {
            return refund;
        }

        public void setRefund(int refund) {
            this.refund = refund;
        }

        public int getExperience() {
            return experience;
        }

        public void setExperience(int experience) {
            this.experience = experience;
        }

        @Override
        public String toString() {
            return  user_id +
                    "," + sex +
                    "," + mobile +
                    "," + nickname +
                    "," + type +
                    "," + status +
                    "," + head_pic +
                    "," + coupon_count +
                    "," + follow +
                    "," + waitPay +
                    "," + waitSend +
                    "," + waitReceive +
                    "," + waitCcomment +
                    "," + order_count +
                    "," + refund +
                    "," + experience +
                    "," + level ;
        }

    }
}
/**
 @Override
 public String toString() {
 return  user_id +
 "," + sex +
 "," + mobile +
 "," + nickname +
 "," + type +
 "," + status +
 "," + head_pic +
 "," + coupon_count +
 "," + follow +
 "," + waitPay +
 "," + waitSend +
 "," + waitReceive +
 "," + waitCcomment +
 "," + order_count +
 "," + refund +
 "," + experience;
 }
 */