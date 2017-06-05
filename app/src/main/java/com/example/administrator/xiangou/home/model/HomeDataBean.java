package com.example.administrator.xiangou.home.model;

import java.util.List;

/**
 * @author zhouzongyao
 * @Description 首页的返回数据实体类
 * @email 18482195579@163.com
 * @Date 2017-06-05 9:57
 */
public class HomeDataBean {

    /**
     * data : {"ad_top":[{"ad_code":"/public/upload/ad/2016/09-12/57d6440cd154d.jpg"},{"ad_code":"/public/upload/ad/2016/09-12/57d645374e8ea.jpg"},{"ad_code":"/public/upload/ad/2016/09-12/57d64661cd041.jpg"}],"ads_middle":[{"ad_code":"/public/upload/ad/2016/09-12/57d6194ce4f2a.jpg"}],"toptics":[{"ad_code":"/public/upload/ad/2016/09-12/57d61e36dad3e.jpg"}],"store_list":[{"logo":"/public/upload/logo/2017/06-05/7FfNRJJrU9Xz4wxVfTpaeG2gN.png","name":"闲购小铺","map_x":"\"104.014725\"","map_y":"\"30.676117\"","did":1,"user_id":1,"status":2},{"logo":"/public/upload/logo/2017/05-24/CpFXYuLNyVJ7Tdt6fr4zumE3c.png","name":"test","map_x":"104.065502","map_y":"30.656076","did":2,"user_id":2,"status":2},{"logo":"/public/upload/logo/2017/05-24/CpFXYuLNyVJ7Tdt6fr4zumE3c.png","name":"xiangou","map_x":"104.081363","map_y":"30.595179","did":3,"user_id":5,"status":2},{"logo":"","name":"柠檬店升级店","map_x":"104.095774","map_y":"30.703138","did":435,"user_id":69,"status":2},{"logo":"/public/upload/logo/2017/05-24/CpFXYuLNyVJ7Tdt6fr4zumE3c.png","name":"柠檬店升级12","map_x":"104.234452","map_y":"30.123434","did":433,"user_id":44,"status":2}],"goods_perfect":[{"original_img":"/public/upload/goods/2016/01-13/569611334359e.jpg","name":"男单肩包","goods_id":53},{"original_img":"/public/upload/goods/2016/03-09/56e01a4088d3b.jpg","name":"手机配件","goods_id":1},{"original_img":"/public/upload/goods/2017/04-13/p9ymhk8d2wnsReL7CTbFnD5Eq.png","name":"男装","goods_id":156},{"original_img":"/public/upload/goods/2016/03-12/56e3eb73912ff.jpg","name":"手机","goods_id":104}],"goods_toptics":[{"original_img":"/public/upload/goods/2017/04-13/p9ymhk8d2wnsReL7CTbFnD5Eq.png","goods_name":"测试商品规格的","goods_id":156,"shop_price":"20.00"},{"original_img":"/public/upload/goods/2017/04-06/tVrQbGyhKdfatsLm75MTUzTbj.jpg","goods_name":"男士西裤修改1","goods_id":155,"shop_price":"20.00"},{"original_img":"/public/upload/goods/2016/04-22/5719923fb2708.jpg","goods_name":"haier海尔 BC-93TMPF 93升单门冰箱","goods_id":143,"shop_price":"0.01"},{"original_img":"/public/upload/goods/2016/04-22/57199141d9c05.jpg","goods_name":"海尔（Haier）BCD-251WDGW 251升 无霜两门冰箱（白色）","goods_id":142,"shop_price":"2699.00"}],"recommened_list":[{"goods_id":1,"original_img":"/public/upload/goods/2016/03-09/56e01a4088d3b.jpg","is_new":1,"store_id":1,"distance":11363},{"goods_id":40,"original_img":"/public/upload/goods/2016/01-13/5695bd0ba3d1d.jpg","is_new":0,"store_id":2,"distance":9},{"goods_id":42,"original_img":"/public/upload/goods/2016/01-13/5695c0873d865.png","is_new":0,"store_id":2,"distance":9},{"goods_id":44,"original_img":"/public/upload/goods/2016/01-13/5695c1e0549fc.jpg","is_new":0,"store_id":2,"distance":9},{"goods_id":46,"original_img":"/public/upload/goods/2016/01-13/5695ef4114f2b.jpg","is_new":0,"store_id":1,"distance":11363},{"goods_id":48,"original_img":"/public/upload/goods/2016/01-13/5695f985d0b39.jpg","is_new":0,"store_id":2,"distance":9},{"goods_id":50,"original_img":"/public/upload/goods/2016/01-13/5696034e703e1.jpg","is_new":0,"store_id":2,"distance":9},{"goods_id":52,"original_img":"/public/upload/goods/2016/01-13/56960d7bec88e.jpg","is_new":0,"store_id":3,"distance":3},{"goods_id":56,"original_img":"/public/upload/goods/2016/01-14/56970fc50a9f3.jpg","is_new":0,"store_id":2,"distance":9},{"goods_id":58,"original_img":"/public/upload/goods/2016/01-14/56971493d2f2d.jpg","is_new":0,"store_id":2,"distance":9},{"goods_id":60,"original_img":"/public/upload/goods/2016/01-14/5697523793096.jpg","is_new":0,"store_id":2,"distance":9},{"goods_id":62,"original_img":"/public/upload/goods/2016/01-14/569769cf6527b.jpg","is_new":0,"store_id":2,"distance":9},{"goods_id":64,"original_img":"/public/upload/goods/2016/01-15/5698553f575b6.jpg","is_new":0,"store_id":2,"distance":9},{"goods_id":66,"original_img":"/public/upload/goods/2016/01-15/56985a8e560b9.jpg","is_new":1,"store_id":2,"distance":9},{"goods_id":68,"original_img":"/public/upload/goods/2016/01-15/569864e0b0315.jpg","is_new":0,"store_id":2,"distance":9},{"goods_id":70,"original_img":"/public/upload/goods/2016/01-15/56988fead74f3.jpg","is_new":0,"store_id":2,"distance":9},{"goods_id":72,"original_img":"/public/upload/goods/2016/01-15/5698c8a65be8d.jpg","is_new":0,"store_id":2,"distance":9},{"goods_id":74,"original_img":"/public/upload/goods/2016/01-16/5699f2fa7e4bf.jpg","is_new":0,"store_id":2,"distance":9},{"goods_id":78,"original_img":"/public/upload/goods/2016/01-18/569c5d125e278.jpg","is_new":0,"store_id":2,"distance":9},{"goods_id":80,"original_img":"/public/upload/goods/2016/01-18/569c862c24b2e.jpg","is_new":0,"store_id":2,"distance":9}]}
     * state : {"code":200,"msg":"获取成功","debugMsg":"","url":"api/Index/index"}
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
        private List<AdTopBean> ad_top;
        private List<AdsMiddleBean> ads_middle;
        private List<TopticsBean> toptics;
        private List<StoreListBean> store_list;
        private List<GoodsPerfectBean> goods_perfect;
        private List<GoodsTopticsBean> goods_toptics;
        private List<RecommenedListBean> recommened_list;
        /**
         * data : {"ad_top":[{"ad_code":"/public/upload/ad/2016/09-12/57d6440cd154d.jpg"},{"ad_code":"/public/upload/ad/2016/09-12/57d645374e8ea.jpg"},{"ad_code":"/public/upload/ad/2016/09-12/57d64661cd041.jpg"}],"ads_middle":[{"ad_code":"/public/upload/ad/2016/09-12/57d6194ce4f2a.jpg"}],"toptics":[{"ad_code":"/public/upload/ad/2016/09-12/57d61e36dad3e.jpg"}],"store_list":[{"logo":"/public/upload/logo/2017/06-05/7FfNRJJrU9Xz4wxVfTpaeG2gN.png","name":"闲购小铺","map_x":"\"104.014725\"","map_y":"\"30.676117\"","did":1,"user_id":1,"status":2},{"logo":"/public/upload/logo/2017/05-24/CpFXYuLNyVJ7Tdt6fr4zumE3c.png","name":"test","map_x":"104.065502","map_y":"30.656076","did":2,"user_id":2,"status":2},{"logo":"/public/upload/logo/2017/05-24/CpFXYuLNyVJ7Tdt6fr4zumE3c.png","name":"xiangou","map_x":"104.081363","map_y":"30.595179","did":3,"user_id":5,"status":2},{"logo":"","name":"柠檬店升级店","map_x":"104.095774","map_y":"30.703138","did":435,"user_id":69,"status":2},{"logo":"/public/upload/logo/2017/05-24/CpFXYuLNyVJ7Tdt6fr4zumE3c.png","name":"柠檬店升级12","map_x":"104.234452","map_y":"30.123434","did":433,"user_id":44,"status":2}],"goods_perfect":[{"original_img":"/public/upload/goods/2016/01-13/569611334359e.jpg","name":"男单肩包","goods_id":53},{"original_img":"/public/upload/goods/2016/03-09/56e01a4088d3b.jpg","name":"手机配件","goods_id":1},{"original_img":"/public/upload/goods/2017/04-13/p9ymhk8d2wnsReL7CTbFnD5Eq.png","name":"男装","goods_id":156},{"original_img":"/public/upload/goods/2016/03-12/56e3eb73912ff.jpg","name":"手机","goods_id":104}],"goods_toptics":[{"original_img":"/public/upload/goods/2017/04-13/p9ymhk8d2wnsReL7CTbFnD5Eq.png","goods_name":"测试商品规格的","goods_id":156,"shop_price":"20.00"},{"original_img":"/public/upload/goods/2017/04-06/tVrQbGyhKdfatsLm75MTUzTbj.jpg","goods_name":"男士西裤修改1","goods_id":155,"shop_price":"20.00"},{"original_img":"/public/upload/goods/2016/04-22/5719923fb2708.jpg","goods_name":"haier海尔 BC-93TMPF 93升单门冰箱","goods_id":143,"shop_price":"0.01"},{"original_img":"/public/upload/goods/2016/04-22/57199141d9c05.jpg","goods_name":"海尔（Haier）BCD-251WDGW 251升 无霜两门冰箱（白色）","goods_id":142,"shop_price":"2699.00"}],"recommened_list":[{"goods_id":1,"original_img":"/public/upload/goods/2016/03-09/56e01a4088d3b.jpg","is_new":1,"goods_name":"Apple iPhone 6s Plus 16G 玫瑰金 移动联通电信4G手机","shop_price":"6007.00","store_id":1,"distance":11363},{"goods_id":40,"original_img":"/public/upload/goods/2016/01-13/5695bd0ba3d1d.jpg","is_new":0,"goods_name":"荣耀X2 标准版 双卡双待双通 移动/联通双4G 16GB ROM（月光银）","shop_price":"1999.00","store_id":2,"distance":9},{"goods_id":42,"original_img":"/public/upload/goods/2016/01-13/5695c0873d865.png","is_new":0,"goods_name":"Teclast/台电 X80 Plus WIFI 32GB Win10平板电脑双系统8英寸","shop_price":"499.00","store_id":2,"distance":9},{"goods_id":44,"original_img":"/public/upload/goods/2016/01-13/5695c1e0549fc.jpg","is_new":0,"goods_name":"荣耀平板 8.0英寸平板电脑（Wi-Fi版 四核1.2GHz处理器 1GB内存 8GB存储）","shop_price":"799.00","store_id":2,"distance":9},{"goods_id":46,"original_img":"/public/upload/goods/2016/01-13/5695ef4114f2b.jpg","is_new":0,"goods_name":"【北京移动老用户专享 话费六折】荣耀畅玩5X 双卡双待 移动版 智能手机（破晓银）","shop_price":"999.00","store_id":1,"distance":11363},{"goods_id":48,"original_img":"/public/upload/goods/2016/01-13/5695f985d0b39.jpg","is_new":0,"goods_name":"荣耀7 双卡双待双通 移动4G版 16GB存储（冰河银）豪华套装一","shop_price":"2099.00","store_id":2,"distance":9},{"goods_id":50,"original_img":"/public/upload/goods/2016/01-13/5696034e703e1.jpg","is_new":0,"goods_name":"华为 HUAWEI 畅享5S 全网通 2GB RAM+16GB ROM（金色）","shop_price":"1199.00","store_id":2,"distance":9},{"goods_id":52,"original_img":"/public/upload/goods/2016/01-13/56960d7bec88e.jpg","is_new":0,"goods_name":"荣耀路由Pro 大户型穿墙王1200Mbps智能AC有线无线双千兆旗舰路由器（白色）","shop_price":"328.00","store_id":3,"distance":3},{"goods_id":56,"original_img":"/public/upload/goods/2016/01-14/56970fc50a9f3.jpg","is_new":0,"goods_name":"创维（skyworth）55M5 55英寸4K超高清网络智能液晶电视机","shop_price":"3699.00","store_id":2,"distance":9},{"goods_id":58,"original_img":"/public/upload/goods/2016/01-14/56971493d2f2d.jpg","is_new":0,"goods_name":"海信彩电LED55EC290N 55英寸 全高清 智能 网络 LED液晶电视","shop_price":"3199.00","store_id":2,"distance":9},{"goods_id":60,"original_img":"/public/upload/goods/2016/01-14/5697523793096.jpg","is_new":0,"goods_name":"创维(Skyworth) 50S9 50英寸 全高清 网络 WIFI 智能 LED液晶电视","shop_price":"2599.00","store_id":2,"distance":9},{"goods_id":62,"original_img":"/public/upload/goods/2016/01-14/569769cf6527b.jpg","is_new":0,"goods_name":"海信彩电LED55EC520UA 55英寸 14核 4K智能电视(黑色)","shop_price":"3599.00","store_id":2,"distance":9},{"goods_id":64,"original_img":"/public/upload/goods/2016/01-15/5698553f575b6.jpg","is_new":0,"goods_name":"whaley/微鲸 WTV43K1 43吋4K LED液晶平板电视 性能小钢炮","shop_price":"2098.00","store_id":2,"distance":9},{"goods_id":66,"original_img":"/public/upload/goods/2016/01-15/56985a8e560b9.jpg","is_new":1,"goods_name":"迎馨家纺全棉斜纹印花双人四件套邂逅 AB版纯棉，亲肤透气","shop_price":"110.00","store_id":2,"distance":9},{"goods_id":68,"original_img":"/public/upload/goods/2016/01-15/569864e0b0315.jpg","is_new":0,"goods_name":"东联LED吸顶灯具客厅灯现代简约长方形卧室灯餐厅灯书房灯x109三档变光变色大号80w不带遥控","shop_price":"358.00","store_id":2,"distance":9},{"goods_id":70,"original_img":"/public/upload/goods/2016/01-15/56988fead74f3.jpg","is_new":0,"goods_name":"布雷尔 皮床 双人床软体床真皮床 皮艺床软床1.8米软包双人床婚床","shop_price":"1799.00","store_id":2,"distance":9},{"goods_id":72,"original_img":"/public/upload/goods/2016/01-15/5698c8a65be8d.jpg","is_new":0,"goods_name":"天堂3311E碰强力高密拒水碰击布一甩干三折晴雨伞深藏青","shop_price":"28.90","store_id":2,"distance":9},{"goods_id":74,"original_img":"/public/upload/goods/2016/01-16/5699f2fa7e4bf.jpg","is_new":0,"goods_name":"乐享 景德镇陶瓷器56头骨瓷餐具套装 微波韩式碗具/盘碟送礼 空谷幽兰","shop_price":"169.00","store_id":2,"distance":9},{"goods_id":78,"original_img":"/public/upload/goods/2016/01-18/569c5d125e278.jpg","is_new":0,"goods_name":"皇英格男式修身圆领针织衫WG050黑色 黑色 L","shop_price":"118.00","store_id":2,"distance":9},{"goods_id":80,"original_img":"/public/upload/goods/2016/01-18/569c862c24b2e.jpg","is_new":0,"goods_name":"美丽包包","shop_price":"79.00","store_id":2,"distance":9}]}
         * state : {"code":200,"msg":"获取成功","debugMsg":"","url":"api/Index/index"}
         */

