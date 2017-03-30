package com.example.administrator.xiangou.nearby.nearbystore;

import java.util.List;

/**
 * @author zhouzongyao
 * @Description 附近商店请求实体类
 * @email 18482195579@163.com
 * @Date 2017-03-28 17:28
 */
public class StoreInfoBean {

    /**
     * data : [{"did":3,"logo":"/public/upload/head_pic/2017/03-14/k4YvRUD7U7E2RqSRfTGvrftyu.jpg","name":"xiangou","synopsis":"sssss","distance":3,"follow":0,"goods_list":[{"goods_id":52,"original_img":"/public/upload/goods/2016/01-13/56960d7bec88e.jpg"},{"goods_id":54,"original_img":"/public/upload/goods/2016/01-13/56961241453f2.jpg"},{"goods_id":82,"original_img":"/public/upload/goods/2016/01-19/569de0c074bdb.jpg"}]},{"did":1,"logo":"\\public\\upload\\test\\2017\\03-20\\KBbGXtRnurH6rpvetUwdr9qPW.png","name":"闲购小铺","synopsis":"shideihidhishihs","distance":52,"follow":2,"goods_list":[{"goods_id":1,"original_img":"/public/upload/goods/2016/03-09/56e01a4088d3b.jpg"}]},{"did":433,"logo":"\\public\\upload\\test\\2017\\03-20\\5EfxwzTjXNHAxY5DMnK42b2mn.png","name":"柠檬店升级12","synopsis":null,"distance":52,"follow":0,"goods_list":[{"goods_id":102,"original_img":"/public/upload/goods/2016/01-21/56a0923c14436.jpg"},{"goods_id":103,"original_img":"/public/upload/goods/2016/01-21/56a092c24060a.jpg"},{"goods_id":105,"original_img":"/public/upload/goods/2016/04-19/5715e06372f03.jpg"}]}]
     * state : {"code":200,"msg":"获取成功","debugMsg":"","url":"api/User/near"}
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
         * url : api/User/near
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
         * did : 3
         * logo : /public/upload/head_pic/2017/03-14/k4YvRUD7U7E2RqSRfTGvrftyu.jpg
         * name : xiangou
         * synopsis : sssss
         * distance : 3
         * follow : 0
         * goods_list : [{"goods_id":52,"original_img":"/public/upload/goods/2016/01-13/56960d7bec88e.jpg"},{"goods_id":54,"original_img":"/public/upload/goods/2016/01-13/56961241453f2.jpg"},{"goods_id":82,"original_img":"/public/upload/goods/2016/01-19/569de0c074bdb.jpg"}]
         */

        private int did;
        private String logo;
        private String name;
        private String synopsis;
        private int distance;
        private int follow;
        private List<GoodsListBean> goods_list;

        public int getDid() {
            return did;
        }

        public void setDid(int did) {
            this.did = did;
        }

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

        public String getSynopsis() {
            return synopsis;
        }

        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getFollow() {
            return follow;
        }

        public void setFollow(int follow) {
            this.follow = follow;
        }

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public static class GoodsListBean {
            /**
             * goods_id : 52
             * original_img : /public/upload/goods/2016/01-13/56960d7bec88e.jpg
             */

            private int goods_id;
            private String original_img;

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
        }
    }
}

//
//public class ShopProductDetailsActivity extends BaseActivity {
//
//    private ImageView topBarBack;
//    private ImageView numberPlus;
//    private ImageView numberMinus;
//    private ProductDetailsBean mProductDetailsBean;
//
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_shoppingcart_product_details;
//    }
//
//    @Override
//    public void initData(Bundle savedInstanceState) {
//
//    }
//
//    @Override
//    public void initView() {
//        topBarBack = (ImageView) findViewById(R.id.top_bar_back);
//        numberPlus = (ImageView) findViewById(R.id.iv_plus_product_details);
//        numberMinus = (ImageView) findViewById(R.id.iv_minus_product_details);
//    }
//
//    @Override
//    public void initEvent() {
//
//        mProductDetailsBean = new ProductDetailsBean();
//
//        topBarBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//        numberPlus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int num = mProductDetailsBean.getNumberProductDetails()+1;
//                mProductDetailsBean.setNumberProductDetails(num);
//            }
//        });
//    }
//}
