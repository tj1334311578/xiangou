package com.example.administrator.xiangou.nearby.frag;

/**
 * Created by Administrator on 2017/3/13.
 */

public class PreferBean2 {
    private int hour_hour;
    private int hour_min;
    private int hour_sec;
    private int hour_img;
    private String hour_next;
    private String hour_description1;
    private String getHour_description2;
    private double hour_price1;
    private double hour_price2;

    public PreferBean2(int hour_hour, int hour_min, int hour_sec, int hour_img, String hour_next, String hour_description1, String getHour_description2, double hour_price1, double hour_price2) {
        this.hour_hour = hour_hour;
        this.hour_min = hour_min;
        this.hour_sec = hour_sec;
        this.hour_img = hour_img;
        this.hour_next = hour_next;
        this.hour_description1 = hour_description1;
        this.getHour_description2 = getHour_description2;
        this.hour_price1 = hour_price1;
        this.hour_price2 = hour_price2;
    }

    public int getHour_hour() {
        return hour_hour;
    }

    public void setHour_hour(int hour_hour) {
        this.hour_hour = hour_hour;
    }

    public int getHour_min() {
        return hour_min;
    }

    public void setHour_min(int hour_min) {
        this.hour_min = hour_min;
    }

    public int getHour_sec() {
        return hour_sec;
    }

    public void setHour_sec(int hour_sec) {
        this.hour_sec = hour_sec;
    }

    public int getHour_img() {
        return hour_img;
    }

    public void setHour_img(int hour_img) {
        this.hour_img = hour_img;
    }

    public String getHour_next() {
        return hour_next;
    }

    public void setHour_next(String hour_next) {
        this.hour_next = hour_next;
    }

    public String getHour_description1() {
        return hour_description1;
    }

    public void setHour_description1(String hour_description1) {
        this.hour_description1 = hour_description1;
    }

    public String getGetHour_description2() {
        return getHour_description2;
    }

    public void setGetHour_description2(String getHour_description2) {
        this.getHour_description2 = getHour_description2;
    }

    public double getHour_price1() {
        return hour_price1;
    }

    public void setHour_price1(float hour_price1) {
        this.hour_price1 = hour_price1;
    }

    public double getHour_price2() {
        return hour_price2;
    }

    public void setHour_price2(float hour_price2) {
        this.hour_price2 = hour_price2;
    }

    @Override
    public String toString() {
        return "hourbean{" +
                "hour_hour=" + hour_hour +
                ", hour_min=" + hour_min +
                ", hour_sec=" + hour_sec +
                ", hour_img=" + hour_img +
                ", hour_next='" + hour_next + '\'' +
                ", hour_description1='" + hour_description1 + '\'' +
                ", getHour_description2='" + getHour_description2 + '\'' +
                ", hour_price1=" + hour_price1 +
                ", hour_price2=" + hour_price2 +
                '}';
    }
}
