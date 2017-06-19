package com.example.administrator.xiangou.goods_sort.comprehensive;

import java.util.List;

/**
 * 作者： tj on 2017/6/19.
 * <p>
 * 功能：
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */

public class SortBean {

    /**
     * data : [{"goods_id":121,"goods_name":"柠檬女装新款田园风半截","original_img":"/public/upload/goods/2016/04-21/571837b30942a.jpg","sign":[],"is_new":0,"shop_price":"69.90","distance":15},{"goods_id":120,"goods_name":"only新款时尚连衣裙","original_img":"/public/upload/goods/2016/04-21/571836e30aaba.jpg","sign":[],"is_new":0,"shop_price":"129.00","distance":9}]
     * state : {"code":200,"msg":"获取成功","debugMsg":"","url":"api/Good/goodsList"}
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
        /**
         * code : 200
         * msg : 获取成功
         * debugMsg :
         * url : api/Good/goodsList
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

    public static class DataBean {
        public DataBean(){//
        }
        public DataBean(String goods_name, String original_img, String shop_price, int distance, List<ItemBean> sign) {
            this.goods_name = goods_name;
            this.original_img = original_img;
            this.shop_price = shop_price;
            this.distance = distance;
            this.sign = sign;
        }

        /**
         * goods_id : 121
         * goods_name : 柠檬女装新款田园风半截
         * original_img : /public/upload/goods/2016/04-21/571837b30942a.jpg
         * sign : []
         * is_new : 0
         * shop_price : 69.90
         * distance : 15
         */

        private int goods_id;
        private String goods_name;
        private String original_img;
        private int is_new;
        private String shop_price;
        private int distance;
        private List<ItemBean> sign;

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getOriginal_img() {
            return original_img;
        }

        public void setOriginal_img(String original_img) {
            this.original_img = original_img;
        }

        public int getIs_new() {
            return is_new;
        }

        public void setIs_new(int is_new) {
            this.is_new = is_new;
        }

        public String getShop_price() {
            return shop_price;
        }

        public void setShop_price(String shop_price) {
            this.shop_price = shop_price;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public List<ItemBean> getSign() {
            return sign;
        }

        public void setSign(List<ItemBean> sign) {
            this.sign = sign;
        }
    }
    public static class ItemBean{
        public ItemBean(){}
        public ItemBean(String item) {
            Item = item;
        }

        private String Item;

        public String getItem() {
            return Item;
        }

        public void setItem(String item) {
            Item = item;
        }
    }
}
