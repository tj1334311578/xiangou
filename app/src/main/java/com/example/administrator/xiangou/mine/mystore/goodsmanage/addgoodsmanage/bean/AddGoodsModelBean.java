package com.example.administrator.xiangou.mine.mystore.goodsmanage.addgoodsmanage.bean;

import java.util.List;

/**
 * 作者： tj on 2017/6/7.
 * <p>
 * 功能：
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */

public class AddGoodsModelBean {
    private List<SpecBean> specList;

    public List<SpecBean> getSpecList() {
        return specList;
    }

    public void setSpecList(List<SpecBean> specList) {
        this.specList = specList;
    }

    public static class SpecBean{
        public int getSpec_id() {
            return spec_id;
        }

        public void setSpec_id(int spec_id) {
            this.spec_id = spec_id;
        }

        public int getModel_id() {
            return model_id;
        }

        public void setModel_id(int model_id) {
            this.model_id = model_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIs_img() {
            return is_img;
        }

        public void setIs_img(int is_img) {
            this.is_img = is_img;
        }

        public List<SpecItemBean>  getSpec_item() {
            return spec_item;
        }

        public void setSpec_item(List<SpecItemBean>  spec_item) {
            this.spec_item = spec_item;
        }

        private int spec_id;
        private int model_id;
        private String name;
        private int is_img;
        private List<SpecItemBean> spec_item;
    }

    public static class SpecItemBean{
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

        private int item_id;
        private String item;
    }
}
