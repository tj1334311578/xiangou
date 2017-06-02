package com.example.administrator.xiangou.mine.mystore.goodsmanage.addgoodsmanage.bean;

import java.util.List;

/**
 * 作者： tj on 2017/6/2.
 * <p>
 * 功能：进入添加商品请求的数据类
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */

public class IntoAddGoodPageBean {

    /**
     * data : {"cate":[{"cat_id":31,"name":"生活家具"},{"cat_id":32,"name":"生活家纺"},{"cat_id":33,"name":"生活厨具"},{"cat_id":34,"name":"生活日用"},{"cat_id":35,"name":"生活装饰"},{"cat_id":36,"name":"鲜花盆栽"}],"model":[{"model_id":33,"name":"鞋子"},{"model_id":32,"name":"箱包"},{"model_id":31,"name":"电器"},{"model_id":30,"name":"母婴"},{"model_id":29,"name":"冰箱"},{"model_id":13,"name":"衣服"},{"model_id":26,"name":"宠物"},{"model_id":27,"name":"香水"},{"model_id":28,"name":"家居"}],"sign":[{"sign_id":1,"name":"时尚"},{"sign_id":2,"name":"爆款"},{"sign_id":3,"name":"人气"},{"sign_id":4,"name":"文艺"},{"sign_id":5,"name":"复古"},{"sign_id":6,"name":"韩版"}],"goodsinfo":{"goods_name":"Apple iPhone 6s Plus 16G 玫瑰金 移动联通电信4G手机","store_count":295,"market_price":"6107.00","shop_price":"6007.00","model_id":4,"is_recommend":1,"is_new":1,"sign":[{"sign_id":1,"name":"时尚"},{"sign_id":2,"name":"爆款"}],"is_free_shipping":1,"fare":"12.00","goods_img":[{"img_id":382,"goods_id":1,"image_url":"/public/upload/goods/2016/03-09/56e01a54a2c6d.jpg"},{"img_id":381,"goods_id":1,"image_url":"/public/upload/goods/2016/03-09/56e01a54bcc53.jpg"},{"img_id":380,"goods_id":1,"image_url":"/public/upload/goods/2016/03-09/56e01a54de5a9.jpg"},{"img_id":544,"goods_id":1,"image_url":"/public/upload/goods/2016/03-09/56e01a4088d3b.jpg"}]},"good_model":{"specList":[],"items_ids":[],"store_count":[],"goods_attr":[],"allattr":[]}}
     * state : {"code":200,"msg":"获取成功","debugMsg":"","url":"api/Stores/into_add"}
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
        /**
         * cate : [{"cat_id":31,"name":"生活家具"},{"cat_id":32,"name":"生活家纺"},{"cat_id":33,"name":"生活厨具"},{"cat_id":34,"name":"生活日用"},{"cat_id":35,"name":"生活装饰"},{"cat_id":36,"name":"鲜花盆栽"}]
         * model : [{"model_id":33,"name":"鞋子"},{"model_id":32,"name":"箱包"},{"model_id":31,"name":"电器"},{"model_id":30,"name":"母婴"},{"model_id":29,"name":"冰箱"},{"model_id":13,"name":"衣服"},{"model_id":26,"name":"宠物"},{"model_id":27,"name":"香水"},{"model_id":28,"name":"家居"}]
         * sign : [{"sign_id":1,"name":"时尚"},{"sign_id":2,"name":"爆款"},{"sign_id":3,"name":"人气"},{"sign_id":4,"name":"文艺"},{"sign_id":5,"name":"复古"},{"sign_id":6,"name":"韩版"}]
         * goodsinfo : {"goods_name":"Apple iPhone 6s Plus 16G 玫瑰金 移动联通电信4G手机","store_count":295,"market_price":"6107.00","shop_price":"6007.00","model_id":4,"is_recommend":1,"is_new":1,"sign":[{"sign_id":1,"name":"时尚"},{"sign_id":2,"name":"爆款"}],"is_free_shipping":1,"fare":"12.00","goods_img":[{"img_id":382,"goods_id":1,"image_url":"/public/upload/goods/2016/03-09/56e01a54a2c6d.jpg"},{"img_id":381,"goods_id":1,"image_url":"/public/upload/goods/2016/03-09/56e01a54bcc53.jpg"},{"img_id":380,"goods_id":1,"image_url":"/public/upload/goods/2016/03-09/56e01a54de5a9.jpg"},{"img_id":544,"goods_id":1,"image_url":"/public/upload/goods/2016/03-09/56e01a4088d3b.jpg"}]}
         * good_model : {"specList":[],"items_ids":[],"store_count":[],"goods_attr":[],"allattr":[]}
         */

        private GoodsinfoBean goodsinfo;
        private GoodModelBean good_model;
        private List<CateBean> cate;
        private List<ModelBean> model;
        private List<SignBeanX> sign;

        public GoodsinfoBean getGoodsinfo() {
            return goodsinfo;
        }

        public void setGoodsinfo(GoodsinfoBean goodsinfo) {
            this.goodsinfo = goodsinfo;
        }

        public GoodModelBean getGood_model() {
            return good_model;
        }

        public void setGood_model(GoodModelBean good_model) {
            this.good_model = good_model;
        }

        public List<CateBean> getCate() {
            return cate;
        }

        public void setCate(List<CateBean> cate) {
            this.cate = cate;
        }

        public List<ModelBean> getModel() {
            return model;
        }

