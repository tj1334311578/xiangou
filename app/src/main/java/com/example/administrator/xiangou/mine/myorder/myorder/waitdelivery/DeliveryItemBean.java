package com.example.administrator.xiangou.mine.myorder.myorder.waitdelivery;


/**
 * Created by Administrator on 2017/4/26.
 */
public class DeliveryItemBean {
    private int img;
    private String description;
    private String Size;
    private String color;
    private double oldprice,price;
    private int acounts;

    public DeliveryItemBean(int img, String description, String size, String color, double oldprice, double price,int acounts) {
        this.img = img;
        this.description = description;
        Size = size;
        this.color = color;
        this.oldprice = oldprice;
        this.price = price;
        this.acounts=acounts;
    }

    public int getAcounts() {
        return acounts;
    }

    public void setAcounts(int acounts) {
        this.acounts = acounts;
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

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getOldprice() {
        return oldprice;
    }

    public void setOldprice(double oldprice) {
        this.oldprice = oldprice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "DeliveryItemBean{" +
                "img=" + img +
                ", description='" + description + '\'' +
                ", Size='" + Size + '\'' +
                ", color='" + color + '\'' +
                ", oldprice=" + oldprice +
                ", price=" + price +
                ", acounts=" + acounts +
                '}';
    }
}
