package com.example.administrator.xiangou.mine.mystore.storemanager;

public class StoreManagerInfoBean {
    /**
     * data : {"did":1,"name":"闲购小铺","address":"\"四川省成都市青羊区府南街道马勒戈壁\"","synopsis":"\"在山的那边海的那边有一群草泥马\\n他们活泼又聪明\"","telephone":"\"18482195579\"","cid":"4","logo":null,"map_x":"\"104.014725\"","map_y":"\"30.676117\"","province":33007,"city":33008,"district":33027,"realname":"杨小贤","cityid":1,"cate_name":"家居","province_name":"四川省","city_name":"成都市","district_name":"青羊区"}
     * state : {"code":200,"msg":"获取成功","debugMsg":"","url":"api/Stores/edit"}
     */

    private DataBean data;
    private StateBean state;

    @Override
    public String toString() {
        return "StoreManagerInfoBean{" +
                "data=" + data +
                ", state=" + state +
                '}';
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
    }


    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "did=" + did +
                    ", name=" + name +
                    ", address=" + address +
                    ", synopsis=" + synopsis +
                    ", telephone=" + telephone +
                    ", cid=" + cid +
                    ", logo=" + logo +
                    ", map_x=" + map_x +
                    ", map_y=" + map_y +
                    ", province=" + province +
                    ", city=" + city +
                    ", district=" + district +
                    ", realname='" + realname + '\'' +
                    ", cityid=" + cityid +
                    ", cate_name=" + cate_name +
                    ", province_name=" + province_name +
                    ", city_name=" + city_name +
                    ", district_name=" + district_name +
                    '}';
        }

        /**
         * did : 1
         * name : 闲购小铺
         * address : "四川省成都市青羊区府南街道马勒戈壁"
         * synopsis : "在山的那边海的那边有一群草泥马\n他们活泼又聪明"
         * telephone : "18482195579"
         * cid : 4
         * logo : null
         * map_x : "104.014725"
         * map_y : "30.676117"
         * province : 33007
         * city : 33008
         * district : 33027
         * realname : 杨小贤
         * cityid : 1
         * cate_name : 家居
         * province_name : 四川省
         * city_name : 成都市
         * district_name : 青羊区
         */

        private int did;
        private String name;
        private String address;
        private String synopsis;
        private String telephone;
        private String cid;
        private String logo;
        private String map_x;
        private String map_y;
        private int province;
        private int city;
        private int district;
        private String realname;
        private int cityid;
        private String cate_name;
        private String province_name;
        private String city_name;
        private String district_name;

        public int getDid() {
            return did;
        }

        public void setDid(int did) {
            this.did = did;
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

        public String getSynopsis() {
            return synopsis;
        }

        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
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

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public int getCityid() {
            return cityid;
        }

        public void setCityid(int cityid) {
            this.cityid = cityid;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getDistrict_name() {
            return district_name;
        }

        public void setDistrict_name(String district_name) {
            this.district_name = district_name;
        }
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
         * url : api/Stores/edit
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
}
