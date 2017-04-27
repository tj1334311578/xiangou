package com.example.administrator.xiangou.mine.myorder.myorder.waitdelivery;


import java.util.List;

/**
 * Created by Administrator on 2017/4/26.
 */

public class DeliveryBean {
    private  String storeName;
    private String status;
    private List<DeliveryItemBean> list;
    private Double allprice,freight;
    private int goodsAcounts;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DeliveryItemBean> getList() {
        return list;
    }

    public void setList(List<DeliveryItemBean> list) {
        this.list = list;
    }

    public DeliveryBean(String storeName, String status, List<DeliveryItemBean> list, int goodsAcounts, Double allprice, Double freight) {
        this.storeName = storeName;
        this.status = status;
        this.list = list;
        this.goodsAcounts = goodsAcounts;
        this.allprice = allprice;
        this.freight = freight;
    }

    public int getGoodsAcounts() {
        return goodsAcounts;
    }

    public void setGoodsAcounts(int goodsAcounts) {
        this.goodsAcounts = goodsAcounts;
    }

    public Double getAllprice() {
        return allprice;
    }

    public void setAllprice(Double allprice) {
        this.allprice = allprice;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    @Override
    public String toString() {
        return "DeliveryBean{" +
                "storeName='" + storeName + '\'' +
                ", status='" + status + '\'' +
                ", list=" + list +
                ", goodsAcounts='" + goodsAcounts + '\'' +
                ", allprice='" + allprice + '\'' +
                ", freight='" + freight + '\'' +
                '}';
    }
}
