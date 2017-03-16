package com.example.administrator.xiangou.shoppingcart;

/**
 * Created by zhouzongyao on 2017/3/15.
 */

public class GoodsDealBean {
    private int goodsImg;
    private String goodsName;
    private String goodsColor;
    private String goodsSize;
    private String goodsInch;
    private int goodsCount;
    private float goodsPrice;
    private float goodsOriginalPrice;//原价
    private float goodsDiscount;//折扣

    public GoodsDealBean() {
    }

    public GoodsDealBean(int goodsImg, String goodsName, String goodsColor, String goodsSize, String goodsInch, int goodsCount, float goodsPrice, float goodsOriginalPrice, float goodsDiscount) {
        this.goodsImg = goodsImg;
        this.goodsName = goodsName;
        this.goodsColor = goodsColor;
        this.goodsSize = goodsSize;
        this.goodsInch = goodsInch;
        this.goodsCount = goodsCount;
        this.goodsPrice = goodsPrice;
        this.goodsOriginalPrice = goodsOriginalPrice;
        this.goodsDiscount = goodsDiscount;
    }

    public String getGoodsInch() {
        return goodsInch;
    }

    public void setGoodsInch(String goodsInch) {
        this.goodsInch = goodsInch;
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

    public String getGoodsColor() {
        return goodsColor;
    }

    public void setGoodsColor(String goodsColor) {
        this.goodsColor = goodsColor;
    }

    public String getGoodsSize() {
        return goodsSize;
    }

    public void setGoodsSize(String goodsSize) {
        this.goodsSize = goodsSize;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public float getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public float getGoodsOriginalPrice() {
        return goodsOriginalPrice;
    }

    public void setGoodsOriginalPrice(float goodsOriginalPrice) {
        this.goodsOriginalPrice = goodsOriginalPrice;
    }

    public float getGoodsDiscount() {
        return goodsDiscount;
    }

    public void setGoodsDiscount(float goodsDiscount) {
        this.goodsDiscount = goodsDiscount;
    }
}
