package com.example.administrator.xiangou.nearby.apimodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author zhouzongyao
 * @Description 附近商品详情请求的返回数据的实体类
 * @email 18482195579@163.com
 * @Date 2017-04-24 17:03
 */
public class NearbyGoodsDetailDataBean {

    /**
     * data : {"goods_id":126,"original_img":"/public/upload/goods/2016/04-21/57187bd5c0178.jpg","goods_name":"Canon/佳能 EOS 700D套机（18-55mm)数码单反相机 苏宁易购","market_price":"3599.00","shop_price":"3499.00","sales_sum":0,"is_free_shipping":0,"fare":0,"name":"柠檬店升级店","prom_type":1,"prom_id":16,"logo":"","store_id":435,"distance":15,"goods_img":[{"img_id":488,"goods_id":126,"image_url":"/public/upload/goods/2016/04-21/57187bc13f7f6.jpg"},{"img_id":489,"goods_id":126,"image_url":"/public/upload/goods/2016/04-21/57187bd601eaf.jpg"},{"img_id":490,"goods_id":126,"image_url":"/public/upload/goods/2016/04-21/57187bd5dd859.jpg"},{"img_id":491,"goods_id":126,"image_url":"/public/upload/goods/2016/04-21/57187bd5c0178.jpg"},{"img_id":492,"goods_id":126,"image_url":"/public/upload/goods/2016/04-21/57187bd5a2705.jpg"}],"favorite":1,"filter_spec":[{"spec_id":20,"name":"颜色","item_id":139,"item":"白色"},{"spec_id":27,"name":"尺寸","item_id":145,"item":"26寸"}],"goods_attr":[{"attr_name":"品牌","attr_value":"佳能"},{"attr_name":"单反级别","attr_value":"入门级"},{"attr_name":"屏幕尺寸","attr_value":"3英寸"},{"attr_name":"储存介质","attr_value":"sd卡"},{"attr_name":"像素","attr_value":"1800万"}],"spec_goods_count":{"139_145":55},"is_favorite":0,"cart_num":0,"coupon":[],"score":0,"store_total_sale":"0","store_follow":0,"comment_counts":2,"comment":[{"comment_id":298,"content":"买来送给我老公的, 嘻嘻....","username":"貌美*****","img":["/public/upload/goods/2016/04-21/57187bc13f7f6.jpg","/public/upload/goods/2016/04-21/57187bd601eaf.jpg","/public/upload/goods/2016/04-21/57187bd5dd859.jpg"],"add_time":"2016-04-21","order_id":1,"head_pic":"/public/upload/head_pic/2017/04-19/kwe62SEPexgc7nv9LZc6m5rSc.png","spec":""},{"comment_id":264,"content":"买回去男朋友很喜欢 ..","username":"美女*****","img":[],"add_time":"2016-04-21","order_id":1,"head_pic":"/public/upload/head_pic/2017/04-19/kwe62SEPexgc7nv9LZc6m5rSc.png","spec":""}],"flash_sale":{"price":3200,"prom_type":1,"prom_id":16,"is_end":0,"start_time":1486787160,"end_time":1493308800}}
     * state : {"code":200,"msg":"获取成功","debugMsg":"","url":"api/Good/goods_detail"}
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
         * goods_id : 126
         * original_img : /public/upload/goods/2016/04-21/57187bd5c0178.jpg
         * goods_name : Canon/佳能 EOS 700D套机（18-55mm)数码单反相机 苏宁易购
         * market_price : 3599.00
         * shop_price : 3499.00
         * sales_sum : 0
         * is_free_shipping : 0
         * fare : 0
         * name : 柠檬店升级店
         * prom_type : 1
         * prom_id : 16
         * logo :
         * store_id : 435
         * distance : 15
         * goods_img : [{"img_id":488,"goods_id":126,"image_url":"/public/upload/goods/2016/04-21/57187bc13f7f6.jpg"},{"img_id":489,"goods_id":126,"image_url":"/public/upload/goods/2016/04-21/57187bd601eaf.jpg"},{"img_id":490,"goods_id":126,"image_url":"/public/upload/goods/2016/04-21/57187bd5dd859.jpg"},{"img_id":491,"goods_id":126,"image_url":"/public/upload/goods/2016/04-21/57187bd5c0178.jpg"},{"img_id":492,"goods_id":126,"image_url":"/public/upload/goods/2016/04-21/57187bd5a2705.jpg"}]
         * favorite : 1
         * filter_spec : [{"spec_id":20,"name":"颜色","item_id":139,"item":"白色"},{"spec_id":27,"name":"尺寸","item_id":145,"item":"26寸"}]
         * goods_attr : [{"attr_name":"品牌","attr_value":"佳能"},{"attr_name":"单反级别","attr_value":"入门级"},{"attr_name":"屏幕尺寸","attr_value":"3英寸"},{"attr_name":"储存介质","attr_value":"sd卡"},{"attr_name":"像素","attr_value":"1800万"}]
         * spec_goods_count : {"139_145":55}
         * is_favorite : 0
         * cart_num : 0
         * coupon : []
         * score : 0
         * store_total_sale : 0
         * store_follow : 0
         * comment_counts : 2
         * comment : [{"comment_id":298,"content":"买来送给我老公的, 嘻嘻....","username":"貌美*****","img":["/public/upload/goods/2016/04-21/57187bc13f7f6.jpg","/public/upload/goods/2016/04-21/57187bd601eaf.jpg","/public/upload/goods/2016/04-21/57187bd5dd859.jpg"],"add_time":"2016-04-21","order_id":1,"head_pic":"/public/upload/head_pic/2017/04-19/kwe62SEPexgc7nv9LZc6m5rSc.png","spec":""},{"comment_id":264,"content":"买回去男朋友很喜欢 ..","username":"美女*****","img":[],"add_time":"2016-04-21","order_id":1,"head_pic":"/public/upload/head_pic/2017/04-19/kwe62SEPexgc7nv9LZc6m5rSc.png","spec":""}]
         * flash_sale : {"price":3200,"prom_type":1,"prom_id":16,"is_end":0,"start_time":1486787160,"end_time":1493308800}
         */

