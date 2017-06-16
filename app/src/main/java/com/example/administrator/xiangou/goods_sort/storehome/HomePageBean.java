package com.example.administrator.xiangou.goods_sort.storehome;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */

public class HomePageBean implements Serializable{

    /**
     * data : {"logo":"/public/upload/logo/2017/05-04/Fs94ebVULYPTsGChvFsxRtqtB.png","name":"闲购小铺","total_commets":5,"total_time":1,"score":"5.0","follow":0,"total_sale":1,"goods_list":[{"goods_id":1,"market_price":"6107.00","shop_price":"6007.00","original_img":"/public/upload/goods/2016/03-09/56e01a4088d3b.jpg","is_new":1,"goods_name":"Apple iPhone","sales_sum":4},{"goods_id":46,"market_price":"1099.00","shop_price":"999.00","original_img":"/public/upload/goods/2016/01-13/5695ef4114f2b.jpg","is_new":0,"goods_name":"【北京移动老用户专享 话","sales_sum":1},{"goods_id":155,"market_price":"20.00","shop_price":"20.00","original_img":"/public/upload/goods/2017/04-06/tVrQbGyhKdfatsLm75MTUzTbj.jpg","is_new":1,"goods_name":"男士西裤修改1","sales_sum":0},{"goods_id":156,"market_price":"20.00","shop_price":"20.00","original_img":"/public/upload/goods/2017/04-13/p9ymhk8d2wnsReL7CTbFnD5Eq.png","is_new":1,"goods_name":"测试商品规格的","sales_sum":0},{"goods_id":157,"market_price":"20.00","shop_price":"20.00","original_img":"/public/upload/goods/2017/04-12/R6hjhyxUK2dd8HtPX7VTr2Ugf.jpg","is_new":1,"goods_name":"测试商品规格的","sales_sum":0}],"is_follow":0,"coupon":[{"id":2,"name":"订单满100优惠券","type":3,"money":"20.00","condition":"100.00","createnum":11,"send_num":1,"use_num":1,"send_start_time":1449763200,"send_end_time":1449763200,"use_start_time":1495269830,"use_end_time":1495269837,"add_time":1489999813,"store_id":1,"is_del":0,"is_get":0},{"id":27,"name":"店铺发放","type":3,"money":"10.00","condition":"99.00","createnum":36,"send_num":1,"use_num":0,"send_start_time":null,"send_end_time":null,"use_start_time":1493261900,"use_end_time":1495261900,"add_time":1493285587,"store_id":1,"is_del":0,"is_get":0}]}
     * state : {"code":200,"msg":"获取成功","debugMsg":"","url":"api/User/store_index"}
     */

    private DataBean data;
    private StateBean state;

    public DataBean getData() {
        return data;
    }

