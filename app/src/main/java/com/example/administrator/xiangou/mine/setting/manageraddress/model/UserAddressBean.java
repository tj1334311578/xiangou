package com.example.administrator.xiangou.mine.setting.manageraddress.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhouzongyao
 * @Description 用户收货地址
 * @email 18482195579@163.com
 * @Date 2017-05-22 15:59
 */
public class UserAddressBean implements Serializable{

    @Override
    public String toString() {
        return "UserAddressBean{" +
                "state=" + state +
                ", data=" + data +
                '}';
    }

    /**
     * data : [{"address_id":859,"user_id":1,"consignee":"11125565","email":"","country":0,"province":"天津市","city":"市辖区","district":"和平区","twon":0,"address":"他他他他","zipcode":"","mobile":"55555555555","is_default":1,"is_pickup":0,"map_x":"39.118327","map_y":"117.195907","province_id":338,"city_id":339,"district_id":340},{"address_id":857,"user_id":1,"consignee":"fds","email":"","country":0,"province":"北京市","city":"市辖区","district":"东城区","twon":0,"address":"Fdsafds","zipcode":"","mobile":"43214231432","is_default":0,"is_pickup":0,"map_x":"39.917544","map_y":"116.418757","province_id":1,"city_id":2,"district_id":3},{"address_id":844,"user_id":1,"consignee":"xia欧晓","email":"","country":0,"province":"浙江省","city":"绍兴市","district":"诸暨市","twon":13491,"address":"登记卡萨龙岗街道斯科拉和；飞快的老师；按键了；","zipcode":"4141254321","mobile":"15368590233","is_default":0,"is_pickup":0,"map_x":0,"map_y":0,"province_id":12596,"city_id":13437,"district_id":13490}]
     * state : {"code":200,"msg":"获取成功","debugMsg":"","url":"api/User/address"}
     */


    private StateBean state;
    private List<DataBean> data;

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class StateBean {
        @Override
        public String toString() {
            return "StateBean{" +
                    "code=" + code +
                    ", msg='" + msg + '\'' +
                    ", debugMsg='" + debugMsg + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }

        /**
         * code : 200
         * msg : 获取成功
         * debugMsg :
         * url : api/User/address
         */


        private int code;
        private String msg;
        private String debugMsg;
        private String url;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getDebugMsg() {
            return debugMsg;
        }

        public void setDebugMsg(String debugMsg) {
            this.debugMsg = debugMsg;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class DataBean implements Serializable{
        @Override
        public String toString() {
            return  address_id +
                    "," + user_id +
                    "," + consignee +
                    "," + email +
                    "," + country +
                    "," + province +
                    "," + city +
                    "," + district +
                    "," + twon +
                    "," + address +
                    "," + zipcode +
                    "," + mobile +
                    "," + is_default +
                    "," + is_pickup +
                    "," + map_x +
                    "," + map_y +
                    "," + province_id +
                    "," + city_id +
                    "," + district_id ;
        }

        public DataBean() {
        }
        public DataBean(int address_id, int user_id, String consignee, String email, int country, String province, String city, String district, int twon, String address, String zipcode, String mobile, int is_default, int is_pickup, String map_x, String map_y, int province_id, int city_id, int district_id) {
            this.address_id = address_id;
            this.user_id = user_id;
            this.consignee = consignee;
            this.email = email;
            this.country = country;
            this.province = province;
            this.city = city;
            this.district = district;
            this.twon = twon;
            this.address = address;
            this.zipcode = zipcode;
            this.mobile = mobile;
            this.is_default = is_default;
            this.is_pickup = is_pickup;
            this.map_x = map_x;
            this.map_y = map_y;
            this.province_id = province_id;
            this.city_id = city_id;
            this.district_id = district_id;
        }

        /**
         * address_id : 859
         * user_id : 1
         * consignee : 11125565
         * email :
         * country : 0
         * province : 天津市
         * city : 市辖区
         * district : 和平区
         * twon : 0
         * address : 他他他他
         * zipcode :
         * mobile : 55555555555
         * is_default : 1
         * is_pickup : 0
         * map_x : 39.118327
         * map_y : 117.195907
         * province_id : 338
         * city_id : 339
         * district_id : 340
         */


        private int address_id;
        private int user_id;
        private String consignee;
        private String email;
        private int country;
        private String province;
        private String city;
        private String district;
        private int twon;
        private String address;
        private String zipcode;
        private String mobile;
        private int is_default;
        private int is_pickup;
        private String map_x;
        private String map_y;
        private int province_id;
        private int city_id;
        private int district_id;

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
            this.address_id = address_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getCountry() {
            return country;
        }

        public void setCountry(int country) {
            this.country = country;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public int getTwon() {
            return twon;
        }

        public void setTwon(int twon) {
            this.twon = twon;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getIs_default() {
            return is_default;
        }

        public void setIs_default(int is_default) {
            this.is_default = is_default;
        }

        public int getIs_pickup() {
            return is_pickup;
        }

        public void setIs_pickup(int is_pickup) {
            this.is_pickup = is_pickup;
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

        public int getProvince_id() {
            return province_id;
        }

        public void setProvince_id(int province_id) {
            this.province_id = province_id;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public int getDistrict_id() {
            return district_id;
        }

        public void setDistrict_id(int district_id) {
            this.district_id = district_id;
        }
    }
}