        private int goods_id;
        private String original_img;
        private String goods_name;
        private String market_price;
        private String shop_price;
        private int sales_sum;
        private int is_free_shipping;
        private int fare;
        private String name;
        private int prom_type;
        private int prom_id;
        private String logo;
        private int store_id;
        private int distance;
        private int favorite;
        private SpecGoodsCountBean spec_goods_count;
        private int is_favorite;
        private int cart_num;
        private int score;
        private String store_total_sale;
        private int store_follow;
        private int comment_counts;
        private FlashSaleBean flash_sale;
        private List<GoodsImgBean> goods_img;
        private List<FilterSpecBean> filter_spec;
        private List<GoodsAttrBean> goods_attr;
        private List<?> coupon;
        private List<CommentBean> comment;

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

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
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

        public int getSales_sum() {
            return sales_sum;
        }

        public void setSales_sum(int sales_sum) {
            this.sales_sum = sales_sum;
        }

        public int getIs_free_shipping() {
            return is_free_shipping;
        }

        public void setIs_free_shipping(int is_free_shipping) {
            this.is_free_shipping = is_free_shipping;
        }

        public int getFare() {
            return fare;
        }

        public void setFare(int fare) {
            this.fare = fare;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
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

        public int getFavorite() {
            return favorite;
        }

        public void setFavorite(int favorite) {
            this.favorite = favorite;
        }

        public SpecGoodsCountBean getSpec_goods_count() {
            return spec_goods_count;
        }

        public void setSpec_goods_count(SpecGoodsCountBean spec_goods_count) {
            this.spec_goods_count = spec_goods_count;
        }

        public int getIs_favorite() {
            return is_favorite;
        }

        public void setIs_favorite(int is_favorite) {
            this.is_favorite = is_favorite;
        }

        public int getCart_num() {
            return cart_num;
        }

        public void setCart_num(int cart_num) {
            this.cart_num = cart_num;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getStore_total_sale() {
            return store_total_sale;
        }

        public void setStore_total_sale(String store_total_sale) {
            this.store_total_sale = store_total_sale;
        }

        public int getStore_follow() {
            return store_follow;
        }

        public void setStore_follow(int store_follow) {
            this.store_follow = store_follow;
        }

        public int getComment_counts() {
            return comment_counts;
        }

        public void setComment_counts(int comment_counts) {
            this.comment_counts = comment_counts;
        }

        public FlashSaleBean getFlash_sale() {
            return flash_sale;
        }

        public void setFlash_sale(FlashSaleBean flash_sale) {
            this.flash_sale = flash_sale;
        }

        public List<GoodsImgBean> getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(List<GoodsImgBean> goods_img) {
            this.goods_img = goods_img;
        }

        public List<FilterSpecBean> getFilter_spec() {
            return filter_spec;
        }

        public void setFilter_spec(List<FilterSpecBean> filter_spec) {
            this.filter_spec = filter_spec;
        }

        public List<GoodsAttrBean> getGoods_attr() {
            return goods_attr;
        }

        public void setGoods_attr(List<GoodsAttrBean> goods_attr) {
            this.goods_attr = goods_attr;
        }

        public List<?> getCoupon() {
            return coupon;
        }

        public void setCoupon(List<?> coupon) {
            this.coupon = coupon;
        }

        public List<CommentBean> getComment() {
            return comment;
        }

        public void setComment(List<CommentBean> comment) {
            this.comment = comment;
        }

        public static class SpecGoodsCountBean {
            /**
             * 139_145 : 55
             */

            @SerializedName("139_145")
            private int _$139_145;

            public int get_$139_145() {
                return _$139_145;
            }

            public void set_$139_145(int _$139_145) {
                this._$139_145 = _$139_145;
            }
        }

        public static class FlashSaleBean {
            /**
             * price : 3200
             * prom_type : 1
             * prom_id : 16
             * is_end : 0
             * start_time : 1486787160
             * end_time : 1493308800
             */

            private int price;
            private int prom_type;
            private int prom_id;
            private int is_end;
            private int start_time;
            private int end_time;

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
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

            public int getIs_end() {
                return is_end;
            }

            public void setIs_end(int is_end) {
                this.is_end = is_end;
            }

            public int getStart_time() {
                return start_time;
            }

            public void setStart_time(int start_time) {
                this.start_time = start_time;
            }

            public int getEnd_time() {
                return end_time;
            }

            public void setEnd_time(int end_time) {
                this.end_time = end_time;
            }
        }

        public static class GoodsImgBean {
            /**
             * img_id : 488
             * goods_id : 126
             * image_url : /public/upload/goods/2016/04-21/57187bc13f7f6.jpg
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

        public static class FilterSpecBean {
            /**
             * spec_id : 20
             * name : 颜色
             * item_id : 139
             * item : 白色
             */

            private int spec_id;
            private String name;
            private int item_id;
            private String item;

            public int getSpec_id() {
                return spec_id;
            }

            public void setSpec_id(int spec_id) {
                this.spec_id = spec_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getItem_id() {
                return item_id;
            }

            public void setItem_id(int item_id) {
                this.item_id = item_id;
            }

            public String getItem() {
                return item;
            }

            public void setItem(String item) {
                this.item = item;
            }
        }

        public static class GoodsAttrBean {
            /**
             * attr_name : 品牌
             * attr_value : 佳能
             */

            private String attr_name;
            private String attr_value;

            public String getAttr_name() {
                return attr_name;
            }

            public void setAttr_name(String attr_name) {
                this.attr_name = attr_name;
            }

            public String getAttr_value() {
                return attr_value;
            }

            public void setAttr_value(String attr_value) {
                this.attr_value = attr_value;
            }
        }

        public static class CommentBean {
            /**
             * comment_id : 298
             * content : 买来送给我老公的, 嘻嘻....
             * username : 貌美*****
             * img : ["/public/upload/goods/2016/04-21/57187bc13f7f6.jpg","/public/upload/goods/2016/04-21/57187bd601eaf.jpg","/public/upload/goods/2016/04-21/57187bd5dd859.jpg"]
             * add_time : 2016-04-21
             * order_id : 1
             * head_pic : /public/upload/head_pic/2017/04-19/kwe62SEPexgc7nv9LZc6m5rSc.png
             * spec :
             */

            private int comment_id;
            private String content;
            private String username;
            private String add_time;
            private int order_id;
            private String head_pic;
            private String spec;
            private List<String> img;

            public int getComment_id() {
                return comment_id;
            }

            public void setComment_id(int comment_id) {
                this.comment_id = comment_id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public String getHead_pic() {
                return head_pic;
            }

            public void setHead_pic(String head_pic) {
                this.head_pic = head_pic;
            }

            public String getSpec() {
                return spec;
            }

            public void setSpec(String spec) {
                this.spec = spec;
            }

            public List<String> getImg() {
                return img;
            }

            public void setImg(List<String> img) {
                this.img = img;
            }
        }
    }

    public static class StateBean {
        /**
         * code : 200
         * msg : 获取成功
         * debugMsg :
         * url : api/Good/goods_detail
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