        public void setModel(List<ModelBean> model) {
            this.model = model;
        }

        public List<SignBeanX> getSign() {
            return sign;
        }

        public void setSign(List<SignBeanX> sign) {
            this.sign = sign;
        }

        public static class GoodsinfoBean {
            /**
             * goods_name : Apple iPhone 6s Plus 16G 玫瑰金 移动联通电信4G手机
             * store_count : 295
             * market_price : 6107.00
             * shop_price : 6007.00
             * model_id : 4
             * is_recommend : 1
             * is_new : 1
             * sign : [{"sign_id":1,"name":"时尚"},{"sign_id":2,"name":"爆款"}]
             * is_free_shipping : 1
             * fare : 12.00
             * goods_img : [{"img_id":382,"goods_id":1,"image_url":"/public/upload/goods/2016/03-09/56e01a54a2c6d.jpg"},{"img_id":381,"goods_id":1,"image_url":"/public/upload/goods/2016/03-09/56e01a54bcc53.jpg"},{"img_id":380,"goods_id":1,"image_url":"/public/upload/goods/2016/03-09/56e01a54de5a9.jpg"},{"img_id":544,"goods_id":1,"image_url":"/public/upload/goods/2016/03-09/56e01a4088d3b.jpg"}]
             */

            private String goods_name;
            private int store_count;
            private String market_price;
            private String shop_price;
            private int model_id;
            private int is_recommend;
            private int is_new;
            private int is_free_shipping;
            private String fare;
            private List<SignBean> sign;
            private List<GoodsImgBean> goods_img;

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public int getStore_count() {
                return store_count;
            }

            public void setStore_count(int store_count) {
                this.store_count = store_count;
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

            public int getModel_id() {
                return model_id;
            }

            public void setModel_id(int model_id) {
                this.model_id = model_id;
            }

            public int getIs_recommend() {
                return is_recommend;
            }

            public void setIs_recommend(int is_recommend) {
                this.is_recommend = is_recommend;
            }

            public int getIs_new() {
                return is_new;
            }

            public void setIs_new(int is_new) {
                this.is_new = is_new;
            }

            public int getIs_free_shipping() {
                return is_free_shipping;
            }

            public void setIs_free_shipping(int is_free_shipping) {
                this.is_free_shipping = is_free_shipping;
            }

            public String getFare() {
                return fare;
            }

            public void setFare(String fare) {
                this.fare = fare;
            }

            public List<SignBean> getSign() {
                return sign;
            }

            public void setSign(List<SignBean> sign) {
                this.sign = sign;
            }

            public List<GoodsImgBean> getGoods_img() {
                return goods_img;
            }

            public void setGoods_img(List<GoodsImgBean> goods_img) {
                this.goods_img = goods_img;
            }

            public static class SignBean {
                /**
                 * sign_id : 1
                 * name : 时尚
                 */

                private int sign_id;
                private String name;

                public int getSign_id() {
                    return sign_id;
                }

                public void setSign_id(int sign_id) {
                    this.sign_id = sign_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }

            public static class GoodsImgBean {
                /**
                 * img_id : 382
                 * goods_id : 1
                 * image_url : /public/upload/goods/2016/03-09/56e01a54a2c6d.jpg
                 */

                private int img_id;
                private int goods_id;
                private String image_url;

                public int getImg_id() {
                    return img_id;
                }

                public void setImg_id(int img_id) {
                    this.img_id = img_id;
                }

                public int getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(int goods_id) {
                    this.goods_id = goods_id;
                }

                public String getImage_url() {
                    return image_url;
                }

                public void setImage_url(String image_url) {
                    this.image_url = image_url;
                }
            }
        }

        public static class GoodModelBean {
            private List<?> specList;
            private List<?> items_ids;
            private List<?> store_count;
            private List<?> goods_attr;
            private List<?> allattr;

            public List<?> getSpecList() {
                return specList;
            }

            public void setSpecList(List<?> specList) {
                this.specList = specList;
            }

            public List<?> getItems_ids() {
                return items_ids;
            }

            public void setItems_ids(List<?> items_ids) {
                this.items_ids = items_ids;
            }

            public List<?> getStore_count() {
                return store_count;
            }

            public void setStore_count(List<?> store_count) {
                this.store_count = store_count;
            }

            public List<?> getGoods_attr() {
                return goods_attr;
            }

            public void setGoods_attr(List<?> goods_attr) {
                this.goods_attr = goods_attr;
            }

            public List<?> getAllattr() {
                return allattr;
            }

            public void setAllattr(List<?> allattr) {
                this.allattr = allattr;
            }
        }

        public static class CateBean {
            /**
             * cat_id : 31
             * name : 生活家具
             */

            private int cat_id;
            private String name;

            public int getCat_id() {
                return cat_id;
            }

            public void setCat_id(int cat_id) {
                this.cat_id = cat_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class ModelBean {
            /**
             * model_id : 33
             * name : 鞋子
             */

            private int model_id;
            private String name;

            public int getModel_id() {
                return model_id;
            }

            public void setModel_id(int model_id) {
                this.model_id = model_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class SignBeanX {
            /**
             * sign_id : 1
             * name : 时尚
             */

            private int sign_id;
            private String name;

            public int getSign_id() {
                return sign_id;
            }

            public void setSign_id(int sign_id) {
                this.sign_id = sign_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class StateBean {
        /**
         * code : 200
         * msg : 获取成功
         * debugMsg :
         * url : api/Stores/into_add
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
