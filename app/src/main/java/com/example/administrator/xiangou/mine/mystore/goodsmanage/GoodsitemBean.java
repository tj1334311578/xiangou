package com.example.administrator.xiangou.mine.mystore.goodsmanage;

import java.util.List;

/**
 * 作者： tj on 2017/5/31.
 * <p>
 * 功能：
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */
public class GoodsitemBean {

    @Override
    public String toString() {
        return "GoodsitemBean{" +
                "state=" + state +
                ", data=" + data +
                '}';
    }

    /**
     * data : [{"goods_id":157,"goods_name":"测试商品规格的","market_price":"20.00","shop_price":"20.00","store_count":80,"original_img":"/public/upload/goods/2017/04-12/R6hjhyxUK2dd8HtPX7VTr2Ugf.jpg","is_on_sale":1,"spec":[{"spec_id":1,"name":"颜色","item_id":1,"item":"红色"},{"spec_id":1,"name":"颜色","item_id":3,"item":"绿色"},{"spec_id":2,"name":"尺码","item_id":4,"item":"S"},{"spec_id":2,"name":"尺码","item_id":5,"item":"M"}]},{"goods_id":156,"goods_name":"测试商品规格的","market_price":"20.00","shop_price":"20.00","store_count":9,"original_img":"/public/upload/goods/2017/04-13/p9ymhk8d2wnsReL7CTbFnD5Eq.png","is_on_sale":1,"spec":[{"spec_id":1,"name":"颜色","item_id":1,"item":"红色"},{"spec_id":1,"name":"颜色","item_id":3,"item":"绿色"},{"spec_id":2,"name":"尺码","item_id":4,"item":"S"}]},{"goods_id":155,"goods_name":"男士西裤修改1","market_price":"20.00","shop_price":"20.00","store_count":11,"original_img":"/public/upload/goods/2017/04-06/tVrQbGyhKdfatsLm75MTUzTbj.jpg","is_on_sale":1,"spec":[{"spec_id":1,"name":"颜色","item_id":1,"item":"红色"},{"spec_id":1,"name":"颜色","item_id":3,"item":"绿色"},{"spec_id":2,"name":"尺码","item_id":4,"item":"S"}]},{"goods_id":79,"goods_name":"恒源祥秋冬新款男士纯色全羊毛衫 中年圆领长袖毛衣 加厚针织衫潮08W18096","market_price":"498.00","shop_price":"398.00","store_count":50,"original_img":"/public/upload/goods/2016/01-18/569c82d77ebfc.jpg","is_on_sale":1,"spec":[{"spec_id":1,"name":"颜色","item_id":1,"item":"红色"},{"spec_id":2,"name":"尺码","item_id":4,"item":"S"},{"spec_id":2,"name":"尺码","item_id":5,"item":"M"},{"spec_id":1,"name":"颜色","item_id":18,"item":"蓝色"}]},{"goods_id":51,"goods_name":"华为 HUAWEI Mate 8 4GB+64GB版 全网通（香槟金）","market_price":"3799.00","shop_price":"3699.00","store_count":68,"original_img":"/public/upload/goods/2016/01-13/56960907f26d1.jpg","is_on_sale":1,"spec":[{"spec_id":25,"name":"鞋码","item_id":123,"item":"42"},{"spec_id":25,"name":"鞋码","item_id":124,"item":"43"},{"spec_id":21,"name":"颜色","item_id":132,"item":"粉色"},{"spec_id":21,"name":"颜色","item_id":133,"item":"灰色"}]},{"goods_id":49,"goods_name":"荣耀畅玩5X 双卡双待 移动版 智能手机（破晓银）","market_price":"1099.00","shop_price":"999.00","store_count":1000,"original_img":"/public/upload/goods/2016/01-13/569600e533b20.jpg","is_on_sale":0,"spec":[]},{"goods_id":46,"goods_name":"【北京移动老用户专享 话费六折】荣耀畅玩5X 双卡双待 移动版 智能手机（破晓银）","market_price":"1099.00","shop_price":"999.00","store_count":1000,"original_img":"/public/upload/goods/2016/01-13/5695ef4114f2b.jpg","is_on_sale":1,"spec":[]},{"goods_id":1,"goods_name":"Apple iPhone 6s Plus 16G 玫瑰金 移动联通电信4G手机","market_price":"6107.00","shop_price":"6007.00","store_count":295,"original_img":"/public/upload/goods/2016/03-09/56e01a4088d3b.jpg","is_on_sale":1,"spec":[]}]
     * state : {"code":200,"msg":"获取成功","debugMsg":"","url":"api/Stores/goodslist"}
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
         * url : api/Stores/goodslist
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


        @Override
        public String toString() {
            return "DataBean{" +
                    "goods_id=" + goods_id +
                    ", goods_name='" + goods_name + '\'' +
                    ", market_price='" + market_price + '\'' +
                    ", shop_price='" + shop_price + '\'' +
                    ", store_count=" + store_count +
                    ", original_img='" + original_img + '\'' +
                    ", is_on_sale=" + is_on_sale +
                    ", spec=" + spec +
                    ", spec2Bean=" + spec2Bean +
                    '}';
        }

        /**
         * goods_id : 157
         * goods_name : 测试商品规格的
         * market_price : 20.00
         * shop_price : 20.00
         * store_count : 80
         * original_img : /public/upload/goods/2017/04-12/R6hjhyxUK2dd8HtPX7VTr2Ugf.jpg
         * is_on_sale : 1
         * spec : [{"spec_id":1,"name":"颜色","item_id":1,"item":"红色"},{"spec_id":1,"name":"颜色","item_id":3,"item":"绿色"},{"spec_id":2,"name":"尺码","item_id":4,"item":"S"},{"spec_id":2,"name":"尺码","item_id":5,"item":"M"}]
         */

        private int goods_id;
        private String goods_name;
        private String market_price;
        private String shop_price;
        private int store_count;
        private String original_img;
        private int is_on_sale;
        private List<SpecBean> spec;
        private Spec2Bean spec2Bean;

        public Spec2Bean getSpec2Bean() {
            return spec2Bean;
        }

        public void setSpec2Bean(Spec2Bean spec2Bean) {
            this.spec2Bean = spec2Bean;
        }

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

        public int getStore_count() {
            return store_count;
        }

        public void setStore_count(int store_count) {
            this.store_count = store_count;
        }

        public String getOriginal_img() {
            return original_img;
        }

        public void setOriginal_img(String original_img) {
            this.original_img = original_img;
        }

        public int getIs_on_sale() {
            return is_on_sale;
        }

        public void setIs_on_sale(int is_on_sale) {
            this.is_on_sale = is_on_sale;
        }

        public List<SpecBean> getSpec() {
            return spec;
        }

        public void setSpec(List<SpecBean> spec) {
            this.spec = spec;
        }

        public static class SpecBean {

            @Override
            public String toString() {
                return "SpecsBean{" +
                        "spec_id=" + spec_id +
                        ", name='" + name + '\'' +
                        ", item_id=" + item_id +
                        ", item='" + item + '\'' +
                        '}';
            }

            /**
             * spec_id : 1
             * name : 颜色
             * item_id : 1
             * item : 红色
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
    }
}
