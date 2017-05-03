package com.example.administrator.xiangou.nearby.apimodel;

import java.util.List;

/**
 * @author zhouzongyao
 * @Description 附近优惠接口实体类
 * @email 18482195579@163.com
 * @Date 2017-04-24 17:34
 */
public class NearbyBenifitDataBean {

    /**
     * data : {"today_goods":[{"goods_id":89,"goods_name":"Versac","original_img":"/public/upload/goods/2016/01-21/56a033ac98f10.jpg","shop_price":"172.00","market_price":"272.00"},{"goods_id":53,"goods_name":"华为 HUA","original_img":"/public/upload/goods/2016/01-13/569611334359e.jpg","shop_price":"349.00","market_price":"449.00"},{"goods_id":95,"goods_name":"CHANEL","original_img":"/public/upload/goods/2016/01-21/56a08cacc0e94.jpg","shop_price":"455.00","market_price":"555.00"},{"goods_id":90,"goods_name":"迪奥迪奥小 ","original_img":"/public/upload/goods/2016/01-21/56a041563ee79.jpg","shop_price":"6000.00","market_price":"6100.00"}],"flash":{"original_img":"/public/upload/goods/2016/03-09/56e01a4088d3b.jpg","goods_name":"Apple iPhone 6s Plus 16G 玫瑰金 移动联通电信4G手机","flash_price":"300.00","shop_price":"6007.00","flash_name":"海量品质包包等你入手","end_time":1593227460,"current_time":1493718852,"next_time":"2017-05-02 17:54"},"behave":[{"goods_id":104,"goods_name":"小米手机5,","original_img":"/public/upload/goods/2016/03-12/56e3eb73912ff.jpg","shop_price":"2999.00","market_price":"2099.00"},{"goods_id":1,"goods_name":"Apple ","original_img":"/public/upload/goods/2016/03-09/56e01a4088d3b.jpg","shop_price":"6007.00","market_price":"6107.00"},{"goods_id":96,"goods_name":"竹萃集自由中","original_img":"/public/upload/goods/2016/01-21/56a08d381b5ff.jpg","shop_price":"49.00","market_price":"149.00"},{"goods_id":52,"goods_name":"荣耀路由Pr","original_img":"/public/upload/goods/2016/01-13/56960d7bec88e.jpg","shop_price":"328.00","market_price":"428.00"},{"goods_id":91,"goods_name":"香奈儿邂逅清","original_img":"/public/upload/goods/2016/01-21/56a08a4c86ecb.jpg","shop_price":"618.00","market_price":"718.00"},{"goods_id":82,"goods_name":"舒肤佳纯白清","original_img":"/public/upload/goods/2016/01-19/569de0c074bdb.jpg","shop_price":"47.60","market_price":"147.60"},{"goods_id":97,"goods_name":"六福珠宝足金","original_img":"/public/upload/goods/2016/01-21/56a08e3362c6f.jpg","shop_price":"279.00","market_price":"379.00"},{"goods_id":53,"goods_name":"华为 HUA","original_img":"/public/upload/goods/2016/01-13/569611334359e.jpg","shop_price":"349.00","market_price":"449.00"},{"goods_id":92,"goods_name":"泊泉雅男士女","original_img":"/public/upload/goods/2016/01-21/56a08aef4ebdf.jpg","shop_price":"15.90","market_price":"115.90"},{"goods_id":83,"goods_name":"力士精油香氛","original_img":"/public/upload/goods/2016/01-19/569de25bdf16a.jpg","shop_price":"39.90","market_price":"139.90"},{"goods_id":98,"goods_name":"六福珠宝足金","original_img":"/public/upload/goods/2016/01-21/56a08eac5412b.jpg","shop_price":"239.00","market_price":"339.00"},{"goods_id":55,"goods_name":"华为（HUA","original_img":"/public/upload/goods/2016/01-13/5696135ce45b6.jpg","shop_price":"259.00","market_price":"359.00"},{"goods_id":93,"goods_name":"CHANEL","original_img":"/public/upload/goods/2016/01-21/56a08b701c7c5.jpg","shop_price":"6000.00","market_price":"6100.00"},{"goods_id":88,"goods_name":"香奈儿 /C","original_img":"/public/upload/goods/2016/01-20/569f5f2e32da3.jpg","shop_price":"290.00","market_price":"390.00"},{"goods_id":99,"goods_name":"CNUTI粤","original_img":"/public/upload/goods/2016/01-21/56a08f61212f7.jpg","shop_price":"409.00","market_price":"509.00"},{"goods_id":94,"goods_name":"凯卓纯净之水","original_img":"/public/upload/goods/2016/01-21/56a08bf378af8.jpg","shop_price":"255.90","market_price":"355.90"},{"goods_id":89,"goods_name":"Versac","original_img":"/public/upload/goods/2016/01-21/56a033ac98f10.jpg","shop_price":"172.00","market_price":"272.00"},{"goods_id":95,"goods_name":"CHANEL","original_img":"/public/upload/goods/2016/01-21/56a08cacc0e94.jpg","shop_price":"455.00","market_price":"555.00"},{"goods_id":90,"goods_name":"迪奥迪奥小 ","original_img":"/public/upload/goods/2016/01-21/56a041563ee79.jpg","shop_price":"6000.00","market_price":"6100.00"},{"goods_id":81,"goods_name":"纤慕文胸 女","original_img":"/public/upload/goods/2016/01-18/569cb6fe1e881.jpg","shop_price":"108.00","market_price":"208.00"},{"goods_id":41,"goods_name":"华为（HUA","original_img":"/public/upload/goods/2016/01-13/5695bf6426994.jpg","shop_price":"1588.00","market_price":"1688.00"},{"goods_id":58,"goods_name":"海信彩电LE","original_img":"/public/upload/goods/2016/01-14/56971493d2f2d.jpg","shop_price":"3199.00","market_price":"3299.00"},{"goods_id":74,"goods_name":"乐享 景德镇","original_img":"/public/upload/goods/2016/01-16/5699f2fa7e4bf.jpg","shop_price":"169.00","market_price":"269.00"},{"goods_id":69,"goods_name":"VNC LE","original_img":"/public/upload/goods/2016/01-15/56988df060d85.jpg","shop_price":"39.00","market_price":"139.00"},{"goods_id":47,"goods_name":"【联通合约机","original_img":"/public/upload/goods/2016/01-13/5695f444941fe.jpg","shop_price":"1399.00","market_price":"1499.00"},{"goods_id":64,"goods_name":"whaley","original_img":"/public/upload/goods/2016/01-15/5698553f575b6.jpg","shop_price":"2098.00","market_price":"2198.00"},{"goods_id":42,"goods_name":"Teclas","original_img":"/public/upload/goods/2016/01-13/5695c0873d865.png","shop_price":"499.00","market_price":"599.00"},{"goods_id":59,"goods_name":"酷开(coo","original_img":"/public/upload/goods/2016/01-14/569749040dcd0.jpg","shop_price":"2499.00","market_price":"2599.00"},{"goods_id":75,"goods_name":"乐享 陶瓷碗","original_img":"/public/upload/goods/2016/01-18/569c3ee26b6a6.jpg","shop_price":"389.00","market_price":"489.00"},{"goods_id":155,"goods_name":"男士西裤修改","original_img":"/public/upload/goods/2017/04-06/tVrQbGyhKdfatsLm75MTUzTbj.jpg","shop_price":"20.00","market_price":"20.00"}]}
     * state : {"code":200,"msg":"获取成功","debugMsg":"","url":"api/Benefit/near_benift"}
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
         * today_goods : [{"goods_id":89,"goods_name":"Versac","original_img":"/public/upload/goods/2016/01-21/56a033ac98f10.jpg","shop_price":"172.00","market_price":"272.00"},{"goods_id":53,"goods_name":"华为 HUA","original_img":"/public/upload/goods/2016/01-13/569611334359e.jpg","shop_price":"349.00","market_price":"449.00"},{"goods_id":95,"goods_name":"CHANEL","original_img":"/public/upload/goods/2016/01-21/56a08cacc0e94.jpg","shop_price":"455.00","market_price":"555.00"},{"goods_id":90,"goods_name":"迪奥迪奥小 ","original_img":"/public/upload/goods/2016/01-21/56a041563ee79.jpg","shop_price":"6000.00","market_price":"6100.00"}]
         * flash : {"original_img":"/public/upload/goods/2016/03-09/56e01a4088d3b.jpg","goods_name":"Apple iPhone 6s Plus 16G 玫瑰金 移动联通电信4G手机","flash_price":"300.00","shop_price":"6007.00","flash_name":"海量品质包包等你入手","end_time":1593227460,"current_time":1493718852,"next_time":"2017-05-02 17:54"}
         * behave : [{"goods_id":104,"goods_name":"小米手机5,","original_img":"/public/upload/goods/2016/03-12/56e3eb73912ff.jpg","shop_price":"2999.00","market_price":"2099.00"},{"goods_id":1,"goods_name":"Apple ","original_img":"/public/upload/goods/2016/03-09/56e01a4088d3b.jpg","shop_price":"6007.00","market_price":"6107.00"},{"goods_id":96,"goods_name":"竹萃集自由中","original_img":"/public/upload/goods/2016/01-21/56a08d381b5ff.jpg","shop_price":"49.00","market_price":"149.00"},{"goods_id":52,"goods_name":"荣耀路由Pr","original_img":"/public/upload/goods/2016/01-13/56960d7bec88e.jpg","shop_price":"328.00","market_price":"428.00"},{"goods_id":91,"goods_name":"香奈儿邂逅清","original_img":"/public/upload/goods/2016/01-21/56a08a4c86ecb.jpg","shop_price":"618.00","market_price":"718.00"},{"goods_id":82,"goods_name":"舒肤佳纯白清","original_img":"/public/upload/goods/2016/01-19/569de0c074bdb.jpg","shop_price":"47.60","market_price":"147.60"},{"goods_id":97,"goods_name":"六福珠宝足金","original_img":"/public/upload/goods/2016/01-21/56a08e3362c6f.jpg","shop_price":"279.00","market_price":"379.00"},{"goods_id":53,"goods_name":"华为 HUA","original_img":"/public/upload/goods/2016/01-13/569611334359e.jpg","shop_price":"349.00","market_price":"449.00"},{"goods_id":92,"goods_name":"泊泉雅男士女","original_img":"/public/upload/goods/2016/01-21/56a08aef4ebdf.jpg","shop_price":"15.90","market_price":"115.90"},{"goods_id":83,"goods_name":"力士精油香氛","original_img":"/public/upload/goods/2016/01-19/569de25bdf16a.jpg","shop_price":"39.90","market_price":"139.90"},{"goods_id":98,"goods_name":"六福珠宝足金","original_img":"/public/upload/goods/2016/01-21/56a08eac5412b.jpg","shop_price":"239.00","market_price":"339.00"},{"goods_id":55,"goods_name":"华为（HUA","original_img":"/public/upload/goods/2016/01-13/5696135ce45b6.jpg","shop_price":"259.00","market_price":"359.00"},{"goods_id":93,"goods_name":"CHANEL","original_img":"/public/upload/goods/2016/01-21/56a08b701c7c5.jpg","shop_price":"6000.00","market_price":"6100.00"},{"goods_id":88,"goods_name":"香奈儿 /C","original_img":"/public/upload/goods/2016/01-20/569f5f2e32da3.jpg","shop_price":"290.00","market_price":"390.00"},{"goods_id":99,"goods_name":"CNUTI粤","original_img":"/public/upload/goods/2016/01-21/56a08f61212f7.jpg","shop_price":"409.00","market_price":"509.00"},{"goods_id":94,"goods_name":"凯卓纯净之水","original_img":"/public/upload/goods/2016/01-21/56a08bf378af8.jpg","shop_price":"255.90","market_price":"355.90"},{"goods_id":89,"goods_name":"Versac","original_img":"/public/upload/goods/2016/01-21/56a033ac98f10.jpg","shop_price":"172.00","market_price":"272.00"},{"goods_id":95,"goods_name":"CHANEL","original_img":"/public/upload/goods/2016/01-21/56a08cacc0e94.jpg","shop_price":"455.00","market_price":"555.00"},{"goods_id":90,"goods_name":"迪奥迪奥小 ","original_img":"/public/upload/goods/2016/01-21/56a041563ee79.jpg","shop_price":"6000.00","market_price":"6100.00"},{"goods_id":81,"goods_name":"纤慕文胸 女","original_img":"/public/upload/goods/2016/01-18/569cb6fe1e881.jpg","shop_price":"108.00","market_price":"208.00"},{"goods_id":41,"goods_name":"华为（HUA","original_img":"/public/upload/goods/2016/01-13/5695bf6426994.jpg","shop_price":"1588.00","market_price":"1688.00"},{"goods_id":58,"goods_name":"海信彩电LE","original_img":"/public/upload/goods/2016/01-14/56971493d2f2d.jpg","shop_price":"3199.00","market_price":"3299.00"},{"goods_id":74,"goods_name":"乐享 景德镇","original_img":"/public/upload/goods/2016/01-16/5699f2fa7e4bf.jpg","shop_price":"169.00","market_price":"269.00"},{"goods_id":69,"goods_name":"VNC LE","original_img":"/public/upload/goods/2016/01-15/56988df060d85.jpg","shop_price":"39.00","market_price":"139.00"},{"goods_id":47,"goods_name":"【联通合约机","original_img":"/public/upload/goods/2016/01-13/5695f444941fe.jpg","shop_price":"1399.00","market_price":"1499.00"},{"goods_id":64,"goods_name":"whaley","original_img":"/public/upload/goods/2016/01-15/5698553f575b6.jpg","shop_price":"2098.00","market_price":"2198.00"},{"goods_id":42,"goods_name":"Teclas","original_img":"/public/upload/goods/2016/01-13/5695c0873d865.png","shop_price":"499.00","market_price":"599.00"},{"goods_id":59,"goods_name":"酷开(coo","original_img":"/public/upload/goods/2016/01-14/569749040dcd0.jpg","shop_price":"2499.00","market_price":"2599.00"},{"goods_id":75,"goods_name":"乐享 陶瓷碗","original_img":"/public/upload/goods/2016/01-18/569c3ee26b6a6.jpg","shop_price":"389.00","market_price":"489.00"},{"goods_id":155,"goods_name":"男士西裤修改","original_img":"/public/upload/goods/2017/04-06/tVrQbGyhKdfatsLm75MTUzTbj.jpg","shop_price":"20.00","market_price":"20.00"}]
         */

