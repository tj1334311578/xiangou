package com.example.administrator.xiangou.nearby.nearbypreferential.model;

/**
 * Created by Administrator on 2017/3/13.
 */

public class PreferBean3 {
    @Override
    public String toString() {
        return "morebean{" +
                "more_img=" + more_img +
                ", more_description='" + more_description + '\'' +
                '}';
    }

    private int more_img;

    public PreferBean3(int more_img, String more_description) {
        this.more_img = more_img;
        this.more_description = more_description;
    }

    private String more_description;

    public int getMore_img() {
        return more_img;
    }

    public void setMore_img(int more_img) {
        this.more_img = more_img;
    }

    public String getMore_description() {
        return more_description;
    }

    public void setMore_description(String more_description) {
        this.more_description = more_description;
    }
}
