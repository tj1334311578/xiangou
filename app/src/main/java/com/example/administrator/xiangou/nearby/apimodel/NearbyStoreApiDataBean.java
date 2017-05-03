package com.example.administrator.xiangou.nearby.apimodel;

import java.util.List;

/**
 * @author zhouzongyao
 * @Description 附近店铺接口实体类
 * @email 18482195579@163.com
 * @Date 2017-04-25 9:21
 */
public class NearbyStoreApiDataBean {

    @Override
    public String toString() {
        return "NearbyStoreApiDataBean{" +
                "state=" + state +
                ", data=" + data +
                '}';
    }

    /**
     * data : [
     * {"did":3,
     * "logo":"/public/upload/licence/2017/03-28/dC3QAGjhCTafMLtDCYnucsZnm.jpg",
     * "name":"xiangou","synopsis":"sssss","distance":3,"follow":0,
     * "goods_list":[
     * "goods_id":52,"original_img":"/public/upload/goods/2016/01-13/56960d7bec88e.jpg"},
     * {"goods_id":82,"original_img":"/public/upload/goods/2016/01-19/569de0c074bdb.jpg"},
     * {"goods_id":88,"original_img":"/public/upload/goods/2016/01-20/569f5f2e32da3.jpg"}]
     * },
     *
     * {"did":1,"logo":"/public/upload/licence/2017/03-28/dC3QAGjhCTafMLtDCYnucsZnm.jpg",
     * "name":"闲购小铺","synopsis":"shideihidhishihs","distance":9,"follow":0,
     * "goods_list":[{"goods_id":1,"original_img":"/public/upload/goods/2016/03-09/56e01a4088d3b.jpg"},{"goods_id":46,"original_img":"/public/upload/goods/2016/01-13/5695ef4114f2b.jpg"},{"goods_id":155,"original_img":"/public/upload/goods/2017/04-06/tVrQbGyhKdfatsLm75MTUzTbj.jpg"}]},{"did":2,"logo":"/public/upload/licence/2017/03-28/dC3QAGjhCTafMLtDCYnucsZnm.jpg","name":"test","synopsis":"买吧买吧","distance":9,"follow":1,"goods_list":[{"goods_id":40,"original_img":"/public/upload/goods/2016/01-13/5695bd0ba3d1d.jpg"},{"goods_id":42,"original_img":"/public/upload/goods/2016/01-13/5695c0873d865.png"},{"goods_id":44,"original_img":"/public/upload/goods/2016/01-13/5695c1e0549fc.jpg"}]},{"did":435,"logo":null,"name":"柠檬店升级店","synopsis":null,"distance":15,"follow":0,"goods_list":[{"goods_id":121,"original_img":"/public/upload/goods/2016/04-21/571837b30942a.jpg"},{"goods_id":123,"original_img":"/public/upload/goods/2016/04-21/57186b2475e75.jpg"},{"goods_id":125,"original_img":"/public/upload/goods/2016/04-21/57187b42bfc0c.jpg"}]},{"did":433,"logo":"/public/upload/licence/2017/03-28/dC3QAGjhCTafMLtDCYnucsZnm.jpg","name":"柠檬店升级12","synopsis":null,"distance":52,"follow":0,"goods_list":[{"goods_id":102,"original_img":"/public/upload/goods/2016/01-21/56a0923c14436.jpg"},{"goods_id":103,"original_img":"/public/upload/goods/2016/01-21/56a092c24060a.jpg"},{"goods_id":105,"original_img":"/public/upload/goods/2016/04-19/5715e06372f03.jpg"}]}]
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
        @Override
        public String toString() {
            return "DataBean{" +
                    "did=" + did +
                    ", logo='" + logo + '\'' +
                    ", name='" + name + '\'' +
                    ", synopsis='" + synopsis + '\'' +
                    ", distance=" + distance +
                    ", follow=" + follow +
                    ", goods_list=" + goods_list +
                    '}';
        }

        /**
         * did : 3
         * logo : /public/upload/licence/2017/03-28/dC3QAGjhCTafMLtDCYnucsZnm.jpg
         * name : xiangou
         * synopsis : sssss
         * distance : 3
         * follow : 0
         * goods_list : [{"goods_id":52,"original_img":"/public/upload/goods/2016/01-13/56960d7bec88e.jpg"},{"goods_id":82,"original_img":"/public/upload/goods/2016/01-19/569de0c074bdb.jpg"},{"goods_id":88,"original_img":"/public/upload/goods/2016/01-20/569f5f2e32da3.jpg"}]
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
            @Override
            public String toString() {
                return "GoodsListBean{" +
                        "goods_id=" + goods_id +
                        ", original_img='" + original_img + '\'' +
                        '}';
            }

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
