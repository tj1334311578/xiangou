package com.example.administrator.xiangou.goods_sort.storehome.storehome.homestore;

/**
 * Created by Administrator on 2017/4/19.
 */
public class Treasure {
    private String goods_description;
    private int img;
    private int beBuyCount;
    private double price;
    private boolean isNewGoods;
    private double oldprice;

    public Treasure(String goods_description, int img, int beBuyCount, double price, double oldprice ,boolean isNewGoods) {
        this.goods_description = goods_description;
        this.img = img;
        this.beBuyCount = beBuyCount;
        this.price = price;
        this.isNewGoods = isNewGoods;
        this.oldprice=oldprice;
    }

    public double getOldprice() {
        return oldprice;
    }

    public void setOldprice(double oldprice) {
        this.oldprice = oldprice;
    }

    public String getGoods_description() {
        return goods_description;
    }

    public void setGoods_description(String goods_description) {
        this.goods_description = goods_description;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getBeBuyCount() {
        return beBuyCount;
    }

    public void setBeBuyCount(int beBuyCount) {
        this.beBuyCount = beBuyCount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isNewGoods() {
        return isNewGoods;
    }

    public void setNewGoods(boolean newGoods) {
        isNewGoods = newGoods;
    }

    @Override
    public String toString() {
        return "Treasure{" +
                "goods_description='" + goods_description + '\'' +
                ", img=" + img +
                ", beBuyCount=" + beBuyCount +
                ", price=" + price +
                ", isNewGoods=" + isNewGoods +
                ", oldprice=" + oldprice +
                '}';
    }
}
