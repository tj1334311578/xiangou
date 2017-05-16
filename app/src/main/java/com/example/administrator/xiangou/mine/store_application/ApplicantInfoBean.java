package com.example.administrator.xiangou.mine.store_application;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author zhouzongyao
 * @Description 申请人填写的店铺信息
 * @email 18482195579@163.com
 * @Date 2017-05-04 10:59
 */
public class ApplicantInfoBean implements Serializable{
    @Override
    public String toString() {
        return "ApplicantInfoBean{" +
                "realname='" + realname + '\'' +
                ", tel='" + tel + '\'' +
                ", idcard='" + idcard + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", map_x='" + map_x + '\'' +
                ", map_y='" + map_y + '\'' +
                ", user_id=" + user_id +
                ", province=" + province +
                ", city=" + city +
                ", district=" + district +
                ", cid=" + Arrays.toString(cid) +
                '}';
    }

    /**
     * realname : 杨小贤
     * tel : 18349264993
     * idcard : 51102122257894
     * name : 柠檬店升级店
     * cid : [1,2,3]
     * address : 四川省成都市高新区环球中心西广场
     * map_x : 104.234452
     * map_y : 30.123434
     * user_id : 69
     * province : 338
     * city : 569
     * district : 570
     */

    private String realname;
    private String tel;
    private String idcard;
    private String name;
    private String address;
    private String map_x;
    private String map_y;
    private int user_id;
    private int province;
    private int city;
    private int district;
    private int[] cid;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMap_x() {
        return map_x;
    }

    public void setMap_x(String map_x) {
        this.map_x = map_x;
    }

    public String getMap_y() {
        return map_y;
    }

    public void setMap_y(String map_y) {
        this.map_y = map_y;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public int[] getCid() {
        return cid;
    }

    public void setCid(int[] cid) {
        this.cid = cid;
    }
}
