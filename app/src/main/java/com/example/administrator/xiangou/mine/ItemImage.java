package com.example.administrator.xiangou.mine;

/**
 * Created by Administrator on 2017/3/8.
 */
public class ItemImage {

    private int src;
    private String str;

    public ItemImage(int src, String str) {
        this.src = src;
        this.str = str;
    }

    @Override
    public String toString() {
        return "ItemImage{" +
                "src=" + src +
                ", str='" + str + '\'' +
                '}';
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
