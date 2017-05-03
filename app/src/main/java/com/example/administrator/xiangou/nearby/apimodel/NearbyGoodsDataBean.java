package com.example.administrator.xiangou.nearby.apimodel;

import java.util.List;

/**
 * @author zhouzongyao
 * @Description 附近商品接口实体类
 * @email 18482195579@163.com
 * @Date 2017-04-24 17:22
 */
public class NearbyGoodsDataBean {

    @Override
    public String toString() {
        return "NearbyGoodsDataBean{" +
                "data=" + data +
                ", state=" + state +
                '}';
    }

    /**
     * data : {"banner":[{"ad_code":"/public/upload/ad/2016/09-12/57d645ec27e00.jpg"},{"ad_code":"/public/upload/ad/2016/09-12/57d6460060058.jpg"}],"catelist":[{"cate_name":"女装","image":"/public/upload/category/2017/04-25/ea9db4a3c03a315020d7f3401296544b.png","goodslist":[{"goods_id":59,"cat_id":844,"original_img":"/public/upload/goods/2016/01-14/569749040dcd0.jpg","name":"女装","store_id":2},{"goods_id":120,"cat_id":848,"original_img":"/public/upload/goods/2016/04-21/571836e30aaba.jpg","name":"冬季外套","store_id":2},{"goods_id":123,"cat_id":849,"original_img":"/public/upload/goods/2016/04-21/57186b2475e75.jpg","name":"毛衣针织","store_id":435},{"goods_id":119,"cat_id":847,"original_img":"/public/upload/goods/2016/04-21/5718365665d97.jpg","name":"春秋外套","store_id":433},{"goods_id":122,"cat_id":849,"original_img":"/public/upload/goods/2016/04-21/5718384936f8a.jpg","name":"毛衣针织","store_id":435},{"goods_id":130,"cat_id":850,"original_img":"/public/upload/goods/2016/04-21/57187e635d509.jpg","name":"运动休闲","store_id":435},{"goods_id":118,"cat_id":847,"original_img":"/public/upload/goods/2016/04-21/571835c77b583.jpg","name":"春秋外套","store_id":433},{"goods_id":121,"cat_id":848,"original_img":"/public/upload/goods/2016/04-21/571837b30942a.jpg","name":"冬季外套","store_id":435}]},{"cate_name":"男装","image":"/public/upload/category/2017/04-25/a7a3ae7415f425786d0bc8eec8a80dee.png","goodslist":[{"goods_id":91,"cat_id":41,"original_img":"/public/upload/goods/2016/01-21/56a08a4c86ecb.jpg","name":"毛衣开衫","store_id":3},{"goods_id":92,"cat_id":39,"original_img":"/public/upload/goods/2016/01-21/56a08aef4ebdf.jpg","name":"春秋外套","store_id":3},{"goods_id":140,"cat_id":859,"original_img":"/public/upload/goods/2016/04-22/5719843a87434.jpg","name":"运动休闲","store_id":435},{"goods_id":82,"cat_id":39,"original_img":"/public/upload/goods/2016/01-19/569de0c074bdb.jpg","name":"春秋外套","store_id":3},{"goods_id":89,"cat_id":40,"original_img":"/public/upload/goods/2016/01-21/56a033ac98f10.jpg","name":"冬季外套","store_id":3},{"goods_id":90,"cat_id":41,"original_img":"/public/upload/goods/2016/01-21/56a041563ee79.jpg","name":"毛衣开衫","store_id":3},{"goods_id":83,"cat_id":39,"original_img":"/public/upload/goods/2016/01-19/569de25bdf16a.jpg","name":"春秋外套","store_id":3},{"goods_id":157,"cat_id":5,"original_img":"/public/upload/goods/2017/04-12/R6hjhyxUK2dd8HtPX7VTr2Ugf.jpg","name":"男装","store_id":1}]},{"cate_name":"鞋子","image":"/public/upload/category/2017/04-25/6c1d9585c37a0927267a675b78edd0a3.png","goodslist":[{"goods_id":81,"cat_id":845,"original_img":"/public/upload/goods/2016/01-18/569cb6fe1e881.jpg","name":"鞋子","store_id":3},{"goods_id":58,"cat_id":845,"original_img":"/public/upload/goods/2016/01-14/56971493d2f2d.jpg","name":"鞋子","store_id":2}]},{"cate_name":"箱包","image":"/public/upload/category/2017/04-25/1b52f49c83c07cb5b45aab1fe0e783fe.png","goodslist":[{"goods_id":56,"cat_id":49,"original_img":"/public/upload/goods/2016/01-14/56970fc50a9f3.jpg","name":"女双肩包","store_id":2},{"goods_id":52,"cat_id":54,"original_img":"/public/upload/goods/2016/01-13/56960d7bec88e.jpg","name":"拉杆箱包","store_id":3},{"goods_id":53,"cat_id":55,"original_img":"/public/upload/goods/2016/01-13/569611334359e.jpg","name":"男单肩包","store_id":3},{"goods_id":55,"cat_id":50,"original_img":"/public/upload/goods/2016/01-13/5696135ce45b6.jpg","name":"女手提包","store_id":3}]},{"cate_name":"化妆","image":"/public/upload/category/2017/04-25/1f32939555a317c7b1e4ae5890cd9ee7.png","goodslist":[{"goods_id":139,"cat_id":43,"original_img":"/public/upload/goods/2016/04-21/57188c82d62a9.jpg","name":"面部护理","store_id":435},{"goods_id":94,"cat_id":43,"original_img":"/public/upload/goods/2016/01-21/56a08bf378af8.jpg","name":"面部护理","store_id":3},{"goods_id":95,"cat_id":44,"original_img":"/public/upload/goods/2016/01-21/56a08cacc0e94.jpg","name":"时尚彩妆","store_id":3},{"goods_id":96,"cat_id":45,"original_img":"/public/upload/goods/2016/01-21/56a08d381b5ff.jpg","name":"美发护发","store_id":3},{"goods_id":73,"cat_id":47,"original_img":"/public/upload/goods/2016/01-16/5699b3fa78d72.jpg","name":"男式护肤","store_id":2},{"goods_id":97,"cat_id":46,"original_img":"/public/upload/goods/2016/01-21/56a08e3362c6f.jpg","name":"身体护理","store_id":3},{"goods_id":98,"cat_id":47,"original_img":"/public/upload/goods/2016/01-21/56a08eac5412b.jpg","name":"男式护肤","store_id":3},{"goods_id":93,"cat_id":42,"original_img":"/public/upload/goods/2016/01-21/56a08b701c7c5.jpg","name":"女士护肤","store_id":3}]},{"cate_name":"电器","image":"/public/upload/category/2017/04-25/c72b1082e07a7e0a2867459458b89625.png","goodslist":[{"goods_id":143,"cat_id":22,"original_img":"/public/upload/goods/2016/04-22/5719923fb2708.jpg","name":"冰箱","store_id":435},{"goods_id":142,"cat_id":22,"original_img":"/public/upload/goods/2016/04-22/57199141d9c05.jpg","name":"冰箱","store_id":435}]},{"cate_name":"母婴","image":"/public/upload/category/2017/04-25/45d3442ea8d0dea743dfa872645089f7.png","goodslist":[{"goods_id":63,"cat_id":82,"original_img":"/public/upload/goods/2016/01-15/569854498fde8.jpg","name":"尿裤湿巾","store_id":2},{"goods_id":65,"cat_id":80,"original_img":"/public/upload/goods/2016/01-15/569856c42e7b7.jpg","name":"营养辅食","store_id":2},{"goods_id":62,"cat_id":83,"original_img":"/public/upload/goods/2016/01-14/569769cf6527b.jpg","name":"玩具车床","store_id":2},{"goods_id":68,"cat_id":76,"original_img":"/public/upload/goods/2016/01-15/569864e0b0315.jpg","name":"婴幼服饰","store_id":2},{"goods_id":67,"cat_id":77,"original_img":"/public/upload/goods/2016/01-15/5698627f769f7.jpg","name":"寝居服饰","store_id":2},{"goods_id":71,"cat_id":73,"original_img":"/public/upload/goods/2016/01-15/5698b4bfd0d36.jpg","name":"营养奶食","store_id":2},{"goods_id":64,"cat_id":81,"original_img":"/public/upload/goods/2016/01-15/5698553f575b6.jpg","name":"童装童鞋","store_id":2},{"goods_id":70,"cat_id":74,"original_img":"/public/upload/goods/2016/01-15/56988fead74f3.jpg","name":"妈妈用品","store_id":2}]},{"cate_name":"家居","image":"/public/upload/category/2017/04-25/d33fd8048fa74b4d5cc62023037414eb.png","goodslist":[{"goods_id":124,"cat_id":33,"original_img":"/public/upload/goods/2016/04-21/5718790894b46.png","name":"生活厨具","store_id":435},{"goods_id":132,"cat_id":33,"original_img":"/public/upload/goods/2016/04-21/5718814f978b0.jpg","name":"生活厨具","store_id":435},{"goods_id":125,"cat_id":33,"original_img":"/public/upload/goods/2016/04-21/57187b42bfc0c.jpg","name":"生活厨具","store_id":435},{"goods_id":129,"cat_id":38,"original_img":"/public/upload/goods/2016/04-21/57187dd92a26f.jpg","name":"灯具","store_id":435},{"goods_id":141,"cat_id":31,"original_img":"/public/upload/goods/2016/04-22/57198a178e5c1.jpg","name":"生活家具","store_id":435},{"goods_id":126,"cat_id":33,"original_img":"/public/upload/goods/2016/04-21/57187bd5c0178.jpg","name":"生活厨具","store_id":435},{"goods_id":127,"cat_id":36,"original_img":"/public/upload/goods/2016/04-21/57187c5d36631.jpg","name":"鲜花盆栽","store_id":435}]},{"cate_name":"宠物","image":"/public/upload/category/2017/04-25/c4ae9e2f67526c7b31d355926e3b810d.png","goodslist":[{"goods_id":57,"cat_id":846,"original_img":"/public/upload/goods/2016/01-14/569710f50e7d8.jpg","name":"宠物","store_id":2},{"goods_id":101,"cat_id":846,"original_img":"/public/upload/goods/2016/01-21/56a0903ae670f.jpg","name":"宠物","store_id":433},{"goods_id":100,"cat_id":846,"original_img":"/public/upload/goods/2016/01-21/56a08fc691bf0.jpg","name":"宠物","store_id":2}]}]}
     * state : {"code":200,"msg":"获取成功","debugMsg":"","url":"api/Good/near_goods"}
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
        private List<BannerBean> banner;
        private List<CatelistBean> catelist;

        @Override
        public String toString() {
            return "DataBean{" +
                    "banner=" + banner +
                    ", catelist=" + catelist +
                    '}';
        }

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<CatelistBean> getCatelist() {
            return catelist;
        }

        public void setCatelist(List<CatelistBean> catelist) {
            this.catelist = catelist;
        }

        public static class BannerBean {
            @Override
            public String toString() {
                return "BannerBean{" +
                        "ad_code='" + ad_code + '\'' +
                        '}';
            }

            /**
             * ad_code : /public/upload/ad/2016/09-12/57d645ec27e00.jpg
             */

