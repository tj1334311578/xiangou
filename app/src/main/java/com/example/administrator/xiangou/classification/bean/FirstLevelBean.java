package com.example.administrator.xiangou.classification.bean;

import java.util.List;

/**
 * 作者： tj on 2017/6/2.
 * <p>
 * 功能：分类页面实体类
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */

public class FirstLevelBean {

    /**
     * data : {"recommend":[{"name":"春秋外套","cat_id":847,"image":"/public/upload/category/2017/04-25/e2db43c4e946207646be570f4ccd30ad.png"},{"name":"运动休闲","cat_id":859,"image":"/public/upload/category/2017/04-25/1b9e3ac42745d158ea43d3395c916dd5.png"},{"name":"女鞋","cat_id":864,"image":"/public/upload/category/2017/04-25/37bbfb42d1721c3d129ef173a5534a73.png"},{"name":"女双肩包","cat_id":49,"image":"/public/upload/category/2017/04-25/bc4318a848e57c35062f3f40ba07f370.png"},{"name":"女士护肤","cat_id":42,"image":"/public/upload/category/2017/04-25/72bc329051e688c1e0bc1fd2e86868ab.png"},{"name":"电视","cat_id":19,"image":"/public/upload/category/2017/04-25/35b3565ebbbc49128ccbbcd0dad6b8c7.png"},{"name":"玩具车床","cat_id":83,"image":"/public/upload/category/2017/04-25/ffaf99ba4016f3b77cfa7c09e340c495.png"},{"name":"生活家具","cat_id":31,"image":"/public/upload/category/2017/04-25/ae8889f81276b51fef900d334b3755ec.png"},{"name":"猫狗主粮","cat_id":880,"image":"/public/upload/category/2017/04-25/d639005db07d66f5446a83d4d8978b99.png"}],"hot_cate":[{"name":"春秋外套","cat_id":847,"image":"/public/upload/category/2017/04-25/e2db43c4e946207646be570f4ccd30ad.png"},{"name":"女装","cat_id":844,"image":"/public/upload/category/2017/04-25/ea9db4a3c03a315020d7f3401296544b.png"},{"name":"女鞋","cat_id":864,"image":"/public/upload/category/2017/04-25/37bbfb42d1721c3d129ef173a5534a73.png"},{"name":"男装","cat_id":5,"image":"/public/upload/category/2017/04-25/a7a3ae7415f425786d0bc8eec8a80dee.png"},{"name":"男鞋","cat_id":865,"image":"/public/upload/category/2017/04-25/b03dd2a1f7b570a932de62b5c671697f.png"},{"name":"毛衣针织","cat_id":849,"image":"/public/upload/category/2017/04-25/f56b38772a94b2365df6e099cef69fae.png"},{"name":"潮鞋","cat_id":866,"image":""},{"name":"箱包","cat_id":7,"image":"/public/upload/category/2017/04-25/1b52f49c83c07cb5b45aab1fe0e783fe.png"},{"name":"高跟鞋","cat_id":867,"image":"/public/upload/category/2017/04-25/f7b74f29e3dec6452c708f2938c7dfb0.png"},{"name":"套装","cat_id":851,"image":"/public/upload/category/2017/04-25/dfb15f461b5fea299f2a127cb4bb6cbf.png"},{"name":"凉鞋","cat_id":869,"image":"/public/upload/category/2017/04-25/e6f2bca473445332434c2eff2b1fbc8e.png"},{"name":"睡衣","cat_id":852,"image":"/public/upload/category/2017/04-25/15036a1d2689221da191cee9c9a486cf.png"},{"name":"休闲鞋","cat_id":870,"image":"/public/upload/category/2017/04-25/094fe33940a364c5a5a4be405ebede2a.png"},{"name":"长裤","cat_id":853,"image":"/public/upload/category/2017/04-25/0668ecbe789e4f5e81ba2c7fad50912e.png"},{"name":"平底鞋","cat_id":872,"image":"/public/upload/category/2017/04-25/76bf2c4675274c03cec361e2e2286e0a.png"},{"name":"春秋外套","cat_id":39,"image":"/public/upload/category/2017/04-25/c59b8f5ea9b00bd33c74809b223d3c60.png"},{"name":"毛衣开衫","cat_id":41,"image":"/public/upload/category/2017/04-25/93afbee6f2b2f8c75880a248f7e58b2d.png"},{"name":"夏季上衣","cat_id":858,"image":"/public/upload/category/2017/04-25/103981aa2773907f863072bf7ddaaa61.png"}],"all_cate":[{"name":"女装","cat_id":844},{"name":"男装","cat_id":5},{"name":"鞋子","cat_id":845},{"name":"箱包","cat_id":7},{"name":"化妆","cat_id":6},{"name":"电器","cat_id":2},{"name":"母婴","cat_id":10},{"name":"家居","cat_id":4},{"name":"宠物","cat_id":846}]}
     * state : {"code":200,"msg":"操作成功","debugMsg":"","url":"api/Good/goods_cate"}
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
        private List<RecommendBean> recommend;
        private List<HotCateBean> hot_cate;
        private List<AllCateBean> all_cate;
        private AdvBean adv;
        private List<CateBean> cate;

        public List<CateBean> getCate() {
            return cate;
        }

        public void setCate(List<CateBean> cate) {
            this.cate = cate;
        }

        public AdvBean getAdv() {
            return adv;
        }

        public void setAdv(AdvBean adv) {
            this.adv = adv;
        }

        public List<RecommendBean> getRecommend() {
            return recommend;
        }

        public void setRecommend(List<RecommendBean> recommend) {
            this.recommend = recommend;
        }

        public List<HotCateBean> getHot_cate() {
            return hot_cate;
        }

        public void setHot_cate(List<HotCateBean> hot_cate) {
            this.hot_cate = hot_cate;
        }

        public List<AllCateBean> getAll_cate() {
            return all_cate;
        }

        public void setAll_cate(List<AllCateBean> all_cate) {
            this.all_cate = all_cate;
        }

        public static class CateBean {
            /**
             * name : 女双肩包
             * cat_id : 49
             * image : /public/upload/category/2017/04-25/bc4318a848e57c35062f3f40ba07f370.png
             */

