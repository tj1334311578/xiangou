package com.example.administrator.xiangou.mine.mystore.goodsmanage.addgoodsmanage;

/**
 * 作者： tj on 2017/6/1.
 * <p>
 * 功能： /*商品属性 不必填
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */

import java.util.ArrayList;
import java.util.List;

/**商品属性 不必填*/
public class AddGoodsAttrBean {
     private List<AttrBean> attrs=new ArrayList<>();

    @Override
    public String toString() {
        return "AddGoodsAttrBean{" +
                "attrs=" + attrs +
                '}';
    }

    public List<AttrBean> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<AttrBean> attrs) {
        this.attrs = attrs;
    }

    public static class AttrBean{
        @Override
        public String toString() {
            return "AttrBean{" +
                    "attr_id=" + attr_id +
                    ", attr_value='" + attr_value + '\'' +
                    '}';
        }

        private int attr_id;
        private String attr_value;

        public int getAttr_id() {
            return attr_id;
        }

        public void setAttr_id(int attr_id) {
            this.attr_id = attr_id;
        }

        public String getAttr_value() {
            return attr_value;
        }

        public void setAttr_value(String attr_value) {
            this.attr_value = attr_value;
        }
    }
}
