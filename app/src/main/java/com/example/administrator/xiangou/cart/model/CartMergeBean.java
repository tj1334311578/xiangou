package com.example.administrator.xiangou.cart.model;

import java.util.List;

/**
 * @author zhouzongyao
 * @Description 合并返回数据的实体类和CheckBox的状态类
 * @email 18482195579@163.com
 * @Date 2017-04-17 9:07
 */
public class CartMergeBean {
    private StoreDealBean mStoreDealBean;
    private StoreStatusBean mStoreStatusBean;
    private List<CartMergeItemBean> mMergeItemBeanList;

    public StoreDealBean getStoreDealBean() {
        return mStoreDealBean;
    }

    public void setStoreDealBean(StoreDealBean storeDealBean) {
        mStoreDealBean = storeDealBean;
    }

    public StoreStatusBean getStoreStatusBean() {
        return mStoreStatusBean;
    }

    public void setStoreStatusBean(StoreStatusBean storeStatusBean) {
        mStoreStatusBean = storeStatusBean;
    }

    public List<CartMergeItemBean> getMergeItemBeanList() {
        return mMergeItemBeanList;
    }

    public void setMergeItemBeanList(List<CartMergeItemBean> mergeItemBeanList) {
        mMergeItemBeanList = mergeItemBeanList;
    }

    public CartMergeBean(StoreDealBean storeDealBean, StoreStatusBean storeStatusBean, List<CartMergeItemBean> mergeItemBeanList) {

        mStoreDealBean = storeDealBean;
        mStoreStatusBean = storeStatusBean;
        mMergeItemBeanList = mergeItemBeanList;
    }
}
