package com.example.administrator.xiangou.mine.mystore.goodsmanage;


import java.util.List;

/**
 * 作者： tj on 2017/6/1.
 * <p>
 * 功能：
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */
public class Spec2Bean {
    @Override
    public String toString() {
        return "Spec2Bean{" +
                "颜色BeanList=" + 颜色BeanList +
                ", 尺码BeanList=" + 尺码BeanList +
                '}';
    }

    private List<颜色Bean> 颜色BeanList;
    private List<尺码Bean> 尺码BeanList;

    public List<颜色Bean> get颜色BeanList() {
        return 颜色BeanList;
    }

    public void set颜色BeanList(List<颜色Bean> 颜色BeanList) {
        this.颜色BeanList = 颜色BeanList;
    }

    public List<尺码Bean> get尺码BeanList() {
        return 尺码BeanList;
    }

    public void set尺码BeanList(List<尺码Bean> 尺码BeanList) {
        this.尺码BeanList = 尺码BeanList;
    }

    public static class 颜色Bean {
        @Override
        public String toString() {
            return "颜色Bean{" +
                    "item_id=" + item_id +
                    ", item='" + item + '\'' +
                    '}';
        }

        int item_id;
        String item;

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
    public static class 尺码Bean{

        @Override
        public String toString() {
            return "尺码Bean{" +
                    "item_id=" + item_id +
                    ", item='" + item + '\'' +
                    '}';
        }

        int item_id;
        String item;

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
