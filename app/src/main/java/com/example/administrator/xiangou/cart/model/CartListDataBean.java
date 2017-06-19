package com.example.administrator.xiangou.cart.model;

import java.util.List;

/**
 * @author zhouzongyao
 * @Description 购物车页面数据
 * @email 18482195579@163.com
 * @Date 2017-06-19 11:30
 */
public class CartListDataBean {

    /**
     * data : {"cart_list":[{"cart_id":16,"original_img":"/public/upload/goods/2017/04-13/p9ymhk8d2wnsReL7CTbFnD5Eq.png","name":"闲购小铺","goods_name":"测试商品规格的","goods_price":"20.00","spec_key":"1_4","goods_id":156,"spec_key_name":"尺码:s 颜色:宝蓝色","market_price":"20.00","goods_num":1,"cat_id":5,"prom_type":1,"prom_id":1,"did":1,"is_on_sale":1,"store_count":10},{"cart_id":15,"original_img":"/public/upload/goods/2016/01-21/56a08e3362c6f.jpg","name":"xiangou","goods_name":"六福珠宝足金心形DIY刻字片吊坠黄金项链手链挂饰计价GMGTBP0056","goods_price":"279.00","spec_key":"","goods_id":97,"spec_key_name":"","market_price":"379.00","goods_num":5,"cat_id":46,"prom_type":0,"prom_id":0,"did":3,"is_on_sale":1,"store_count":1000},{"cart_id":21,"original_img":"/public/upload/goods/2016/01-13/56960d7bec88e.jpg","name":"xiangou","goods_name":"荣耀路由Pro 大户型穿墙王1200Mbps智能AC有线无线双千兆旗舰路由器（白色）","goods_price":"328.00","spec_key":"","goods_id":52,"spec_key_name":"","market_price":"428.00","goods_num":1,"cat_id":54,"prom_type":0,"prom_id":0,"did":3,"is_on_sale":1,"store_count":998},{"cart_id":13,"original_img":"/public/upload/goods/2017/04-06/tVrQbGyhKdfatsLm75MTUzTbj.jpg","name":"闲购小铺","goods_name":"男士西裤修改1","goods_price":"20.00","spec_key":"1_4","goods_id":155,"spec_key_name":"颜色:宝蓝色 尺码:s","market_price":"20.00","goods_num":1,"cat_id":5,"prom_type":0,"prom_id":0,"did":1,"is_on_sale":1,"store_count":2}],"goods_list":[{"goods_id":155,"original_img":"/public/upload/goods/2017/04-06/tVrQbGyhKdfatsLm75MTUzTbj.jpg","is_new":1,"goods_name":"男士西裤修改1","shop_price":"20.00","store_id":1,"distance":1524,"sale_num":0},{"goods_id":156,"original_img":"/public/upload/goods/2017/04-13/p9ymhk8d2wnsReL7CTbFnD5Eq.png","is_new":1,"goods_name":"测试商品规格的","shop_price":"20.00","store_id":1,"distance":1524,"sale_num":0},{"goods_id":52,"original_img":"/public/upload/goods/2016/01-13/56960d7bec88e.jpg","is_new":0,"goods_name":"荣耀路由Pro 大户型穿墙王1200Mbps智能AC有线无线双千兆旗舰路由器（白色）","shop_price":"328.00","store_id":3,"distance":3,"sale_num":1},{"goods_id":74,"original_img":"/public/upload/goods/2016/01-16/5699f2fa7e4bf.jpg","is_new":0,"goods_name":"乐享 景德镇陶瓷器56头骨瓷餐具套装 微波韩式碗具/盘碟送礼 空谷幽兰","shop_price":"169.00","store_id":2,"distance":9,"sale_num":0},{"goods_id":97,"original_img":"/public/upload/goods/2016/01-21/56a08e3362c6f.jpg","is_new":0,"goods_name":"六福珠宝足金心形DIY刻字片吊坠黄金项链手链挂饰计价GMGTBP0056","shop_price":"279.00","store_id":3,"distance":3,"sale_num":1}]}
     * state : {"code":200,"msg":"获取成功","debugMsg":"","url":"api/Cart/cartLists"}
     */

