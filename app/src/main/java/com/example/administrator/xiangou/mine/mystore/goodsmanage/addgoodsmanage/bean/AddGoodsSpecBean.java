package com.example.administrator.xiangou.mine.mystore.goodsmanage.addgoodsmanage.bean;

/**
 * 作者： tj on 2017/6/1.
 * <p>
 * 功能：传产品当前页面若未传默认第一页   商品规格库存列表
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */

import java.util.ArrayList;
import java.util.List;

/**商品规格库存列表*/
public class AddGoodsSpecBean {
    @Override
    public String toString() {
        return "AddGoodsSpecBean{" +
                "specs=" + specs +
                '}';
    }

    public List<SpecBean> getSpecs() {
        return specs;
    }

    public void setSpecs(List<SpecBean> specs) {
        this.specs = specs;
    }

    private List<SpecBean>  specs=new ArrayList<>();
    public static class SpecBean{
        @Override
        public String toString() {
            return "SpecsBean{" +
                    "key='" + key + '\'' +
                    ", store_count=" + store_count +
                    '}';
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public int getStore_count() {
            return store_count;
        }

        public void setStore_count(int store_count) {
            this.store_count = store_count;
        }

        private String key;/**商品规格组合1_4对应规格项id若规格组只有一个id则不需下划线  若选择必填*/
        private int store_count; /**此规格组的库存*/
    }
}
