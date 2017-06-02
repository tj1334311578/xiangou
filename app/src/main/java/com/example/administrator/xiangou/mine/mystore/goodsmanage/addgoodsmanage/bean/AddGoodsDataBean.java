package com.example.administrator.xiangou.mine.mystore.goodsmanage.addgoodsmanage.bean;

/**
 * 作者： tj on 2017/6/1.
 * <p>
 * 功能：商品基本信息
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */

public class AddGoodsDataBean {
    @Override
    public String toString() {

        return "AddGoodsDataBean{" +
                "cat_id=" + cat_id +
                ", model_id=" + model_id +
                ", goods_name='" + goods_name + '\'' +
                ", marker_price='" + marker_price + '\'' +
                ", shop_price='" + shop_price + '\'' +
                ", store_count=" + store_count +
                ", is_new=" + is_new +
                ", fare=" + fare +
                ", did=" + did +
                ", is_recommend=" + is_recommend +
                ", is_free_shipping=" + is_free_shipping +
                ", sign='" + sign + '\'' +
                '}';
    }

    private  int cat_id;
    private  int model_id;
    private  String goods_name;
    private  String marker_price;
    private  String shop_price;
    private  int store_count;
    private  int is_new;
    private int fare;
    private int did;
    private int is_recommend;
    private int is_free_shipping;
    private String sign;

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getMarker_price() {
        return marker_price;
    }

    public void setMarker_price(String marker_price) {
        this.marker_price = marker_price;
    }

    public String getShop_price() {
        return shop_price;
    }

    public void setShop_price(String shop_price) {
        this.shop_price = shop_price;
    }

    public int getStore_count() {
        return store_count;
    }

    public void setStore_count(int store_count) {
        this.store_count = store_count;
    }

    public int getIs_new() {
        return is_new;
    }

    public void setIs_new(int is_new) {
        this.is_new = is_new;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getIs_recommend() {
        return is_recommend;
    }

    public void setIs_recommend(int is_recommend) {
        this.is_recommend = is_recommend;
    }

    public int getIs_free_shipping() {
        return is_free_shipping;
    }

    public void setIs_free_shipping(int is_free_shipping) {
        this.is_free_shipping = is_free_shipping;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
