package com.example.administrator.xiangou.nearby.frag;

/**
 * Created by zhouzongyao on 2017/3/13.
 */

public class GoodsBean {
    private int goodsImg;
    private String goodsName;

    public GoodsBean() {
    }

    public GoodsBean(int goodsImg, String goodsName) {
        this.goodsImg = goodsImg;
        this.goodsName = goodsName;
    }

    public int getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(int goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
