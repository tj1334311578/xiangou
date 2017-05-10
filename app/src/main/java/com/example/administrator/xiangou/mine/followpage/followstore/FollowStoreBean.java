package com.example.administrator.xiangou.mine.followpage.followstore;

/**
 * Created by Administrator on 2017/5/9.
 */

public class FollowStoreBean {
    private int img;
    private String description;
    private int follows;

    public FollowStoreBean(int img, String description, int follows) {
        this.img = img;
        this.description = description;
        this.follows = follows;
    }

    @Override
    public String toString() {
        return "FollowStoreBean{" +
                "img=" + img +
                ", description='" + description + '\'' +
                ", follows=" + follows +
                '}';
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

    public int getFollows() {
        return follows;
    }

    public void setFollows(int follows) {
        this.follows = follows;
    }
}