        private DataBean data;

        public List<AdTopBean> getAd_top() {
            return ad_top;
        }

        public void setAd_top(List<AdTopBean> ad_top) {
            this.ad_top = ad_top;
        }

        public List<AdsMiddleBean> getAds_middle() {
            return ads_middle;
        }

        public void setAds_middle(List<AdsMiddleBean> ads_middle) {
            this.ads_middle = ads_middle;
        }

        public List<TopticsBean> getToptics() {
            return toptics;
        }

        public void setToptics(List<TopticsBean> toptics) {
            this.toptics = toptics;
        }

        public List<StoreListBean> getStore_list() {
            return store_list;
        }

        public void setStore_list(List<StoreListBean> store_list) {
            this.store_list = store_list;
        }

        public List<GoodsPerfectBean> getGoods_perfect() {
            return goods_perfect;
        }

        public void setGoods_perfect(List<GoodsPerfectBean> goods_perfect) {
            this.goods_perfect = goods_perfect;
        }

        public List<GoodsTopticsBean> getGoods_toptics() {
            return goods_toptics;
        }

        public void setGoods_toptics(List<GoodsTopticsBean> goods_toptics) {
            this.goods_toptics = goods_toptics;
        }

        public List<RecommenedListBean> getRecommened_list() {
            return recommened_list;
        }