        private FlashBean flash;
        private List<TodayGoodsBean> today_goods;
        private List<BehaveBean> behave;

        public FlashBean getFlash() {
            return flash;
        }

        public void setFlash(FlashBean flash) {
            this.flash = flash;
        }

        public List<TodayGoodsBean> getToday_goods() {
            return today_goods;
        }

        public void setToday_goods(List<TodayGoodsBean> today_goods) {
            this.today_goods = today_goods;
        }

        public List<BehaveBean> getBehave() {
            return behave;
        }

        public void setBehave(List<BehaveBean> behave) {
            this.behave = behave;
        }

        public static class FlashBean {
            /**
             * original_img : /public/upload/goods/2016/03-09/56e01a4088d3b.jpg
             * goods_name : Apple iPhone 6s Plus 16G 玫瑰金 移动联通电信4G手机
             * flash_price : 300.00
             * shop_price : 6007.00
             * flash_name : 海量品质包包等你入手
             * end_time : 1593227460
             * current_time : 1493718852
             * next_time : 2017-05-02 17:54
             */

            private String original_img;
            private String goods_name;
            private String flash_price;
            private String shop_price;
            private String flash_name;
            private int end_time;
            private int current_time;
            private String next_time;

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

            public String getFlash_price() {
                return flash_price;
            }

