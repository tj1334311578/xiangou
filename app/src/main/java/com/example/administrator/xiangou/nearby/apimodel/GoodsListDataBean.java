package com.example.administrator.xiangou.nearby.apimodel;

import java.util.List;

/**
 * @author zhouzongyao
 * @Description 商品列表接口实体类
 * @email 18482195579@163.com
 * @Date 2017-04-24 17:30
 */
public class GoodsListDataBean {
    /**
     * data : [{"goods_id":118,"goods_name":"针织衫","original_img":"/public/upload/goods/2016/04-21/571835c77b583.jpg","sign":[],"is_new":0,"shop_price":"59.00","distance":52},{"goods_id":119,"goods_name":"新品针织衫","original_img":"/public/upload/goods/2016/04-21/5718365665d97.jpg","sign":[],"is_new":0,"shop_price":"69.00","distance":52},{"goods_id":121,"goods_name":"柠檬女装新款田园风半截","original_img":"/public/upload/goods/2016/04-21/571837b30942a.jpg","sign":[],"is_new":0,"shop_price":"69.90","distance":15},{"goods_id":122,"goods_name":"春季格子衬衣","original_img":"/public/upload/goods/2016/04-21/5718384936f8a.jpg","sign":[],"is_new":0,"shop_price":"69.90","distance":15},{"goods_id":120,"goods_name":"only新款时尚连衣裙","original_img":"/public/upload/goods/2016/04-21/571836e30aaba.jpg","sign":[],"is_new":0,"shop_price":"129.00","distance":9},{"goods_id":59,"goods_name":"酷开(coocaa) ","original_img":"/public/upload/goods/2016/01-14/569749040dcd0.jpg","sign":[],"is_new":0,"shop_price":"2499.00","distance":9},{"goods_id":123,"goods_name":"纯白衬衣","original_img":"/public/upload/goods/2016/04-21/57186b2475e75.jpg","sign":[],"is_new":0,"shop_price":"3997.00","distance":15},{"goods_id":130,"goods_name":"女士修身牛仔裤","original_img":"/public/upload/goods/2016/04-21/57187e635d509.jpg","sign":[],"is_new":0,"shop_price":"6340.00","distance":15}]
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
        /**
         * goods_id : 118
         * goods_name : 针织衫
         * original_img : /public/upload/goods/2016/04-21/571835c77b583.jpg
         * sign : []
         * is_new : 0
         * shop_price : 59.00
         * distance : 52
         */

        private int goods_id;
        private String goods_name;
        private String original_img;
        private int is_new;
        private String shop_price;
        private int distance;
        private List<?> sign;

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

        public List<?> getSign() {
            return sign;
        }

        public void setSign(List<?> sign) {
            this.sign = sign;
        }
    }
}
