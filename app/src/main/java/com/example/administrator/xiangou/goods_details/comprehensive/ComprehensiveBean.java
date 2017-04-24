package com.example.administrator.xiangou.goods_details.comprehensive;

import java.util.List;

/**
 * Created by Administrator on 2017/4/18.
 */

public class ComprehensiveBean {
    private int Img;
    private String description;
    private double price;
    private long location;
    private List<String> items;

    public ComprehensiveBean(int img, String description, double price, long location, List<String> items) {
        Img = img;
        this.description = description;
        this.price = price;
        this.location = location;
        this.items = items;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getLocation() {
        return location;
    }

    public void setLocation(long location) {
        this.location = location;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ComprehensiveBean{" +
                "Img=" + Img +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", location=" + location +
                ", items=" + items +
                '}';
    }
}