        public void setRecommened_list(List<RecommenedListBean> recommened_list) {
            this.recommened_list = recommened_list;
        }

        public static class AdTopBean {
            /**
             * ad_code : /public/upload/ad/2016/09-12/57d6440cd154d.jpg
             */

            private String ad_code;

            public String getAd_code() {
                return ad_code;
            }

            public void setAd_code(String ad_code) {
                this.ad_code = ad_code;
            }
        }

        public static class AdsMiddleBean {
            /**
             * ad_code : /public/upload/ad/2016/09-12/57d6194ce4f2a.jpg
             */

            private String ad_code;

            public String getAd_code() {
                return ad_code;
            }

            public void setAd_code(String ad_code) {
                this.ad_code = ad_code;
            }
        }

        public static class TopticsBean {
            /**
             * ad_code : /public/upload/ad/2016/09-12/57d61e36dad3e.jpg
             */

            private String ad_code;

            public String getAd_code() {
                return ad_code;
            }

            public void setAd_code(String ad_code) {
                this.ad_code = ad_code;
            }
        }

        public static class StoreListBean {
            /**
             * logo : /public/upload/logo/2017/06-05/7FfNRJJrU9Xz4wxVfTpaeG2gN.png
             * name : 闲购小铺
             * map_x : "104.014725"
             * map_y : "30.676117"
             * did : 1
             * user_id : 1
             * status : 2
             */

