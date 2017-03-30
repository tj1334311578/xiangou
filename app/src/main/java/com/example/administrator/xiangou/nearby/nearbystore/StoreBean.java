package com.example.administrator.xiangou.nearby.nearbystore;

/**
 * Created by zhouzongyao on 2017/3/14.
 */

public class StoreBean {
    private int iconId;
    private String nameStore;
    private int distance;
    private int img1Url;
    private int img2Url;
    private int img3Url;
    private String discribe;
    private int lookCount;
    private int likeCount;
    private int attentionCount;

    public StoreBean(int iconId, String nameStore, int distance, int img1Url, int img2Url, int img3Url, String discribe, int lookCount, int likeCount, int attentionCount) {
        this.iconId = iconId;
        this.nameStore = nameStore;
        this.distance = distance;
        this.img1Url = img1Url;
        this.img2Url = img2Url;
        this.img3Url = img3Url;
        this.discribe = discribe;
        this.lookCount = lookCount;
        this.likeCount = likeCount;
        this.attentionCount = attentionCount;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getImg1Url() {
        return img1Url;
    }

    public void setImg1Url(int img1Url) {
        this.img1Url = img1Url;
    }

    public int getImg2Url() {
        return img2Url;
    }

    public void setImg2Url(int img2Url) {
        this.img2Url = img2Url;
    }

    public int getImg3Url() {
        return img3Url;
    }

    public void setImg3Url(int img3Url) {
        this.img3Url = img3Url;
    }

    public String getDiscribe() {
        return discribe;
    }

    public void setDiscribe(String discribe) {
        this.discribe = discribe;
    }

    public int getLookCount() {
        return lookCount;
    }

    public void setLookCount(int lookCount) {
        this.lookCount = lookCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getAttentionCount() {
        return attentionCount;
    }

    public void setAttentionCount(int attentionCount) {
        this.attentionCount = attentionCount;
    }
}
