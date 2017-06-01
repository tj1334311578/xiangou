package com.example.administrator.xiangou.mine.setting.manageraddress.model;

import java.util.List;

/**
 * @author zhouzongyao
 * @Description 进入编辑地址页面的请求返回实体类
 * @email 18482195579@163.com
 * @Date 2017-05-24 9:46
 */
public class EditAddressEnterBean {

    /**
     * data : {"area":[{"region_id":1,"name":"北京市"},{"region_id":338,"name":"天津市"},{"region_id":636,"name":"河北省"},{"region_id":3102,"name":"山西"},{"region_id":4670,"name":"内蒙古自治区"},{"region_id":5827,"name":"辽宁省"},{"region_id":7531,"name":"吉林省"},{"region_id":8558,"name":"黑龙江省"},{"region_id":10543,"name":"上海市"},{"region_id":10808,"name":"江苏省"},{"region_id":12596,"name":"浙江省"},{"region_id":14234,"name":"安徽省"},{"region_id":16068,"name":"福建省"},{"region_id":17359,"name":"江西省"},{"region_id":19280,"name":"山东省"},{"region_id":21387,"name":"河南省"},{"region_id":24022,"name":"湖北省"},{"region_id":25579,"name":"湖南省"},{"region_id":28240,"name":"广东省"},{"region_id":30164,"name":"广西壮族自治区"},{"region_id":31563,"name":"海南省"},{"region_id":31929,"name":"重庆市"},{"region_id":33007,"name":"四川省"},{"region_id":37906,"name":"贵州省"},{"region_id":39556,"name":"云南省"},{"region_id":41103,"name":"西藏自治区"},{"region_id":41877,"name":"陕西省"},{"region_id":43776,"name":"甘肃省"},{"region_id":45286,"name":"青海省"},{"region_id":45753,"name":"宁夏回族自治区"},{"region_id":46047,"name":"新疆维吾尔自治区"},{"region_id":47493,"name":"台湾省"},{"region_id":47494,"name":"香港特别行政区"},{"region_id":47495,"name":"澳门特别行政区"}]}
     * state : {"code":200,"msg":"获取成功","debugMsg":"","url":"api/User/into_address"}
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
        private List<AreaBean> area;

        public List<AreaBean> getArea() {
            return area;
        }

        public void setArea(List<AreaBean> area) {
            this.area = area;
        }

        public static class AreaBean {
            /**
             * region_id : 1
             * name : 北京市
             */

            private int region_id;
            private String name;

            public int getRegion_id() {
                return region_id;
            }

            public void setRegion_id(int region_id) {
                this.region_id = region_id;
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
         * url : api/User/into_address
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
