package com.example.administrator.xiangou.mine.mystore.couponmanager;

/**
 * 作者： tj on 2017/5/27.
 * <p>
 * 功能：
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */

public class CouponManageBean {
    public CouponManageBean(String condition, String condition1, String startTime, String endTime, String remainingquantity, String preferentialvalue) {
        this.condition = condition;
        this.condition1 = condition1;
        this.startTime = startTime;
        this.endTime = endTime;
        this.remainingquantity = remainingquantity;
        this.preferentialvalue = preferentialvalue;
    }

    String condition,condition1;
    String startTime,endTime,remainingquantity,preferentialvalue;

    @Override
    public String toString() {
        return "CouponManageBean{" +
                "condition='" + condition + '\'' +
                ", condition1='" + condition1 + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", remainingquantity='" + remainingquantity + '\'' +
                ", preferentialvalue='" + preferentialvalue + '\'' +
                '}';
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCondition1() {
        return condition1;
    }

    public void setCondition1(String condition1) {
        this.condition1 = condition1;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRemainingquantity() {
        return remainingquantity;
    }

    public void setRemainingquantity(String remainingquantity) {
        this.remainingquantity = remainingquantity;
    }

    public String getPreferentialvalue() {
        return preferentialvalue;
    }

    public void setPreferentialvalue(String preferentialvalue) {
        this.preferentialvalue = preferentialvalue;
    }
}
