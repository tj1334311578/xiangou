package com.example.administrator.xiangou.mine.setting.manageraddress;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/24.
 */

public class AddressBean implements Serializable{

    private String consignee;
    private String mobile;
    private int province;
    private int city;
    private int district;
    private String address;
    private int user_id;
    private int address_id;
    private String map_x;
    private String map_y;

    public String getMap_y() {
        return map_y;
    }

    public void setMap_y(String map_y) {
        this.map_y = map_y;
    }

    public String getMap_x() {
        return map_x;
    }

    public void setMap_x(String map_x) {
        this.map_x = map_x;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public AddressBean(String consignee, String mobile, int province, int city, int district, String address, int user_id, int address_id, String map_x, String map_y) {
        this.consignee = consignee;
        this.mobile = mobile;
        this.province = province;
        this.city = city;
        this.district = district;
        this.address = address;
        this.user_id = user_id;
        this.address_id = address_id;
        this.map_x = map_x;
        this.map_y = map_y;
    }

    @Override
    public String toString() {
        return "AddressBean{" +
                "consignee='" + consignee + '\'' +
                ", mobile='" + mobile + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", address='" + address + '\'' +
                ", user_id=" + user_id +
                ", address_id=" + address_id +
                ", map_x='" + map_x + '\'' +
                ", map_y='" + map_y + '\'' +
                '}';
    }
}
