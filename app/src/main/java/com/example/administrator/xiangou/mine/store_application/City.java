package com.example.administrator.xiangou.mine.store_application;

import java.util.List;

/**
 * Created by Administrator on 2017/4/7.
 */

public class City {
    private int ID,PID,ZipCode;
    private String CityName;
    private List<District> districtsList;
    public List<District> getDistrictsList() {
        return districtsList;
    }

    public void setDistrictsList(List<District> districtsList) {
        this.districtsList = districtsList;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public int getZipCode() {
        return ZipCode;
    }

    public void setZipCode(int zipCode) {
        ZipCode = zipCode;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    @Override
    public String toString() {
        return "City{" +
                "ID=" + ID +
                ", PID=" + PID +
                ", ZipCode=" + ZipCode +
                ", CityName='" + CityName + '\'' +
                ", districtsList=" + districtsList +
                '}';
    }
}
