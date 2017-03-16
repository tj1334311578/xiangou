package com.example.administrator.xiangou.shoppingcart;

/**
 * Created by zhouzongyao on 2017/3/15.
 */

public class DealBean {
    private String storeName;
    private float goodsFreePrice;//包邮底价

    public DealBean(String storeName, float goodsFreePrice) {
        this.storeName = storeName;
        this.goodsFreePrice = goodsFreePrice;
    }

    public float getGoodsFreePrice() {
        return goodsFreePrice;
    }

    public void setGoodsFreePrice(float goodsFreePrice) {
        this.goodsFreePrice = goodsFreePrice;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
