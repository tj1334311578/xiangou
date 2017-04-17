package com.example.administrator.xiangou.cart.model;

import java.util.List;

/**
 * @author zhouzongyao
 * @Description 合并返回数据的实体类和CheckBox的状态类
 * @email 18482195579@163.com
 * @Date 2017-04-17 9:07
 */
public class CartMergeBean {
    private DealBean mDealBean;
    private CartAllCbBean mCartAllCbBean;
    private List<CartMergeItemBean> mMergeItemBeanList;

    public DealBean getDealBean() {
        return mDealBean;
    }

    public void setDealBean(DealBean dealBean) {
        mDealBean = dealBean;
    }

    public CartAllCbBean getCartAllCbBean() {
        return mCartAllCbBean;
    }

    public void setCartAllCbBean(CartAllCbBean cartAllCbBean) {
        mCartAllCbBean = cartAllCbBean;
    }

    public List<CartMergeItemBean> getMergeItemBeanList() {
        return mMergeItemBeanList;
    }

    public void setMergeItemBeanList(List<CartMergeItemBean> mergeItemBeanList) {
        mMergeItemBeanList = mergeItemBeanList;
    }

    public CartMergeBean(DealBean dealBean, CartAllCbBean cartAllCbBean, List<CartMergeItemBean> mergeItemBeanList) {

        mDealBean = dealBean;
        mCartAllCbBean = cartAllCbBean;
        mMergeItemBeanList = mergeItemBeanList;
    }
}