    @Override
    public String toString() {
        return "HomePageBean{" +
                "data=" + data +
                ", state=" + state +
                '}';
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

    public static class DataBean implements Serializable {
        @Override
        public String toString() {
            return "DataBean{" +
                    "banner='" + banner + '\'' +
                    ", logo='" + logo + '\'' +
                    ", name='" + name + '\'' +
                    ", total_commets=" + total_commets +
                    ", total_time=" + total_time +
                    ", score='" + score + '\'' +
                    ", follow=" + follow +
                    ", total_sale=" + total_sale +
                    ", is_follow=" + is_follow +
                    ", goods_list=" + goods_list +
                    ", coupon=" + coupon +
                    '}';
        }

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }

        /**
         * logo : /public/upload/logo/2017/05-04/Fs94ebVULYPTsGChvFsxRtqtB.png
         * name : 闲购小铺
         * total_commets : 5
         * total_time : 1
         * score : 5.0
         * follow : 0
         * banner : "\/public\/images\/banner.png",
         * total_sale : 1
         * goods_list : [{"goods_id":1,"market_price":"6107.00","shop_price":"6007.00","original_img":"/public/upload/goods/2016/03-09/56e01a4088d3b.jpg","is_new":1,"goods_name":"Apple iPhone","sales_sum":4},{"goods_id":46,"market_price":"1099.00","shop_price":"999.00","original_img":"/public/upload/goods/2016/01-13/5695ef4114f2b.jpg","is_new":0,"goods_name":"【北京移动老用户专享 话","sales_sum":1},{"goods_id":155,"market_price":"20.00","shop_price":"20.00","original_img":"/public/upload/goods/2017/04-06/tVrQbGyhKdfatsLm75MTUzTbj.jpg","is_new":1,"goods_name":"男士西裤修改1","sales_sum":0},{"goods_id":156,"market_price":"20.00","shop_price":"20.00","original_img":"/public/upload/goods/2017/04-13/p9ymhk8d2wnsReL7CTbFnD5Eq.png","is_new":1,"goods_name":"测试商品规格的","sales_sum":0},{"goods_id":157,"market_price":"20.00","shop_price":"20.00","original_img":"/public/upload/goods/2017/04-12/R6hjhyxUK2dd8HtPX7VTr2Ugf.jpg","is_new":1,"goods_name":"测试商品规格的","sales_sum":0}]
         * is_follow : 0
         * coupon : [{"id":2,"name":"订单满100优惠券","type":3,"money":"20.00","condition":"100.00","createnum":11,"send_num":1,"use_num":1,"send_start_time":1449763200,"send_end_time":1449763200,"use_start_time":1495269830,"use_end_time":1495269837,"add_time":1489999813,"store_id":1,"is_del":0,"is_get":0},{"id":27,"name":"店铺发放","type":3,"money":"10.00","condition":"99.00","createnum":36,"send_num":1,"use_num":0,"send_start_time":null,"send_end_time":null,"use_start_time":1493261900,"use_end_time":1495261900,"add_time":1493285587,"store_id":1,"is_del":0,"is_get":0}]
         */
        private String banner;
        private String logo;
        private String name;
        private int total_commets;
        private int total_time;
        private String score;
        private int follow;
        private int total_sale;
        private int is_follow;
        private List<GoodsListBean> goods_list;
        private List<CouponBean> coupon;

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTotal_commets() {
            return total_commets;
        }

        public void setTotal_commets(int total_commets) {
            this.total_commets = total_commets;
        }

        public int getTotal_time() {
            return total_time;
        }

        public void setTotal_time(int total_time) {
            this.total_time = total_time;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public int getFollow() {
            return follow;
        }

        public void setFollow(int follow) {
            this.follow = follow;
        }

        public int getTotal_sale() {
            return total_sale;
        }

        public void setTotal_sale(int total_sale) {
            this.total_sale = total_sale;
        }

        public int getIs_follow() {
            return is_follow;
        }

        public void setIs_follow(int is_follow) {
            this.is_follow = is_follow;
        }

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public List<CouponBean> getCoupon() {
            return coupon;
        }

        public void setCoupon(List<CouponBean> coupon) {
            this.coupon = coupon;
        }

        public static class GoodsListBean implements Serializable{
            @Override
            public String toString() {
                return "GoodsListBean{" +
                        "goods_id=" + goods_id +
                        ", market_price='" + market_price + '\'' +
                        ", shop_price='" + shop_price + '\'' +
                        ", original_img='" + original_img + '\'' +
                        ", is_new=" + is_new +
                        ", goods_name='" + goods_name + '\'' +
                        ", sales_sum=" + sales_sum +
                        '}';
            }

            /**
             * goods_id : 1
             * market_price : 6107.00
             * shop_price : 6007.00
             * original_img : /public/upload/goods/2016/03-09/56e01a4088d3b.jpg
             * is_new : 1
             * goods_name : Apple iPhone
             * sales_sum : 4
             */

            private int goods_id;
            private String market_price;
            private String shop_price;
            private String original_img;
            private int is_new;
            private String goods_name;
            private int sales_sum;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
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

            public int getSales_sum() {
                return sales_sum;
            }

            public void setSales_sum(int sales_sum) {
                this.sales_sum = sales_sum;
            }
        }

        public static class CouponBean  implements Serializable{
            public CouponBean(){

            }
            public CouponBean(String money, String condition) {
                this.money = money;
                this.condition = condition;
            }

            @Override
            public String toString() {
                return "CouponBean{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", type=" + type +
                        ", money='" + money + '\'' +
                        ", condition='" + condition + '\'' +
                        ", createnum=" + createnum +
                        ", send_num=" + send_num +
                        ", use_num=" + use_num +
                        ", send_start_time=" + send_start_time +
                        ", send_end_time=" + send_end_time +
                        ", use_start_time=" + use_start_time +
                        ", use_end_time=" + use_end_time +
                        ", add_time=" + add_time +
                        ", store_id=" + store_id +
                        ", is_del=" + is_del +
                        ", is_get=" + is_get +
                        '}';
            }

            /**
             * id : 2
             * name : 订单满100优惠券
             * type : 3
             * money : 20.00
             * condition : 100.00
             * createnum : 11
             * send_num : 1
             * use_num : 1
             * send_start_time : 1449763200
             * send_end_time : 1449763200
             * use_start_time : 1495269830
             * use_end_time : 1495269837
             * add_time : 1489999813
             * store_id : 1
             * is_del : 0
             * is_get : 0
             */

            private int id;
            private String name;
            private int type;
            private String money;
            private String condition;
            private int createnum;
            private int send_num;
            private int use_num;
            private int send_start_time;
            private int send_end_time;
            private int use_start_time;
            private int use_end_time;
            private int add_time;
            private int store_id;
            private int is_del;
            private int is_get;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getCondition() {
                return condition;
            }

            public void setCondition(String condition) {
                this.condition = condition;
            }

            public int getCreatenum() {
                return createnum;
            }

            public void setCreatenum(int createnum) {
                this.createnum = createnum;
            }

            public int getSend_num() {
                return send_num;
            }

            public void setSend_num(int send_num) {
                this.send_num = send_num;
            }

            public int getUse_num() {
                return use_num;
            }

            public void setUse_num(int use_num) {
                this.use_num = use_num;
            }

            public int getSend_start_time() {
                return send_start_time;
            }

            public void setSend_start_time(int send_start_time) {
                this.send_start_time = send_start_time;
            }

            public int getSend_end_time() {
                return send_end_time;
            }

            public void setSend_end_time(int send_end_time) {
                this.send_end_time = send_end_time;
            }

            public int getUse_start_time() {
                return use_start_time;
            }

            public void setUse_start_time(int use_start_time) {
                this.use_start_time = use_start_time;
            }

            public int getUse_end_time() {
                return use_end_time;
            }

            public void setUse_end_time(int use_end_time) {
                this.use_end_time = use_end_time;
            }

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public int getIs_del() {
                return is_del;
            }

            public void setIs_del(int is_del) {
                this.is_del = is_del;
            }

            public int getIs_get() {
                return is_get;
            }

            public void setIs_get(int is_get) {
                this.is_get = is_get;
            }
        }
    }

    public static class StateBean implements Serializable{
        /**
         * code : 200
         * msg : 获取成功
         * debugMsg :
         * url : api/User/store_index
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
