package com.example.administrator.xiangou.cart.model;

/**
 * 商品的选中和编辑状态
 */

public class ItemStatusBean {
    private boolean ischeck;

    private boolean hasToEditGoods;

    public boolean isHasToEditGoods() {
        return hasToEditGoods;
    }

    public void setHasToEditGoods(boolean hasToEditGoods) {
        this.hasToEditGoods = hasToEditGoods;
    }

    public boolean ischeck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

}