            public void setFlash_price(String flash_price) {
                this.flash_price = flash_price;
            }

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }

            public String getFlash_name() {
                return flash_name;
            }

            public void setFlash_name(String flash_name) {
                this.flash_name = flash_name;
            }

            public int getEnd_time() {
                return end_time;
            }

            public void setEnd_time(int end_time) {
                this.end_time = end_time;
            }

            public int getCurrent_time() {
                return current_time;
            }

            public void setCurrent_time(int current_time) {
                this.current_time = current_time;
            }

            public String getNext_time() {
                return next_time;
            }

            public void setNext_time(String next_time) {
                this.next_time = next_time;
            }
        }

        public static class TodayGoodsBean {
            /**
             * goods_id : 89
             * goods_name : Versac
             * original_img : /public/upload/goods/2016/01-21/56a033ac98f10.jpg
             * shop_price : 172.00
             * market_price : 272.00
             */

            private int goods_id;
            private String goods_name;
            private String original_img;
            private String shop_price;
            private String market_price;

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
        }

        public static class BehaveBean {
            /**
             * goods_id : 104
             * goods_name : 小米手机5,
             * original_img : /public/upload/goods/2016/03-12/56e3eb73912ff.jpg
             * shop_price : 2999.00
             * market_price : 2099.00
             */

            private int goods_id;
            private String goods_name;
            private String original_img;
            private String shop_price;
            private String market_price;

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
        }
    }

    public static class StateBean {
        /**
         * code : 200
         * msg : 获取成功
         * debugMsg :
         * url : api/Benefit/near_benift
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
