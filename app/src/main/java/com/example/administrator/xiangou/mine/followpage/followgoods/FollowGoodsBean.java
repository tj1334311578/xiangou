package com.example.administrator.xiangou.mine.followpage.followgoods;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */

public class FollowGoodsBean implements Serializable{

    /**
     * data : [{"goods_id":126,"goods_name":"Canon/佳能 EOS 700D套机（18-55mm)数码单反相机 苏宁易购","original_img":"/public/upload/goods/2016/04-21/57187bd5c0178.jpg","shop_price":"3499.00","market_price":"3599.00","is_coupon":1}]
     * state : {"code":200,"msg":"获取成功","debugMsg":"","url":"api/Collect/collect_goods"}
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
         * url : api/Collect/collect_goods
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
        /**
         * goods_id : 126
         * goods_name : Canon/佳能 EOS 700D套机（18-55mm)数码单反相机 苏宁易购
         * original_img : /public/upload/goods/2016/04-21/57187bd5c0178.jpg
         * shop_price : 3499.00
         * market_price : 3599.00
         * is_coupon : 1
         */

        private int goods_id;
        private String goods_name;
        private String original_img;
        private String shop_price;
        private String market_price;
        private int is_coupon;

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

        public String getShop_price() {
            return shop_price;
        }

        public void setShop_price(String shop_price) {
            this.shop_price = shop_price;
        }

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
        }

        public int getIs_coupon() {
            return is_coupon;
        }

        public void setIs_coupon(int is_coupon) {
            this.is_coupon = is_coupon;
        }
    }
}


