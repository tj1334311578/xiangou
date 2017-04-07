package com.example.administrator.xiangou.mine.store_application;

import java.util.List;

/**
 * Created by Administrator on 2017/4/7.
 */

public class Province {
    private int ID;
    private String ProvinceName;
    private List<City> cityList;

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProvinceName() {
        return ProvinceName;
    }

    public void setProvinceName(String provinceName) {
        ProvinceName = provinceName;
    }

    @Override
    public String toString() {
        return "Province{" +
                "ID=" + ID +
                ", ProvinceName='" + ProvinceName + '\'' +
                ", cityList=" + cityList +
                '}';
    }
}
