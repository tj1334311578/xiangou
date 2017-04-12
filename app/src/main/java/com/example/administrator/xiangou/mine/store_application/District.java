package com.example.administrator.xiangou.mine.store_application;

/**
 * Created by Administrator on 2017/4/7.
 */

public class District {
    private int ID,CID;
    private String DistrictName;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public String getDistrictName() {
        return DistrictName;
    }

    public void setDistrictName(String districtName) {
        DistrictName = districtName;
    }

    @Override
    public String toString() {
        return "Districts{" +
                "ID=" + ID +
                ", CID=" + CID +
                ", DistrictName='" + DistrictName + '\'' +
                '}';
    }
}
