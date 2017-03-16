package com.example.administrator.xiangou.nearby.frag;

/**
 * Created by Administrator on 2017/3/13.
 */

public class PreferBean1 {
    private int day_img;
    private String day_description;
    private double day_price1;
    private double day_price2;

    public PreferBean1(int day_img, String day_description, double day_price1, double day_price2) {
        this.day_img = day_img;
        this.day_description = day_description;
        this.day_price1 = day_price1;
        this.day_price2 = day_price2;
    }

    public int getDay_img() {
        return day_img;
    }

    public void setDay_img(int day_img) {
        this.day_img = day_img;
    }

    public String getDay_description() {
        return day_description;
    }

    public void setDay_description(String day_description) {
        this.day_description = day_description;
    }

    public double getDay_price1() {
        return day_price1;
    }

    public void setDay_price1(float day_price1) {
        this.day_price1 = day_price1;
    }

    public double getDay_price2() {
        return day_price2;
    }

    public void setDay_price2(float day_price2) {
        this.day_price2 = day_price2;
    }

    @Override
    public String toString() {
        return "daybean{" +
                "day_img=" + day_img +
                ", day_description='" + day_description + '\'' +
                ", day_price1=" + day_price1 +
                ", day_price2=" + day_price2 +
                '}';
    }
}
