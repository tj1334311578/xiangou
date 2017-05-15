package com.example.administrator.xiangou.mine.couponpage;

/**
 * Created by Administrator on 2017/5/11.
 */

public class CouponBean {
    private int couponStyle,condition;
    private boolean isuse;
    private double discount;
    private String effectiveDate;

    public boolean isuse() {
        return isuse;
    }

    public void setIsuse(boolean isuse) {
        this.isuse = isuse;
    }

    public CouponBean(int couponStyle, int condition, double discount, String effectiveDate, String storeName,boolean isuse) {
        this.couponStyle = couponStyle;
        this.condition = condition;
        this.discount = discount;
        this.effectiveDate = effectiveDate;
        StoreName = storeName;
        this.isuse=isuse;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    private String StoreName;

    public CouponBean(int couponStyle, double discount, int condition, String effectiveDate,boolean isuse) {
        this.couponStyle = couponStyle;
        this.discount = discount;
        this.condition = condition;
        this.effectiveDate = effectiveDate;
        this.isuse=isuse;
    }

    public int getCouponStyle() {
        return couponStyle;
    }

    public void setCouponStyle(int couponStyle) {
        this.couponStyle = couponStyle;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Override
    public String toString() {
        return "CouponBean{" +
                "couponStyle=" + couponStyle +
                ", condition=" + condition +
                ", isuse=" + isuse +
                ", discount=" + discount +
                ", effectiveDate='" + effectiveDate + '\'' +
                ", StoreName='" + StoreName + '\'' +
                '}';
    }
}