            private String name;
            private int cat_id;
            private String image;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCat_id() {
                return cat_id;
            }

            public void setCat_id(int cat_id) {
                this.cat_id = cat_id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }

        public static class AdvBean {
            /**
             * img_url : /public/upload/ad/2017/04-25/c88ce4ee28312c179bfddea47d0ec081.png
             * store_id : 0
             */

            private String img_url;
            private int store_id;

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }
        }
        public static class RecommendBean {
            /**
             * name : 春秋外套
             * cat_id : 847
             * image : /public/upload/category/2017/04-25/e2db43c4e946207646be570f4ccd30ad.png
             */

            private String name;
            private int cat_id;
            private String image;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCat_id() {
                return cat_id;
            }

            public void setCat_id(int cat_id) {
                this.cat_id = cat_id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }

        public static class HotCateBean {
            /**
             * name : 春秋外套
             * cat_id : 847
             * image : /public/upload/category/2017/04-25/e2db43c4e946207646be570f4ccd30ad.png
             */

            private String name;
            private int cat_id;
            private String image;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCat_id() {
                return cat_id;
            }

            public void setCat_id(int cat_id) {
                this.cat_id = cat_id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }

        public static class AllCateBean {
            /**
             * name : 女装
             * cat_id : 844
             */

            private String name;
            private int cat_id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCat_id() {
                return cat_id;
            }

            public void setCat_id(int cat_id) {
                this.cat_id = cat_id;
            }
        }
    }

    public static class StateBean {
        /**
         * code : 200
         * msg : 操作成功
         * debugMsg :
         * url : api/Good/goods_cate
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
