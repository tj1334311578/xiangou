package com.example.administrator.xiangou.home;

/**
 * Created by zhouzongyao on 2017/3/4.
 */

public class BannerBean {
    private String title;
    private String imgUrl;

    public BannerBean(String title, String imgUrl) {
        this.title = title;
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
