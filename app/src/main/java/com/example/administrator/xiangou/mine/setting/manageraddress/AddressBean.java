package com.example.administrator.xiangou.mine.setting.manageraddress;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/24.
 */

public class AddressBean implements Serializable{
    private String name,number,address;
    public boolean isDefaultAddress() {
        return isDefaultAddress;
    }

    public void setDefaultAddress(boolean defaultAddress) {
        isDefaultAddress = defaultAddress;
    }

    private boolean isDefaultAddress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AddressBean(String name, String number, String address) {
        this.name = name;
        this.number = number;
        this.address = address;
    }

    @Override
    public String toString() {
        return "AddressBean{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", address='" + address + '\'' +
                ", isDefaultAddress=" + isDefaultAddress +
                '}';
    }
}
