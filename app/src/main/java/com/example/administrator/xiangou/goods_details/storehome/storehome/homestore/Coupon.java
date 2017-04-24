package com.example.administrator.xiangou.goods_details.storehome.storehome.homestore;

/**
 * Created by Administrator on 2017/4/19.
 */
public class Coupon {
    private  int value;

    private String description;

    public void setDescription(String description) {
        this.description = description;
    }

    public Coupon(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "value=" + value +
                ", description='" + description + '\'' +
                '}';
    }
}