            private String ad_code;

            public String getAd_code() {
                return ad_code;
            }

            public void setAd_code(String ad_code) {
                this.ad_code = ad_code;
            }
        }

        public static class CatelistBean {
            @Override
            public String toString() {
                return "CatelistBean{" +
                        "cate_name='" + cate_name + '\'' +
                        ", image='" + image + '\'' +
                        ", goodslist=" + goodslist +
                        '}';
            }

            /**
             * cate_name : 女装
             * image : /public/upload/category/2017/04-25/ea9db4a3c03a315020d7f3401296544b.png
             * goodslist : [{"goods_id":59,"cat_id":844,"original_img":"/public/upload/goods/2016/01-14/569749040dcd0.jpg","name":"女装","store_id":2},{"goods_id":120,"cat_id":848,"original_img":"/public/upload/goods/2016/04-21/571836e30aaba.jpg","name":"冬季外套","store_id":2},{"goods_id":123,"cat_id":849,"original_img":"/public/upload/goods/2016/04-21/57186b2475e75.jpg","name":"毛衣针织","store_id":435},{"goods_id":119,"cat_id":847,"original_img":"/public/upload/goods/2016/04-21/5718365665d97.jpg","name":"春秋外套","store_id":433},{"goods_id":122,"cat_id":849,"original_img":"/public/upload/goods/2016/04-21/5718384936f8a.jpg","name":"毛衣针织","store_id":435},{"goods_id":130,"cat_id":850,"original_img":"/public/upload/goods/2016/04-21/57187e635d509.jpg","name":"运动休闲","store_id":435},{"goods_id":118,"cat_id":847,"original_img":"/public/upload/goods/2016/04-21/571835c77b583.jpg","name":"春秋外套","store_id":433},{"goods_id":121,"cat_id":848,"original_img":"/public/upload/goods/2016/04-21/571837b30942a.jpg","name":"冬季外套","store_id":435}]
             */

            private String cate_name;
            private String image;
            private List<GoodslistBean> goodslist;

            public String getCate_name() {
                return cate_name;
            }

            public void setCate_name(String cate_name) {
                this.cate_name = cate_name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public List<GoodslistBean> getGoodslist() {
                return goodslist;
            }

            public void setGoodslist(List<GoodslistBean> goodslist) {
                this.goodslist = goodslist;
            }

            public static class GoodslistBean {
                @Override
                public String toString() {
                    return "GoodslistBean{" +
                            "goods_id=" + goods_id +
                            ", cat_id=" + cat_id +
                            ", original_img='" + original_img + '\'' +
                            ", name='" + name + '\'' +
                            ", store_id=" + store_id +
                            '}';
                }

                /**
                 * goods_id : 59
                 * cat_id : 844
                 * original_img : /public/upload/goods/2016/01-14/569749040dcd0.jpg
                 * name : 女装
                 * store_id : 2
                 */

                private int goods_id;
                private int cat_id;
                private String original_img;
                private String name;
                private int store_id;

                public int getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(int goods_id) {
                    this.goods_id = goods_id;
                }

                public int getCat_id() {
                    return cat_id;
                }

                public void setCat_id(int cat_id) {
                    this.cat_id = cat_id;
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

                public int getStore_id() {
                    return store_id;
                }

                public void setStore_id(int store_id) {
                    this.store_id = store_id;
                }
            }
        }
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
         * url : api/Good/near_goods
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