            private String logo;
            private String name;
            private String map_x;
            private String map_y;
            private int did;
            private int user_id;
            private int status;

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

            public int getDid() {
                return did;
            }

            public void setDid(int did) {
                this.did = did;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }

        public static class GoodsPerfectBean {
            /**
             * original_img : /public/upload/goods/2016/01-13/569611334359e.jpg
             * name : 男单肩包
             * goods_id : 53
             */

            private String original_img;
            private String name;
            private int goods_id;

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

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }
        }

        public static class GoodsTopticsBean {
            /**
             * original_img : /public/upload/goods/2017/04-13/p9ymhk8d2wnsReL7CTbFnD5Eq.png
             * goods_name : 测试商品规格的
             * goods_id : 156
             * shop_price : 20.00
             */

            private String original_img;
            private String goods_name;
            private int goods_id;
            private String shop_price;

            public String getOriginal_img() {
                return original_img;
            }

            public void setOriginal_img(String original_img) {
                this.original_img = original_img;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }
        }

        public static class RecommenedListBean {
                /**
                 * goods_id : 1
                 * original_img : /public/upload/goods/2016/03-09/56e01a4088d3b.jpg
                 * is_new : 1
                 * goods_name : Apple iPhone 6s Plus 16G 玫瑰金 移动联通电信4G手机
                 * shop_price : 6007.00
                 * store_id : 1
                 * distance : 11363
                 */

                private int goods_id;
                private String original_img;
                private int is_new;
                private String goods_name;
                private String shop_price;
                private int store_id;
                private int distance;

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
            }

    }

    public static class StateBean {
        /**
         * code : 200
         * msg : 获取成功
         * debugMsg :
         * url : api/Index/index
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