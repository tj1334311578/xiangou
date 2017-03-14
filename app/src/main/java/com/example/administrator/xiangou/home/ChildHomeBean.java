package com.example.administrator.xiangou.home;

/**
 * Created by zhouzongyao on 2017/3/2.
 */

public class ChildHomeBean {

    private int id;
    private int imgSrc;
    private String img;
    private String title;

    public ChildHomeBean(int id, String img, String title) {
        this.id = id;
        this.img = img;
        this.title = title;
    }

    public ChildHomeBean(String img, String title) {
        this.img = img;
        this.title = title;
    }

    public ChildHomeBean(int imgSrc, String title) {
        this.imgSrc = imgSrc;
        this.title = title;
    }

    @Override
    public String toString() {
        return "ChildHomeBean{" +
                "imgSrc=" + imgSrc +
                ", title='" + title + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(int imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
