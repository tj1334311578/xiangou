package com.example.administrator.xiangou.main;


import com.example.administrator.xiangou.login.LoginBean;

/**
 * Created by zhouzongyao on 2017/3/21.
 */

public class User extends LoginBean.DataBean{
    private static User user;
    private User(){}
    public static User getUser(){
        if (user == null){
            user = new User();
        }
        return user;
    }
    public static void setUser(LoginBean.DataBean data){
        if (data.toString().equals(user.toString()))
        user = (User) data;
    }

    /**
     * user_id : 2625
     * sex : 0
     * mobile : 18482195579
     * nickname : xg_38649699sn
     * type : 1
     * status : null
     * head_pic : null
     * coupon_count : 0
     * follow : 0
     * waitPay : 0
     * waitSend : 0
     * waitReceive : 0
     * order_count : 0
     * refund : 0
     * experience : 11
     */

    private int user_id;
    private int sex;
    private String mobile;
    private String nickname;
    private int type;
    private Object status;
    private Object head_pic;
    private int coupon_count;
    private int follow;
    private int waitPay;
    private int waitSend;
    private int waitReceive;
    private int order_count;
    private int refund;
    private int experience;

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

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getHead_pic() {
        return head_pic;
    }

    public void setHead_pic(Object head_pic) {
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
}
