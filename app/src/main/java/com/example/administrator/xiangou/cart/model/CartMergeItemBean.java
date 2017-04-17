package com.example.administrator.xiangou.cart.model;

/**
 * @author zhouzongyao
 * @Description 合并item的数据实体类和CheckBox的状态类
 * @email 18482195579@163.com
 * @Date 2017-04-17 9:12
 */
public class CartMergeItemBean {
    private GoodsDealBean mGoodsDealBean;
    private CartItemCbBean mItemCbBean;

    public GoodsDealBean getGoodsDealBean() {
        return mGoodsDealBean;
    }

    public void setGoodsDealBean(GoodsDealBean goodsDealBean) {
        mGoodsDealBean = goodsDealBean;
    }

    public CartItemCbBean getItemCbBean() {
        return mItemCbBean;
    }

    public void setItemCbBean(CartItemCbBean itemCbBean) {
        mItemCbBean = itemCbBean;
    }

    public CartMergeItemBean(GoodsDealBean goodsDealBean, CartItemCbBean itemCbBean) {
        mGoodsDealBean = goodsDealBean;
        mItemCbBean = itemCbBean;
    }
}
