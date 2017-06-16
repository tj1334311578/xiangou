package com.example.administrator.xiangou.main;


import android.util.Log;

import com.example.administrator.xiangou.login.LoginBean;

/**
 * Created by zhouzongyao on 2017/3/21.
 */

public class User {
    private static User user;
    private User(){}
    public static User getUser(){
        if (user == null){
            user = new User();
        }
        return user;
    }
    //判断用户信息是否改变
    public boolean changeUser(LoginBean.DataBean data){
        if (user_id!=data.getUser_id()){
            Log.e("position", "changeUser: user_id" );
            return true;
        }
        if (sex != data.getSex()){
            Log.e("position", "changeUser: sex" );
            return true;
        }
        if (mobile.equals(data.getMobile())){
            Log.e("position", "changeUser: mobile" );
            return true;
        }
        String name = data.getNickname();
        if (name.charAt(0) == '\"') {
            name = name.substring(1,name.length()-1);
            Log.e("subname", "changeUser: " + name);
        }
        if (!nickname.equals(name)){
            Log.e("position", "changeUser: nickname" );
            return true;
        }
        if (type != data.getType()){
            Log.e("position", "changeUser: type" );
            return true;
        }
        if (status != data.getStatus()){
            Log.e("position", "changeUser: status" );
            return true;
        }
        if (!head_pic.equals(data.getHead_pic())){
            Log.e("position", "changeUser: head_pic" );
            return true;
        }
        if (coupon_count != data.getCoupon_count()){
            Log.e("position", "changeUser: coupon_count" );
            return true;
        }
        if (follow !=data.getFollow()){
            Log.e("position", "changeUser: follow" );
            return true;
        }
        if (waitPay != data.getWaitPay()){
            return true;
        }
        if (waitSend !=data.getWaitSend()){
            return true;
        }
        if (waitReceive != data.getWaitReceive()){
            return true;
        }
        if (waitCcomment != data.getWaitCcomment()){
            return true;
        }
        if (order_count != data.getOrder_count()){
            return true;
        }
        if (refund != data.getRefund()){
            return true;
        }
        if (experience != data.getExperience()){
            return true;
        }
        return false;
    }
    public void setUser(LoginBean.DataBean data){
        user.user_id = data.getUser_id();
        user.sex = data.getSex();
        user.mobile = data.getMobile();
        String nickName = data.getNickname();
        if (nickName.charAt(0) == '\"') {
            nickName = nickName.substring(1,nickName.length()-1);
        }
        user.nickname = nickName;
        user.type = data.getType();
        user.status = data.getStatus();
        user.head_pic = data.getHead_pic();
        user.coupon_count = data.getCoupon_count();
        user.follow = data.getFollow();
        user.waitPay = data.getWaitPay();
        user.waitSend = data.getWaitSend();
        user.waitReceive = data.getWaitReceive();
        user.waitCcomment = data.getWaitCcomment();
        user.order_count = data.getOrder_count();
        user.refund = data.getRefund();
        user.experience = data.getExperience();
    }

    public void setbUserBySP(String str) {
        String[] user = str.split(",");
        setUser_id(Integer.parseInt(user[0]));
        setSex(Integer.parseInt(user[1]));
        setMobile(user[2]);
        String nickName = user[3];
        if (nickName.charAt(0) == '\"'){
            nickName = nickName.substring(1,nickName.length()-1);
        }
        setNickname(nickName);
        setType(Integer.parseInt(user[4]));
        setStatus(Integer.parseInt(user[5]));
        setHead_pic(user[6]);
        setCoupon_count(Integer.parseInt(user[7]));
        setFollow(Integer.parseInt(user[8]));
        setWaitPay(Integer.parseInt(user[9]));
        setWaitSend(Integer.parseInt(user[10]));
        setWaitReceive(Integer.parseInt(user[11]));
        setWaitCcomment(Integer.parseInt(user[12]));
        setOrder_count(Integer.parseInt(user[13]));
        setRefund(Integer.parseInt(user[14]));
        setExperience(Integer.parseInt(user[15]));
        setLevel(Integer.parseInt(user[16]));
    }
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
    @Override
    public String toString() {
        return user_id +
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

}