    private DataBean data;
    private StateBean state;

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
        private List<CartListBean> cart_list;
        private List<GoodsListBean> goods_list;

        public List<CartListBean> getCart_list() {
            return cart_list;
        }

        public void setCart_list(List<CartListBean> cart_list) {
            this.cart_list = cart_list;
        }

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public static class CartListBean {
            /**
             * cart_id : 16
             * original_img : /public/upload/goods/2017/04-13/p9ymhk8d2wnsReL7CTbFnD5Eq.png
             * name : 闲购小铺
             * goods_name : 测试商品规格的
             * goods_price : 20.00
             * spec_key : 1_4
             * goods_id : 156
             * spec_key_name : 尺码:s 颜色:宝蓝色
             * market_price : 20.00
             * goods_num : 1
             * cat_id : 5
             * prom_type : 1
             * prom_id : 1
             * did : 1
             * is_on_sale : 1
             * store_count : 10
             */

            private int cart_id;
            private String original_img;
            private String name;
            private String goods_name;
            private String goods_price;
            private String spec_key;
            private int goods_id;
            private String spec_key_name;
            private String market_price;
            private int goods_num;
            private int cat_id;
            private int prom_type;
            private int prom_id;
            private int did;
            private int is_on_sale;
            private int store_count;

            public int getCart_id() {
                return cart_id;
            }

            public void setCart_id(int cart_id) {
                this.cart_id = cart_id;
            }

            public String getOriginal_img() {
                return original_img;
            }

            public void setOriginal_img(String original_img) {
                this.original_img = original_img;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public String getSpec_key() {
                return spec_key;
            }

            public void setSpec_key(String spec_key) {
                this.spec_key = spec_key;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getSpec_key_name() {
                return spec_key_name;
            }

            public void setSpec_key_name(String spec_key_name) {
                this.spec_key_name = spec_key_name;
            }

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public int getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(int goods_num) {
                this.goods_num = goods_num;
            }

            public int getCat_id() {
                return cat_id;
            }

            public void setCat_id(int cat_id) {
                this.cat_id = cat_id;
            }

            public int getProm_type() {
                return prom_type;
            }

            public void setProm_type(int prom_type) {
                this.prom_type = prom_type;
            }

            public int getProm_id() {
                return prom_id;
            }

            public void setProm_id(int prom_id) {
                this.prom_id = prom_id;
            }

            public int getDid() {
                return did;
            }

            public void setDid(int did) {
                this.did = did;
            }

            public int getIs_on_sale() {
                return is_on_sale;
            }

            public void setIs_on_sale(int is_on_sale) {
                this.is_on_sale = is_on_sale;
            }

            public int getStore_count() {
                return store_count;
            }

            public void setStore_count(int store_count) {
                this.store_count = store_count;
            }
        }

        public static class GoodsListBean {
            /**
             * goods_id : 155
             * original_img : /public/upload/goods/2017/04-06/tVrQbGyhKdfatsLm75MTUzTbj.jpg
             * is_new : 1
             * goods_name : 男士西裤修改1
             * shop_price : 20.00
             * store_id : 1
             * distance : 1524
             * sale_num : 0
             */

            private int goods_id;
            private String original_img;
            private int is_new;
            private String goods_name;
            private String shop_price;
            private int store_id;
            private int distance;
            private int sale_num;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
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

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }

            public int getSale_num() {
                return sale_num;
            }

            public void setSale_num(int sale_num) {
                this.sale_num = sale_num;
            }
        }
    }

    public static class StateBean {
        /**
         * code : 200
         * msg : 获取成功
         * debugMsg :
         * url : api/Cart/cartLists
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
