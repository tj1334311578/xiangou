package com.example.administrator.xiangou.mine.mystore.goodsmanage.addgoodsmanage.bean;

import java.util.List;

/**
 * 作者： tj on 2017/6/10.
 * <p>
 * 功能：
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */

public class SpecsBean {
    private int model_id;
    private String model_name;
    //屬性未添加
    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    private List<SpecBean> Specs;
    private List<AttrBean> attra;

    public List<SpecBean> getSpecs() {
        return Specs;
    }

    public void setSpecs(List<SpecBean> specs) {
        Specs = specs;
    }

    public List<AttrBean> getAttra() {
        return attra;
    }

    public void setAttra(List<AttrBean> attra) {
        this.attra = attra;
    }

    class SpecBean{
        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStore_count() {
            return store_count;
        }

        public void setStore_count(int store_count) {
            this.store_count = store_count;
        }

        private String key;
        private String name;
        private int store_count;
    }
    private class AttrBean{
        private int attr_id;
        private String attr_name;
        private int attr_input_type;
        private List<String> attr_values;

        public int getAttr_id() {
            return attr_id;
        }

        public void setAttr_id(int attr_id) {
            this.attr_id = attr_id;
        }

        public String getAttr_name() {
            return attr_name;
        }

        public void setAttr_name(String attr_name) {
            this.attr_name = attr_name;
        }

        public int getAttr_input_type() {
            return attr_input_type;
        }

        public void setAttr_input_type(int attr_input_type) {
            this.attr_input_type = attr_input_type;
        }

        public List<String> getAttr_values() {
            return attr_values;
        }

        public void setAttr_values(List<String> attr_values) {
            this.attr_values = attr_values;
        }
    }

}
