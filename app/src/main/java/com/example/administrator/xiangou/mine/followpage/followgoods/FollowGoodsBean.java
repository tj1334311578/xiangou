package com.example.administrator.xiangou.mine.followpage.followgoods;

/**
 * Created by Administrator on 2017/5/9.
 */

public class FollowGoodsBean {
    private int img;
    private String description;
    private double oldprice;
    private double nowprice;
    private boolean iscoupon;

    public FollowGoodsBean(int img, String description, double oldprice, double nowprice, boolean iscoupon) {
        this.img = img;
        this.description = description;
        this.oldprice = oldprice;
        this.nowprice = nowprice;
        this.iscoupon = iscoupon;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getOldprice() {
        return oldprice;
    }

    public void setOldprice(double oldprice) {
        this.oldprice = oldprice;
    }

    public double getNowprice() {
        return nowprice;
    }

    public void setNowprice(double nowprice) {
        this.nowprice = nowprice;
    }

    public boolean iscoupon() {
        return iscoupon;
    }

    public void setIscoupon(boolean iscoupon) {
        this.iscoupon = iscoupon;
    }

    @Override
    public String toString() {
        return "FollowGoodsBean{" +
                "img=" + img +
                ", description='" + description + '\'' +
                ", oldprice=" + oldprice +
                ", nowprice=" + nowprice +
                ", iscoupon=" + iscoupon +
                '}';
    }
}


