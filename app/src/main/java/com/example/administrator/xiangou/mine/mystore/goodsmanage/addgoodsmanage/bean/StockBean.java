package com.example.administrator.xiangou.mine.mystore.goodsmanage.addgoodsmanage.bean;

/**
 * 作者： tj on 2017/6/7.
 * <p>
 * 功能：
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */

public class StockBean {
    @Override
    public String toString() {
        return "StockBean{" +
                "color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", stock='" + stock + '\'' +
                ", delbtn='" + delbtn + '\'' +
                '}';
    }

    private String color;
    private String size;
    private String stock;
    private String delbtn;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public StockBean(int position,String color, String size, String stock) {
        this.color = color;
        this.size = size;
        this.stock = stock;
        this.position=position;
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

    public String getDelbtn() {
        return delbtn;
    }

    public void setDelbtn(String delbtn) {
        this.delbtn = delbtn;
    }
}
