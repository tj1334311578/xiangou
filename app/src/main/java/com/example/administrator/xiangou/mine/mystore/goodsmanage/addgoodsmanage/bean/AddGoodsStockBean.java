package com.example.administrator.xiangou.mine.mystore.goodsmanage.addgoodsmanage.bean;

/**
 * 作者： tj on 2017/6/8.
 * <p>
 * 功能：
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */

public class AddGoodsStockBean {
    private String color;
    private String size;
    private String stock;
    private String del="删除";

    @Override
    public String toString() {
        return "AddGoodsStockBean{" +
                "color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", stock='" + stock + '\'' +
                ", del='" + del + '\'' +
                '}';
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
