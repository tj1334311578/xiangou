package com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.goodsbean;

import java.util.List;

/**
 * 作者： tj on 2017/5/24.
 * <p>
 * 功能：
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */

public class commenttempBean {

    /**
     * data : {"count":{"c0":3,"c1":2,"c2":0,"c3":1,"rate1":67,"rate2":0,"rate3":34},"comment_list":[{"comment_id":125,"username":"蒙娜*****","img":false,"add_time":"2016-03-12","order_id":1,"head_pic":"/public/upload/head_pic/2017/05-15/UqSUKfvARCakYFYC8wwkTaTpa.png","content":"不错,买回来老公很喜欢","spec":""},{"comment_id":59,"username":"茱莉*****","img":false,"add_time":"2016-03-12","order_id":1,"head_pic":"/public/upload/head_pic/2017/05-15/UqSUKfvARCakYFYC8wwkTaTpa.png","content":"这东西不错,下次还会来买","spec":""},{"comment_id":191,"username":"嫦娥*****","img":["/public/upload/goods/2016/01-21/56a08d4c42e99.jpg","/public/upload/goods/2016/01-21/56a08d4c25b37.jpg","/public/upload/goods/2016/01-21/56a08d4c0a8cb.jpg"],"add_time":"2016-03-12","order_id":1,"head_pic":"/public/upload/head_pic/2017/05-15/UqSUKfvARCakYFYC8wwkTaTpa.png","content":"晒单给大家看看.我刚买的.","spec":""}]}
     * state : {"code":200,"msg":"操作成功","debugMsg":"","url":"api/Good/comment_list"}
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
         * count : {"c0":3,"c1":2,"c2":0,"c3":1,"rate1":67,"rate2":0,"rate3":34}
         * comment_list : [{"comment_id":125,"username":"蒙娜*****","img":false,"add_time":"2016-03-12","order_id":1,"head_pic":"/public/upload/head_pic/2017/05-15/UqSUKfvARCakYFYC8wwkTaTpa.png","content":"不错,买回来老公很喜欢","spec":""},{"comment_id":59,"username":"茱莉*****","img":false,"add_time":"2016-03-12","order_id":1,"head_pic":"/public/upload/head_pic/2017/05-15/UqSUKfvARCakYFYC8wwkTaTpa.png","content":"这东西不错,下次还会来买","spec":""},{"comment_id":191,"username":"嫦娥*****","img":["/public/upload/goods/2016/01-21/56a08d4c42e99.jpg","/public/upload/goods/2016/01-21/56a08d4c25b37.jpg","/public/upload/goods/2016/01-21/56a08d4c0a8cb.jpg"],"add_time":"2016-03-12","order_id":1,"head_pic":"/public/upload/head_pic/2017/05-15/UqSUKfvARCakYFYC8wwkTaTpa.png","content":"晒单给大家看看.我刚买的.","spec":""}]
         */

        private CountBean count;
        private List<CommentListBean> comment_list;

        public CountBean getCount() {
            return count;
        }

        public void setCount(CountBean count) {
            this.count = count;
        }

        public List<CommentListBean> getComment_list() {
            return comment_list;
        }

        public void setComment_list(List<CommentListBean> comment_list) {
            this.comment_list = comment_list;
        }

        public static class CountBean {
            /**
             * c0 : 3
             * c1 : 2
             * c2 : 0
             * c3 : 1
             * rate1 : 67
             * rate2 : 0
             * rate3 : 34
             */

            private int c0;
            private int c1;
            private int c2;
            private int c3;
            private int rate1;
            private int rate2;
            private int rate3;

            public int getC0() {
                return c0;
            }

            public void setC0(int c0) {
                this.c0 = c0;
            }

            public int getC1() {
                return c1;
            }

            public void setC1(int c1) {
                this.c1 = c1;
            }

            public int getC2() {
                return c2;
            }

            public void setC2(int c2) {
                this.c2 = c2;
            }

            public int getC3() {
                return c3;
            }

            public void setC3(int c3) {
                this.c3 = c3;
            }

            public int getRate1() {
                return rate1;
            }

            public void setRate1(int rate1) {
                this.rate1 = rate1;
            }

            public int getRate2() {
                return rate2;
            }

            public void setRate2(int rate2) {
                this.rate2 = rate2;
            }

            public int getRate3() {
                return rate3;
            }

            public void setRate3(int rate3) {
                this.rate3 = rate3;
            }
        }

        public static class CommentListBean {
            /**
             * comment_id : 125
             * username : 蒙娜*****
             * img : false
             * add_time : 2016-03-12
             * order_id : 1
             * head_pic : /public/upload/head_pic/2017/05-15/UqSUKfvARCakYFYC8wwkTaTpa.png
             * content : 不错,买回来老公很喜欢
             * spec :
             */

            private int comment_id;
            private String username;
            private boolean img;
            private String add_time;
            private int order_id;
            private String head_pic;
            private String content;
            private String spec;

            public int getComment_id() {
                return comment_id;
            }

            public void setComment_id(int comment_id) {
                this.comment_id = comment_id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public boolean isImg() {
                return img;
            }

            public void setImg(boolean img) {
                this.img = img;
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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getSpec() {
                return spec;
            }

            public void setSpec(String spec) {
                this.spec = spec;
            }
        }
    }

    public static class StateBean {
        /**
         * code : 200
         * msg : 操作成功
         * debugMsg :
         * url : api/Good/comment_list
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
