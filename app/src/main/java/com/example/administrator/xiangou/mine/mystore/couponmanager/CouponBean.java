package com.example.administrator.xiangou.mine.mystore.couponmanager;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： tj on 2017/6/13.
 * <p>
 * 功能：网络获取的data数据类
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */

public class CouponBean {
    @Override
    public String toString() {
        return "CouponBean{" +
                "data=" + data +
                '}';
    }

    private List<CouponsBean> data=new ArrayList<>();

    public List<CouponsBean> getData() {
        return data;
    }

    public void setData(List<CouponsBean> data) {
        this.data = data;
    }

    static class CouponsBean{
        @Override
        public String toString() {
            return "CouponsBean{" +
                    "couponid=" + couponid +
                    ", rests=" + rests +
                    ", createnum=" + createnum +
                    ", money='" + money + '\'' +
                    ", condition='" + condition + '\'' +
                    ", use_start_time='" + use_start_time + '\'' +
                    ", use_end_time='" + use_end_time + '\'' +
                    '}';
        }

        private int couponid;//优惠卷id
        private int rests;//优惠卷剩余数量
        private int createnum;//优惠卷发放数量
        private String money;//优惠卷金额
        private String condition;//优惠卷使用条件
        private String use_start_time;//使用开始时间
        private String use_end_time;//使用结束时间
        public CouponsBean(){

        }
        public CouponsBean(int couponid, int rests, int createnum, String money, String condition, String use_start_time, String use_end_time) {
            this.couponid = couponid;
            this.rests = rests;
            this.createnum = createnum;
            this.money = money;
            this.condition = condition;
            this.use_start_time = use_start_time;
            this.use_end_time = use_end_time;
        }

        public int getCouponid() {
            return couponid;
        }

        public void setCouponid(int couponid) {
            this.couponid = couponid;
        }

        public int getRests() {
            return rests;
        }

        public void setRests(int rests) {
            this.rests = rests;
        }

        public int getCreatenum() {
            return createnum;
        }

        public void setCreatenum(int createnum) {
            this.createnum = createnum;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getUse_start_time() {
            return use_start_time;
        }

        public void setUse_start_time(String use_start_time) {
            this.use_start_time = use_start_time;
        }

        public String getUse_end_time() {
            return use_end_time;
        }

        public void setUse_end_time(String use_end_time) {
            this.use_end_time = use_end_time;
        }
    }
}
