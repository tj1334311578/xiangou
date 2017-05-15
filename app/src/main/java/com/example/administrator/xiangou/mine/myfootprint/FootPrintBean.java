package com.example.administrator.xiangou.mine.myfootprint;


/**
 * Created by Administrator on 2017/5/11.
 */

public class FootPrintBean {
    private int Img;
    private String description;
    private int nowprice;
    private  int oldprice;

    public FootPrintBean(int img, String description, int nowprice, int oldprice) {
        Img = img;
        this.description = description;
        this.nowprice = nowprice;
        this.oldprice = oldprice;
    }

    public int getImg() {
        return Img;
    }

    public void setImg(int img) {
        Img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNowprice() {
        return nowprice;
    }

    public void setNowprice(int nowprice) {
        this.nowprice = nowprice;
    }

    public int getOldprice() {
        return oldprice;
    }

    public void setOldprice(int oldprice) {
        this.oldprice = oldprice;
    }

    @Override
    public String toString() {
        return "FootPrintBean{" +
                "Img=" + Img +
                ", description='" + description + '\'' +
                ", nowprice=" + nowprice +
                ", oldprice=" + oldprice +
                '}';
    }
}
